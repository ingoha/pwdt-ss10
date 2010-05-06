/*
 * Test.java
 *
 * Generated Thu May 06 17:15:21 CEST 2010 by org.gello.compiler.Compiler.
 *
 */

package GelloTestFile.txt_package;

import org.gello.runtime.*;

public class Test implements GelloRunnable {

    public boolean isRunnable(IGelloModel model) {
        return true;
    }

    public String run(IGelloModel model) throws GelloException {
        java.lang.Integer v1 = Integer.valueOf("100");

        return v1 + "";
    }
}
