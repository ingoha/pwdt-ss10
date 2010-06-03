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


package org.gello.server.tools;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import org.gello.server.model.GelloNode;
import org.gello.server.model.ObjectXMLSerializer;

/**
 * GenerateDrugSPLTemplates.java generates the xml templates for
 * the DrugSPL template type.
 * 
 */
public class GenerateDrugSPLTemplates
{
    private static final String PROJECT = "src/java/org/gello/server/templates/Drug SPL_Project.xml";
    private static final String INDICATION = "src/java/org/gello/server/templates/Drug SPL_Indication.xml";
    private static final String DETAIL = "src/java/org/gello/server/templates/Drug SPL_Detail.xml";
    private static final String TEST = "src/java/org/gello/server/templates/Drug SPL_Test.xml";
    private static final String FILE = "src/java/org/gello/server/templates/Drug SPL_File.xml";
    
    private static final String OPEN_FILE_MENU_ACTION = "org.gello.client.actions.contextMenu.OpenFile";
    private static final String CHECKOUT_FILE_MENU_ACTION = "org.gello.client.actions.contextMenu.CheckoutFile";
    private static final String CHECKIN_FILE_MENU_ACTION = "org.gello.client.actions.contextMenu.CheckinFile";
    private static final String COMPILE_FILE_MENU_ACTION = "org.gello.client.actions.contextMenu.CompileFile";
    private static final String TEST_FILE_MENU_ACTION = "org.gello.client.actions.contextMenu.TestFile";
    private static final String VERSION_INFO_MENU_ACTION = "org.gello.client.actions.contextMenu.VersionInfo";
    private static final String DELETE_FILE_MENU_ACTION = "org.gello.client.actions.contextMenu.DeleteFile";
    private static final String ADD_INDICATION_MENU_ACTION = "org.gello.client.actions.contextMenu.AddIndication";
    private static final String DELETE_INDICATION_MENU_ACTION = "org.gello.client.actions.contextMenu.DeleteIndication";
    private static final String ADD_DETAIL_MENU_ACTION = "org.gello.client.actions.contextMenu.AddDetail";
    private static final String DELETE_DETAIL_MENU_ACTION = "org.gello.client.actions.contextMenu.DeleteDetail";
    private static final String CLEAN_TESTS_MENU_ACTION = "org.gello.client.actions.contextMenu.CleanTests";
    private static final String ADD_TEST_MENU_ACTION = "org.gello.client.actions.contextMenu.AddTest";
    private static final String DELETE_TEST_MENU_ACTION = "org.gello.client.actions.contextMenu.DeleteTest";
    private static final String BUILD_PROJECT_MENU_ACTION = "org.gello.client.actions.contextMenu.BuildProject";
    private static final String TEST_PROJECT_MENU_ACTION = "org.gello.client.actions.contextMenu.TestProject";
    private static final String EXPORT_PROJECT_MENU_ACTION = "org.gello.client.actions.contextMenu.ExportProject";
    
    private static final String CHECKOUT_FILE_EDITOR_ACTION = "org.gello.client.actions.fileEditor.CheckoutFile";
    private static final String CHECKIN_FILE_EDITOR_ACTION = "org.gello.client.actions.fileEditor.CheckinFile";
    private static final String SAVE_FILE_EDITOR_ACTION = "org.gello.client.actions.fileEditor.SaveFile";
    private static final String COMPILE_FILE_EDITOR_ACTION = "org.gello.client.actions.fileEditor.CompileFile";
    private static final String TEST_FILE_EDITOR_ACTION = "org.gello.client.actions.fileEditor.TestFile";
    private static final String VERSION_INFO_EDITOR_ACTION = "org.gello.client.actions.fileEditor.VersionInfo";
    private static final String GENERATE_EXPECTED_RESULTS_EDITOR_ACTION = "org.gello.client.actions.fileEditor.GenerateExpectedResults";
    private static final String DELETE_FILE_EDITOR_ACTION = "org.gello.client.actions.fileEditor.DeleteFile";
    
    public static void main(String[] args)
    {
        GenerateDrugSPLTemplates runner = new GenerateDrugSPLTemplates();
        ObjectXMLSerializer serializer = new ObjectXMLSerializer();
        
        GelloNode projectNode = runner.generateProject();
        String projectXML = serializer.toXML(projectNode);
        try
        {
            FileWriter fileWriter = new FileWriter(new File(PROJECT));
            fileWriter.write(projectXML);
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        GelloNode indicationNode = runner.generateIndication();
        String indicationXML = serializer.toXML(indicationNode);
        try
        {
            FileWriter fileWriter = new FileWriter(new File(INDICATION));
            fileWriter.write(indicationXML);
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        GelloNode detailNode = runner.generateDetail();
        String detailXML = serializer.toXML(detailNode);
        try
        {
            FileWriter fileWriter = new FileWriter(new File(DETAIL));
            fileWriter.write(detailXML);
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        GelloNode testNode = runner.generateTest();
        String testXML = serializer.toXML(testNode);
        try
        {
            FileWriter fileWriter = new FileWriter(new File(TEST));
            fileWriter.write(testXML);
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        
        GelloNode fileNode = runner.generateFile();
        String fileXML = serializer.toXML(fileNode);
        try
        {
            FileWriter fileWriter = new FileWriter(new File(FILE));
            fileWriter.write(fileXML);
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    private GelloNode generateProject()
    {
        GelloNode projectNode = new GelloNode();
        projectNode.setPath("/*Project 1");
        projectNode.setName("*Project 1");
        ArrayList<String> projectNodeMenuActions = new ArrayList<String>();
        projectNode.setMenuActions(projectNodeMenuActions);
        
        GelloNode Properties = new GelloNode();
        Properties.setName("Properties");
        Properties.setPath("/*Project 1/Properties");
        Properties.setFile(true);
        ArrayList<String> PropertiesMenuActions = new ArrayList<String>();
        PropertiesMenuActions.add(OPEN_FILE_MENU_ACTION);
        PropertiesMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        PropertiesMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        PropertiesMenuActions.add(BUILD_PROJECT_MENU_ACTION);
        PropertiesMenuActions.add(TEST_PROJECT_MENU_ACTION);
        PropertiesMenuActions.add(EXPORT_PROJECT_MENU_ACTION);
        PropertiesMenuActions.add(VERSION_INFO_MENU_ACTION);
        Properties.setMenuActions(PropertiesMenuActions);
        projectNode.addChild(Properties);
        
        GelloNode SPL_Sections = new GelloNode();
        SPL_Sections.setName("SPL Sections");
        SPL_Sections.setPath("/*Project 1/SPL Sections");
        ArrayList<String> SPL_SectionsMenuActions = new ArrayList<String>();
        SPL_Sections.setMenuActions(SPL_SectionsMenuActions);
        
        GelloNode Indications = new GelloNode();
        Indications.setName("Indications");
        Indications.setPath("/*Project 1/SPL Sections/Indications");
        ArrayList<String> IndicationsMenuActions = new ArrayList<String>();
        IndicationsMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Indications.setMenuActions(IndicationsMenuActions);
        SPL_Sections.addChild(Indications);
        
        GelloNode Indication = generateIndication();
        prefixPaths(Indication, "/*Project 1/SPL Sections/Indications");
        
        Indications.addChild(Indication);
        
        
        GelloNode Contraindications = new GelloNode();
        Contraindications.setName("Contraindications");
        Contraindications.setPath("/*Project 1/SPL Sections/Contraindications");
        ArrayList<String> ContraindicationsMenuActions = new ArrayList<String>();
        ContraindicationsMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Contraindications.setMenuActions(ContraindicationsMenuActions);
        SPL_Sections.addChild(Contraindications);
        
        GelloNode Precautions = new GelloNode();
        Precautions.setName("Precautions");
        Precautions.setPath("/*Project 1/SPL Sections/Precautions");
        ArrayList<String> PrecautionsMenuActions = new ArrayList<String>();
        PrecautionsMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Precautions.setMenuActions(PrecautionsMenuActions);
        SPL_Sections.addChild(Precautions);
        
        GelloNode Special_Risk = new GelloNode();
        Special_Risk.setName("Special Risk");
        Special_Risk.setPath("/*Project 1/SPL Sections/Special Risk");
        ArrayList<String> Special_RiskMenuActions = new ArrayList<String>();
        Special_RiskMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Special_Risk.setMenuActions(Special_RiskMenuActions);
        SPL_Sections.addChild(Special_Risk);
        
        GelloNode Patient_Population = new GelloNode();
        Patient_Population.setName("Patient Population");
        Patient_Population.setPath("/*Project 1/SPL Sections/Patient Population");
        ArrayList<String> Patient_PopulationMenuActions = new ArrayList<String>();
        Patient_PopulationMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Patient_Population.setMenuActions(Patient_PopulationMenuActions);
        SPL_Sections.addChild(Patient_Population);
        
        GelloNode Drug_Interactions = new GelloNode();
        Drug_Interactions.setName("Drug Interactions");
        Drug_Interactions.setPath("/*Project 1/SPL Sections/Drug Interactions");
        ArrayList<String> Drug_InteractionsMenuActions = new ArrayList<String>();
        Drug_InteractionsMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Drug_Interactions.setMenuActions(Drug_InteractionsMenuActions);
        SPL_Sections.addChild(Drug_Interactions);
        
        GelloNode Usage_in_Pregnancy = new GelloNode();
        Usage_in_Pregnancy.setName("Usage in Pregnancy");
        Usage_in_Pregnancy.setPath("/*Project 1/SPL Sections/Usage in Pregnancy");
        ArrayList<String> Usage_in_PregnancyMenuActions = new ArrayList<String>();
        Usage_in_PregnancyMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Usage_in_Pregnancy.setMenuActions(Usage_in_PregnancyMenuActions);
        SPL_Sections.addChild(Usage_in_Pregnancy);
        
        GelloNode Adverse_Reactions = new GelloNode();
        Adverse_Reactions.setName("Adverse Reactions");
        Adverse_Reactions.setPath("/*Project 1/SPL Sections/Adverse Reactions");
        ArrayList<String> Adverse_ReactionsMenuActions = new ArrayList<String>();
        Adverse_ReactionsMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Adverse_Reactions.setMenuActions(Adverse_ReactionsMenuActions);
        SPL_Sections.addChild(Adverse_Reactions);
        
        GelloNode Drug_Abuse_and_Dependence = new GelloNode();
        Drug_Abuse_and_Dependence.setName("Drug Abuse and Dependence");
        Drug_Abuse_and_Dependence.setPath("/*Project 1/SPL Sections/Drug Abuse and Dependence");
        ArrayList<String> Drug_Abuse_and_DependenceMenuActions = new ArrayList<String>();
        Drug_Abuse_and_DependenceMenuActions.add(ADD_INDICATION_MENU_ACTION);
        Drug_Abuse_and_Dependence.setMenuActions(Drug_Abuse_and_DependenceMenuActions);
        SPL_Sections.addChild(Drug_Abuse_and_Dependence);
        
        projectNode.addChild(SPL_Sections);
        
        GelloNode Project_Test_Cases = new GelloNode();
        Project_Test_Cases.setName("Test Cases");
        Project_Test_Cases.setPath("/*Project 1/Test Cases");
        ArrayList<String> Project_Test_CasesMenuActions = new ArrayList<String>();
        Project_Test_CasesMenuActions.add(ADD_TEST_MENU_ACTION);
        Project_Test_CasesMenuActions.add(TEST_PROJECT_MENU_ACTION);
        Project_Test_CasesMenuActions.add(CLEAN_TESTS_MENU_ACTION);        
        Project_Test_Cases.setMenuActions(Project_Test_CasesMenuActions);
        projectNode.addChild(Project_Test_Cases);
        
        return projectNode;
    }
    
    private GelloNode generateIndication()
    {
        GelloNode indicationNode = new GelloNode();
        indicationNode.setPath("/*Indication 1");
        indicationNode.setName("*Indication 1");
        ArrayList<String> indicationNodeMenuActions = new ArrayList<String>();
        indicationNodeMenuActions.add(ADD_DETAIL_MENU_ACTION);
        indicationNodeMenuActions.add(DELETE_INDICATION_MENU_ACTION);   
        indicationNode.setMenuActions(indicationNodeMenuActions);
        
        GelloNode SPL_Content = new GelloNode();
        SPL_Content.setName(" SPL Content");
        SPL_Content.setPath("/*Indication 1/ SPL Content");
        SPL_Content.setFile(true);
        ArrayList<String> SPL_ContentMenuActions = new ArrayList<String>();
        SPL_ContentMenuActions.add(OPEN_FILE_MENU_ACTION);
        SPL_ContentMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        SPL_ContentMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        SPL_ContentMenuActions.add(VERSION_INFO_MENU_ACTION);
        SPL_Content.setMenuActions(SPL_ContentMenuActions);
        ArrayList<String> SPL_ContentEditorActions = new ArrayList<String>();
        SPL_ContentEditorActions.add(CHECKOUT_FILE_EDITOR_ACTION);
        SPL_ContentEditorActions.add(CHECKIN_FILE_EDITOR_ACTION);
        SPL_ContentEditorActions.add(SAVE_FILE_EDITOR_ACTION);
        SPL_ContentEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        SPL_Content.setEditorActions(SPL_ContentEditorActions);
        indicationNode.addChild(SPL_Content);
        
        GelloNode Detail = generateDetail();
        prefixPaths(Detail, "/*Indication 1");
        
        indicationNode.addChild(Detail);
        
        return indicationNode;
    }
    
    private GelloNode generateDetail()
    {
        GelloNode detailNode = new GelloNode();
        detailNode.setPath("/*Detail 1");
        detailNode.setName("*Detail 1");
        ArrayList<String> detailNodeMenuActions = new ArrayList<String>();
        detailNodeMenuActions.add(DELETE_DETAIL_MENU_ACTION);
        detailNode.setMenuActions(detailNodeMenuActions);
        
        GelloNode Parsed_SPL = new GelloNode();
        Parsed_SPL.setName("Parsed SPL");
        Parsed_SPL.setPath("/*Detail 1/Parsed SPL");
        Parsed_SPL.setFile(true);
        ArrayList<String> Parsed_SPLMenuActions = new ArrayList<String>();
        Parsed_SPLMenuActions.add(OPEN_FILE_MENU_ACTION);
        Parsed_SPLMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        Parsed_SPLMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        Parsed_SPLMenuActions.add(VERSION_INFO_MENU_ACTION);
        Parsed_SPL.setMenuActions(Parsed_SPLMenuActions);
        ArrayList<String> Parsed_SPLEditorActions = new ArrayList<String>();
        Parsed_SPLEditorActions.add(CHECKOUT_FILE_EDITOR_ACTION);
        Parsed_SPLEditorActions.add(CHECKIN_FILE_EDITOR_ACTION);
        Parsed_SPLEditorActions.add(SAVE_FILE_EDITOR_ACTION);
        Parsed_SPLEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        Parsed_SPL.setEditorActions(Parsed_SPLEditorActions);
        detailNode.addChild(Parsed_SPL);
        
        GelloNode Expression_Description = new GelloNode();
        Expression_Description.setName("Expression Description");
        Expression_Description.setPath("/*Detail 1/Expression Description");
        Expression_Description.setFile(true);
        ArrayList<String> Expression_DescriptionMenuActions = new ArrayList<String>();
        Expression_DescriptionMenuActions.add(OPEN_FILE_MENU_ACTION);
        Expression_DescriptionMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        Expression_DescriptionMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        Expression_DescriptionMenuActions.add(VERSION_INFO_MENU_ACTION);
        Expression_Description.setMenuActions(Expression_DescriptionMenuActions);
        ArrayList<String> Expression_DescriptionEditorActions = new ArrayList<String>();
        Expression_DescriptionEditorActions.add(CHECKOUT_FILE_EDITOR_ACTION);
        Expression_DescriptionEditorActions.add(CHECKIN_FILE_EDITOR_ACTION);
        Expression_DescriptionEditorActions.add(SAVE_FILE_EDITOR_ACTION);
        Expression_DescriptionEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        Expression_Description.setEditorActions(Expression_DescriptionEditorActions);
        detailNode.addChild(Expression_Description);
        
        GelloNode Gello_Expression = new GelloNode();
        Gello_Expression.setName("Gello Expression");
        Gello_Expression.setPath("/*Detail 1/Gello Expression");
        Gello_Expression.setFile(true);
        Gello_Expression.setCompilable(true);
        ArrayList<String> Gello_ExpressionMenuActions = new ArrayList<String>();
        Gello_ExpressionMenuActions.add(OPEN_FILE_MENU_ACTION);
        Gello_ExpressionMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        Gello_ExpressionMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        Gello_ExpressionMenuActions.add(COMPILE_FILE_MENU_ACTION);
        Gello_ExpressionMenuActions.add(VERSION_INFO_MENU_ACTION);
        Gello_Expression.setMenuActions(Gello_ExpressionMenuActions);
        ArrayList<String> Gello_ExpressionEditorActions = new ArrayList<String>();
        Gello_ExpressionEditorActions.add(CHECKOUT_FILE_EDITOR_ACTION);
        Gello_ExpressionEditorActions.add(CHECKIN_FILE_EDITOR_ACTION);
        Gello_ExpressionEditorActions.add(SAVE_FILE_EDITOR_ACTION);
        Gello_ExpressionEditorActions.add(COMPILE_FILE_EDITOR_ACTION);
        Gello_ExpressionEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        Gello_Expression.setEditorActions(Gello_ExpressionEditorActions);
        detailNode.addChild(Gello_Expression);
        
        GelloNode Author_Comment = new GelloNode();
        Author_Comment.setName("Author Comment");
        Author_Comment.setPath("/*Detail 1/Author Comment");
        Author_Comment.setFile(true);
        ArrayList<String> Author_CommentMenuActions = new ArrayList<String>();
        Author_CommentMenuActions.add(OPEN_FILE_MENU_ACTION);
        Author_CommentMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        Author_CommentMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        Author_CommentMenuActions.add(VERSION_INFO_MENU_ACTION);
        Author_Comment.setMenuActions(Author_CommentMenuActions);
        ArrayList<String> Author_CommentEditorActions = new ArrayList<String>();
        Author_CommentEditorActions.add(CHECKOUT_FILE_EDITOR_ACTION);
        Author_CommentEditorActions.add(CHECKIN_FILE_EDITOR_ACTION);
        Author_CommentEditorActions.add(SAVE_FILE_EDITOR_ACTION);
        Author_CommentEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        Author_Comment.setEditorActions(Author_CommentEditorActions);
        detailNode.addChild(Author_Comment);
        
        GelloNode Properties = new GelloNode();
        Properties.setName("Properties");
        Properties.setPath("/*Detail 1/Properties");
        Properties.setFile(true);
        ArrayList<String> PropertiesMenuActions = new ArrayList<String>();
        PropertiesMenuActions.add(OPEN_FILE_MENU_ACTION);
        Properties.setMenuActions(PropertiesMenuActions);
        ArrayList<String> PropertiesEditorActions = new ArrayList<String>();
        Properties.setEditorActions(PropertiesEditorActions);
        detailNode.addChild(Properties);
        
        GelloNode Test_Cases = new GelloNode();
        Test_Cases.setName("Test Cases");
        Test_Cases.setPath("/*Detail 1/Test Cases");
        ArrayList<String> Test_CasesMenuActions = new ArrayList<String>();
        Test_CasesMenuActions.add(ADD_TEST_MENU_ACTION);
        Test_Cases.setMenuActions(Test_CasesMenuActions);
        
        GelloNode Test = generateTest();
        prefixPaths(Test, "/*Detail 1/Test Cases");
        Test_Cases.addChild(Test);
        
        detailNode.addChild(Test_Cases);        
        
        return detailNode;
    }
    
    private GelloNode generateTest()
    {
        GelloNode testNode = new GelloNode();
        testNode.setPath("/*Test 1");
        testNode.setName("*Test 1");
        ArrayList<String> TestNodeMenuActions = new ArrayList<String>();
        TestNodeMenuActions.add(DELETE_TEST_MENU_ACTION);
        TestNodeMenuActions.add(CLEAN_TESTS_MENU_ACTION);
        TestNodeMenuActions.add(TEST_FILE_MENU_ACTION);
        testNode.setMenuActions(TestNodeMenuActions);
        
        GelloNode Test_Case = new GelloNode();
        Test_Case.setName("Test Case");
        Test_Case.setPath("/*Test 1/Test Case");
        Test_Case.setFile(true);
        Test_Case.setTestable(true);
        ArrayList<String> Test_CaseMenuActions = new ArrayList<String>();
        Test_CaseMenuActions.add(OPEN_FILE_MENU_ACTION);
        Test_CaseMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        Test_CaseMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        Test_CaseMenuActions.add(VERSION_INFO_MENU_ACTION);
        Test_CaseMenuActions.add(TEST_FILE_MENU_ACTION);
        Test_Case.setMenuActions(Test_CaseMenuActions);
        ArrayList<String> Test_CaseEditorActions = new ArrayList<String>();
        Test_CaseEditorActions.add(CHECKOUT_FILE_EDITOR_ACTION);
        Test_CaseEditorActions.add(CHECKIN_FILE_EDITOR_ACTION);
        Test_CaseEditorActions.add(SAVE_FILE_EDITOR_ACTION);
        Test_CaseEditorActions.add(TEST_FILE_EDITOR_ACTION);
        Test_CaseEditorActions.add(GENERATE_EXPECTED_RESULTS_EDITOR_ACTION);
        Test_CaseEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        Test_Case.setEditorActions(Test_CaseEditorActions);
        testNode.addChild(Test_Case);
        
        GelloNode Author_Comment = new GelloNode();
        Author_Comment.setName("Author Comment");
        Author_Comment.setPath("/*Test 1/Author Comment");
        Author_Comment.setFile(true);
        ArrayList<String> Author_CommentMenuActions = new ArrayList<String>();
        Author_CommentMenuActions.add(OPEN_FILE_MENU_ACTION);
        Author_CommentMenuActions.add(CHECKOUT_FILE_MENU_ACTION);
        Author_CommentMenuActions.add(CHECKIN_FILE_MENU_ACTION);
        Author_CommentMenuActions.add(VERSION_INFO_MENU_ACTION);
        Author_Comment.setMenuActions(Author_CommentMenuActions);
        ArrayList<String> Author_CommentEditorActions = new ArrayList<String>();
        Author_CommentEditorActions.add(CHECKOUT_FILE_EDITOR_ACTION);
        Author_CommentEditorActions.add(CHECKIN_FILE_EDITOR_ACTION);
        Author_CommentEditorActions.add(SAVE_FILE_EDITOR_ACTION);
        Author_CommentEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        Author_Comment.setEditorActions(Author_CommentEditorActions);
        testNode.addChild(Author_Comment);
        
        return testNode;
    }
    
    private GelloNode generateFile()
    {
        GelloNode fileNode = new GelloNode();
        fileNode.setName("*File 1");
        fileNode.setPath("/*File 1");
        fileNode.setFile(true);
        fileNode.setDeletable(true);
        ArrayList<String> fileNodeMenuActions = new ArrayList<String>();
        fileNodeMenuActions.add(OPEN_FILE_MENU_ACTION);
        fileNodeMenuActions.add(DELETE_FILE_MENU_ACTION);
        fileNodeMenuActions.add(VERSION_INFO_MENU_ACTION);
        fileNode.setMenuActions(fileNodeMenuActions);
        ArrayList<String> fileNodeEditorActions = new ArrayList<String>();
        fileNodeEditorActions.add(DELETE_FILE_EDITOR_ACTION);
        fileNodeEditorActions.add(VERSION_INFO_EDITOR_ACTION);
        fileNode.setEditorActions(fileNodeEditorActions);
        
        return fileNode;
    }    
    
    private void prefixPaths(GelloNode gelloNode, String prefix)
    {
        gelloNode.setPath(prefix + gelloNode.getPath());
        for (int i = 0; i < gelloNode.getChildren().size(); i++)
            prefixPaths(gelloNode.getChildren().get(i), prefix);
    }
}
