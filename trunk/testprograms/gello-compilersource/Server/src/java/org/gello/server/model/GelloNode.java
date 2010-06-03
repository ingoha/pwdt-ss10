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

import java.util.ArrayList;
import java.util.List;

/**
 * GelloNode holds all directory listing data from the SVN repository.
 * 
 */
public class GelloNode {
	private boolean isFile = false;
        
        private boolean isCompilable = false;
        
        private boolean isTestable = false;

        private boolean isDeletable = false;
        
	private String name;

	private String path;

	private List<GelloNode> children = new ArrayList<GelloNode>();
        
        private List<String> menuActions = new ArrayList<String>();
        
        private List<String> editorActions = new ArrayList<String>();

	private String fileData = "";

	private String lockOwner;

	/**
	 * Tells if the node is a file or not.
	 * 
	 * @return true if node is a file, false if it is not.
	 */
	public boolean isFile() {
		return isFile;
	}

	/**
	 * Set to true if the node is a file, false if it is not.
	 * 
	 * @param isFile
	 */
	public void setFile(boolean isFile) {
		this.isFile = isFile;
	}
        
        public boolean isCompilable() {
            return isCompilable;
        }
        
        public void setCompilable(boolean isCompilable) {
            this.isCompilable = isCompilable;
        }
        
        public boolean isTestable() {
            return isTestable;
        }
        
        public void setTestable(boolean isTestable) {
            this.isTestable = isTestable;
        }
        
        public boolean isDeletable() {
            return isDeletable;
        }
        
        public void setDeletable(boolean isDeletable) {
            this.isDeletable = isDeletable;
        }

	/**
	 * Get the name of the node for display purposes.
	 * 
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the node for display purposes.
	 * 
	 * @param name
	 *            name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Get the path of the node.
	 * 
	 * @return path
	 */
	public String getPath() {
		return path;
	}

	/**
	 * Set the path of the node.
	 * 
	 * @param path
	 *            path
	 */
	public void setPath(String path) {
		this.path = path;
	}
        
	/**
	 * Get the list of children GelloNodes underneath this node.
	 * 
	 * @return List of GelloNodes.
	 */
	public List<GelloNode> getChildren() {
		return children;
	}
        
        /**
         * Set the list of children GelloNodes underneath this node.
         *
         * @param children
         */
        public void setChildren(List<GelloNode> children) {
            this.children = children;
        }

	/**
	 * Add a child to the list of GelloNodes underneath this node.
	 * 
	 * @param child
	 */
	public void addChild(GelloNode child) {
		children.add(child);
	}


	/**
	 * Get the data of this file node.
	 * 
	 * @return data
	 */
	public String getFileData() {
		return fileData;
	}

	/**
	 * Set the properties of this node.
	 * 
	 * @param properties
	 *            properties
	 */
	public void setFileData(String fileData) {
		this.fileData = fileData;
	}

	/**
	 * Get the lock owner of this node.
	 * 
	 * @return lockOwner
	 */
	public String getLockOwner() {
		return lockOwner;
	}

	/**
	 * Set the lock owner of this node.
	 * 
	 * @param lockOwner
	 *            lock owner
	 */
	public void setLockOwner(String lockOwner) {
		this.lockOwner = lockOwner;
	}
        
        public List<String> getMenuActions() {
            return this.menuActions;
        }
        
        public void setMenuActions(List<String> menuActions) {
            this.menuActions = menuActions;
        }
        
        public void addMenuAction(String menuAction) {
            this.menuActions.add(menuAction);
        }
        
        public List<String> getEditorActions() {
            return this.editorActions;
        }
        
        public void setEditorActions(List<String> editorActions) {
            this.editorActions = editorActions;
        }
        
        public void addEditorAction(String editorAction) {
            this.editorActions.add(editorAction);
        }
}
