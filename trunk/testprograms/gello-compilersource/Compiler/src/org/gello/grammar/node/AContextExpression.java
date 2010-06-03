/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AContextExpression extends PContextExpression
{
    private TContext _context_;
    private TIdentifier _identifier_;
    private PConditionalExpression _conditionalExpression_;
    private TIn _in_;
    private PExpression _expression_;
    private TEndcontext _endcontext_;

    public AContextExpression()
    {
        // Constructor
    }

    public AContextExpression(
        @SuppressWarnings("hiding") TContext _context_,
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") PConditionalExpression _conditionalExpression_,
        @SuppressWarnings("hiding") TIn _in_,
        @SuppressWarnings("hiding") PExpression _expression_,
        @SuppressWarnings("hiding") TEndcontext _endcontext_)
    {
        // Constructor
        setContext(_context_);

        setIdentifier(_identifier_);

        setConditionalExpression(_conditionalExpression_);

        setIn(_in_);

        setExpression(_expression_);

        setEndcontext(_endcontext_);

    }

    @Override
    public Object clone()
    {
        return new AContextExpression(
            cloneNode(this._context_),
            cloneNode(this._identifier_),
            cloneNode(this._conditionalExpression_),
            cloneNode(this._in_),
            cloneNode(this._expression_),
            cloneNode(this._endcontext_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAContextExpression(this);
    }

    public TContext getContext()
    {
        return this._context_;
    }

    public void setContext(TContext node)
    {
        if(this._context_ != null)
        {
            this._context_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._context_ = node;
    }

    public TIdentifier getIdentifier()
    {
        return this._identifier_;
    }

    public void setIdentifier(TIdentifier node)
    {
        if(this._identifier_ != null)
        {
            this._identifier_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._identifier_ = node;
    }

    public PConditionalExpression getConditionalExpression()
    {
        return this._conditionalExpression_;
    }

    public void setConditionalExpression(PConditionalExpression node)
    {
        if(this._conditionalExpression_ != null)
        {
            this._conditionalExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._conditionalExpression_ = node;
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

    public TEndcontext getEndcontext()
    {
        return this._endcontext_;
    }

    public void setEndcontext(TEndcontext node)
    {
        if(this._endcontext_ != null)
        {
            this._endcontext_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._endcontext_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._context_)
            + toString(this._identifier_)
            + toString(this._conditionalExpression_)
            + toString(this._in_)
            + toString(this._expression_)
            + toString(this._endcontext_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._context_ == child)
        {
            this._context_ = null;
            return;
        }

        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._conditionalExpression_ == child)
        {
            this._conditionalExpression_ = null;
            return;
        }

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

        if(this._endcontext_ == child)
        {
            this._endcontext_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._context_ == oldChild)
        {
            setContext((TContext) newChild);
            return;
        }

        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._conditionalExpression_ == oldChild)
        {
            setConditionalExpression((PConditionalExpression) newChild);
            return;
        }

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

        if(this._endcontext_ == oldChild)
        {
            setEndcontext((TEndcontext) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
