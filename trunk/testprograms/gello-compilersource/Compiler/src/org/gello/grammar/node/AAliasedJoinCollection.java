/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AAliasedJoinCollection extends PJoinCollection
{
    private TIdentifier _alias_;
    private TIn _in_;
    private TIdentifier _collection_;

    public AAliasedJoinCollection()
    {
        // Constructor
    }

    public AAliasedJoinCollection(
        @SuppressWarnings("hiding") TIdentifier _alias_,
        @SuppressWarnings("hiding") TIn _in_,
        @SuppressWarnings("hiding") TIdentifier _collection_)
    {
        // Constructor
        setAlias(_alias_);

        setIn(_in_);

        setCollection(_collection_);

    }

    @Override
    public Object clone()
    {
        return new AAliasedJoinCollection(
            cloneNode(this._alias_),
            cloneNode(this._in_),
            cloneNode(this._collection_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAAliasedJoinCollection(this);
    }

    public TIdentifier getAlias()
    {
        return this._alias_;
    }

    public void setAlias(TIdentifier node)
    {
        if(this._alias_ != null)
        {
            this._alias_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._alias_ = node;
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

    @Override
    public String toString()
    {
        return ""
            + toString(this._alias_)
            + toString(this._in_)
            + toString(this._collection_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._alias_ == child)
        {
            this._alias_ = null;
            return;
        }

        if(this._in_ == child)
        {
            this._in_ = null;
            return;
        }

        if(this._collection_ == child)
        {
            this._collection_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._alias_ == oldChild)
        {
            setAlias((TIdentifier) newChild);
            return;
        }

        if(this._in_ == oldChild)
        {
            setIn((TIn) newChild);
            return;
        }

        if(this._collection_ == oldChild)
        {
            setCollection((TIdentifier) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
