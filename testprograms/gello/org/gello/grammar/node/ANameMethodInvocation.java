/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ANameMethodInvocation extends PMethodInvocation
{
    private PName _name_;
    private TLParen _lParen_;
    private PArgumentList _argumentList_;
    private TRParen _rParen_;

    public ANameMethodInvocation()
    {
        // Constructor
    }

    public ANameMethodInvocation(
        @SuppressWarnings("hiding") PName _name_,
        @SuppressWarnings("hiding") TLParen _lParen_,
        @SuppressWarnings("hiding") PArgumentList _argumentList_,
        @SuppressWarnings("hiding") TRParen _rParen_)
    {
        // Constructor
        setName(_name_);

        setLParen(_lParen_);

        setArgumentList(_argumentList_);

        setRParen(_rParen_);

    }

    @Override
    public Object clone()
    {
        return new ANameMethodInvocation(
            cloneNode(this._name_),
            cloneNode(this._lParen_),
            cloneNode(this._argumentList_),
            cloneNode(this._rParen_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseANameMethodInvocation(this);
    }

    public PName getName()
    {
        return this._name_;
    }

    public void setName(PName node)
    {
        if(this._name_ != null)
        {
            this._name_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._name_ = node;
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

    public PArgumentList getArgumentList()
    {
        return this._argumentList_;
    }

    public void setArgumentList(PArgumentList node)
    {
        if(this._argumentList_ != null)
        {
            this._argumentList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._argumentList_ = node;
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
            + toString(this._name_)
            + toString(this._lParen_)
            + toString(this._argumentList_)
            + toString(this._rParen_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._name_ == child)
        {
            this._name_ = null;
            return;
        }

        if(this._lParen_ == child)
        {
            this._lParen_ = null;
            return;
        }

        if(this._argumentList_ == child)
        {
            this._argumentList_ = null;
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
        if(this._name_ == oldChild)
        {
            setName((PName) newChild);
            return;
        }

        if(this._lParen_ == oldChild)
        {
            setLParen((TLParen) newChild);
            return;
        }

        if(this._argumentList_ == oldChild)
        {
            setArgumentList((PArgumentList) newChild);
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
