/*******************************************************************************
 * Copyright (c) 2006, 2007 Pfizer, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jacob Brauer (WebReach, Inc.) - initial implementation
 *******************************************************************************/


package org.gello.compiler;

//import com.sun.org.apache.bcel.internal.verifier.statics.DOUBLE_Upper;
import org.gello.grammar.analysis.DepthFirstAdapter;
import org.gello.grammar.node.*;
import org.gello.runtime.*;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gello.runtime.IGelloModel;

/**
 * The parse tree walker that actually generates Java source code.
 * @author Erik Horstkotte
 */
public class CodeGenTreeAdapter extends DepthFirstAdapter {

    private static Logger log = Logger.getLogger(CodeGenTreeAdapter.class.getName());

    /** The compiler result, for error messages */
    protected CompileResult result;
    /** The standard naming context */
    protected StackedNamingContext rootNamingContext = new StackedNamingContext();
    /** The symbol table naming context. */
    protected TableNamingContext symbols = new TableNamingContext();
    /** User symbols, in the order in which their declarations should be written out. */
    protected List<Symbol> userSymbols = new ArrayList<Symbol>();
    /** Java object name generator */
    protected JavaNameGenerator nameGenerator = new JavaNameGenerator();
    // Map of collection operator information by operator name
    protected Map<String, OperatorInfo> collectionOperatorMap = new HashMap<String, OperatorInfo>();
    /** The generated Java source for the body of the "isRunnable" method. */
    protected StringBuffer runnableBody = new StringBuffer(1024);
    /** The generated Java source for the body of the "run" method. */
    protected StringBuffer runBody = new StringBuffer(1024);
    
    // The runtime support method name for the '=' relational operator
    protected static final String RELOP_EQUALS = "equals";
    // The runtime support method name for the '!=' relational operator
    protected static final String RELOP_NOTEQUALS = "notequals";
    // The runtime support method name for the '<' relational operator
    protected static final String RELOP_LESS = "less";
    // The runtime support method name for the '>' relational operator
    protected static final String RELOP_GREATER = "greater";
    // The runtime support method name for the '<=' relational operator
    protected static final String RELOP_NOTGREATER = "notgreater";
    // The runtime support method name for the '>=' relational operator
    protected static final String RELOP_NOTLESS = "notless";

//------------------------------------------------------------------------------------------------
// Public interface methods
//------------------------------------------------------------------------------------------------

    /** Creates a new instance of TreeWalker */
    public CodeGenTreeAdapter(CompileResult result, IGelloModel model) {
        
        this.result = result;
        
        // Initialize lookup tables
        initTables(model);
    }
    
    /** Gets the Java source for the generated class. */
    public String getGeneratedClass(String packageName, String className) {

        StringBuffer buf = new StringBuffer(1024);

        // Ye Olde Standarde Commente Headere. Even though this code is never read by a human. Excepte mee.
        buf.append("/*\n");
        buf.append(" * " + className + ".java\n");
        buf.append(" *\n");
        buf.append(" * Generated " + new Date().toString() + " by org.gello.compiler.Compiler.\n");
        buf.append(" *\n");
        buf.append(" */\n");
        
        buf.append("\n");
        
        // Package and import statements
        buf.append("package " + packageName + ";\n");
        buf.append("\n");
        buf.append("import org.gello.runtime.*;\n");

        buf.append("\n");
        
        // Class prologue
        buf.append("public class " + className + " implements GelloRunnable {\n");

        buf.append("\n");
        
        // isRunnable method
        buf.append("    public boolean isRunnable(IGelloModel model) {\n");
        if (runnableBody.length() <= 0) {
            buf.append("        return true;\n");
        }
        else {
            buf.append(runnableBody);
        }
        buf.append("    }\n");

        buf.append("\n");
        
        // run method
        buf.append("    public String run(IGelloModel model) throws GelloException {\n");
        if (userSymbols.size() > 0) {
            ListIterator<Symbol> it = userSymbols.listIterator();
            while (it.hasNext()) {
                Symbol s = it.next();
                buf.append("        " + BeanHelper.getTypeName(s.getType()) + " " + s.getJavaName() + " = " + s.getValue() + ";\n");
            }
            buf.append("\n");
        }
        buf.append("        ");
        buf.append(runBody);
        buf.append("    }\n");

        // Class epilogue
        buf.append("}\n");

        return buf.toString();
    }

//------------------------------------------------------------------------------------------------
// Protected implementation methods
//------------------------------------------------------------------------------------------------

    /** Add a constant value to the global named constant table */
    protected void addGlobalConstant(String name, Type type, String value) {

        symbols.enterScope(new Symbol(name, value, type, true, value));
    }
    
    /** Initialize the compiler lookup tables */
    protected void initTables(IGelloModel model) {

        // Add the global named constants
        addGlobalConstant("true", GelloBoolean.class, "new GelloBoolean(GelloBoolean.TRUE)");
        addGlobalConstant("false", GelloBoolean.class, "new GelloBoolean(GelloBoolean.FALSE)");
        addGlobalConstant("unknown", GelloBoolean.class, "new GelloBoolean(GelloBoolean.UNKNOWN)");
        addGlobalConstant("null", GelloNull.class, "null");
        
        // Build the root naming context
        rootNamingContext.push(new ModelNamingContext(model, "model"));
        rootNamingContext.push(symbols);

        // Load up the collection operator table
        // TODO: lots more entries
        collectionOperatorMap.put("exists", new OperatorInfo("exists", GelloBoolean.class, false));
    }

    /** Add a user symbol to the symbol table */
    protected Symbol enterScope(Token where, String name, String javaName, Type type, boolean constant, String initialValue)
    {
        // If the symbol shadows another of the same name
        if (symbols.resolveId(name) != null) {
            
            // Warn the user
            result.warning(where, "msg.warning.name.shadowing", new Object[] { name });
        }
        
        // Add the symbol to the symbol table
        Symbol s = new Symbol(name, javaName, type, constant, initialValue);
        symbols.enterScope(s);
        
        // Add the symbol to the list of java locals
        userSymbols.add(s);
        
        // Return the symbol
        return s;
    }
    
    /** Add code to the run method body */
    protected void emitRun(String text) {

        runBody.append(text);
    }
    
    /** Get the expression for a node */
    protected CompilerExpression getExpr(Node n) {
        
        Object o = getOut(n);
        if (o == null) {
            return null;
        }
        else if (!(o instanceof CompilerExpression)) {

            result.error(null, "msg.error.internal.getExpr", null);
            return new CompilerExpression(GelloNull.class, true, "null");
        }

        return (CompilerExpression) o;
    }

    protected void log(Node node, String inout)
    {
//        log.info(inout + "(" + node.getClass().getName() + "=" + node + "), in=" + getIn(node) + ", out=" + getOut(node));
        log.info(inout + "(" + node.getClass().getName() + "), in=" + getIn(node) + ", out=" + getOut(node));
    }
    
    protected void in(Node node)
    {
//        log(node, "in");
    }
    
    public void defaultIn(Node node)
    {
//        log(node, "defaultIn");
    }

    protected void caseNode(Node node)
    {
        log(node, "case");
    }

    protected void out(Node node)
    {
        log(node, "out");
    }
    
    public void defaultOut(Node node)
    {
        log(node, "defaultOut");
    }

    // Handles the very common rule case where the child node's object just needs to be copied
    // to its parent node.
    protected void outRippleUp(Node node, Node child)
    {
        // Ripple the child up
        setOut(node, getOut(child));

        // Dump this node
        out(node);
    }

    /** Checks if an expression of type rightType can be assigned to an object of declared type leftType */
    protected void checkAssignTypes(Token where, Type leftType, Type rightType)
    {
        log.finest("where=" + where + ", leftType=" + leftType + ", rightType=" + rightType);
        
        // If the types differ
        if (leftType != rightType) {

            // Add an error message
            result.error(where, "msg.error.assign.type.mismatch", new Object[] { leftType, rightType });
        }
    }

    /** Get the appropriate naming context for this node */
    protected NamingContext getNamingContext(Node node) {

        // NOTE:
        // While we're compiling certain collection operators (select, etc.),
        // there may be an external context that name references are relative to.
        // For example, in Medications->select(code="x"), "code" is relative to
        // Medications[n] for "the current value of n". The name context will be
        // brought into this method as in(node) as a NamingContext, if a "special"
        // naming context is required. The same problem affects all "naming contexts."
        NamingContext names = (NamingContext) getIn(node);
        
        if (names == null) {
            names = rootNamingContext;
        }

        return names;
    }

    protected Symbol resolveId(Token where, Node here, String id)
    {
        // Get the naming context
        NamingContext names = getNamingContext(here);

        // If we can't resolve the identifer in this naming context
        Symbol object = names.resolveId(id);
        if (object == null) {

            log.fine("resolveId: cannot resolve \"" + id + "\"");
            result.error(where, "msg.error.cannot.resolve", new Object[] { id });
            object = rootNamingContext.resolveId("null");
        }

        return object;
    }

    /** Promote an expression from Integer to Double if necessary */
    protected CompilerExpression promoteToDouble(CompilerExpression thisExpr, Type thatType) {

        // If this expression is not integer, or the other operand is not double
        if (thisExpr.getType() != Integer.class || thatType != Double.class) {
            
            // Return this expression unchanged
            return thisExpr;
        }
        else {

            // If the source expression is constant
            if (thisExpr.isConstant()) {

                // A constant Integer expression will match Integer.valueOf(".*")
                // If not
                String valueText = thisExpr.getExpression();
                if (!valueText.startsWith("Integer.valueOf(\"")) {

                    // Die terribly.
                    result.error(null, "msg.error.internal.promote.pattern", new Object[] { valueText });

                    // Try to make it work anyway
                    valueText += ".doubleValue()";
                }
                else {

                    // Extract the quoted literal integer constant
                    valueText = valueText.substring("Integer.valueOf(".length(), -2);
                }

                // Construct a new constant Double expression from it
                return new CompilerExpression(java.lang.Double.class, true, "new Double(" + valueText + ")");
            }
            else {

                // Wrap the source expression with conversion to Double.
                return new CompilerExpression(java.lang.Double.class, true, "new Double(" + thisExpr.getExpression() + ")");
            }
        }
    }

    /** Tests if a combination of operand types is allowed for the specified relational operator */
    protected boolean relopTypesAreLegal(String operator, Type leftType, Type rightType)
    {
        // All relops require both arguments to be the same type (after int->real promotion)
        if (leftType != rightType) {
            return false;
        }

        // equals and notequals allow String and Boolean in addition to int and real
        if ((operator.equals(RELOP_EQUALS) || operator.equals(RELOP_NOTEQUALS)) && 
            (leftType == String.class || leftType == GelloBoolean.class)) {

            return true;
        }
        
        // All relops allow integer and real
        if (leftType == Integer.class || leftType == Double.class) {
            
            return true;
        }

        // Otherwise, outtalucko
        return false;
    }

    /** Compile X <relop> Y */
    protected void compileRelational(Token where, Node thisNode, Node leftNode, Node rightNode, String operator)
    {
        // TODO: How do null values affect relationals?

        // Get the two operand expressions
        CompilerExpression left = getExpr(leftNode);
        CompilerExpression right = getExpr(rightNode);

        // TODO: If either operand expression is a collection, coerce it to a scalar. How?

        // TODO: If left is unknown or right is constant unknown
            // The result is constant unknown

        // Promote left and right expressions if required
        left = promoteToDouble(left, right.getType());
        right = promoteToDouble(right, left.getType());

        // If the type combination is not allowed for this operator
        if (!relopTypesAreLegal(operator, left.getType(), right.getType()))
        {
            // Warning
            result.warning(where, "msg.warning.relop.types", new Object[] { operator, BeanHelper.getTypeName(left.getType()), BeanHelper.getTypeName(right.getType()) });

            // The result is constant unknown
            setOut(thisNode, new CompilerExpression(GelloBoolean.class, true, "new GelloBoolean(GelloBoolean.UNKNOWN)"));
        }
        else {

            // TODO: If both operands are constant, the result is constant. Do we care?

            // Assemble java to do the comparison according to the GELLO rules.
            CompilerExpression outExpr = new CompilerExpression(GelloBoolean.class, false, "GelloRuntime." + operator + "(" + left.getExpression() + ", " + right.getExpression() + ")");
            setOut(thisNode, outExpr);
        }

        out(thisNode);
    }

    protected void compileLogical(Token where, Node thisNode, Node leftNode, Node rightNode, String operator)
    {
        // TODO: How do null values affect logical operators?

        // Get the two operand expressions
        CompilerExpression left = getExpr(leftNode);
        CompilerExpression right = getExpr(rightNode);
        
        // Junk output expression, if not replaced with the real one.
        CompilerExpression outExpr = new CompilerExpression(GelloBoolean.class, true, "new GelloBoolean(GelloBoolean.UNKNOWN)");

        // TODO: If either operand expression is a collection, coerce it to a scalar. How?

        // If either expression is not type boolean
        if (left.getType() != GelloBoolean.class || right.getType() != GelloBoolean.class) {

            // Tell the user.
            result.error(where, "msg.error.logop.types", null);
        }
        else {

            // TODO: If either operands is constant, the result may be constant. Do we care?

            // Assemble java to do the operation according to the GELLO rules.
            outExpr = new CompilerExpression(GelloBoolean.class, false, "(" + left.getExpression() + ")." + operator + "(" + right.getExpression() + ")");
        }

        setOut(thisNode, outExpr);
        out(thisNode);
    }
    
    // Given a collection type 'type', infer the type of element in the collection
    private CollectionInfo inferCollectionInfo(Token where, Type type) {

        // If it's a wildcard or TypeVariable
        if (type instanceof WildcardType || type instanceof TypeVariable) {

            // We don't currently have code to handle this.
            log.fine("error: don't know how to convert a WildcardType or TypeVariable to a GELLO collection.");
            result.error(where, "msg.error.internal.unexpected.type", new Object[] { BeanHelper.getTypeName(type) });
            return new CollectionInfo(GelloSequence.class, type, true);
        }
        
        // If it's a generic array
        if (type instanceof GenericArrayType) {
            
            // Get the element type
            GenericArrayType gat = (GenericArrayType) type;
            Class elementType = (Class) gat.getGenericComponentType();

            // We'll wrap it in a GelloSequence later
            return new CollectionInfo(GelloSequence.class, elementType, true);
        }
        
        // If it's a regular array
        if ((type instanceof Class) && ((Class) type).isArray()) {

            // It's the element type for the array - we'll wrap it in a GelloSequence later.
            return new CollectionInfo(GelloSequence.class, ((Class) type).getComponentType(), true);
        }
        
        // If it's parameterized (generic)
        if (type instanceof ParameterizedType) {

            // Get the underlying raw type
            ParameterizedType ptype = (ParameterizedType) type;
            Type rawType = ptype.getRawType();
            
            // Get the element type
            Class elementType = (Class) ptype.getActualTypeArguments()[0];

            // If the type is a class
            if (rawType instanceof Class) {
                
                Class rawClass = (Class) rawType;

                // If it implements Set
                if (Set.class.isAssignableFrom(rawClass)) {

                    // If it is already a GelloSet
                    if (GelloSet.class.isAssignableFrom(rawClass)) {
                        
                        // Don't rewrap it
                        return new CollectionInfo(type, elementType, false);
                    }
                    else {
                        
                        // Wrap it as a GelloSet
                        return new CollectionInfo(GelloSet.class, elementType, true);
                    }
                }
                // If it implements Collection
                else if (Collection.class.isAssignableFrom(rawClass)) {

                    // If it's a GelloBag
                    if (GelloBag.class.isAssignableFrom(rawClass)) {
                        
                        // Don't rewrap it
                        return new CollectionInfo(type, elementType, false);
                    }
                    // Else if it's a GelloSequence
                    else if (GelloSequence.class.isAssignableFrom(rawClass)) {
                        
                        // Don't rewrap it
                        return new CollectionInfo(type, elementType, false);
                    }
                    else {
                        
                        // Wrap it as a GelloSequence
                        return new CollectionInfo(GelloSequence.class, elementType, true);
                    }
                }
                else {

                    // It's a generic non-collection class. Wrap it as a singleton GelloSequence
                    return new CollectionInfo(GelloSequence.class, type, true);
                }
            }
            else {

                // Raw type is not a class?  Pretend it is, and wrap it as a singleton GelloSequence
                log.fine("error: don't know how to convert a parameterized non-class to a GELLO collection.");
                result.error(where, "msg.error.internal.unexpected.type", new Object[] { type });
                return new CollectionInfo(GelloSequence.class, type, true);
            }
        }

        // It's something else. Wrap it as a singleton GelloSequence
        return new CollectionInfo(GelloSequence.class, type, true);
    }

    // Build a Type for a parameterized type
    private Type buildParameterizedType(Type genericType, Type parameterType) {
        
        // TODO
        return genericType;
    }

//------------------------------------------------------------------------------------------------
// Grammar rule actions, listed in the order in which they appear in the grammar (gello.sable)
//------------------------------------------------------------------------------------------------

    // (implicit)
    // start = goal
    @Override
    public void outStart(Start node) {
        
        // The object associated with the PGoal is the return value expression
        CompilerExpression expr = getExpr(node.getPGoal());

        // Generate and emit code to return that expression
        emitRun("return ");
        emitRun(expr.getExpression());
        emitRun(" + \"\";\n");

        out(node);
    }

    // goal = {expression} expression
    @Override
    public void outAExpressionGoal(AExpressionGoal node)
    {
        outRippleUp(node, node.getExpression());
    }

    // goal = {context} context_expression
    @Override
    public void outAContextGoal(AContextGoal node)
    {
        outRippleUp(node, node.getContextExpression());
    }

    // TODO: context_expression = 'context' identifier conditional_expression? 'in' expression 'endContext'
    @Override
    public void caseAContextExpression(AContextExpression node)
    {
        inAContextExpression(node);
        if(node.getContext() != null)
        {
            node.getContext().apply(this);
        }
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        if(node.getConditionalExpression() != null)
        {
            node.getConditionalExpression().apply(this);
        }
        if(node.getIn() != null)
        {
            node.getIn().apply(this);
        }
        if(node.getExpression() != null)
        {
            node.getExpression().apply(this);
        }
        if(node.getEndcontext() != null)
        {
            node.getEndcontext().apply(this);
        }
        outAContextExpression(node);
    }

    // expression = let_expression
    @Override
    public void outAExpression(AExpression node)
    {
        outRippleUp(node, node.getLetExpression());
    }

    // let_expression = {if} if_expression
    @Override
    public void outAIfLetExpression(AIfLetExpression node)
    {
        outRippleUp(node, node.getIfExpression());
    }

    // let_expression = {let} 'let' identifier ':' type '=' if_expression in_clause?
    @Override
    public void caseALetLetExpression(ALetLetExpression node)
    {
        caseNode(node);

        inALetLetExpression(node);
        
        // . let identifier : type = if_expression (in expression)?
        if(node.getLet() != null)
        {
            node.getLet().apply(this);
        }
        
        // let . identifier : type = if_expression (in expression)?
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);
        }
        
        // let identifier . : type = if_expression (in expression)?
        if(node.getColon() != null)
        {
            node.getColon().apply(this);
        }
        
        // let identifier : . type = if_expression (in expression)?
        if(node.getType() != null)
        {
            node.getType().apply(this);
        }
        
        // let identifier : type . = if_expression (in expression)?
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        
        // let identifier : type = . if_expression (in expression)?
        if(node.getIfExpression() != null)
        {
            node.getIfExpression().apply(this);
        }
        
        // let identifier : type = if_expression . (in expression)?
        String id = node.getIdentifier().getText();
        String javaId = nameGenerator.next();
        Type type = (Type) getOut(node.getType());
        CompilerExpression expr = (CompilerExpression) getOut(node.getIfExpression());

        // Check that the specified type and the type of the initialization expression are compatible
        checkAssignTypes(node.getEq(), type, expr.getType());

        // Enter the scope of the identifier. Remember how to allocate and initialize it.
        Symbol idSymbol = enterScope(node.getIdentifier(), id, javaId, type, expr.isConstant(), expr.getExpression());

        if(node.getInClause() != null)
        {
            // Compile the in clause with the identifier in the symbol table
            node.getInClause().apply(this);
        }

        // let identifier : type = if_expression (in expression)? .

        // Leave the scope of the identifier.
        symbols.leaveScope(idSymbol);

        // If there is no in clause
        if (node.getInClause() == null) {

            // The code to evaluate this expression is just the java name of the identifier
            setOut(node, new CompilerExpression(idSymbol.getType(), idSymbol.isConstant(), idSymbol.getJavaName()));
        }
        else {

            // The code to evaluate this expression is the in clause expression
            setOut(node, getOut(node.getInClause()));
        }

        outALetLetExpression(node);
    }

    // in_clause = 'in' expression
    @Override
    public void outAInClause(AInClause node)
    {
        outRippleUp(node, node.getExpression());
    }

    // if_expression = {conditional} conditional_expression
    @Override
    public void outAConditionalIfExpression(AConditionalIfExpression node)
    {
        outRippleUp(node, node.getConditionalExpression());
    }

    // TODO: if_expression = {if} 'if' [if_xpr]:expression 'then'
    //                                [then_xpr]:expression 
    //                            'else'
    //                                [else_xpr]:expression 
    //                            'endif'
    @Override
    public void outAIfIfExpression(AIfIfExpression node)
    {
        defaultOut(node);
    }

    // literal = {integer_literal} decimal_integer_literal
    @Override
    public void outAIntegerLiteralLiteral(AIntegerLiteralLiteral node)
    {
        // The result is a constant Integer expression from the token
        setOut(node, new CompilerExpression(java.lang.Integer.class, true, "Integer.valueOf(\"" + node.getDecimalIntegerLiteral().getText() + "\")"));

        out(node);
    }

    // literal = {floating_point_literal} floating_point_literal
    @Override
    public void outAFloatingPointLiteralLiteral(AFloatingPointLiteralLiteral node)
    {
        // The result is a constant Double expression from the token
        setOut(node, new CompilerExpression(java.lang.Double.class, true, "Double.valueOf(\"" + node.getFloatingPointLiteral().getText() + "\")"));
        
        out(node);
    }

    // literal = {string_literal} string_literal
    @Override
    public void outAStringLiteralLiteral(AStringLiteralLiteral node)
    {
        // Strip off the enclosing ticks
        StringBuffer buf = new StringBuffer(node.getStringLiteral().getText());
        buf = new StringBuffer(buf.substring(1, buf.length() - 1));

        // Replace any double-quotes by backslash and double-quote
        int index = 0;
        while (index < buf.length()) {
            index = buf.indexOf("\"", index);
            if (index == -1)
                break;
            buf.insert(index, '\\');
            index += 2;
        }

        // Wrap it in double quotes
        buf.insert(0, '"');
        buf.append('"');
        
        // The result is a constant String expression from the token
        setOut(node, new CompilerExpression(java.lang.String.class, true, buf.toString()));

        out(node);
    }

    // literal = {collection_literal} collection_literal
    @Override
    public void outACollectionLiteralLiteral(ACollectionLiteralLiteral node)
    {
        outRippleUp(node, node.getCollectionLiteral());
    }

    // literal = {tuple_literal} tuple_literal
    @Override
    public void outATupleLiteralLiteral(ATupleLiteralLiteral node)
    {
        outRippleUp(node, node.getTupleLiteral());
    }

    // name = {simple_name} identifier
    @Override
    public void outASimpleNameName(ASimpleNameName node)
    {
        // Resolve the id to a Symbol
        Symbol object = resolveId(node.getIdentifier(), node, node.getIdentifier().getText());

        // The Symbol is our out.
        setOut(node, object);

        out(node);
    }

    // name = {qualified_name} name '.' identifier
    @Override
    public void inAQualifiedNameName(AQualifiedNameName node)
    {
        in(node);

        // Ripple the naming context down to the child name node
        setIn(node.getName(), getIn(node));
    }

    // name = {qualified_name} name '.' identifier
    @Override
    public void outAQualifiedNameName(AQualifiedNameName node)
    {
        // Instead of the rippled-down naming context, we want to use the "name" as the naming context
        setIn(node, getOut(node.getName()));

        // Resolve the id to a Symbol
        Symbol object = resolveId(node.getIdentifier(), node, node.getIdentifier().getText());

        // The Symbol is our out.
        setOut(node, object);

        out(node);
    }

    // gello_type = {basic} basic_type
    @Override
    public void outABasicGelloType(ABasicGelloType node)
    {
        outRippleUp(node, node.getBasicType());
    }

    // gello_type = {collection} collection_type
    @Override
    public void outACollectionGelloType(ACollectionGelloType node)
    {
        outRippleUp(node, node.getCollectionType());
    }

    // gello_type = {tuple} tuple_type
    @Override
    public void outATupleGelloType(ATupleGelloType node)
    {
        outRippleUp(node, node.getTupleType());
    }

    // basic_type = {int} 'integer'
    @Override
    public void outAIntBasicType(AIntBasicType node)
    {
        // The result is the class for the type.
        setOut(node, java.lang.Integer.class);
        
        out(node);
    }

    // basic_type = {string} 'string'
    @Override
    public void outAStringBasicType(AStringBasicType node)
    {
        // The result is the class for the type.
        setOut(node, java.lang.String.class);
        
        out(node);
    }

    // basic_type = {real} 'real'
    @Override
    public void outARealBasicType(ARealBasicType node)
    {
        // The result is the class for the type.
        setOut(node, java.lang.Double.class);
        
        out(node);
    }

    // basic_type = {boolean} 'boolean'
    @Override
    public void outABooleanBasicType(ABooleanBasicType node)
    {
        // The result is the class for the type.
        setOut(node, org.gello.runtime.GelloBoolean.class);
        
        out(node);
    }

    // TODO: collection_type = {set} 'set'
    @Override
    public void outASetCollectionType(ASetCollectionType node)
    {
        defaultOut(node);
    }

    // TODO: collection_type = {bag} 'bag'
    @Override
    public void outABagCollectionType(ABagCollectionType node)
    {
        defaultOut(node);
    }

    // TODO: collection_type = {sequence} 'sequence'
    @Override
    public void outASequenceCollectionType(ASequenceCollectionType node)
    {
        defaultOut(node);
    }

    // TODO: tuple_type = 'tuple'
    @Override
    public void outATupleType(ATupleType node)
    {
        defaultOut(node);
    }
    
    // primary = {literal} literal
    @Override
    public void outALiteralPrimary(ALiteralPrimary node)
    {
        outRippleUp(node, node.getLiteral());
    }

    // primary = {l_paren} '(' expression ')'
    @Override
    public void outALParenPrimary(ALParenPrimary node)
    {
        outRippleUp(node, node.getExpression());
    }

    // primary = {field_access} field_access
    @Override
    public void outAFieldAccessPrimary(AFieldAccessPrimary node)
    {
        outRippleUp(node, node.getFieldAccess());
    }

    // primary = {method_invocation} method_invocation
    @Override
    public void outAMethodInvocationPrimary(AMethodInvocationPrimary node)
    {
        outRippleUp(node, node.getMethodInvocation());
    }

    // TODO: argument_list = {expression} expression
    @Override
    public void outAExpressionArgumentList(AExpressionArgumentList node)
    {
        defaultOut(node);
    }

    // TODO: argument_list = {argument_list} argument_list ',' expression
    @Override
    public void outAArgumentListArgumentList(AArgumentListArgumentList node)
    {
        defaultOut(node);
    }

    // TODO: field_access = primary '.' identifier
    @Override
    public void outAFieldAccess(AFieldAccess node)
    {
        defaultOut(node);
    }

    // TODO: method_invocation = {name} name '(' argument_list? ')'
    @Override
    public void outANameMethodInvocation(ANameMethodInvocation node)
    {
        defaultOut(node);
    }

    // TODO: method_invocation = {primary} primary '.' identifier '(' argument_list? ')'
    @Override
    public void outAPrimaryMethodInvocation(APrimaryMethodInvocation node)
    {
        defaultOut(node);
    }

    // method_invocation = {name_collection} name '->' identifier '(' collection_argument? ')'
    @Override
    public void caseANameCollectionMethodInvocation(ANameCollectionMethodInvocation node)
    {
        inANameCollectionMethodInvocation(node);
        if(node.getName() != null)
        {
            node.getName().apply(this);
        }
        if(node.getArrow() != null)
        {
            node.getArrow().apply(this);
        }
        String operatorName = "missing";
        if(node.getIdentifier() != null)
        {
            node.getIdentifier().apply(this);

            operatorName = node.getIdentifier().getText();
        }
        if(node.getLParen() != null)
        {
            node.getLParen().apply(this);
        }
        
        // The 'out' for 'name' is the resolved symbol
        Symbol collectionSym = (Symbol) getOut(node.getName());

        // Determine how and whether to wrap 'name' into a collection, and the type of the collection element.
        CollectionInfo collectionInfo = inferCollectionInfo(node.getArrow(), collectionSym.getType());
        log.fine("collectionInfo=" + collectionInfo);
        
        // The collection argument will be able to reference the current collection element as _instance.
        // NOTE: This must match the name of the formal parameter to GelloFilter.matches.
        // TODO: The collection argument can contain another name by which the current element can be referenced.
        // I'm punting on this issue for now.
        Symbol paramSym = new Symbol(null, "_instance", collectionInfo.elementType, false, "_instance");

        // Push a naming context for the filter class parameter
        rootNamingContext.push(paramSym);

        String filterExprText = "false";
        if(node.getCollectionArgument() != null)
        {
            // Carry the instance parameter symbol into the CA, so it can be named if specified
            setIn(node.getCollectionArgument(), paramSym);

            node.getCollectionArgument().apply(this);
            
            CompilerExpression filterExpr = getExpr(node.getCollectionArgument());
            filterExprText = filterExpr.getExpression();
        }

        // Pop the naming context for the filter class parameter
        rootNamingContext.pop();

        if(node.getRParen() != null)
        {
            node.getRParen().apply(this);
        }

        /*
         * For anything but iterate, we want to generate:
        $(collection).exists(new GelloFilter<$(elementtype)>() { public GelloBoolean matches($(elementtype) _instance) { return $(expression); } });
         */

        // Construct an expression to refer to the value of the "collection"
        String collectionExpr = collectionSym.getJavaName();

        // Wrap it in a GELLO collection type if required
        if (collectionInfo.needsWrapping) {
            
            collectionExpr = "new " + 
                             BeanHelper.getTypeName(collectionInfo.collectionType) + 
                             "(" + 
                             collectionExpr + 
                             ")";
        }
        
        // Construct a nested or inner 'filter class' instance from the collection argument.
        String innerClass = "new GelloFilter<" + BeanHelper.getTypeName(collectionInfo.elementType) + ">() {" + 
                                "public GelloBoolean matches(" + BeanHelper.getTypeName(collectionInfo.elementType) + " _instance) {" + 
                                    " return " + filterExprText + ";" +
                                "}" +
                            "}";

        // Get the information about this operator
        OperatorInfo opInfo = collectionOperatorMap.get(operatorName);

        // Build a phony result expression to keep from blowing the compiler up if the operator is bad.
        CompilerExpression resultExpr = new CompilerExpression(GelloNull.class, true, "null");

        if (opInfo == null) {

            log.fine("error: unknown collection operator \"" + operatorName + "\"");
            result.error(node.getIdentifier(), 
                         "msg.error.unknown.collection.operator", 
                         new Object[] { operatorName });
        }
        else {

            // The expression is a call to the method's implementation
            String resultExprText = collectionExpr + "." + opInfo.getLibraryName() + "(" + innerClass + ")";
            
            // If the return type is parameterized
            Type resultType = opInfo.getReturnType();
            if (opInfo.isReturnTypeParameterized()) {
                resultType = buildParameterizedType(opInfo.getReturnType(), collectionInfo.elementType);
            }
            
            resultExpr = new CompilerExpression(resultType, false, resultExprText);
        }

        setOut(node, resultExpr);
        outANameCollectionMethodInvocation(node);
    }

    // TODO: method_invocation = {collection} primary '->' identifier '(' collection_argument? ')'
    @Override
    public void outACollectionMethodInvocation(ACollectionMethodInvocation node)
    {
        defaultOut(node);
    }

    // TODO: method_invocation = {name_tuple} name '->' identifier '(' join_argument ')'
    @Override
    public void outANameTupleMethodInvocation(ANameTupleMethodInvocation node)
    {
        defaultOut(node);
    }

    // TODO: method_invocation = {tuple} primary '->' identifier '(' join_argument ')'
    @Override
    public void outATupleMethodInvocation(ATupleMethodInvocation node)
    {
        defaultOut(node);
    }

    // TODO: type = {id} identifier
    @Override
    public void outAIdType(AIdType node)
    {
        defaultOut(node);
    }

    // type = {gello} gello_type
    @Override
    public void outAGelloType(AGelloType node)
    {
        outRippleUp(node, node.getGelloType());
    }

    // collection_argument = {simple} expression
    @Override
    public void outASimpleCollectionArgument(ASimpleCollectionArgument node)
    {
        // Just ripple the expression up
        outRippleUp(node, node.getExpression());
    }

    // collection_argument = {named} identifier '|' expression
    @Override
    public void inANamedCollectionArgument(ANamedCollectionArgument node)
    {
        // TODO
        // This node's 'in' is the Symbol for the parameter to the GelloFilter matches method.
        // This node's 'identifier' is the GELLO name to give that parameter.
        // Update the Symbol's GELLO name
        // Enter scope for the Symbol

        defaultOut(node);
    }

    // collection_argument = {named} identifier '|' expression
    @Override
    public void outANamedCollectionArgument(ANamedCollectionArgument node)
    {
        // TODO
        // Leave scope for the filter parameter

        // Ripple the expression up
        outRippleUp(node, node.getExpression());
    }

    // collection_argument = {typed} identifier ':' type '|' expression
    @Override
    public void inATypedCollectionArgument(ATypedCollectionArgument node)
    {
        // TODO
        // This node's 'in' is the Symbol for the parameter to the GelloFilter matches method.
        // This node's 'identifier' is the GELLO name to give that parameter.
        // Update the Symbol's GELLO name
        // Enter scope for the Symbol

        defaultOut(node);
    }

    // collection_argument = {typed} identifier ':' type '|' expression
    @Override
    public void outATypedCollectionArgument(ATypedCollectionArgument node)
    {
        // TODO
        defaultOut(node);
    }
    
    // collection_argument = {iterate} [iterator]:identifier [itcolon]:colon [itertype]:type
    // semicolon [accumulator]:identifier [acccolon]:colon [acctype]:type eq [accexpr]:expression
    // pipe [filterexpr]:expression
    //
    // Ugly, innit?
    @Override
    public void caseAIterateCollectionArgument(AIterateCollectionArgument node)
    {
        inAIterateCollectionArgument(node);
        if(node.getIterid() != null)
        {
            node.getIterid().apply(this);
        }
        if(node.getItercolon() != null)
        {
            node.getItercolon().apply(this);
        }
        if(node.getItertype() != null)
        {
            node.getItertype().apply(this);
        }
        if(node.getSemicolon() != null)
        {
            node.getSemicolon().apply(this);
        }
        if(node.getAccid() != null)
        {
            node.getAccid().apply(this);
        }
        if(node.getAcccolon() != null)
        {
            node.getAcccolon().apply(this);
        }
        if(node.getAcctype() != null)
        {
            node.getAcctype().apply(this);
        }
        if(node.getEq() != null)
        {
            node.getEq().apply(this);
        }
        if(node.getAccexpr() != null)
        {
            node.getAccexpr().apply(this);
        }
        if(node.getPipe() != null)
        {
            node.getPipe().apply(this);
        }

        /*
         * new interface:
         * public interface GelloIterator<E> {
         *     void step(E _instance);
         * }
         *
         * in GelloCollection:
         * public void iterate(GelloIterator<T> iterator) {
         *      // Assumes that the accumulator is already initialized
         *      // while there are elements in the collection
         *      Iterator<T> elementIterator = iterator();
         *      while (elementIterator.hasNext()) {
         *          // step
         *          iterator.step(elementIterator.next());
         *      }
         * }
         *
         * compiled code:
         * $(collection_ref).iterate(new GelloIterator<$(element_type)>() { void step($(element_type) _instance) { $(accumulatorName) = $(accumExpr); } })
         */
        
        // TODO: 'in' is the Symbol for the instance parameter to the GelloIterate step method.
        // TODO: set the GELLO name for the instance parameter
        // TODO: itertype is the type for the instance parameter to the GelloIterate step method.
        // TODO: But we already know that. Why is this here?
        // TODO: enter scope for the instance parameter (not a user variable)

        // TODO: accid is the GELLO name for the iterate accumulator variable.
        // TODO: acctype is the GELLO type for the iterate accumulator variable.
        // TODO: enter scope for the iterate accumulator variable (a user variable).

        // TODO: accexpr is the initial value for the iterate accumulator variable.

        if(node.getFilterexpr() != null)
        {
            node.getFilterexpr().apply(this);
        }

        // TODO: leave scope for the iterate accumulator variable.
        // TODO: leave scope for the instance parameter.
        // TODO: filterexpr is the filter expression.
        // TODO: out for this node is 

        outAIterateCollectionArgument(node);
    }

    // TODO: join_collection = identifier
    @Override
    public void outASimpleJoinCollection(ASimpleJoinCollection node)
    {
        // TODO: Resolve the identifier.
        // TODO: Promote it to a GELLO collection of some type.
        // TODO: out is a CompilerExpression to load the collection.
        // TODO: The name of the CompilerExpression is the name of the collection.

        defaultOut(node);
    }

    // TODO: join_collection = [alias]:identifier in [collection]:identifier
    @Override
    public void outAAliasedJoinCollection(AAliasedJoinCollection node)
    {
        // TODO: Resolve the collection name identifier.
        // TODO: Promote it to a GELLO collection of some type.
        // TODO: out is a CompilerExpression to load the collection.
        // TODO: The name of the CompilerExpression is the alias identifier's text.

        defaultOut(node);
    }

    // TODO: join_collection_list = join_collection
    @Override
    public void outASingleJoinCollectionList(ASingleJoinCollectionList node)
    {
        // TODO: out is an ArrayList<CompilerExpression> containing the single CompilerExpression.

        defaultOut(node);
    }

    // TODO: join_collection_list = join_collection_list ',' join_collection
    @Override
    public void outAListJoinCollectionList(AListJoinCollectionList node)
    {
        // TODO: get the ArrayList<CompilerExpression> for the left list.
        // TODO: If the name of the right CompilerExpression already appears in the left list
            // TODO: Complain - duplicate collection name or alias.
        // TODO: add the right CompilerExpression to the left list.
        // TODO: out is the modified left list.

        defaultOut(node);
    }

    // TODO: join_property = [collection]:identifier dot [member]:identifier
    @Override
    public void outAJoinProperty(AJoinProperty node)
    {
        // TODO: out is Pair<String, String> of the two identifiers.

        defaultOut(node);
    }

    // TODO: join_property_list = join_property
    @Override
    public void outASingleJoinPropertyList(ASingleJoinPropertyList node)
    {
        // TODO: out is a singleton ArrayList<Pair<String,String>> containing the property reference.

        defaultOut(node);
    }

    // TODO: join_property_list = join_property_list ',' join_property
    @Override
    public void outAListJoinPropertyList(AListJoinPropertyList node)
    {
        // TODO: get the ArrayList<Pair<String,String>> for the left list.
        // TODO: add the right property to the left list.
        // TODO: out is the modified left list.

        defaultOut(node);
    }

    public void outAUnorderedJoinArgument(AUnorderedJoinArgument node)
    {
        defaultOut(node);
    }
    
    // Join code generation strategy:
    // 
    // unordered uses:
    // new GelloBagJoiner() {
    //      public GelloBag join() {
    //          GelloBag<GelloTuple> _result = new GelloBag<GelloTuple>();
    //          for (ce0 in $(collection0)) {
    //              for (ce1 in $(collection1)) {
    //                  // nest one loop per collection
    //                  if ($(filter on ce*)) {
    //                      GelloTuple _row = new GelloTuple($(parts from select list));
    //                      _result.add(_row);
    //                  }
    //              }
    //          }
    //          return _result;
    //      }
    // }.join()
    //
    // ordered uses:
    // new GelloSequenceJoiner() {
    //      public GelloSequence join() {
    //          GelloSequence<GelloTuple> _result = new GelloSequence<GelloTuple>();
    //          for (ce0 in $(collection0)) {
    //              for (ce1 in $(collection1)) {
    //                  // nest one loop per collection
    //                  if ($(filter on ce*)) {
    //                      GelloTuple _row = new GelloTuple($(parts from select list));
    //                      _result.add(_row);
    //                  }
    //              }
    //          }
    //          Collections.sort(_result, new Comparator<GelloTuple>() { 
    //                                      public boolean equals(Object right) {
    //                                          retrn compareTo(this, (GelloTuple) right) == 0;
    //                                      }
    //                                      public int compareTo(GelloTuple left, GelloTuple right) {
    //                                          $(implementation based on order "expression")
    //                                      }
    //                                    });
    //          return _result;
    //      }
    // }.join()
    // 

    // TODO: join_argument = join_collection_list [s1]:semicolon
    //                       join_property_list [s2]:semicolon
    //                       [filter]:expression
    @Override
    public void caseAUnorderedJoinArgument(AUnorderedJoinArgument node)
    {
        inAUnorderedJoinArgument(node);
        if(node.getJoinCollectionList() != null)
        {
            node.getJoinCollectionList().apply(this);
        }
        // TODO: move all collections in the list into scope, under their aliases?
        if(node.getS1() != null)
        {
            node.getS1().apply(this);
        }
        if(node.getJoinPropertyList() != null)
        {
            node.getJoinPropertyList().apply(this);
        }
        if(node.getS2() != null)
        {
            node.getS2().apply(this);
        }
        if(node.getFilter() != null)
        {
            node.getFilter().apply(this);
        }

        // TODO: generate an anonymous GelloBagJoiner
        // TODO: move all collections in the list out of scope
        outAUnorderedJoinArgument(node);
    }

    // TODO: join_argument = join_collection_list [s1]:semicolon
    //                       join_property_list [s2]:semicolon
    //                       [filter]:expression [s3]:semicolon
    //                       [order]:expression
    @Override
    public void caseAOrderedJoinArgument(AOrderedJoinArgument node)
    {
        inAOrderedJoinArgument(node);
        if(node.getJoinCollectionList() != null)
        {
            node.getJoinCollectionList().apply(this);
        }
        // TODO: move all collections in the list into scope, under their aliases?
        if(node.getS1() != null)
        {
            node.getS1().apply(this);
        }
        if(node.getJoinPropertyList() != null)
        {
            node.getJoinPropertyList().apply(this);
        }
        if(node.getS2() != null)
        {
            node.getS2().apply(this);
        }
        if(node.getFilter() != null)
        {
            node.getFilter().apply(this);
        }
        if(node.getS3() != null)
        {
            node.getS3().apply(this);
        }
        if(node.getOrder() != null)
        {
            node.getOrder().apply(this);
        }

        // TODO: generate an anonymous GelloSequenceJoiner
        // TODO: move all collections in the list out of scope
        outAOrderedJoinArgument(node);
    }

    // postfix_expression = {primary} primary
    @Override
    public void outAPrimaryPostfixExpression(APrimaryPostfixExpression node)
    {
        outRippleUp(node, node.getPrimary());
    }

    // postfix_expression = {name} name
    @Override
    public void outANamePostfixExpression(ANamePostfixExpression node)
    {
        // Convert the name to the Java expression to load the named object.
        Symbol nameSymbol = (Symbol) getOut(node.getName());
        CompilerExpression expr = new CompilerExpression(nameSymbol.getType(), nameSymbol.isConstant(), nameSymbol.getJavaName());
        setOut(node, expr);

        out(node);
    }

    // TODO: unary_expression = {minus} '-' unary_expression
    @Override
    public void outAMinusUnaryExpression(AMinusUnaryExpression node)
    {
        defaultOut(node);
    }

    // TODO: unary_expression = {not} 'not' unary_expression
    @Override
    public void outANotUnaryExpression(ANotUnaryExpression node)
    {
        defaultOut(node);
    }

    // unary_expression = {postfix} postfix_expression
    @Override
    public void outAPostfixUnaryExpression(APostfixUnaryExpression node)
    {
        outRippleUp(node, node.getPostfixExpression());
    }

    // multiplicative_expression = {unary} unary_expression
    @Override
    public void outAUnaryMultiplicativeExpression(AUnaryMultiplicativeExpression node)
    {
        outRippleUp(node, node.getUnaryExpression());
    }

    // TODO: multiplicative_expression = {multiply} multiplicative_expression '*' unary_expression
    @Override
    public void outAMultiplyMultiplicativeExpression(AMultiplyMultiplicativeExpression node)
    {
        defaultOut(node);
    }

    // TODO: multiplicative_expression = {divide} multiplicative_expression '/' unary_expression
    @Override
    public void outADivideMultiplicativeExpression(ADivideMultiplicativeExpression node)
    {
        defaultOut(node);
    }

    // TODO: multiplicative_expression = {intdiv} multiplicative_expression 'div' unary_expression
    @Override
    public void outAIntdivMultiplicativeExpression(AIntdivMultiplicativeExpression node)
    {
        defaultOut(node);
    }

    // TODO: multiplicative_expression = {mod} multiplicative_expression 'mod' unary_expression
    @Override
    public void outAModMultiplicativeExpression(AModMultiplicativeExpression node)
    {
        defaultOut(node);
    }

    // TODO: multiplicative_expression = {max} multiplicative_expression 'max' unary_expression
    @Override
    public void outAMaxMultiplicativeExpression(AMaxMultiplicativeExpression node)
    {
        defaultOut(node);
    }

    // TODO: multiplicative_expression = {min} multiplicative_expression 'min' unary_expression
    @Override
    public void outAMinMultiplicativeExpression(AMinMultiplicativeExpression node)
    {
        defaultOut(node);
    }

    // TODO: additive_expression = {multiply} multiplicative_expression
    @Override
    public void outAMultiplyAdditiveExpression(AMultiplyAdditiveExpression node)
    {
        outRippleUp(node, node.getMultiplicativeExpression());
    }

    // TODO: additive_expression = {plus} additive_expression '+' multiplicative_expression
    @Override
    public void outAPlusAdditiveExpression(APlusAdditiveExpression node)
    {
        defaultOut(node);
    }

    // TODO: additive_expression = {minus} additive_expression '-' multiplicative_expression
    @Override
    public void outAMinusAdditiveExpression(AMinusAdditiveExpression node)
    {
        defaultOut(node);
    }

    // relational_expression = {additive_expression} additive_expression
    @Override
    public void outAAdditiveExpressionRelationalExpression(AAdditiveExpressionRelationalExpression node)
    {
        outRippleUp(node, node.getAdditiveExpression());
    }

    // relational_expression = {lt} relational_expression '<' additive_expression
    @Override
    public void outALtRelationalExpression(ALtRelationalExpression node)
    {
        compileRelational(node.getLt(), node, node.getRelationalExpression(), node.getAdditiveExpression(), RELOP_LESS);
    }

    // relational_expression = {gt} relational_expression '>' additive_expression
    @Override
    public void outAGtRelationalExpression(AGtRelationalExpression node)
    {
        compileRelational(node.getGt(), node, node.getRelationalExpression(), node.getAdditiveExpression(), RELOP_GREATER);
    }

    // relational_expression = {lteq} relational_expression '<=' additive_expression
    @Override
    public void outALteqRelationalExpression(ALteqRelationalExpression node)
    {
        compileRelational(node.getLteq(), node, node.getRelationalExpression(), node.getAdditiveExpression(), RELOP_NOTGREATER);
    }

    // relational_expression = {gteq} relational_expression '>=' additive_expression
    @Override
    public void outAGteqRelationalExpression(AGteqRelationalExpression node)
    {
        compileRelational(node.getGteq(), node, node.getRelationalExpression(), node.getAdditiveExpression(), RELOP_NOTLESS);
    }

    // relational_expression = {eq} relational_expression '=' additive_expression
    @Override
    public void outAEqRelationalExpression(AEqRelationalExpression node)
    {
        compileRelational(node.getEq(), node, node.getRelationalExpression(), node.getAdditiveExpression(), RELOP_EQUALS);
    }

    // relational_expression = {neq} relational_expression '!=' additive_expression
    @Override
    public void outANeqRelationalExpression(ANeqRelationalExpression node)
    {
        compileRelational(node.getNeq(), node, node.getRelationalExpression(), node.getAdditiveExpression(), "notequals");
    }

    // conditional_and_expression = {relational} relational_expression
    @Override
    public void outARelationalConditionalAndExpression(ARelationalConditionalAndExpression node)
    {
        outRippleUp(node, node.getRelationalExpression());
    }

    // TODO: conditional_and_expression = {and} conditional_and_expression 'and' relational_expression
    @Override
    public void outAAndConditionalAndExpression(AAndConditionalAndExpression node)
    {
        compileLogical(node.getAnd(), node, node.getConditionalAndExpression(), node.getRelationalExpression(), "and");
    }

    // conditional_or_expression = {and} conditional_and_expression
    @Override
    public void outAAndConditionalOrExpression(AAndConditionalOrExpression node)
    {
        outRippleUp(node, node.getConditionalAndExpression());
    }

    // TODO: conditional_or_expression = {or} conditional_or_expression 'or' conditional_and_expression
    @Override
    public void outAOrConditionalOrExpression(AOrConditionalOrExpression node)
    {
        compileLogical(node.getOr(), node, node.getConditionalOrExpression(), node.getConditionalAndExpression(), "or");
    }

    // TODO: conditional_or_expression = {xor} conditional_or_expression 'xor' conditional_and_expression
    @Override
    public void outAXorConditionalOrExpression(AXorConditionalOrExpression node)
    {
        compileLogical(node.getXor(), node, node.getConditionalOrExpression(), node.getConditionalAndExpression(), "xor");
    }

    // conditional_expression = {or} conditional_or_expression
    @Override
    public void outAOrConditionalExpression(AOrConditionalExpression node)
    {
        outRippleUp(node, node.getConditionalOrExpression());
    }

    // TODO: conditional_expression = {implies} conditional_expression 'implies' conditional_or_expression
    @Override
    public void outAImpliesConditionalExpression(AImpliesConditionalExpression node)
    {
        compileLogical(node.getImplies(), node, node.getConditionalExpression(), node.getConditionalOrExpression(), "implies");
    }

    // TODO: collection_literal_argument = {expression} expression
    @Override
    public void outAExpressionCollectionLiteralArgument(AExpressionCollectionLiteralArgument node)
    {
        defaultOut(node);
    }

    // TODO: collection_literal_argument = {range} '[' [begin]:expression '..' [end]:expression ']'
    @Override
    public void outARangeCollectionLiteralArgument(ARangeCollectionLiteralArgument node)
    {
        defaultOut(node);
    }

    // TODO: collection_list = {single} collection_literal_argument
    @Override
    public void outASingleCollectionList(ASingleCollectionList node)
    {
        defaultOut(node);
    }

    // TODO: collection_list = {list} collection_list ',' collection_literal_argument
    @Override
    public void outAListCollectionList(AListCollectionList node)
    {
        defaultOut(node);
    }

    // TODO: collection_literal = collection_type '{' collection_list? '}'
    @Override
    public void outACollectionLiteral(ACollectionLiteral node)
    {
        defaultOut(node);
    }

    // typed_var_definition = identifier ':' type '=' expression
    // in = identifier, type and CompilerExpression.
    // out: new singleton CompilerTuple
    @Override
    public void outATypedVarDefinition(ATypedVarDefinition node)
    {
        String name = node.getIdentifier().getText();
        Type type = (Type) getOut(node.getType());
        CompilerExpression expr = getExpr(node.getExpression());

        // TODO: scalar <> collection conversions

        // Check if the type and expression are compatible
        checkAssignTypes(node.getEq(), type, expr.getType());

        // out = CompilerTuple
        setOut(node, new CompilerTuple(name, expr));
        
        // Dump this node
        out(node);
    }

    // tuple_literal_list = {binding} typed_var_definition
    @Override
    public void outABindingTupleLiteralList(ABindingTupleLiteralList node)
    {
        outRippleUp(node, node.getTypedVarDefinition());
    }

    // tuple_literal_list = {list} tuple_literal_list ',' typed_var_definition
    @Override
    public void outAListTupleLiteralList(AListTupleLiteralList node)
    {
        CompilerTuple listTuple = (CompilerTuple) getOut(node.getTupleLiteralList());
        CompilerTuple newTuple = (CompilerTuple) getOut(node.getTypedVarDefinition());

        // Pull the single Part out of the new Tuple
        CompilerTuple.Part newPart = newTuple.getParts()[0];

        // If the list already contains a Part with this new Part's name
        if (listTuple.containsPart(newPart.getName())) {

            // Whine
            result.error(node.getComma(), "msg.error.duplicate.part.name", new Object[] { newPart.getName() } );
        }
        else {

            // Add the new Part to the list Tuple
            listTuple.add(newPart);
        }

        // out = the merged Tuple
        setOut(node, listTuple);
        
        // Dump this node
        out(node);
    }

    // tuple_literal = 'tuple' '(' tuple_literal_list? ')'
    @Override
    public void outATupleLiteral(ATupleLiteral node)
    {
        // If the list is null
        CompilerTuple tuple = (CompilerTuple) getOut(node.getTupleLiteralList());
        if (tuple == null) {
            
            // Use an empty Tuple.
            tuple = new CompilerTuple();
        }

        // Get the parts of the Tuple
        CompilerTuple.Part[] parts = tuple.getParts();

        // Construct an expression to evaluate the literal
        // new GelloTuple(new GelloTuple.Part[] { new GelloTuple.Part( "$(partname)", $(partvalue) ) ... } )
        StringBuffer outExpr = new StringBuffer("new org.gello.runtime.GelloTuple(");
        outExpr.append("new org.gello.runtime.GelloTuple.Part[] {");
        for (int i = 0; i < parts.length; i++) {
            if (i != 0) {
                outExpr.append(", ");
            }
            outExpr.append("new org.gello.runtime.GelloTuple.Part(\"");
            outExpr.append(parts[i].getName());
            outExpr.append("\", ");
            outExpr.append(parts[i].getValue().getExpression());
            outExpr.append(")");
        }
        outExpr.append("}");
        outExpr.append(")");
        
        // That expression is the out for this node
        setOut(node, new CompilerExpression(GelloTuple.class, false, outExpr.toString()));

        // Dump this node
        out(node);
    }
}
