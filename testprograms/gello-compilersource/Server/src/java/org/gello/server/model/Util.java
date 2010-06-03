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

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 *
 */
public class Util
{
    
    private static final String TEMPLATES = "templates";
    
    public static GelloNode readXMLTemplate(String template, String type)
    {
        String fileName = template + "_" + type + ".xml";
        StringBuffer xml = new StringBuffer();
        Scanner scanner = null;
        try
        {
            String separator = System.getProperty("file.separator");
            URI classURI = new URI(Util.class.getResource("Util.class").toString());
            String filePath = new File(classURI).getParentFile().getParent() + separator + TEMPLATES + separator + fileName;
            scanner = new Scanner(new File(filePath));
            
            while(scanner.hasNextLine())
                xml.append(scanner.nextLine() + "\n");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (URISyntaxException e)
        {
            e.printStackTrace();
        }
        
        ObjectXMLSerializer serializer = new ObjectXMLSerializer();
        GelloNode projectNode = (GelloNode)serializer.fromXML(xml.toString());
        
        return projectNode;
    }
    
    public static void changeNodeName(GelloNode projectNode, String newNodeName)
    {
        projectNode.setName(newNodeName);
        changeNodeNameHelper(projectNode, newNodeName);
    }
    
    private static void changeNodeNameHelper(GelloNode projectNode, String newNodeName)
    {
        String path = projectNode.getPath();
        int index = path.indexOf('/', 1);
        if (index == -1)
            index = path.length();
        String oldName = path.substring(1,index);

        // Replace the "name" part of the path
        projectNode.setPath(path.replaceFirst(oldName, newNodeName));
        
        for (int i = 0; i < projectNode.getChildren().size(); i++)
            changeNodeNameHelper(projectNode.getChildren().get(i), newNodeName);
    }
    
    public static void prefixPaths(GelloNode gelloNode, String prefix)
    {
        gelloNode.setPath(prefix + gelloNode.getPath());
        for (int i = 0; i < gelloNode.getChildren().size(); i++)
            prefixPaths(gelloNode.getChildren().get(i), prefix);
    }
    
    public static void removeTemplateMetaChars(GelloNode gelloNode)
    {
        if (gelloNode.getName().indexOf("*") != -1)
            gelloNode.setName(gelloNode.getName().replaceAll("\\*", ""));
        
        if (gelloNode.getPath().indexOf("*") != -1)
            gelloNode.setPath(gelloNode.getPath().replaceAll("\\*", ""));
        
        for (int i = 0; i < gelloNode.getChildren().size(); i++)
            removeTemplateMetaChars(gelloNode.getChildren().get(i));
    }
    
    public static void buildNodeActions(GelloNode gelloNode, String projectTemplate)
    {
        GelloNode templateNode = Util.readXMLTemplate(projectTemplate, "Project");
        buildNodeActionsHelper(gelloNode, templateNode, false);
        
    }
    
    private static void buildNodeActionsHelper(GelloNode gelloNode, GelloNode templateNode, boolean useDefaultActions)
    {
        if (useDefaultActions)
        {
            ArrayList<String> defaultMenuActions = new ArrayList<String>();
            defaultMenuActions.add("org.gello.client.actions.contextMenu.OpenFile");
            defaultMenuActions.add("org.gello.client.actions.contextMenu.DeleteFile");
            defaultMenuActions.add("org.gello.client.actions.contextMenu.VersionInfo");
            gelloNode.setMenuActions(defaultMenuActions);
            
            ArrayList<String> defaultEditorActions = new ArrayList<String>();
            defaultEditorActions.add("org.gello.client.actions.fileEditor.DeleteFile");
            defaultEditorActions.add("org.gello.client.actions.fileEditor.VersionInfo");
            gelloNode.setEditorActions(defaultEditorActions);
            
            gelloNode.setDeletable(true);
            
        }
        else
        {
            gelloNode.setMenuActions(templateNode.getMenuActions());
            gelloNode.setEditorActions(templateNode.getEditorActions());
            gelloNode.setCompilable(templateNode.isCompilable());
            gelloNode.setTestable(templateNode.isTestable());
            gelloNode.setDeletable(templateNode.isDeletable());
        }
        
        for (int i = 0; i < gelloNode.getChildren().size(); i++)
        {
            int j = 0;
            while (j < templateNode.getChildren().size() && !comparePaths(gelloNode.getChildren().get(i).getPath(), templateNode.getChildren().get(j).getPath()))
                j++;
            if (j == templateNode.getChildren().size())
                buildNodeActionsHelper(gelloNode.getChildren().get(i), null, true);
            else
                buildNodeActionsHelper(gelloNode.getChildren().get(i), templateNode.getChildren().get(j), false);
        }
    }
    
    private static boolean comparePaths(String specific, String generic)
    {        
        StringTokenizer specificTokenizer = new StringTokenizer(specific, "/");
        StringTokenizer genericTokenizer = new StringTokenizer(generic, "/");
        
        if (specificTokenizer.countTokens() != genericTokenizer.countTokens())
            return false;
        
        while (specificTokenizer.hasMoreTokens())
        {
            String specificToken = specificTokenizer.nextToken();
            String genericToken = genericTokenizer.nextToken();

            if ((genericToken.indexOf("*") == -1) && !genericToken.equals(specificToken))
                return false;
        }
        
        return true;
    }
    
    
}
