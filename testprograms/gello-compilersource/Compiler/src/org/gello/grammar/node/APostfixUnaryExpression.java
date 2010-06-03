/* This file was generated by SableCC (http://www.sablecc.org/). */

package org.gello.grammar.node;

import org.gello.grammar.analysis.*;

@SuppressWarnings("nls")
public final class APostfixUnaryExpression extends PUnaryExpression
{
    private PPostfixExpression _postfixExpression_;

    public APostfixUnaryExpression()
    {
        // Constructor
    }

    public APostfixUnaryExpression(
        @SuppressWarnings("hiding") PPostfixExpression _postfixExpression_)
    {
        // Constructor
        setPostfixExpression(_postfixExpression_);

    }

    @Override
    public Object clone()
    {
        return new APostfixUnaryExpression(
            cloneNode(this._postfixExpression_));
    }

    public void apply(Switch sw)
    {
        ((Analysis) sw).caseAPostfixUnaryExpression(this);
    }

    public PPostfixExpression getPostfixExpression()
    {
        return this._postfixExpression_;
    }

    public void setPostfixExpression(PPostfixExpression node)
    {
        if(this._postfixExpression_ != null)
        {
            this._postfixExpression_.parent(null);
        }

        if(node != null)
        {
            if(node.parent() != null)
            {
                node.parent().removeChild(node);
            }

            node.parent(this);
        }

        this._postfixExpression_ = node;
    }

    @Override
    public String toString()
    {
        return ""
            + toString(this._postfixExpression_);
    }

    @Override
    void removeChild(@SuppressWarnings("unused") Node child)
    {
        // Remove child
        if(this._postfixExpression_ == child)
        {
            this._postfixExpression_ = null;
            return;
        }

        throw new RuntimeException("Not a child.");
    }

    @Override
    void replaceChild(@SuppressWarnings("unused") Node oldChild, @SuppressWarnings("unused") Node newChild)
    {
        // Replace child
        if(this._postfixExpression_ == oldChild)
        {
            setPostfixExpression((PPostfixExpression) newChild);
            return;
        }

        throw new RuntimeException("Not a child.");
    }
}
