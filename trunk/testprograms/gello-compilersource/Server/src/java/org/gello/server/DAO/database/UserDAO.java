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


package org.gello.server.DAO.database;

import java.util.List;
import org.gello.server.model.User;

public interface UserDAO
{
    public User getUser(String username);
    public User updateUser(User user);
    public List<User> getUserList();
    public boolean removeUser(User user);
}
