/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AMinMultiplicativeExpression extends PMultiplicativeExpression
{
    private PMultiplicativeExpression _multiplicativeExpression_;
    private TMin _min_;
    private PUnaryExpression _unaryExpression_;

    public AMinMultiplicativeExpression()
    {
        // Constructor
    }

    public AMinMultiplicativeExpression(
        @SuppressWarnings("hiding") PMultiplicativeExpression _multiplicativeExpression_,
        @SuppressWarnings("hiding") TMin _min_,
        @SuppressWarnings("hiding") PUnaryExpression _unaryExpression_)
    {
        // Constructor
        setMultiplicativeExpression(_multiplicativeExpression_);

        setMin(_min_);

        setUnaryExpression(_unaryExpression_);

    }

    @Override
    public Object clone()
    {
        return new AMinMultiplicativeExpression(
            cloneNode(this._multiplicativeExpression_),
            cloneNode(this._min_),
            cloneNode(this._unaryExpression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAMinMultiplicativeExpression(this);
    }

    public PMultiplicativeExpression getMultiplicativeExpression()
    {
        return this._multiplicativeExpression_;
    }

    public void setMultiplicativeExpression(PMultiplicativeExpression node)
    {
        if(this._multiplicativeExpression_ != null)
        {
            this._multiplicativeExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._multiplicativeExpression_ = node;
    }

    public TMin getMin()
    {
        return this._min_;
    }

    public void setMin(TMin node)
    {
        if(this._min_ != null)
        {
            this._min_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._min_ = node;
    }

    public PUnaryExpression getUnaryExpression()
    {
        return this._unaryExpression_;
    }

    public void setUnaryExpression(PUnaryExpression node)
    {
        if(this._unaryExpression_ != null)
        {
            this._unaryExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._unaryExpression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._multiplicativeExpression_)
            + toString(this._min_)
            + toString(this._unaryExpression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._multiplicativeExpression_ == child)
        {
            this._multiplicativeExpression_ = null;
            return;
        }

        if(this._min_ == child)
        {
            this._min_ = null;
            return;
        }

        if(this._unaryExpression_ == child)
        {
            this._unaryExpression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._multiplicativeExpression_ == oldChild)
        {
            setMultiplicativeExpression((PMultiplicativeExpression) newChild);
            return;
        }

        if(this._min_ == oldChild)
        {
            setMin((TMin) newChild);
            return;
        }

        if(this._unaryExpression_ == oldChild)
        {
            setUnaryExpression((PUnaryExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}