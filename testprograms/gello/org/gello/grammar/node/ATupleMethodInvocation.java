/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ATupleMethodInvocation extends PMethodInvocation
{
    private PPrimary _primary_;
    private TArrow _arrow_;
    private TIdentifier _identifier_;
    private TLParen _lParen_;
    private PJoinArgument _joinArgument_;
    private TRParen _rParen_;

    public ATupleMethodInvocation()
    {
        // Constructor
    }

    public ATupleMethodInvocation(
        @SuppressWarnings("hiding") PPrimary _primary_,
        @SuppressWarnings("hiding") TArrow _arrow_,
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TLParen _lParen_,
        @SuppressWarnings("hiding") PJoinArgument _joinArgument_,
        @SuppressWarnings("hiding") TRParen _rParen_)
    {
        // Constructor
        setPrimary(_primary_);

        setArrow(_arrow_);

        setIdentifier(_identifier_);

        setLParen(_lParen_);

        setJoinArgument(_joinArgument_);

        setRParen(_rParen_);

    }

    @Override
    public Object clone()
    {
        return new ATupleMethodInvocation(
            cloneNode(this._primary_),
            cloneNode(this._arrow_),
            cloneNode(this._identifier_),
            cloneNode(this._lParen_),
            cloneNode(this._joinArgument_),
            cloneNode(this._rParen_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATupleMethodInvocation(this);
    }

    public PPrimary getPrimary()
    {
        return this._primary_;
    }

    public void setPrimary(PPrimary node)
    {
        if(this._primary_ != null)
        {
            this._primary_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._primary_ = node;
    }

    public TArrow getArrow()
    {
        return this._arrow_;
    }

    public void setArrow(TArrow node)
    {
        if(this._arrow_ != null)
        {
            this._arrow_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._arrow_ = node;
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

    public TLParen getLParen()
    {
        return this._lParen_;
    }

    public void setLParen(TLParen node)
    {
        if(this._lParen_ != null)
        {
            this._lParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lParen_ = node;
    }

    public PJoinArgument getJoinArgument()
    {
        return this._joinArgument_;
    }

    public void setJoinArgument(PJoinArgument node)
    {
        if(this._joinArgument_ != null)
        {
            this._joinArgument_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._joinArgument_ = node;
    }

    public TRParen getRParen()
    {
        return this._rParen_;
    }

    public void setRParen(TRParen node)
    {
        if(this._rParen_ != null)
        {
            this._rParen_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rParen_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._primary_)
            + toString(this._arrow_)
            + toString(this._identifier_)
            + toString(this._lParen_)
            + toString(this._joinArgument_)
            + toString(this._rParen_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._primary_ == child)
        {
            this._primary_ = null;
            return;
        }

        if(this._arrow_ == child)
        {
            this._arrow_ = null;
            return;
        }

        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._lParen_ == child)
        {
            this._lParen_ = null;
            return;
        }

        if(this._joinArgument_ == child)
        {
            this._joinArgument_ = null;
            return;
        }

        if(this._rParen_ == child)
        {
            this._rParen_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._primary_ == oldChild)
        {
            setPrimary((PPrimary) newChild);
            return;
        }

        if(this._arrow_ == oldChild)
        {
            setArrow((TArrow) newChild);
            return;
        }

        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._lParen_ == oldChild)
        {
            setLParen((TLParen) newChild);
            return;
        }

        if(this._joinArgument_ == oldChild)
        {
            setJoinArgument((PJoinArgument) newChild);
            return;
        }

        if(this._rParen_ == oldChild)
        {
            setRParen((TRParen) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
