/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AExpression extends PExpression
{
    private PLetExpression _letExpression_;

    public AExpression()
    {
        // Constructor
    }

    public AExpression(
        @SuppressWarnings("hiding") PLetExpression _letExpression_)
    {
        // Constructor
        setLetExpression(_letExpression_);

    }

    @Override
    public Object clone()
    {
        return new AExpression(
            cloneNode(this._letExpression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAExpression(this);
    }

    public PLetExpression getLetExpression()
    {
        return this._letExpression_;
    }

    public void setLetExpression(PLetExpression node)
    {
        if(this._letExpression_ != null)
        {
            this._letExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._letExpression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._letExpression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._letExpression_ == child)
        {
            this._letExpression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._letExpression_ == oldChild)
        {
            setLetExpression((PLetExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
