/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class TEndif extends Token
{
    public TEndif()
    {
        super.setText("endif");
    }

    public TEndif(int line, int pos)
    {
        super.setText("endif");
        setLine(line);
        setPos(pos);
    }

    @Override
    public Object clone()
    {
      return new TEndif(getLine(), getPos());
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseTEndif(this);
    }

    @Override
    public void setText(@SuppressWarnings("unused") String text)
    {
        throw new RuntimeException("Cannot change TEndif text.");
    }
}
