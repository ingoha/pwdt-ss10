/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class TMax extends Token
{
    public TMax()
    {
        super.setText("max");
    }

    public TMax(int line, int pos)
    {
        super.setText("max");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TMax(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTMax(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TMax text.");
    }
}
