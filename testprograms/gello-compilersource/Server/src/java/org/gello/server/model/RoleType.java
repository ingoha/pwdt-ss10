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

public enum RoleType {
    SYSTEM_ADMINISTRATOR(new Role(1, "system adminstrator")),
    DEMONSTRATOR(new Role(2, "demonstrator")),
    EXPRESSION_AUTHOR(new Role(3, "expression author")),
    EXPRESSION_TEST_CASE_AUTHOR(new Role(4, "expression test case author")),
    CIE_AUTHOR(new Role(5, "cie author")),
    CIE_TEST_CASE_AUTHOR(new Role(6, "cie test case author")),
    INTERNAL_APPROVER(new Role(7, "internal approver")),
    EXTERNAL_CERTIFIER(new Role(8, "external certifier")),
    PROJECT_MANAGER(new Role(9, "project manager"));

    private Role role;

    RoleType(Role role) {
        this.role = role;
    }

    public Role getRole() {
        return role;
    }

    public static RoleType getRoleTypeFromRole(Role role) {
        for (RoleType rt : RoleType.values()) {
            if (rt.getRole().equals(role)) {
                return rt;
            }
        }
        throw new IllegalArgumentException("invalid key: " + role);
    }

}
