/*******************************************************************************
 * Copyright (c) 2006, 2007 Pfizer, Inc.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *   Jacob Brauer (WebReach, Inc.) - initial implementation
 *******************************************************************************/


package org.gello.model.sample;

/**
 *
 * @author Administrator
 */
public class Allergy {
    private String code;
    private DateInterval effectiveTime;
    private String reaction;
    private int severity;
    
    /** Creates a new instance of Allergy */
    public Allergy() {
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DateInterval getEffectiveTime() {
        return effectiveTime;
    }

    public void setEffectiveTime(DateInterval effectiveTime) {
        this.effectiveTime = effectiveTime;
    }

    public String getReaction() {
        return reaction;
    }

    public void setReaction(String reaction) {
        this.reaction = reaction;
    }

    public int getSeverity() {
        return severity;
    }

    public void setSeverity(int severity) {
        this.severity = severity;
    }
    
}
