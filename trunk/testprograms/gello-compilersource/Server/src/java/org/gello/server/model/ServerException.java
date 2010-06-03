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

public class ServerException extends Exception
{
    public ServerException(Throwable cause)
    {
        super(cause);
    }
    
    public ServerException(String message, Throwable cause)
    {
        super(message, cause);
    }
    
    public ServerException(String message)
    {
        super(message);
    }
}
