/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class TLt extends Token
{
    public TLt()
    {
        super.setText("<");
    }

    public TLt(int line, int pos)
    {
        super.setText("<");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TLt(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTLt(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TLt text.");
    }
}
