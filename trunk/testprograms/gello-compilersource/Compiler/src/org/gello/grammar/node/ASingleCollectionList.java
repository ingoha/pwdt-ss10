/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ASingleCollectionList extends PCollectionList
{
    private PCollectionLiteralArgument _collectionLiteralArgument_;

    public ASingleCollectionList()
    {
        // Constructor
    }

    public ASingleCollectionList(
        @SuppressWarnings("hiding") PCollectionLiteralArgument _collectionLiteralArgument_)
    {
        // Constructor
        setCollectionLiteralArgument(_collectionLiteralArgument_);

    }

    @Override
    public Object clone()
    {
        return new ASingleCollectionList(
            cloneNode(this._collectionLiteralArgument_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleCollectionList(this);
    }

    public PCollectionLiteralArgument getCollectionLiteralArgument()
    {
        return this._collectionLiteralArgument_;
    }

    public void setCollectionLiteralArgument(PCollectionLiteralArgument node)
    {
        if(this._collectionLiteralArgument_ != null)
        {
            this._collectionLiteralArgument_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._collectionLiteralArgument_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._collectionLiteralArgument_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._collectionLiteralArgument_ == child)
        {
            this._collectionLiteralArgument_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._collectionLiteralArgument_ == oldChild)
        {
            setCollectionLiteralArgument((PCollectionLiteralArgument) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}