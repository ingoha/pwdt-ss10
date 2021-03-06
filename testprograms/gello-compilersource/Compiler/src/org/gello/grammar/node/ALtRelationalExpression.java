/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ALtRelationalExpression extends PRelationalExpression
{
    private PRelationalExpression _relationalExpression_;
    private TLt _lt_;
    private PAdditiveExpression _additiveExpression_;

    public ALtRelationalExpression()
    {
        // Constructor
    }

    public ALtRelationalExpression(
        @SuppressWarnings("hiding") PRelationalExpression _relationalExpression_,
        @SuppressWarnings("hiding") TLt _lt_,
        @SuppressWarnings("hiding") PAdditiveExpression _additiveExpression_)
    {
        // Constructor
        setRelationalExpression(_relationalExpression_);

        setLt(_lt_);

        setAdditiveExpression(_additiveExpression_);

    }

    @Override
    public Object clone()
    {
        return new ALtRelationalExpression(
            cloneNode(this._relationalExpression_),
            cloneNode(this._lt_),
            cloneNode(this._additiveExpression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseALtRelationalExpression(this);
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

    public TLt getLt()
    {
        return this._lt_;
    }

    public void setLt(TLt node)
    {
        if(this._lt_ != null)
        {
            this._lt_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lt_ = node;
    }

    public PAdditiveExpression getAdditiveExpression()
    {
        return this._additiveExpression_;
    }

    public void setAdditiveExpression(PAdditiveExpression node)
    {
        if(this._additiveExpression_ != null)
        {
            this._additiveExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._additiveExpression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._relationalExpression_)
            + toString(this._lt_)
            + toString(this._additiveExpression_);
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

        if(this._lt_ == child)
        {
            this._lt_ = null;
            return;
        }

        if(this._additiveExpression_ == child)
        {
            this._additiveExpression_ = null;
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

        if(this._lt_ == oldChild)
        {
            setLt((TLt) newChild);
            return;
        }

        if(this._additiveExpression_ == oldChild)
        {
            setAdditiveExpression((PAdditiveExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
