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

public enum CertifiableExpressionType {

    DRUG_SPL_EXPRESSIONS(new CertifiableExpression(1, "Drug SPL Expressions")),
    CLINICAL_GUIDELINE_EXPRESSIONS(new CertifiableExpression(2, "Clinical Guideline Expressions")),
    CLINICAL_TRIAL_EXPRESSIONS(new CertifiableExpression(3, "Clinical Trial Expressions")),
    POC_DECISION_SUPPORT_EXPRESSIONS(new CertifiableExpression(4, "POC Decision Support Expressions"));

    private CertifiableExpression certifiableExpression;

    CertifiableExpressionType(CertifiableExpression certifiableExpression) {        
        this.certifiableExpression = certifiableExpression;
    }

    public CertifiableExpression getCertifiableExpression() {
        return certifiableExpression;
    }

    public static CertifiableExpressionType getTypeFromCertifiableExpression(CertifiableExpression key) {
        for (CertifiableExpressionType cet : CertifiableExpressionType.values()) {
            if (cet.getCertifiableExpression().equals(key)) {
                return cet;
            }
        }
        throw new IllegalArgumentException("invalid key: " + key);
    }
}
