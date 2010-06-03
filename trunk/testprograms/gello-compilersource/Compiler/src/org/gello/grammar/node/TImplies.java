/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class TImplies extends Token
{
    public TImplies()
    {
        super.setText("implies");
    }

    public TImplies(int line, int pos)
    {
        super.setText("implies");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TImplies(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTImplies(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TImplies text.");
    }
}
