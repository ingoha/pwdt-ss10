/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.parser;

import org.gello.grammar.node.*;
import org.gello.grammar.analysis.*;

class TokenIndex extends AnalysisAdapter
{
    int index;

    @Override
    public void caseTIf(@SuppressWarnings("unused") TIf node)
    {
        this.index = 0;
    }

    @Override
    public void caseTThen(@SuppressWarnings("unused") TThen node)
    {
        this.index = 1;
    }

    @Override
    public void caseTElse(@SuppressWarnings("unused") TElse node)
    {
        this.index = 2;
    }

    @Override
    public void caseTEndif(@SuppressWarnings("unused") TEndif node)
    {
        this.index = 3;
    }

    @Override
    public void caseTLet(@SuppressWarnings("unused") TLet node)
    {
        this.index = 4;
    }

    @Override
    public void caseTIn(@SuppressWarnings("unused") TIn node)
    {
        this.index = 5;
    }

    @Override
    public void caseTContext(@SuppressWarnings("unused") TContext node)
    {
        this.index = 6;
    }

    @Override
    public void caseTEndcontext(@SuppressWarnings("unused") TEndcontext node)
    {
        this.index = 7;
    }

    @Override
    public void caseTBoolean(@SuppressWarnings("unused") TBoolean node)
    {
        this.index = 8;
    }

    @Override
    public void caseTInt(@SuppressWarnings("unused") TInt node)
    {
        this.index = 9;
    }

    @Override
    public void caseTReal(@SuppressWarnings("unused") TReal node)
    {
        this.index = 10;
    }

    @Override
    public void caseTString(@SuppressWarnings("unused") TString node)
    {
        this.index = 11;
    }

    @Override
    public void caseTSet(@SuppressWarnings("unused") TSet node)
    {
        this.index = 12;
    }

    @Override
    public void caseTBag(@SuppressWarnings("unused") TBag node)
    {
        this.index = 13;
    }

    @Override
    public void caseTSequence(@SuppressWarnings("unused") TSequence node)
    {
        this.index = 14;
    }

    @Override
    public void caseTTuple(@SuppressWarnings("unused") TTuple node)
    {
        this.index = 15;
    }

    @Override
    public void caseTAnd(@SuppressWarnings("unused") TAnd node)
    {
        this.index = 16;
    }

    @Override
    public void caseTArrow(@SuppressWarnings("unused") TArrow node)
    {
        this.index = 17;
    }

    @Override
    public void caseTColon(@SuppressWarnings("unused") TColon node)
    {
        this.index = 18;
    }

    @Override
    public void caseTComma(@SuppressWarnings("unused") TComma node)
    {
        this.index = 19;
    }

    @Override
    public void caseTDivide(@SuppressWarnings("unused") TDivide node)
    {
        this.index = 20;
    }

    @Override
    public void caseTDot(@SuppressWarnings("unused") TDot node)
    {
        this.index = 21;
    }

    @Override
    public void caseTDotdot(@SuppressWarnings("unused") TDotdot node)
    {
        this.index = 22;
    }

    @Override
    public void caseTEq(@SuppressWarnings("unused") TEq node)
    {
        this.index = 23;
    }

    @Override
    public void caseTGteq(@SuppressWarnings("unused") TGteq node)
    {
        this.index = 24;
    }

    @Override
    public void caseTGt(@SuppressWarnings("unused") TGt node)
    {
        this.index = 25;
    }

    @Override
    public void caseTImplies(@SuppressWarnings("unused") TImplies node)
    {
        this.index = 26;
    }

    @Override
    public void caseTIntdiv(@SuppressWarnings("unused") TIntdiv node)
    {
        this.index = 27;
    }

    @Override
    public void caseTLteq(@SuppressWarnings("unused") TLteq node)
    {
        this.index = 28;
    }

    @Override
    public void caseTLt(@SuppressWarnings("unused") TLt node)
    {
        this.index = 29;
    }

    @Override
    public void caseTMax(@SuppressWarnings("unused") TMax node)
    {
        this.index = 30;
    }

    @Override
    public void caseTMin(@SuppressWarnings("unused") TMin node)
    {
        this.index = 31;
    }

    @Override
    public void caseTMinus(@SuppressWarnings("unused") TMinus node)
    {
        this.index = 32;
    }

    @Override
    public void caseTMod(@SuppressWarnings("unused") TMod node)
    {
        this.index = 33;
    }

    @Override
    public void caseTNeq(@SuppressWarnings("unused") TNeq node)
    {
        this.index = 34;
    }

    @Override
    public void caseTNot(@SuppressWarnings("unused") TNot node)
    {
        this.index = 35;
    }

    @Override
    public void caseTOr(@SuppressWarnings("unused") TOr node)
    {
        this.index = 36;
    }

    @Override
    public void caseTPlus(@SuppressWarnings("unused") TPlus node)
    {
        this.index = 37;
    }

    @Override
    public void caseTLParen(@SuppressWarnings("unused") TLParen node)
    {
        this.index = 38;
    }

    @Override
    public void caseTRParen(@SuppressWarnings("unused") TRParen node)
    {
        this.index = 39;
    }

    @Override
    public void caseTLBrace(@SuppressWarnings("unused") TLBrace node)
    {
        this.index = 40;
    }

    @Override
    public void caseTRBrace(@SuppressWarnings("unused") TRBrace node)
    {
        this.index = 41;
    }

    @Override
    public void caseTLBracket(@SuppressWarnings("unused") TLBracket node)
    {
        this.index = 42;
    }

    @Override
    public void caseTRBracket(@SuppressWarnings("unused") TRBracket node)
    {
        this.index = 43;
    }

    @Override
    public void caseTSemicolon(@SuppressWarnings("unused") TSemicolon node)
    {
        this.index = 44;
    }

    @Override
    public void caseTStar(@SuppressWarnings("unused") TStar node)
    {
        this.index = 45;
    }

    @Override
    public void caseTPipe(@SuppressWarnings("unused") TPipe node)
    {
        this.index = 46;
    }

    @Override
    public void caseTXor(@SuppressWarnings("unused") TXor node)
    {
        this.index = 47;
    }

    @Override
    public void caseTDecimalIntegerLiteral(@SuppressWarnings("unused") TDecimalIntegerLiteral node)
    {
        this.index = 48;
    }

    @Override
    public void caseTFloatingPointLiteral(@SuppressWarnings("unused") TFloatingPointLiteral node)
    {
        this.index = 49;
    }

    @Override
    public void caseTStringLiteral(@SuppressWarnings("unused") TStringLiteral node)
    {
        this.index = 50;
    }

    @Override
    public void caseTIdentifier(@SuppressWarnings("unused") TIdentifier node)
    {
        this.index = 51;
    }

    @Override
    public void caseEOF(@SuppressWarnings("unused") EOF node)
    {
        this.index = 52;
    }
}
