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


package org.gello.client.model;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Calendar;

import org.eclipse.core.runtime.FileLocator;

public class Util
{
	/**
	 * Returns a path to the lib folder where the jars are.
	 * 
	 */
	public static String getJarPath(String jarName)
	{
		String pathSeparator = System.getProperty("path.separator");
		String fileSeparator = System.getProperty("file.separator");
		return getRootPath() + "lib" + fileSeparator + jarName + pathSeparator;
	}

	/**
	 * Returns a path to base gello directory.
	 * 
	 */
	public static String getTempPath()
	{
		String fileSeparator = System.getProperty("file.separator");
		
		File f = new File("plugins");

		// if f exists the project is deployed
		if (f.exists())
			return getRootPath() + "org" + fileSeparator + "gello" + fileSeparator + "client" + fileSeparator + "temp" + fileSeparator;
		else
			return getRootPath() + "bin" + fileSeparator + "org" + fileSeparator + "gello" + fileSeparator + "client" + fileSeparator + "temp" + fileSeparator;
	}

	/**
	 * Returns a temporary class name.
	 * 
	 */
	public static String getTempClassName()
	{
		return "TempClazz" + Calendar.getInstance().getTimeInMillis();
	}

	/**
	 * Returns a temporary package name that corresponds to the base gello
	 * directory.
	 * 
	 */
	public static String getTempPackageName()
	{
		return "org.gello.client.temp";
	}

	/**
	 * Returns a path to the root folder for the deployed project.
	 * If the project is not deployed, returns a path to the root folder by getting
	 * Util.class, then model, then client, then gello, then org, then bin, then the root.
	 * 
	 */
	public static String getRootPath()
	{
		String fileSeparator = System.getProperty("file.separator");

		File f = new File("plugins");

		// if f exists it is in its deployed state
		if (f.exists())
			return "plugins" + fileSeparator + "org.gello.client_1.0.0" + fileSeparator;
		else
		{
			String filePath = "";
			try
			{
				URL url = null;
				try
				{
					url = FileLocator.resolve(Util.class.getResource("Util.class"));
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
				filePath = new File(url.toURI()).getParentFile().getParentFile().getParentFile().getParentFile().getParentFile().getParent() + fileSeparator;
			}
			catch (URISyntaxException e)
			{
				e.printStackTrace();
			}
			
			return filePath;
		}
	}

	/**
	 * Loads a class from a file that is on the path of the current loader...
	 * 
	 */
	public static Object loadClassAndReturnInstance(String classname, ClassLoader current)
	{
		try
		{
			Class clazz = Class.forName(classname, true, current);
			Object instance = clazz.newInstance();
			return instance;
		}
		catch (Exception e)
		{
			throw new RuntimeException("The compiled class " + classname + " could not be instantiated.", e);
		}
	}
}
