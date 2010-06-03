/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ASingleJoinCollectionList extends PJoinCollectionList
{
    private PJoinCollection _joinCollection_;

    public ASingleJoinCollectionList()
    {
        // Constructor
    }

    public ASingleJoinCollectionList(
        @SuppressWarnings("hiding") PJoinCollection _joinCollection_)
    {
        // Constructor
        setJoinCollection(_joinCollection_);

    }

    @Override
    public Object clone()
    {
        return new ASingleJoinCollectionList(
            cloneNode(this._joinCollection_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleJoinCollectionList(this);
    }

    public PJoinCollection getJoinCollection()
    {
        return this._joinCollection_;
    }

    public void setJoinCollection(PJoinCollection node)
    {
        if(this._joinCollection_ != null)
        {
            this._joinCollection_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._joinCollection_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._joinCollection_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._joinCollection_ == child)
        {
            this._joinCollection_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._joinCollection_ == oldChild)
        {
            setJoinCollection((PJoinCollection) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
