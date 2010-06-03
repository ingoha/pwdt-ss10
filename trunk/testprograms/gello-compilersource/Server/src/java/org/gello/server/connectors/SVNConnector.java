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


package org.gello.server.connectors;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.gello.server.model.GelloNode;
import org.tmatesoft.svn.core.SVNCommitInfo;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNLock;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNProperty;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.BasicAuthenticationManager;
import org.tmatesoft.svn.core.internal.io.dav.DAVRepositoryFactory;
import org.tmatesoft.svn.core.internal.io.svn.SVNRepositoryFactoryImpl;
import org.tmatesoft.svn.core.io.ISVNEditor;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.io.diff.SVNDeltaGenerator;

/**
 * Allows people to connect to the SVN repository and do tasks.
 *
 */
public class SVNConnector
{
    SVNRepository repository;
    
    public SVNConnector(SVNRepository repository)
    {
        this.repository = repository;
    }
    
    /**
     * Gets the svn directory structure as a GelloNode and returns it.
     *
     * @param projectName The name of the current project.
     * @param repository The SVNRepository that will be worked on.
     * @param includeFileData A boolean to say if the GelloNode should
     * include file data or not.
     * @return A GelloNode directory structure.
     */
    public GelloNode getSVNList(String projectName, boolean includeFileData)
    {
        GelloNode projectNode = new GelloNode();
        projectNode.setName(projectName);
        projectNode.setPath("/" + projectName);
        getSVNListHelper(projectNode, projectName, includeFileData);
        return projectNode;
    }
    
    /**
     * A recursive helper method to get the directory structure.
     *
     * @param repository The SVNRepository that will be worked on.
     * @param parentNode The main GelloNode.
     * @param path The starting path, "".
     * @param includeFileData A boolean to say if the GelloNode should
     * include file data or not.
     */
    private void getSVNListHelper(GelloNode parentNode, String path, boolean includeFileData)
    {
        
        /*
         * Gets the contents of the directory specified by path at the latest
         * revision (for this purpose -1 is used here as the revision number to
         * mean HEAD-revision) getDir returns a Collection of SVNDirEntry
         * elements. SVNDirEntry represents information about the directory
         * entry. Here this information is used to get the entry name, the name
         * of the person who last changed this entry, the number of the revision
         * when it was last changed and the entry type to determine whether it's
         * a directory or a file. If it's a directory listEntries steps into a
         * next recursion to display the contents of this directory. The third
         * parameter of getDir is null and means that a user is not interested
         * in directory properties. The fourth one is null, too - the user
         * doesn't provide its own Collection instance and uses the one returned
         * by getDir.
         */
        
        Collection entries = null;
        try
        {
            entries = repository.getDir(path, -1, null, (Collection) null);
        }
        catch (SVNException e)
        {
            e.printStackTrace();
        }
        
        Iterator iterator = entries.iterator();
        while (iterator.hasNext())
        {
            GelloNode currNode = new GelloNode();
            SVNDirEntry entry = (SVNDirEntry) iterator.next();
            
            String filePath = "/" + (path.equals("") ? "" : path + "/") + entry.getName();
            
            currNode.setPath(filePath);
            currNode.setName(entry.getName());
            
            if (entry.getKind() != SVNNodeKind.DIR)
            {
                currNode.setFile(true);
                currNode.setLockOwner(getLockOwner(filePath));
                if (includeFileData)
                    currNode.setFileData(new String(getFile(filePath)));
            }
            else
            {
                currNode.setFile(false);
                getSVNListHelper(currNode, (path.equals("")) ? entry.getName() : path + "/" + entry.getName(), includeFileData);
            }
            parentNode.addChild(currNode);
        }
    }
    
    /**
     * Creates a structure in the specified repository based on the
     * GelloNode that is passed in.  Uses addGelloNodeStructureHelper.
     *
     *
     * @param repository The repository to create the structure in.
     * @param gelloNode The GelloNode for the structire to be created.
     */
    public SVNCommitInfo addGelloNodeStructure(GelloNode gelloNode, String comment)
    {
        ISVNEditor editor = null;
        SVNCommitInfo commitInfo = null;
        try
        {
            editor = repository.getCommitEditor(comment, null);
            editor.openRoot(-1);
            String parentDir = gelloNode.getPath().substring(0, gelloNode.getPath().lastIndexOf("/"));
            editor.openDir(parentDir, -1);
            addGelloNodeStructureHelper(editor, gelloNode);
            editor.closeDir();
            editor.closeDir();
            commitInfo = editor.closeEdit();
            
        }
        catch (SVNException e)
        {
            try
            {
                editor.abortEdit();
            }
            catch (SVNException e1)
            {
            }
        }
        
        return commitInfo;
    }
    
    /**
     * Creates a new structure in the specified repository based on
     * GelloNode that is passed in.  Recursive method.
     *
     * @param editor The editor for the repository.
     * @param projectNode The GelloNode for the project to be created.
     */
    private void addGelloNodeStructureHelper(ISVNEditor editor, GelloNode projectNode) throws SVNException
    {
        String path = projectNode.getPath();
        
        if (!projectNode.isFile())
        {
            editor.addDir(path, null, -1);
            
            for (int i = 0; i < projectNode.getChildren().size(); i++)
                addGelloNodeStructureHelper(editor, projectNode.getChildren().get(i));
            
            editor.closeDir(); // close added directory
        }
        else
        {
            editor.addFile(path, null, -1);
            
            editor.applyTextDelta(path, null);
            
            SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
            String checksum = deltaGenerator.sendDelta(path, new ByteArrayInputStream(projectNode.getFileData().getBytes()), editor, true);
            
            editor.closeFile(path, checksum); // close added file
        }
    }
    
    /**
     * Logs a user into the repository and returns the SVNRepository connection.
     * The SVNRepository connection is null upon failure.
     *
     * @param repositoryPath The path of the repository.
     * @param SVNUsername The username for the repository.
     * @param SVNPassword The password for the repository.
     * @return The SVNRepository conneciton. Null if login failed.
     */
    public static SVNRepository login(String repositoryPath, String SVNUsername, String SVNPassword)
    {
        setupLibrary();
        SVNRepository repository = null;
        
        try
        {
            /*
             * Creates an instance of SVNRepository to work with the repository.
             * All user's requests to the repository are relative to the
             * repository location used to create this SVNRepository. SVNURL is
             * a wrapper for URL strings that refer to repository locations.
             */
            repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(repositoryPath));
        }
        catch (SVNException e)
        {
            /*
             * Perhaps a malformed URL is the cause of this exception
             */
            System.out.println("Error while creating an SVNRepository for location '" + repositoryPath + "': " + e.getMessage());
            e.printStackTrace();
        }
        
        /*
         * User's authentication information is provided via an
         * ISVNAuthenticationManager instance. SVNWCUtil creates a default
         * user's authentication manager given user's name and password.
         */
        //ISVNAuthenticationManager authManager = SVNWCUtil.createDefaultAuthenticationManager(SVNUsername, SVNPassword);
        BasicAuthenticationManager authManager = new BasicAuthenticationManager(SVNUsername, SVNPassword);
        
        /*
         * Sets the manager of the user's authentication information that will
         * be used to authenticate the user to the server (if needed) during
         * operations handled by the SVNRepository.
         */
        repository.setAuthenticationManager(authManager);
        
        if (!testCommitAuthentication(repository, SVNUsername, SVNPassword))
            return null;
        
        /*
         * Checks up if the specified path/to/repository part of the URL really
         * corresponds to a directory. If doesn't the program errors.
         * SVNNodeKind is that one who says what is located at a path in a
         * revision. -1 means the latest revision.
         */
        SVNNodeKind nodeKind = null;
        try
        {
            nodeKind = repository.checkPath("", -1);
        }
        catch (SVNException e)
        {
            e.printStackTrace();
        }
        
        if (nodeKind == SVNNodeKind.NONE)
        {
            System.out.println("There is no entry at '" + repositoryPath + "'.");
        }
        else if (nodeKind == SVNNodeKind.FILE)
        {
            System.out.println("The entry at '" + repositoryPath + "' is a file while a directory was expected.");
        }
        
        return repository;
    }
    
    /**
     * Tests to see if the user has commit permission on the repository.
     *
     * @param repository The SVNRepository connection.
     * @param username The username for the repository.
     * @param password The password for the repository.
     * @return true if authenticated, false if not.
     */
    private static boolean testCommitAuthentication(SVNRepository repository, String username, String password)
    {
        ISVNEditor editor = null;
        try
        {
            editor = repository.getCommitEditor("Testing Authentication", null);
            editor.openRoot(-1);
            editor.abortEdit();
        }
        catch (SVNException e)
        {
            try
            {
                editor.abortEdit();
            }
            catch (SVNException e1)
            {
            }
            
//            new DatabaseController().writeEventToDB(username, "Login attempt failed");
            return false;
        }
        
//        new DatabaseController().writeEventToDB(username, "Logged in");
        return true;
    }
    
    /**
     * This method performs file modifications on the repository and commits
     * those changes.
     *
     * @param repository The SVNRepository that will be worked on.
     * @param filePath The original file path.
     * @param comment The comment to be used when committing.
     * @param newData The new data that the file should have.
     * @return Gives back SVNCommitInfo.
     */
    public SVNCommitInfo modifyFile(String filePath, String comment, String newData)
    {
        ISVNEditor editor = null;
        SVNCommitInfo commitInfo = null;
        try
        {
            editor = repository.getCommitEditor(comment, getLockMap(filePath), true, null);
            editor.openRoot(-1);
            
            /*
             * Opens the file added in the previous commit.
             *
             * filePath is also defined as a relative path to the root directory.
             */
            editor.openFile(filePath, -1);
            
            /*
             * The next steps are directed to applying and writing the file delta.
             */
            editor.applyTextDelta(filePath, null);
            
            /*
             * Use delta generator utility class to generate and send delta
             *
             * Note that you may use only 'target' data to generate delta when there
             * is no access to the 'base' (previous) version of the file. However,
             * using 'base' data will result in smaller network overhead.
             *
             * SVNDeltaGenerator will call editor.textDeltaChunk(...) method for
             * each generated "diff window" and then editor.textDeltaEnd(...) in the
             * end of delta transmission. Number of diff windows depends on the file
             * size.
             *
             */
            SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
            String checksum = deltaGenerator.sendDelta(filePath, new ByteArrayInputStream(newData.getBytes()), editor, true);
            
            /*
             * Closes the file.
             */
            editor.closeFile(filePath, checksum);
            
            editor.closeDir();
            commitInfo = editor.closeEdit();
            
        }
        catch (SVNException e)
        {
            e.printStackTrace();
            try
            {
                editor.abortEdit();
            }
            catch (SVNException e1)
            {
            }
        }
        
        return commitInfo;
    }
    
    /**
     * This method performs and commits the deletion of a file or directory in
     * the repository.
     *
     * @param repository The SVNRepository that will be worked on.
     * @param filePath The original file path.
     * @param comment The comment to be used when committing.
     * @return Gives back SVNCommitInfo.
     */
    public SVNCommitInfo delete(String filePath, String comment)
    {
        ISVNEditor editor = null;
        SVNCommitInfo commitInfo = null;
        try
        {
            editor = repository.getCommitEditor(comment, getLockMap(filePath), false, null);
            editor.openRoot(-1);
            
            /*
             * Deletes a file or subdirectory with all its contents.
             *
             * filePath is relative to the root directory.
             */
            editor.deleteEntry(filePath, -1);
            
            editor.closeDir();
            commitInfo = editor.closeEdit();
            
        }
        catch (SVNException e)
        {
            try
            {
                editor.abortEdit();
            }
            catch (SVNException e1)
            {
            }
        }
        
        return commitInfo;
    }
    
    /**
     * This method is called to move a file or directory in the repository.
     *
     * @param repository The SVNRepository that will be worked on.
     * @param filePath The original file path.
     * @param comment The comment to be used when committing.
     * @param newPath The new path where this file or directory will be located.
     * @return Gives back SVNCommitInfo.
     */
    public SVNCommitInfo moveFile(String filePath, String newPath, String comment)
    {
        ISVNEditor editor = null;
        SVNCommitInfo commitInfo = null;
        try
        {
            editor = repository.getCommitEditor(comment, getLockMap(filePath), false, null);
            editor.openRoot(-1);
            
            int lastSlash = newPath.lastIndexOf("/");
            if (lastSlash != -1)
            {
                editor.openDir(newPath.substring(0, lastSlash), -1);
                editor.addFile(newPath, filePath, -1);
                editor.closeDir();
            }
            else
                editor.addFile(newPath, filePath, -1);
            /*
             * Deletes a file or subdirectory with all its contents.
             *
             * filePath is relative to the root directory.
             */
            editor.deleteEntry(filePath, -1);
            
            editor.closeDir();
            commitInfo = editor.closeEdit();
            
        }
        catch (SVNException e)
        {
            try
            {
                editor.abortEdit();
            }
            catch (SVNException e1)
            {
            }
        }
        
        return commitInfo;
    }
    
    /**
     * This method is called to add a file in the repository.
     *
     * @param repository The SVNRepository that will be worked on.
     * @param filePath The original file path.
     * @param comment The comment to be used when committing.
     * @param contents The String with the contents of the new file.
     * @return Gives back SVNCommitInfo.
     */
    public SVNCommitInfo addFile(String filePath, String comment, String contents)
    {
        ISVNEditor editor = null;
        SVNCommitInfo commitInfo = null;
        try
        {
            editor = repository.getCommitEditor(comment, getLockMap(filePath), false, null);
            editor.openRoot(-1);
            
            int lastSlash = filePath.lastIndexOf("/");
            if (lastSlash != -1)
            {
                editor.openDir(filePath.substring(0, lastSlash), -1);
                editor.addFile(filePath, null, -1);
                editor.closeDir();
            }
            else
                editor.addFile(filePath, null, -1);
            
            editor.applyTextDelta(filePath, null);
            
            SVNDeltaGenerator deltaGenerator = new SVNDeltaGenerator();
            String checksum = deltaGenerator.sendDelta(filePath, new ByteArrayInputStream(contents.getBytes()), editor, true);
            
            editor.closeFile(filePath, checksum); // close added file
            
            editor.closeDir();
            commitInfo = editor.closeEdit();
            
        }
        catch (SVNException e)
        {
            try
            {
                editor.abortEdit();
            }
            catch (SVNException e1)
            {
            }
        }
        
        return commitInfo;
    }
    
    /**
     * This method is called to add a directory in the repository.
     *
     * @param repository The SVNRepository that will be worked on.
     * @param filePath The new directory path.
     * @param comment The comment to be used when committing.
     * @param newPath The new path where this file or directory will be located.
     * @return Gives back SVNCommitInfo.
     */
    public SVNCommitInfo addDir(String filePath, String comment)
    {
        ISVNEditor editor = null;
        SVNCommitInfo commitInfo = null;
        try
        {
            editor = repository.getCommitEditor(comment, getLockMap(filePath), false, null);
            editor.openRoot(-1);
            
            int lastSlash = filePath.lastIndexOf("/");
            if (lastSlash != -1)
            {
                editor.openDir(filePath.substring(0, lastSlash), -1);
                editor.addDir(filePath, null, -1);
                editor.closeDir();
            }
            else
                editor.addDir(filePath, null, -1);
            
            editor.closeDir();
            
            editor.closeDir();
            commitInfo = editor.closeEdit();
            
        }
        catch (SVNException e)
        {
            try
            {
                editor.abortEdit();
            }
            catch (SVNException e1)
            {
            }
        }
        
        return commitInfo;
    }
    
    public boolean lock(String path, boolean forceLock)
    {
        Map<String, Long> lockMap = new HashMap<String, Long>();
        lockHelper(path, lockMap);
        
        try
        {
            repository.lock(lockMap, null, forceLock, null);
        }
        catch (SVNException e)
        {
            return false;
        }
        return true;
    }
    
    private void lockHelper(String path, Map<String, Long> lockMap)
    {
        Collection entries = null;
        try
        {
            entries = repository.getDir(path, -1, null, (Collection) null);
        }
        catch (SVNException e)
        {
        }
        
        if (entries != null && entries.size() != 0)
        {
            Iterator iterator = entries.iterator();
            while (iterator.hasNext())
            {
                SVNDirEntry entry = (SVNDirEntry) iterator.next();
                
                String filePath = (path.equals("") ? "" : path + "/") + entry.getName();
                
                if (entry.getKind() != SVNNodeKind.DIR)
                    lockMap.put(filePath, new Long(-1));
                else
                    lockHelper((path.equals("")) ? entry.getName() : path + "/" + entry.getName(), lockMap);
            }
        }
        else
            lockMap.put(path, new Long(-1));
        
    }
    
    public boolean unlock(String path, boolean forceUnlock)
    {
        try
        {
            Map lockMap = getLockMap(path);
            
            if (lockMap == null)
                return true;
            
            repository.unlock(lockMap, forceUnlock, null);
        }
        catch (SVNException e)
        {
            return false;
        }
        return true;
    }
    
    public boolean isLocked(String path)
    {
        try
        {
            SVNLock lock = repository.getLock(path);
            
            if (lock == null)
                return false;
            else
            {
                return true;
            }
        }
        catch (SVNException e)
        {
            return false;       // returns false if exception is caught
        }
    }
    
    public String getLockOwner(String path)
    {
        try
        {
            SVNLock lock = repository.getLock(path);
            
            if (lock == null)
                return null;
            else
                return lock.getOwner();
        }
        catch (SVNException e)
        {
            return null;       // returns null if exception is caught
        }
    }
    
    /**
     * Gets a String with the file contents of the file specified with filePath.
     *
     * @param repository The SVN repository to be worked on.
     * @param filePath The file path of the file to be retrieved.
     * @return A String with the file contents
     */
    public String getFile(String filePath)
    {
        
        /*
         * This Map will be used to get the file properties. Each Map key is a
         * property name and the value associated with the key is the property
         * value.
         */
        Map fileProperties = new HashMap();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        
        try
        {
            /*
             * Checks up if the specified path really corresponds to a file. If
             * doesn't the program exits. SVNNodeKind is that one who says what
             * is located at a path in a revision. -1 means the latest revision.
             */
            SVNNodeKind nodeKind = repository.checkPath(filePath, -1);
            
            if (nodeKind == SVNNodeKind.NONE)
            {
                System.out.println("There is no entry at '" + filePath + "'.");
            }
            else if (nodeKind == SVNNodeKind.DIR)
            {
                System.out.println("The entry at '" + filePath + "' is a directory while a file was expected.");
            }
            /*
             * Gets the contents and properties of the file located at filePath
             * in the repository at the latest revision (which is meant by a
             * negative revision number).
             */
            repository.getFile(filePath, -1, fileProperties, baos);
            
        }
        catch (SVNException svne)
        {
            System.out.println("error while fetching the file contents and properties: " + svne.getMessage());
        }
        
        /*
         * Here the SVNProperty class is used to get the value of the
         * svn:mime-type property (if any). SVNProperty is used to facilitate
         * the work with versioned properties.
         */
        String mimeType = (String) fileProperties.get(SVNProperty.MIME_TYPE);
        
        /*
         * SVNProperty.isTextMimeType(..) method checks up the value of the
         * mime-type file property and says if the file is a text (true) or not
         * (false).
         */
        boolean isTextType = SVNProperty.isTextMimeType(mimeType);
        
        Iterator iterator = fileProperties.keySet().iterator();
        /*
         * Displays file properties.
         */
        while (iterator.hasNext())
        {
            String propertyName = (String) iterator.next();
            String propertyValue = (String) fileProperties.get(propertyName);
        }
        
        byte[] fileContents = null;
        if (isTextType)
        {
            fileContents = baos.toByteArray();
        }
        // else File contents can not be displayed in the console since the mime-type property says that it's not a kind of a text file.
        
        return new String(fileContents);
    }
    
    
    /**
     * Gets a String with the version info for the path specified.
     *
     * @param repository The SVN repository to be worked on.
     * @param filePath The file path of the file or directory of which to retrieve the version info.
     * @return A String with the version info.
     */
    public String getVersionInfo(String filePath)
    {
        StringBuffer versionInfo = new StringBuffer();
        /*
         * This Map will be used to get the file properties. Each Map key is a
         * property name and the value associated with the key is the property
         * value.
         */
        Map fileProperties = new HashMap();
        
        try
        {
            SVNNodeKind nodeKind = repository.checkPath(filePath, -1);
            
            if (nodeKind == SVNNodeKind.NONE)
                System.out.println("There is no entry at '" + filePath + "'.");
            else if (nodeKind == SVNNodeKind.DIR)
                repository.getDir(filePath, -1, fileProperties, (Collection)null);
            else
                repository.getFile(filePath, -1, fileProperties, null);
        }
        catch (SVNException svne)
        {
            System.out.println("error while fetching the file contents and properties: " + svne.getMessage());
        }
        
        Iterator iterator = fileProperties.keySet().iterator();
        
        while (iterator.hasNext())
        {
            String propertyName = (String) iterator.next();
            String propertyValue = (String) fileProperties.get(propertyName);
            versionInfo.append(propertyName + ": " + propertyValue + "\n");
        }
        
        return versionInfo.toString();
    }
    
    public boolean checkPath(String path)
    {
        try
        {
            SVNNodeKind nodeKind = repository.checkPath(path, -1);
            
            if (nodeKind == SVNNodeKind.NONE)
                return false;
        }
        catch (SVNException e)
        {
            e.printStackTrace();
        }
        return true;
    }
    
    private Map getLockMap(String path)
    {
        Map<String, String> lockMap = new HashMap<String, String>();
        SVNLock[] locks = null;
        try
        {
            locks = repository.getLocks(path);
        }
        catch (SVNException ex)
        {
        }
        if (locks == null)
            return null;
        
        for (int i = 0; i < locks.length; i++)
            lockMap.put(locks[i].getPath(), locks[i].getID());
        
        return lockMap;
    }
    
    /**
     * Sets up the SVN library. Must be done before any SVN calls are used.
     *
     */
    private static void setupLibrary()
    {
                /*
                 * for DAV (over http and https)
                 */
        DAVRepositoryFactory.setup();
        
                /*
                 * for SVN (over svn and svn+ssh)
                 */
        SVNRepositoryFactoryImpl.setup();
    }
}
