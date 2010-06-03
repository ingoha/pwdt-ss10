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


package org.gello.client;

import org.eclipse.jface.action.ActionContributionItem;
import org.eclipse.jface.action.IContributionItem;
import org.eclipse.jface.action.ICoolBarManager;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.action.MenuManager;
import org.eclipse.jface.action.Separator;
import org.eclipse.jface.action.ToolBarManager;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.actions.ActionFactory;
import org.eclipse.ui.actions.ContributionItemFactory;
import org.eclipse.ui.application.ActionBarAdvisor;
import org.eclipse.ui.application.IActionBarConfigurer;
import org.gello.client.actions.LogoutAction;
import org.gello.client.actions.ManageAccountsAction;
import org.gello.client.actions.MyAccountAction;
import org.gello.client.actions.ResourceLinksAction;
import org.gello.client.actions.ShowExpressionsAction;
import org.gello.client.actions.ShowProjectsAction;
import org.gello.client.actions.contextMenu.AddDetailMenuAction;
import org.gello.client.actions.contextMenu.AddIndicationMenuAction;
import org.gello.client.actions.contextMenu.AddTestMenuAction;
import org.gello.client.actions.contextMenu.BuildProjectMenuAction;
import org.gello.client.actions.contextMenu.CheckinFileMenuAction;
import org.gello.client.actions.contextMenu.CheckoutFileMenuAction;
import org.gello.client.actions.contextMenu.CleanTestsMenuAction;
import org.gello.client.actions.contextMenu.CompileFileMenuAction;
import org.gello.client.actions.contextMenu.DeleteDetailMenuAction;
import org.gello.client.actions.contextMenu.DeleteFileMenuAction;
import org.gello.client.actions.contextMenu.DeleteIndicationMenuAction;
import org.gello.client.actions.contextMenu.DeleteTestMenuAction;
import org.gello.client.actions.contextMenu.ExportProjectMenuAction;
import org.gello.client.actions.contextMenu.OpenFileMenuAction;
import org.gello.client.actions.contextMenu.TestFileMenuAction;
import org.gello.client.actions.contextMenu.TestProjectMenuAction;
import org.gello.client.actions.contextMenu.VersionInfoMenuAction;
import org.gello.client.actions.expressionsView.ExportExpressionAction;
import org.gello.client.actions.expressionsView.FilterExpressionsAction;
import org.gello.client.actions.expressionsView.ImportExpressionAction;
import org.gello.client.actions.expressionsView.NewExpressionAction;
import org.gello.client.actions.expressionsView.PrintExpressionAction;
import org.gello.client.actions.expressionsView.ViewExpressionAction;
import org.gello.client.actions.fileEditor.CheckinFileEditorAction;
import org.gello.client.actions.fileEditor.CheckoutFileEditorAction;
import org.gello.client.actions.fileEditor.CompileFileEditorAction;
import org.gello.client.actions.fileEditor.DeleteFileEditorAction;
import org.gello.client.actions.fileEditor.GenerateExpectedResultsEditorAction;
import org.gello.client.actions.fileEditor.SaveFileEditorAction;
import org.gello.client.actions.fileEditor.TestFileEditorAction;
import org.gello.client.actions.fileEditor.VersionInfoEditorAction;
import org.gello.client.actions.manageAccountsDialog.DeleteOrganizationAction;
import org.gello.client.actions.manageAccountsDialog.DeleteUserAction;
import org.gello.client.actions.manageAccountsDialog.EditOrganizationAction;
import org.gello.client.actions.manageAccountsDialog.EditUserAction;
import org.gello.client.actions.manageAccountsDialog.NewOrganizationAction;
import org.gello.client.actions.manageAccountsDialog.NewUserAction;
import org.gello.client.actions.projectsView.BuildProjectAction;
import org.gello.client.actions.projectsView.DeleteProjectAction;
import org.gello.client.actions.projectsView.ExportProjectAction;
import org.gello.client.actions.projectsView.FilterProjectsAction;
import org.gello.client.actions.projectsView.ForwardProjectAction;
import org.gello.client.actions.projectsView.ImportProjectAction;
import org.gello.client.actions.projectsView.NewProjectAction;
import org.gello.client.actions.projectsView.OpenProjectAction;
import org.gello.client.actions.projectsView.TestProjectAction;

/**
 * The action bar advisor for Gello.
 * Creates all the actions for the application.
 * Also creates the menubar and coolbar.
 *
 */
public class ApplicationActionBarAdvisor extends ActionBarAdvisor
{

	private IContributionItem viewList;

	public ApplicationActionBarAdvisor(IActionBarConfigurer configurer)
	{
		super(configurer);
	}

	/**
	 * Creates all actions and adds them to the action HashMap in Application.
	 * 
	 */
	protected void makeActions(IWorkbenchWindow window)
	{
		viewList = ContributionItemFactory.VIEWS_SHORTLIST.create(window);

		// ---------- Menu Actions ----------

		Application.addAction(ActionFactory.QUIT.create(window));
		register(Application.getAction(ActionFactory.QUIT.getId()));

		Application.addAction(ActionFactory.ABOUT.create(window));
		register(Application.getAction(ActionFactory.ABOUT.getId()));

		Application.addAction(ActionFactory.PREFERENCES.create(window));
		register(Application.getAction(ActionFactory.PREFERENCES.getId()));

		Application.addAction(new LogoutAction(window));
		register(Application.getAction(LogoutAction.ID));

		Application.addAction(new ShowProjectsAction(window));
		register(Application.getAction(ShowProjectsAction.ID));

		Application.addAction(new ShowExpressionsAction(window));
		register(Application.getAction(ShowExpressionsAction.ID));

		Application.addAction(new MyAccountAction(window));
		register(Application.getAction(MyAccountAction.ID));

		Application.addAction(new ResourceLinksAction(window));
		register(Application.getAction(ResourceLinksAction.ID));

		Application.addAction(new ManageAccountsAction(window));
		register(Application.getAction(ManageAccountsAction.ID));

		// ---------- Manage Accounts Actions ----------

		Application.addAction(new NewUserAction(window));
		register(Application.getAction(NewUserAction.ID));

		Application.addAction(new EditUserAction(window));
		register(Application.getAction(EditUserAction.ID));

		Application.addAction(new DeleteUserAction(window));
		register(Application.getAction(DeleteUserAction.ID));

		Application.addAction(new NewOrganizationAction(window));
		register(Application.getAction(NewOrganizationAction.ID));

		Application.addAction(new EditOrganizationAction(window));
		register(Application.getAction(EditOrganizationAction.ID));

		Application.addAction(new DeleteOrganizationAction(window));
		register(Application.getAction(DeleteOrganizationAction.ID));

		// ---------- Context Menu Actions ----------

		Application.addAction(new OpenFileMenuAction(window));
		register(Application.getAction(OpenFileMenuAction.ID));

		Application.addAction(new CheckoutFileMenuAction(window));
		register(Application.getAction(CheckoutFileMenuAction.ID));

		Application.addAction(new CheckinFileMenuAction(window));
		register(Application.getAction(CheckinFileMenuAction.ID));

		Application.addAction(new CompileFileMenuAction(window));
		register(Application.getAction(CompileFileMenuAction.ID));

		Application.addAction(new TestFileMenuAction(window));
		register(Application.getAction(TestFileMenuAction.ID));

		Application.addAction(new VersionInfoMenuAction(window));
		register(Application.getAction(VersionInfoMenuAction.ID));

		Application.addAction(new DeleteFileMenuAction(window));
		register(Application.getAction(DeleteFileMenuAction.ID));

		Application.addAction(new AddIndicationMenuAction(window));
		register(Application.getAction(AddIndicationMenuAction.ID));

		Application.addAction(new DeleteIndicationMenuAction(window));
		register(Application.getAction(DeleteIndicationMenuAction.ID));

		Application.addAction(new AddDetailMenuAction(window));
		register(Application.getAction(AddDetailMenuAction.ID));

		Application.addAction(new DeleteDetailMenuAction(window));
		register(Application.getAction(DeleteDetailMenuAction.ID));

		Application.addAction(new CleanTestsMenuAction(window));
		register(Application.getAction(CleanTestsMenuAction.ID));

		Application.addAction(new AddTestMenuAction(window));
		register(Application.getAction(AddTestMenuAction.ID));

		Application.addAction(new DeleteTestMenuAction(window));
		register(Application.getAction(DeleteTestMenuAction.ID));

		Application.addAction(new BuildProjectMenuAction(window));
		register(Application.getAction(BuildProjectMenuAction.ID));

		Application.addAction(new TestProjectMenuAction(window));
		register(Application.getAction(TestProjectMenuAction.ID));

		Application.addAction(new ExportProjectMenuAction(window));
		register(Application.getAction(ExportProjectMenuAction.ID));

		// ---------- File Editor Actions ----------

		Application.addAction(new CheckoutFileEditorAction(window));
		register(Application.getAction(CheckoutFileMenuAction.ID));

		Application.addAction(new CheckinFileEditorAction(window));
		register(Application.getAction(CheckinFileMenuAction.ID));

		Application.addAction(new SaveFileEditorAction(window));
		register(Application.getAction(SaveFileEditorAction.ID));

		Application.addAction(new CompileFileEditorAction(window));
		register(Application.getAction(CompileFileMenuAction.ID));

		Application.addAction(new GenerateExpectedResultsEditorAction(window));
		register(Application.getAction(GenerateExpectedResultsEditorAction.ID));

		Application.addAction(new DeleteFileEditorAction(window));
		register(Application.getAction(DeleteFileEditorAction.ID));

		Application.addAction(new TestFileEditorAction(window));
		register(Application.getAction(TestFileMenuAction.ID));

		Application.addAction(new VersionInfoEditorAction(window));
		register(Application.getAction(VersionInfoMenuAction.ID));

		// ---------- Projects Actions ----------

		Application.addAction(new FilterProjectsAction(window));
		register(Application.getAction(FilterProjectsAction.ID));

		Application.addAction(new NewProjectAction(window));
		register(Application.getAction(NewProjectAction.ID));

		Application.addAction(new OpenProjectAction(window));
		register(Application.getAction(OpenProjectAction.ID));

		Application.addAction(new BuildProjectAction(window));
		register(Application.getAction(BuildProjectAction.ID));

		Application.addAction(new TestProjectAction(window));
		register(Application.getAction(TestProjectAction.ID));

		Application.addAction(new ExportProjectAction(window));
		register(Application.getAction(ExportProjectAction.ID));

		Application.addAction(new ImportProjectAction(window));
		register(Application.getAction(ImportProjectAction.ID));

		Application.addAction(new ForwardProjectAction(window));
		register(Application.getAction(ForwardProjectAction.ID));

		Application.addAction(new DeleteProjectAction(window));
		register(Application.getAction(DeleteProjectAction.ID));

		// ---------- Expressions Actions ----------

		Application.addAction(new FilterExpressionsAction(window));
		register(Application.getAction(FilterExpressionsAction.ID));

		Application.addAction(new NewExpressionAction(window));
		register(Application.getAction(NewExpressionAction.ID));

		Application.addAction(new ViewExpressionAction(window));
		register(Application.getAction(ViewExpressionAction.ID));

		Application.addAction(new ExportExpressionAction(window));
		register(Application.getAction(ExportExpressionAction.ID));

		Application.addAction(new ImportExpressionAction(window));
		register(Application.getAction(ImportExpressionAction.ID));

		Application.addAction(new PrintExpressionAction(window));
		register(Application.getAction(PrintExpressionAction.ID));

	}

	/**
	 * Creates the menu bar.
	 * 
	 */
	protected void fillMenuBar(IMenuManager menuBar)
	{
		MenuManager fileMenu = new MenuManager("&File", "file");
		fileMenu.add(Application.getAction(ActionFactory.PREFERENCES.getId()));
		fileMenu.add(new Separator());
		fileMenu.add(Application.getAction(LogoutAction.ID));
		fileMenu.add(Application.getAction(ActionFactory.QUIT.getId()));

		MenuManager viewMenu = new MenuManager("&View", "view");

		viewMenu.add(viewList);

		MenuManager toolsMenu = new MenuManager("&Tools", "tools");
		toolsMenu.add(Application.getAction(MyAccountAction.ID));
		toolsMenu.add(Application.getAction(ResourceLinksAction.ID));
		toolsMenu.add(Application.getAction(ManageAccountsAction.ID));

		MenuManager helpMenu = new MenuManager("&Help", "help");
		helpMenu.add(Application.getAction(ActionFactory.ABOUT.getId()));

		menuBar.add(fileMenu);
		menuBar.add(viewMenu);
		menuBar.add(toolsMenu);
		menuBar.add(helpMenu);
	}

	/**
	 * Creates the coolbar.
	 * 
	 */
	@Override
	protected void fillCoolBar(ICoolBarManager coolBar)
	{
		IToolBarManager projectToolbar = new ToolBarManager(coolBar.getStyle());

		ActionContributionItem projects = new ActionContributionItem(Application.getAction(ShowProjectsAction.ID));
		projects.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		ActionContributionItem expressions = new ActionContributionItem(Application.getAction(ShowExpressionsAction.ID));
		expressions.setMode(ActionContributionItem.MODE_FORCE_TEXT);

		projectToolbar.add(projects);
		projectToolbar.add(expressions);

		IToolBarManager fisheyeToolbar = new ToolBarManager(coolBar.getStyle());
		fisheyeToolbar.add(new FisheyeSearch("fisheyeSearch"));

		coolBar.add(projectToolbar);
		coolBar.add(fisheyeToolbar);
	}

}
