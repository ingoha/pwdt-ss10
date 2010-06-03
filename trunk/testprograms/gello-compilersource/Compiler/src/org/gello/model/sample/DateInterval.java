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

import java.util.Date;

/**
 *
 * @author Administrator
 */
public class DateInterval {
    private Date low;
    private Date high;
    
    /** Creates a new instance of DateInterval */
    public DateInterval() {
    }

    public Date getLow() {
        return low;
    }

    public void setLow(Date low) {
        this.low = low;
    }

    public Date getHigh() {
        return high;
    }

    public void setHigh(Date high) {
        this.high = high;
    }
    
}
