/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AJoinProperty extends PJoinProperty
{
    private TIdentifier _collection_;
    private TDot _dot_;
    private TIdentifier _member_;

    public AJoinProperty()
    {
        // Constructor
    }

    public AJoinProperty(
        @SuppressWarnings("hiding") TIdentifier _collection_,
        @SuppressWarnings("hiding") TDot _dot_,
        @SuppressWarnings("hiding") TIdentifier _member_)
    {
        // Constructor
        setCollection(_collection_);

        setDot(_dot_);

        setMember(_member_);

    }

    @Override
    public Object clone()
    {
        return new AJoinProperty(
            cloneNode(this._collection_),
            cloneNode(this._dot_),
            cloneNode(this._member_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAJoinProperty(this);
    }

    public TIdentifier getCollection()
    {
        return this._collection_;
    }

    public void setCollection(TIdentifier node)
    {
        if(this._collection_ != null)
        {
            this._collection_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._collection_ = node;
    }

    public TDot getDot()
    {
        return this._dot_;
    }

    public void setDot(TDot node)
    {
        if(this._dot_ != null)
        {
            this._dot_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._dot_ = node;
    }

    public TIdentifier getMember()
    {
        return this._member_;
    }

    public void setMember(TIdentifier node)
    {
        if(this._member_ != null)
        {
            this._member_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._member_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._collection_)
            + toString(this._dot_)
            + toString(this._member_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._collection_ == child)
        {
            this._collection_ = null;
            return;
        }

        if(this._dot_ == child)
        {
            this._dot_ = null;
            return;
        }

        if(this._member_ == child)
        {
            this._member_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._collection_ == oldChild)
        {
            setCollection((TIdentifier) newChild);
            return;
        }

        if(this._dot_ == oldChild)
        {
            setDot((TDot) newChild);
            return;
        }

        if(this._member_ == oldChild)
        {
            setMember((TIdentifier) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
