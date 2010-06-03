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


package org.gello.server.model;

public enum PhoneNumberType {
    HOME(1),
    WORK(2),
    MOBILE(3),
    ORGANIZATION_CONTACT(4);

    private final int dbKey;
    PhoneNumberType(int dbKey) {
        this.dbKey = dbKey;
    }

    public int getDbKey() {
        return dbKey;
    }

    public static PhoneNumberType getTypeFromKey(int key) {
        for (PhoneNumberType pn : PhoneNumberType.values()) {
            if (pn.getDbKey() == key) {
                return pn;
            }
        }
        throw new IllegalArgumentException("invalid key: " + key);
    }
}
