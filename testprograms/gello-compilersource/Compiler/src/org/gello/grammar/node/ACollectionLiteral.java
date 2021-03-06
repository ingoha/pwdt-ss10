/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ACollectionLiteral extends PCollectionLiteral
{
    private PCollectionType _collectionType_;
    private TLBrace _lBrace_;
    private PCollectionList _collectionList_;
    private TRBrace _rBrace_;

    public ACollectionLiteral()
    {
        // Constructor
    }

    public ACollectionLiteral(
        @SuppressWarnings("hiding") PCollectionType _collectionType_,
        @SuppressWarnings("hiding") TLBrace _lBrace_,
        @SuppressWarnings("hiding") PCollectionList _collectionList_,
        @SuppressWarnings("hiding") TRBrace _rBrace_)
    {
        // Constructor
        setCollectionType(_collectionType_);

        setLBrace(_lBrace_);

        setCollectionList(_collectionList_);

        setRBrace(_rBrace_);

    }

    @Override
    public Object clone()
    {
        return new ACollectionLiteral(
            cloneNode(this._collectionType_),
            cloneNode(this._lBrace_),
            cloneNode(this._collectionList_),
            cloneNode(this._rBrace_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseACollectionLiteral(this);
    }

    public PCollectionType getCollectionType()
    {
        return this._collectionType_;
    }

    public void setCollectionType(PCollectionType node)
    {
        if(this._collectionType_ != null)
        {
            this._collectionType_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._collectionType_ = node;
    }

    public TLBrace getLBrace()
    {
        return this._lBrace_;
    }

    public void setLBrace(TLBrace node)
    {
        if(this._lBrace_ != null)
        {
            this._lBrace_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._lBrace_ = node;
    }

    public PCollectionList getCollectionList()
    {
        return this._collectionList_;
    }

    public void setCollectionList(PCollectionList node)
    {
        if(this._collectionList_ != null)
        {
            this._collectionList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._collectionList_ = node;
    }

    public TRBrace getRBrace()
    {
        return this._rBrace_;
    }

    public void setRBrace(TRBrace node)
    {
        if(this._rBrace_ != null)
        {
            this._rBrace_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._rBrace_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._collectionType_)
            + toString(this._lBrace_)
            + toString(this._collectionList_)
            + toString(this._rBrace_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._collectionType_ == child)
        {
            this._collectionType_ = null;
            return;
        }

        if(this._lBrace_ == child)
        {
            this._lBrace_ = null;
            return;
        }

        if(this._collectionList_ == child)
        {
            this._collectionList_ = null;
            return;
        }

        if(this._rBrace_ == child)
        {
            this._rBrace_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._collectionType_ == oldChild)
        {
            setCollectionType((PCollectionType) newChild);
            return;
        }

        if(this._lBrace_ == oldChild)
        {
            setLBrace((TLBrace) newChild);
            return;
        }

        if(this._collectionList_ == oldChild)
        {
            setCollectionList((PCollectionList) newChild);
            return;
        }

        if(this._rBrace_ == oldChild)
        {
            setRBrace((TRBrace) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
