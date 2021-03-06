/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ATupleLiteralLiteral extends PLiteral
{
    private PTupleLiteral _tupleLiteral_;

    public ATupleLiteralLiteral()
    {
        // Constructor
    }

    public ATupleLiteralLiteral(
        @SuppressWarnings("hiding") PTupleLiteral _tupleLiteral_)
    {
        // Constructor
        setTupleLiteral(_tupleLiteral_);

    }

    @Override
    public Object clone()
    {
        return new ATupleLiteralLiteral(
            cloneNode(this._tupleLiteral_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATupleLiteralLiteral(this);
    }

    public PTupleLiteral getTupleLiteral()
    {
        return this._tupleLiteral_;
    }

    public void setTupleLiteral(PTupleLiteral node)
    {
        if(this._tupleLiteral_ != null)
        {
            this._tupleLiteral_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._tupleLiteral_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._tupleLiteral_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._tupleLiteral_ == child)
        {
            this._tupleLiteral_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._tupleLiteral_ == oldChild)
        {
            setTupleLiteral((PTupleLiteral) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
