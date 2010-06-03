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


package org.gello.compiler;

import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of <code>VirtualCompiler</code> which makes a system
 * call to jikes.
 * Shamelessly stolen from RSL, with adaptations.
 */
public class JavaCompiler {
    
    private static Logger log = Logger.getLogger(JavaCompiler.class.getName());
    
    /** The compile-time classpath for javac */
    private String classPath;
    /** The stderr output from the compiler */
    private String stderrBuf = "";

    public JavaCompiler() {
        this.classPath = System.getProperty("java.class.path");
    }

    public JavaCompiler(String classPath) {
        this.classPath = classPath;
    }

    /** Compile the named Java source file.
     * @return True iff the compilation succeeded.
     */
    public boolean compile(String fileName) {
        String javaHome = System.getenv("JAVA_HOME");
        String fileSeparator = System.getProperty("file.separator");
        String javac = "\"" + javaHome + fileSeparator + "bin" + fileSeparator + "javac\"";
        String cmd = javac + " -classpath \"" + classPath + "\" " + fileName;
        ProgramRunner runner = new ProgramRunner(ProgramRunner.NO_STDOUT);
        int exitValue = runner.execute(cmd);
        stderrBuf = runner.getStderr();
        if (exitValue != 0) {

            return false;
        }
        else {

            return true;
        }
    }
    
    /** Returns the stderr written by the compiler. */
    public String getStderr() {
        return stderrBuf;
    }
}
