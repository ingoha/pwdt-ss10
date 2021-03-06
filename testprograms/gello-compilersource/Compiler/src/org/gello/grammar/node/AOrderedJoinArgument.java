/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AOrderedJoinArgument extends PJoinArgument
{
    private PJoinCollectionList _joinCollectionList_;
    private TSemicolon _s1_;
    private PJoinPropertyList _joinPropertyList_;
    private TSemicolon _s2_;
    private PExpression _filter_;
    private TSemicolon _s3_;
    private PExpression _order_;

    public AOrderedJoinArgument()
    {
        // Constructor
    }

    public AOrderedJoinArgument(
        @SuppressWarnings("hiding") PJoinCollectionList _joinCollectionList_,
        @SuppressWarnings("hiding") TSemicolon _s1_,
        @SuppressWarnings("hiding") PJoinPropertyList _joinPropertyList_,
        @SuppressWarnings("hiding") TSemicolon _s2_,
        @SuppressWarnings("hiding") PExpression _filter_,
        @SuppressWarnings("hiding") TSemicolon _s3_,
        @SuppressWarnings("hiding") PExpression _order_)
    {
        // Constructor
        setJoinCollectionList(_joinCollectionList_);

        setS1(_s1_);

        setJoinPropertyList(_joinPropertyList_);

        setS2(_s2_);

        setFilter(_filter_);

        setS3(_s3_);

        setOrder(_order_);

    }

    @Override
    public Object clone()
    {
        return new AOrderedJoinArgument(
            cloneNode(this._joinCollectionList_),
            cloneNode(this._s1_),
            cloneNode(this._joinPropertyList_),
            cloneNode(this._s2_),
            cloneNode(this._filter_),
            cloneNode(this._s3_),
            cloneNode(this._order_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAOrderedJoinArgument(this);
    }

    public PJoinCollectionList getJoinCollectionList()
    {
        return this._joinCollectionList_;
    }

    public void setJoinCollectionList(PJoinCollectionList node)
    {
        if(this._joinCollectionList_ != null)
        {
            this._joinCollectionList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._joinCollectionList_ = node;
    }

    public TSemicolon getS1()
    {
        return this._s1_;
    }

    public void setS1(TSemicolon node)
    {
        if(this._s1_ != null)
        {
            this._s1_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._s1_ = node;
    }

    public PJoinPropertyList getJoinPropertyList()
    {
        return this._joinPropertyList_;
    }

    public void setJoinPropertyList(PJoinPropertyList node)
    {
        if(this._joinPropertyList_ != null)
        {
            this._joinPropertyList_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._joinPropertyList_ = node;
    }

    public TSemicolon getS2()
    {
        return this._s2_;
    }

    public void setS2(TSemicolon node)
    {
        if(this._s2_ != null)
        {
            this._s2_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._s2_ = node;
    }

    public PExpression getFilter()
    {
        return this._filter_;
    }

    public void setFilter(PExpression node)
    {
        if(this._filter_ != null)
        {
            this._filter_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._filter_ = node;
    }

    public TSemicolon getS3()
    {
        return this._s3_;
    }

    public void setS3(TSemicolon node)
    {
        if(this._s3_ != null)
        {
            this._s3_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._s3_ = node;
    }

    public PExpression getOrder()
    {
        return this._order_;
    }

    public void setOrder(PExpression node)
    {
        if(this._order_ != null)
        {
            this._order_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._order_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._joinCollectionList_)
            + toString(this._s1_)
            + toString(this._joinPropertyList_)
            + toString(this._s2_)
            + toString(this._filter_)
            + toString(this._s3_)
            + toString(this._order_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._joinCollectionList_ == child)
        {
            this._joinCollectionList_ = null;
            return;
        }

        if(this._s1_ == child)
        {
            this._s1_ = null;
            return;
        }

        if(this._joinPropertyList_ == child)
        {
            this._joinPropertyList_ = null;
            return;
        }

        if(this._s2_ == child)
        {
            this._s2_ = null;
            return;
        }

        if(this._filter_ == child)
        {
            this._filter_ = null;
            return;
        }

        if(this._s3_ == child)
        {
            this._s3_ = null;
            return;
        }

        if(this._order_ == child)
        {
            this._order_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._joinCollectionList_ == oldChild)
        {
            setJoinCollectionList((PJoinCollectionList) newChild);
            return;
        }

        if(this._s1_ == oldChild)
        {
            setS1((TSemicolon) newChild);
            return;
        }

        if(this._joinPropertyList_ == oldChild)
        {
            setJoinPropertyList((PJoinPropertyList) newChild);
            return;
        }

        if(this._s2_ == oldChild)
        {
            setS2((TSemicolon) newChild);
            return;
        }

        if(this._filter_ == oldChild)
        {
            setFilter((PExpression) newChild);
            return;
        }

        if(this._s3_ == oldChild)
        {
            setS3((TSemicolon) newChild);
            return;
        }

        if(this._order_ == oldChild)
        {
            setOrder((PExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
