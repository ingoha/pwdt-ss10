/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class ATypedVarDefinition extends PTypedVarDefinition
{
    private TIdentifier _identifier_;
    private TColon _colon_;
    private PType _type_;
    private TEq _eq_;
    private PExpression _expression_;

    public ATypedVarDefinition()
    {
        // Constructor
    }

    public ATypedVarDefinition(
        @SuppressWarnings("hiding") TIdentifier _identifier_,
        @SuppressWarnings("hiding") TColon _colon_,
        @SuppressWarnings("hiding") PType _type_,
        @SuppressWarnings("hiding") TEq _eq_,
        @SuppressWarnings("hiding") PExpression _expression_)
    {
        // Constructor
        setIdentifier(_identifier_);

        setColon(_colon_);

        setType(_type_);

        setEq(_eq_);

        setExpression(_expression_);

    }

    @Override
    public Object clone()
    {
        return new ATypedVarDefinition(
            cloneNode(this._identifier_),
            cloneNode(this._colon_),
            cloneNode(this._type_),
            cloneNode(this._eq_),
            cloneNode(this._expression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseATypedVarDefinition(this);
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

    public TColon getColon()
    {
        return this._colon_;
    }

    public void setColon(TColon node)
    {
        if(this._colon_ != null)
        {
            this._colon_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._colon_ = node;
    }

    public PType getType()
    {
        return this._type_;
    }

    public void setType(PType node)
    {
        if(this._type_ != null)
        {
            this._type_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._type_ = node;
    }

    public TEq getEq()
    {
        return this._eq_;
    }

    public void setEq(TEq node)
    {
        if(this._eq_ != null)
        {
            this._eq_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._eq_ = node;
    }

    public PExpression getExpression()
    {
        return this._expression_;
    }

    public void setExpression(PExpression node)
    {
        if(this._expression_ != null)
        {
            this._expression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._expression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._identifier_)
            + toString(this._colon_)
            + toString(this._type_)
            + toString(this._eq_)
            + toString(this._expression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._identifier_ == child)
        {
            this._identifier_ = null;
            return;
        }

        if(this._colon_ == child)
        {
            this._colon_ = null;
            return;
        }

        if(this._type_ == child)
        {
            this._type_ = null;
            return;
        }

        if(this._eq_ == child)
        {
            this._eq_ = null;
            return;
        }

        if(this._expression_ == child)
        {
            this._expression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._identifier_ == oldChild)
        {
            setIdentifier((TIdentifier) newChild);
            return;
        }

        if(this._colon_ == oldChild)
        {
            setColon((TColon) newChild);
            return;
        }

        if(this._type_ == oldChild)
        {
            setType((PType) newChild);
            return;
        }

        if(this._eq_ == oldChild)
        {
            setEq((TEq) newChild);
            return;
        }

        if(this._expression_ == oldChild)
        {
            setExpression((PExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
