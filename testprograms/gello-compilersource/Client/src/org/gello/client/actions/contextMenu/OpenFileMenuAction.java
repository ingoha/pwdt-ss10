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


package org.gello.client.actions.contextMenu;

import org.eclipse.jface.action.Action;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.jface.viewers.IStructuredSelection;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.ISelectionListener;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.plugin.AbstractUIPlugin;
import org.gello.client.Application;
import org.gello.client.IImageKeys;
import org.gello.client.dialogs.ProjectPropertiesDialog;
import org.gello.client.editors.FileEditor;
import org.gello.client.editors.FileEditorInput;
import org.gello.client.manager.GelloNode;
import org.gello.client.manager.Project;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.model.CommonActions;
import org.gello.client.model.ObjectXMLSerializer;
import org.gello.client.model.ProjectProperties;
import org.gello.client.model.Session;
import org.gello.client.views.Browser;

public class OpenFileMenuAction extends Action implements ISelectionListener, ActionFactory.IWorkbenchAction
{
	private final IWorkbenchWindow window;
	public final static String ID = "org.gello.client.actions.contextMenu.OpenFile";
	private IStructuredSelection selection;

	public OpenFileMenuAction(IWorkbenchWindow window)
	{
		this.window = window;
		setId(ID);
		setText("&Open File");
		setToolTipText("Open File.");
		setImageDescriptor(AbstractUIPlugin.imageDescriptorFromPlugin(Application.PLUGIN_ID, IImageKeys.OPEN));
		window.getSelectionService().addSelectionListener(this);
	}

	public void selectionChanged(IWorkbenchPart part, ISelection incoming)
	{
		if (part instanceof Browser)
		{
			if (incoming instanceof IStructuredSelection)
			{
				selection = (IStructuredSelection) incoming;
				setEnabled(selection.size() == 1 && (selection.getFirstElement() instanceof GelloNode) && ((GelloNode) selection.getFirstElement()).isFile());
			}
			else
				setEnabled(false);
		}
	}

	public void dispose()
	{
		window.getSelectionService().removeSelectionListener(this);
	}

	/**
	 * Opens the selected file in the editor.
	 * 
	 */
	public void run()
	{
		GelloNode gelloNode = (GelloNode) selection.getFirstElement();

		// If nothing is selected then leave
		if (gelloNode == null)
			return;

		// Load Properties Page...
		if (gelloNode.isFile() && gelloNode.getPath().matches("/" + Session.getInstance().getCurrentProject().getName() + "/Properties"))
		{
			ProjectPropertiesDialog d = new ProjectPropertiesDialog(window.getShell());
			d.create();

			String fileData = CommonActions.getInstance().loadFileData(gelloNode.getPath());

			if (fileData == null)
				return;
			else if (fileData.equals(""))
			{
				ProjectProperties p = new ProjectProperties();
				Project currentProject = Session.getInstance().getCurrentProject();

				p.setProjectName(currentProject.getName());
				p.setCreatedBy(currentProject.getCreatedby());
				p.setDrugClass(currentProject.getDrugClass());
				p.setProjectDescription(currentProject.getDescr());
				p.setCurrentProjectStatus(currentProject.getStatus());
				p.setTemplateType(currentProject.getTemplateType());
				p.setGUID(currentProject.getUniqueId());

				d.setProjectProperties(p);
			}
			else
				d.setProjectProperties((ProjectProperties) new ObjectXMLSerializer().fromXML(fileData));

			int rc = d.open();
			if (rc == ProjectPropertiesDialog.OK)
			{
				String xml = new ObjectXMLSerializer().toXML(d.getProjectProperties());
				try
				{
					Application.getManager().checkinFile(gelloNode.getPath(), xml);
				}
				catch (ServerException_Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		// Load File...
		else if (gelloNode.isFile())
		{
			String fileData = CommonActions.getInstance().loadFileData(gelloNode.getPath());

			if (fileData != null)
			{
				IWorkbenchPage page = window.getActivePage();
				FileEditorInput input = new FileEditorInput(gelloNode, fileData);
				IEditorPart editor = page.findEditor(input);

				if (editor != null)
					input = (FileEditorInput) editor.getEditorInput();

				input.resetEditable();
				try
				{
					page.openEditor(input, FileEditor.ID);
					((FileEditor) page.findEditor(input)).refreshEditor();
				}
				catch (PartInitException e)
				{
					e.printStackTrace();
				}
			}
		}
		// Change expanded state to opposite of current...
		else
		{
			boolean isCurrentlyExpanded = Browser.viewer.getExpandedState(selection.getFirstElement());
			Browser.viewer.setExpandedState(selection.getFirstElement(), !isCurrentlyExpanded);
		}
	}
}
