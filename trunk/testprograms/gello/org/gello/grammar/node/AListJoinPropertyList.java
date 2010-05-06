/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AListJoinPropertyList extends PJoinPropertyList
{
    private PJoinPropertyList _joinPropertyList_;
    private TComma _comma_;
    private PJoinProperty _joinProperty_;

    public AListJoinPropertyList()
    {
        // Constructor
    }

    public AListJoinPropertyList(
        @SuppressWarnings("hiding") PJoinPropertyList _joinPropertyList_,
        @SuppressWarnings("hiding") TComma _comma_,
        @SuppressWarnings("hiding") PJoinProperty _joinProperty_)
    {
        // Constructor
        setJoinPropertyList(_joinPropertyList_);

        setComma(_comma_);

        setJoinProperty(_joinProperty_);

    }

    @Override
    public Object clone()
    {
        return new AListJoinPropertyList(
            cloneNode(this._joinPropertyList_),
            cloneNode(this._comma_),
            cloneNode(this._joinProperty_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAListJoinPropertyList(this);
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

    public TComma getComma()
    {
        return this._comma_;
    }

    public void setComma(TComma node)
    {
        if(this._comma_ != null)
        {
            this._comma_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._comma_ = node;
    }

    public PJoinProperty getJoinProperty()
    {
        return this._joinProperty_;
    }

    public void setJoinProperty(PJoinProperty node)
    {
        if(this._joinProperty_ != null)
        {
            this._joinProperty_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._joinProperty_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._joinPropertyList_)
            + toString(this._comma_)
            + toString(this._joinProperty_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._joinPropertyList_ == child)
        {
            this._joinPropertyList_ = null;
            return;
        }

        if(this._comma_ == child)
        {
            this._comma_ = null;
            return;
        }

        if(this._joinProperty_ == child)
        {
            this._joinProperty_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._joinPropertyList_ == oldChild)
        {
            setJoinPropertyList((PJoinPropertyList) newChild);
            return;
        }

        if(this._comma_ == oldChild)
        {
            setComma((TComma) newChild);
            return;
        }

        if(this._joinProperty_ == oldChild)
        {
            setJoinProperty((PJoinProperty) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}