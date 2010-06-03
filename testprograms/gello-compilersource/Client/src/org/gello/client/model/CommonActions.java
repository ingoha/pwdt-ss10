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

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.gello.client.Application;
import org.gello.client.manager.GelloNode;
import org.gello.client.manager.ServerException_Exception;
import org.gello.client.views.Browser;
import org.gello.client.views.CompilerOutput;
import org.gello.client.views.Status;
import org.gello.client.views.TestOutput;
import org.gello.compiler.CompileResult;
import org.gello.compiler.Compiler;
import org.gello.model.HL7RIM.HL7Model;
import org.gello.runtime.CompileMessage;
import org.gello.runtime.GelloException;
import org.gello.runtime.GelloModelException;
import org.gello.runtime.GelloRunnable;
import org.gello.runtime.IGelloModel;

public class CommonActions
{
	private static CommonActions commonActions;
	private static IWorkbenchWindow window;

	public static CommonActions getInstance()
	{
		if (commonActions == null)
		{
			window = PlatformUI.getWorkbench().getActiveWorkbenchWindow();
			return new CommonActions();
		}
		return commonActions;
	}

	/**
	 * Loads a file given a path.  Also sets status information.
	 * @param path The path of the file.
	 * @return The file data.
	 */
	public String loadFileData(String path)
	{
		Status status = null;
		try
		{
			status = (Status) window.getActivePage().showView(Status.ID, null, IWorkbenchPage.VIEW_CREATE);
			status.setText(status.getText() + "Opening " + path + " ... ");
		}
		catch (PartInitException e1)
		{
			e1.printStackTrace();
		}

		String fileData = null;
		try
		{
			fileData = Application.getManager().openFile(path).replaceAll("\n", "\r\n");
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		if (fileData == null)
			status.setText(status.getText() + "FAILED!\r\n");
		else
			status.setText(status.getText() + "done.\r\n");

		return fileData;
	}
	
	/**
	 * Loads a file given a path.  Does not set status information.
	 * @param path The path of the file.
	 * @return The file data.
	 */
	public String loadFileDataWithoutStatus(String path)
	{
		String fileData = null;
		try
		{
			fileData = Application.getManager().openFile(path).replaceAll("\n", "\r\n");
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}

		return fileData;
	}

	/**
	 * Compile a file given it's path and data.
	 * @param filePath The path of the file.
	 * @param fileData The data of the file.
	 * @return The compiler output.
	 */
	public String compileFile(String filePath, String fileData)
	{
		StringBuffer output = new StringBuffer();
		output.append("GELLO Compiler 1.0.0\n");
		output.append("© Copyright 2007 Pfizer, Inc. All Rights Reserved.\n");
		output.append(filePath + "\n");

		String packageName = Util.getTempPackageName();
		String className = Util.getTempClassName();
		String path = Util.getTempPath();

		String javaClassPath = Util.getJarPath("Gello.jar") + Util.getJarPath("HL7_Model.jar") + Util.getJarPath("jaxws/activation.jar") + Util.getJarPath("jaxws/jaxb-api.jar")
				+ Util.getJarPath("jaxws/jsr173_api.jar") + Util.getJarPath("jaxws/jaxb-impl.jar");

		IGelloModel model = new HL7Model();

		CompileResult result = new Compiler().compile(fileData, model, packageName, className, path, javaClassPath);

		if (result.isSuccessful())
		{
			output.append("Compilation successful.  No errors found.\n");
		}
		else
		{
			CompileMessage[] messages = result.getMessages();
			ResourceBundle compilerMessages = ResourceBundle.getBundle("org.gello.client.properties.compilermessages", Locale.ENGLISH);
			output.append("Compilation failed.  Errors found.\n");

			for (int i = 0; i < messages.length; i++)
			{
				CompileMessage message = messages[i];
				String thisMessage;

				if (compilerMessages != null)
				{
					thisMessage = compilerMessages.getString(message.getKey());
					if (message.getParameters().length > 0)
					{
						if (message.getKey().equals("msg.error.cannot.resolve"))
						{
							Object[] params = message.getParameters();
							String invalidString = (String) params[0];
							thisMessage = MessageFormat.format(thisMessage, invalidString);
						}
						else
						{
							thisMessage = MessageFormat.format(thisMessage, message.getParameters());
						}
					}
				}
				else
				{
					thisMessage = MessageFormat.format("{0}", message.getParameters());
				}
				output.append(thisMessage);
			}
		}

		return output.toString();
	}

	/**
	 * Tests a file given its path and ata.
	 * @param testFilePath The path of the file.
	 * @param testFileData The data of the file.
	 * @return The test output.
	 */
	public String testFile(String testFilePath, String testFileData)
	{
		StringBuffer output = new StringBuffer();
		output.append("GELLO Test Case Validator 1.0.0\n");
		output.append("© Copyright 2007 Pfizer, Inc. All Rights Reserved.\n");
		output.append(testFilePath + "\n");

		try
		{
			InputStream testStream = new ByteArrayInputStream(testFileData.getBytes("UTF-8"));
			IGelloModel model = new HL7Model();
			CompileMessage[] messages = model.parseData(testStream);

			if (messages.length == 0)
			{

				// Successful test case compilation
				output.append("Test case valid.  No errors found.\n\n");

				String expressionPath = getExpressionPathFromTestPath(testFilePath);
				String expressionContent = "";
				try
				{
					expressionContent = Application.getManager().openFile(expressionPath);
				}
				catch (ServerException_Exception e)
				{
					e.printStackTrace();
				}

				// Expression compilation
				output.append("GELLO Compiler 1.0.0\n");
				output.append("© Copyright 2007 Pfizer, Inc. All Rights Reserved.\n");
				output.append(expressionPath + "\n");

				String packageName = Util.getTempPackageName();
				String className = Util.getTempClassName();
				String path = Util.getTempPath();

				String javaClassPath = Util.getJarPath("Gello.jar") + Util.getJarPath("HL7_Model.jar") + Util.getJarPath("jaxws/activation.jar") + Util.getJarPath("jaxws/jaxb-api.jar")
						+ Util.getJarPath("jaxws/jsr173_api.jar") + Util.getJarPath("jaxws/jaxb-impl.jar");

				CompileResult result = new Compiler().compile(expressionContent, model, packageName, className, path, javaClassPath);

				if (result.isSuccessful())
				{
					// Successful expression compilation
					output.append("Expression compilation successful.  No errors found.\n\n");

					// Run test case
					output.append("GELLO Test Case Runner 1.0.0\n");
					output.append("© Copyright 2007 Pfizer, Inc. All Rights Reserved.\n");

					GelloRunnable runner = (GelloRunnable) Util.loadClassAndReturnInstance(packageName + "." + className, model.getClass().getClassLoader());
					testStream.reset();
					model.loadData(testStream);

					if (runner.isRunnable(model))
					{
						output.append("Test case runnable with expression.\n\n");
						String runtimeResult = runner.run(model);
						int startIndex = runtimeResult.indexOf('(') + 1;
						int endIndex = runtimeResult.indexOf(')');
						String outputResult = runtimeResult.substring(startIndex, endIndex);
						output.append("Output: " + outputResult + "\r\n");

					}
					else
					{
						output.append("Test case unrunnable with expression.\n");
					}
				}
				else
				{
					output.append("Expression compilation failed.  Errors found.\n");
				}
			}
			else
			{
				output.append("Test case invalid.  Errors found.\n");

				for (int i = 0; i < messages.length; i++)
					output.append(MessageFormat.format("{0}", messages[i].getParameters()));
			}
			testStream.close();

		}
		catch (GelloModelException e)
		{
			// There was an error parsing the data in the model... this should
			// never happen
			e.printStackTrace();

		}
		catch (GelloException e)
		{
			// There was an error compiling or running the expression... this
			// should never happen
			e.printStackTrace();

		}
		catch (IOException e)
		{
			// There was an error resettting or closing the stream... this
			// should never happen
			e.printStackTrace();

		}

		return output.toString();
	}

	/**
	 * Returns the path from a test case to it's parent expression.
	 * 
	 */
	private String getExpressionPathFromTestPath(String testPath)
	{
		for (int i = 0; i < 3; i++)
		{
			int endIndex = testPath.lastIndexOf('/');
			testPath = testPath.substring(0, endIndex);
		}
		return testPath += "/Gello Expression";
	}

	/**
	 * Seet the text area in TestOutput or CompilerOutput to some text.
	 * @param viewId The id of the view to use (TestOutput or CompilerOutput).
	 * @param text The text to set.
	 */
	public void setOutput(String viewId, String text)
	{
		try
		{
			IViewPart view = window.getActivePage().showView(viewId);

			if (view instanceof TestOutput)
				((TestOutput) view).setText(text);
			else if (view instanceof CompilerOutput)
				((CompilerOutput) view).setText(text);
		}
		catch (PartInitException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a test output file given the file path of the test and the output.
	 * @param testFilePath The file path of the test (not the output file to be created).
	 * @param output The output to put in the new file.
	 */
	public void createTestOutputFile(String testFilePath, String output)
	{
		// Create test output file...
		String outputFolder = testFilePath.substring(0, testFilePath.lastIndexOf("/"));

		Date date = new Date();
		String DATE_FORMAT = "yyyy-MM-dd HH.mm.ss"; // no colons because it's
		// not supported in windows
		// filenames.
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		String outputName = sdf.format(date);
		try
		{
			GelloNode newFile = Application.getManager().addStructure(Session.getInstance().getCurrentProject().getTemplateType(), "File", outputFolder, outputName);
			GelloNode parentNode = ((BrowserViewContentProvider) Browser.viewer.getContentProvider()).getParentFromPath((GelloNode) Browser.viewer.getInput(), testFilePath);
			parentNode.getChildren().add(newFile);
			Browser.viewer.refresh();

			Application.getManager().checkinFile(outputFolder + "/" + outputName, output);
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * Creates an expected results output file given the file path of the test and the output.
	 * @param testFilePath The file path of the test (not the output file to be created).
	 * @param output The output to put in the new file.
	 */
	public void createExpectedResultsFile(String testFilePath, String output)
	{
		// Create test output file...
		String outputFolder = testFilePath.substring(0, testFilePath.lastIndexOf("/"));
		String outputName = "Expected Results";

		try
		{
			GelloNode newFile = Application.getManager().addStructure(Session.getInstance().getCurrentProject().getTemplateType(), "File", outputFolder, outputName);
			GelloNode parentNode = ((BrowserViewContentProvider) Browser.viewer.getContentProvider()).getParentFromPath((GelloNode) Browser.viewer.getInput(), testFilePath);
			parentNode.getChildren().add(newFile);
			Browser.viewer.refresh();

			Application.getManager().checkinFile(outputFolder + "/" + outputName, output);
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Creates a test output file without that project being open.
	 * @param templateType The template type of the project.
	 * @param testFilePath The file path of the test (not the output file to be created).
	 * @param output The output to put in the new file.
	 */
	public void createTestOutputFileWithoutBrowser(String templateType, String testFilePath, String output)
	{
		// Create test output file...
		String outputFolder = testFilePath.substring(0, testFilePath.lastIndexOf("/"));

		Date date = new Date();
		// no colons because it's not supported in windows filenames.
		String DATE_FORMAT = "yyyy-MM-dd HH.mm.ss";
		SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);

		String outputName = sdf.format(date);
		try
		{
			Application.getManager().addStructure(templateType, "File", outputFolder, outputName);
			Application.getManager().checkinFile(outputFolder + "/" + outputName, output);
		}
		catch (ServerException_Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Compile all Gello Expressions in a given GelloNode.
	 * @param gelloNode The gelloNode to compile all expressions in.
	 * @param output An ArrayList to store the output in.
	 */
	public void compileAll(GelloNode gelloNode, ArrayList<String> output)
	{
		if (gelloNode.isCompilable())
			output.add(compileFile(gelloNode.getPath(), loadFileDataWithoutStatus(gelloNode.getPath())));

		for (GelloNode child : gelloNode.getChildren())
			compileAll(child, output);
	}

	/**
	 * Test all test cases in a given GelloNode.
	 * @param gelloNode The gelloNode to test all test cases in.
	 * @param output An ArrayList of String[]'s to store the output in.  Item 0 is the path, item 1 is the output.
	 */
	public void testAll(GelloNode gelloNode, ArrayList<String[]> output)
	{
		if (gelloNode.isTestable())
		{
			String testOutput = testFile(gelloNode.getPath(), loadFileDataWithoutStatus(gelloNode.getPath()));
			output.add(new String[] { gelloNode.getPath(), testOutput });
		}
		for (GelloNode child : gelloNode.getChildren())
			testAll(child, output);
	}

}
