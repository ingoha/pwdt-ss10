/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ARelationalConditionalAndExpression extends PConditionalAndExpression
{
    private PRelationalExpression _relationalExpression_;

    public ARelationalConditionalAndExpression()
    {
        // Constructor
    }

    public ARelationalConditionalAndExpression(
        @SuppressWarnings("hiding") PRelationalExpression _relationalExpression_)
    {
        // Constructor
        setRelationalExpression(_relationalExpression_);

    }

    @Override
    public Object clone()
    {
        return new ARelationalConditionalAndExpression(
            cloneNode(this._relationalExpression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseARelationalConditionalAndExpression(this);
    }

    public PRelationalExpression getRelationalExpression()
    {
        return this._relationalExpression_;
    }

    public void setRelationalExpression(PRelationalExpression node)
    {
        if(this._relationalExpression_ != null)
        {
            this._relationalExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._relationalExpression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._relationalExpression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._relationalExpression_ == child)
        {
            this._relationalExpression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._relationalExpression_ == oldChild)
        {
            setRelationalExpression((PRelationalExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
