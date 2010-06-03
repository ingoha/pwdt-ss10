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

public enum EmailAddressType {
    FIRST(1),
    SECOND(2),
    THIRD(3),
    ORGANIZATION_CONTACT(4);

    private final int dbKey;

    EmailAddressType(int dbKey) {
        this.dbKey = dbKey;
    }

    int getDbKey() {
        return dbKey;
    }

    static EmailAddressType getTypeFromKey(int key) {
        for (EmailAddressType et : EmailAddressType.values()) {
            if (et.getDbKey() == key) {
                return et;
            }
        }
        throw new IllegalArgumentException("invalid key: " + key);
    }
}
