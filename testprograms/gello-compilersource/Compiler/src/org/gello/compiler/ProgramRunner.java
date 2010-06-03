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

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * A class to execute external programs, adapted from the RSL JavaCompiler class.
 * @author Erik Horstkotte
 */
public class ProgramRunner {

    private static Logger log = Logger.getLogger(ProgramRunner.class.getName());

    /* Special behavior flag values */
    /** Disable stdout capture */
    public static final int NO_STDOUT = 1;

    /** Special behavior flags */
    private int behavior_flags;
    /** The stdout written by the program */
    private String stdoutBuf = "";
    /** The stderr written by the program */
    private String stderrBuf = "";

    /** Creates a new instance of ProgramRunner */
    public ProgramRunner() {
        this.behavior_flags = 0;
    }

    /** Creates a new instance of ProgramRunner with the specified behavior flags */
    public ProgramRunner(int behavior_flags) {
        this.behavior_flags = behavior_flags;
    }

    /** Execute a command-line program, capturing its stdout and stderr.
     * @return The program's integer exit status.
     */
    public int execute(String cmd) {
        
        log.fine("cmd=" + cmd);

        stdoutBuf = "";
        stderrBuf = "";

        Runtime r = Runtime.getRuntime();
        Process p = null;

        // execute and get the process.
        try {
            
            // Run it...
            p = r.exec(cmd);
            
            // Capture stdout and stderr
            if ((behavior_flags & NO_STDOUT) == 0) {
                stdoutBuf = captureStream(p.getInputStream());
            }
            stderrBuf = captureStream(p.getErrorStream());

            log.fine("exitValue=" + p.exitValue());
        
            // Return the exit status
            return p.exitValue();
        }
        catch (Exception ex) {

            ex.printStackTrace();
            return -1;
        }
    }

    /** Gets the stdout written by the program */
    public String getStdout() {
        return stdoutBuf.toString();
    }

    /** Gets the stderr written by the program */
    public String getStderr() {
        return stderrBuf.toString();
    }
    
    /** Close a stream, ignoring exceptions. */
    protected void close(InputStream s) {
        
        if (s != null) {
            
            try {
                s.close();
            }
            catch (IOException ie) {
                // Ignore it
            }
        }
    }

    /** Close a Writer, ignoring exceptions. */
    protected void close(Writer w) {
        
        if (w != null) {
            
            try {
                w.close();
            }
            catch (IOException ie) {
                // Ignore it
            }
        }
    }

    /** Capture a string to a StringBuffer */
    protected String captureStream(InputStream s) throws IOException {

        BufferedInputStream in = new BufferedInputStream(s);
        StringWriter out = new StringWriter();
        try {

            // Read fully
            int c;
            while ((c = in.read()) != -1)
                out.write(c);
        }
        finally {
            
            close(out);
            close(in);
        }

        // Return the captured text
        return out.toString();
    }
}
