/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ASingleJoinPropertyList extends PJoinPropertyList
{
    private PJoinProperty _joinProperty_;

    public ASingleJoinPropertyList()
    {
        // Constructor
    }

    public ASingleJoinPropertyList(
        @SuppressWarnings("hiding") PJoinProperty _joinProperty_)
    {
        // Constructor
        setJoinProperty(_joinProperty_);

    }

    @Override
    public Object clone()
    {
        return new ASingleJoinPropertyList(
            cloneNode(this._joinProperty_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseASingleJoinPropertyList(this);
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
            + toString(this._joinProperty_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
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
        if(this._joinProperty_ == oldChild)
        {
            setJoinProperty((PJoinProperty) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
