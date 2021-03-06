/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class TContext extends Token
{
    public TContext()
    {
        super.setText("context");
    }

    public TContext(int line, int pos)
    {
        super.setText("context");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TContext(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTContext(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TContext text.");
    }
}
