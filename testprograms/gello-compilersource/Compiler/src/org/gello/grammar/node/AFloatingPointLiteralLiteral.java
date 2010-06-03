/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class AFloatingPointLiteralLiteral extends PLiteral
{
    private TFloatingPointLiteral _floatingPointLiteral_;

    public AFloatingPointLiteralLiteral()
    {
        // Constructor
    }

    public AFloatingPointLiteralLiteral(
        @SuppressWarnings("hiding") TFloatingPointLiteral _floatingPointLiteral_)
    {
        // Constructor
        setFloatingPointLiteral(_floatingPointLiteral_);

    }

    @Override
    public Object clone()
    {
        return new AFloatingPointLiteralLiteral(
            cloneNode(this._floatingPointLiteral_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAFloatingPointLiteralLiteral(this);
    }

    public TFloatingPointLiteral getFloatingPointLiteral()
    {
        return this._floatingPointLiteral_;
    }

    public void setFloatingPointLiteral(TFloatingPointLiteral node)
    {
        if(this._floatingPointLiteral_ != null)
        {
            this._floatingPointLiteral_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._floatingPointLiteral_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._floatingPointLiteral_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._floatingPointLiteral_ == child)
        {
            this._floatingPointLiteral_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._floatingPointLiteral_ == oldChild)
        {
            setFloatingPointLiteral((TFloatingPointLiteral) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
