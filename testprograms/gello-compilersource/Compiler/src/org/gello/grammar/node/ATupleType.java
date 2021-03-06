/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ATupleType extends PTupleType
{
    private TTuple _tuple_;

    public ATupleType()
    {
        // Constructor
    }

    public ATupleType(
        @SuppressWarnings("hiding") TTuple _tuple_)
    {
        // Constructor
        setTuple(_tuple_);

    }

    @Override
    public Object clone()
    {
        return new ATupleType(
            cloneNode(this._tuple_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATupleType(this);
    }

    public TTuple getTuple()
    {
        return this._tuple_;
    }

    public void setTuple(TTuple node)
    {
        if(this._tuple_ != null)
        {
            this._tuple_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._tuple_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._tuple_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._tuple_ == child)
        {
            this._tuple_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._tuple_ == oldChild)
        {
            setTuple((TTuple) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
