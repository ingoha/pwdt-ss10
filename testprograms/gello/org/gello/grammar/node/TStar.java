/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class TStar extends Token
{
    public TStar()
    {
        super.setText("*");
    }

    public TStar(int line, int pos)
    {
        super.setText("*");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TStar(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTStar(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TStar text.");
    }
}
