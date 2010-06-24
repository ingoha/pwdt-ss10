/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AInClause extends PInClause
{
    private TIn _in_;
    private PExpression _expression_;

    public AInClause()
    {
        // Constructor
    }

    public AInClause(
        @SuppressWarnings("hiding") TIn _in_,
        @SuppressWarnings("hiding") PExpression _expression_)
    {
        // Constructor
        setIn(_in_);

        setExpression(_expression_);

    }

    @Override
    public Object clone()
    {
        return new AInClause(
            cloneNode(this._in_),
            cloneNode(this._expression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAInClause(this);
    }

    public TIn getIn()
    {
        return this._in_;
    }

    public void setIn(TIn node)
    {
        if(this._in_ != null)
        {
            this._in_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._in_ = node;
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._in_)
            + toString(this._expression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._in_ == child)
        {
            this._in_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._in_ == oldChild)
        {
            setIn((TIn) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}