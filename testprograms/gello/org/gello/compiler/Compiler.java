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

import java.io.IOException;
import org.gello.grammar.lexer.Lexer;
import org.gello.grammar.lexer.LexerException;
import org.gello.grammar.node.Start;
import org.gello.grammar.node.Token;
import org.gello.grammar.parser.Parser;

import java.io.File;
import java.io.FileWriter;
import java.io.PushbackReader;
import java.io.StringReader;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.gello.grammar.parser.ParserException;
import org.gello.runtime.IGelloModel;

/**
 * The public interface to the GELLO compiler.
 * @author Erik Horstkotte
 */
public class Compiler {

    private static Logger log = Logger.getLogger(Compiler.class.getName());

    /** Creates a new instance of Compiler */
    public Compiler() {
    }

    /** Helper to get the current token */
    public Token getCurrentToken(Lexer lexer) {

        if (lexer == null) {
            return null;
        }

        try {
            return lexer.peek();
        } catch (LexerException ex) {
            return null;
        } catch (IOException ex) {
            return null;
        }
    }

    /** Compile a GELLO unit, assuming it will be run against the supplied data model.
     * NOTE:
     * - The generated Java source is written to a .java file with the specified path
     *   prefix and a name based on the specified classname.
     * - The compiled Java class  is written to a .class file with the specified path
     *   prefix and a name based on the specified classname.
     *
     * @param source The Unicode GELLO text to be compiled.
     * @param model The GELLO data model to compile against, usually the HL7 RIM.
     * @param packageName The Java package name to emit in the compiled code.
     * @param className The name of the Java class to be generated.
     * @param path The absolute or relative filesystem path where the generated Java
     * and class files should be stored.
     * @param javacClassPath The compile-time classpath for the Java compiler. This should
     * include the org.gello.runtime package at a minimum.
     * @return A <code>CompileResult</code> describing the results of the attempted compilation.
     */
    public CompileResult compile(String source, IGelloModel model, String packageName, String className, String path, String javacClassPath)
    {
        // Create a result object. Assume success.
        CompileResult result = new CompileResult();

        // Create a lexer and a parser.
        PushbackReader reader = new PushbackReader(new StringReader(source));
        Lexer lexer = new Lexer(reader);
        Parser parser = new Parser(lexer);

        try {

            // Run the lexer and parser.
            Start tree = parser.parse();

            // If debugging is enabled
            if (log.isLoggable(Level.FINEST)) {

                // Dump the parse tree
                //tree.apply(new DumpingTreeAdapter());
            }

            // Generate Java source code from the parse tree
            CodeGenTreeAdapter codeGenerator = new CodeGenTreeAdapter(result, model);
            tree.apply(codeGenerator);
            String javaCode = codeGenerator.getGeneratedClass(packageName, className);
            if (log.isLoggable(Level.FINEST)) {
                log.finest("Generated code:\n" + javaCode);
            }

            // Write the Java source code to a .java file
            String javaFilename = path + className + ".java";
            FileWriter javaWriter = new FileWriter(javaFilename, false);
            javaWriter.write(javaCode);
            javaWriter.flush();
            javaWriter.close();
            
			/*
            // Compile the Java source code to a .class file
            JavaCompiler javac = new JavaCompiler(javacClassPath);
            if (!javac.compile(javaFilename)) {
                result.fatal(null, "msg.error.internal.javac", new Object[] { javac.getStderr() });
            
            }
            */
        }
        catch (LexerException ex) {

            log.log(Level.SEVERE, "LexerException: " + ex.getMessage(), ex);
            result.fatal(getCurrentToken(lexer), "msg.error.fatal.lexer", new Object[] { ex.getMessage() });
        }
        catch (ParserException ex) {

            log.log(Level.SEVERE, "ParserException: " + ex.getMessage(), ex);
            result.fatal(getCurrentToken(lexer), "msg.error.fatal.parser", new Object[] { ex.getMessage() });
        }
        catch (IOException ex) {

            log.log(Level.SEVERE, "IOException: " + ex.getMessage(), ex);
            result.fatal(getCurrentToken(lexer), "msg.error.fatal.io", new Object[] { ex.getMessage() });
        }

        if (log.isLoggable(Level.INFO)) {
            log.log(Level.INFO, "Result=" + result);
        }

        // Return the result object.
        return result;
    }
}
