/*
 * Test.java
 *
 * Generated Thu May 06 17:15:21 CEST 2010 by org.gello.compiler.Compiler.
 *
 */


import org.gello.runtime.*;
import org.gello.model.HL7RIM.*;
import org.gello.model.HL7RIM.generated.*;


public class Test implements GelloRunnable {

    public boolean isRunnable(IGelloModel model) {
        return true;
    }

    public String run(IGelloModel model) throws GelloException {
        java.lang.Integer v1 = Integer.valueOf("100");

        return v1 + "";
    }
    
    /**
     *
	 * @param args
	 */
	public static void main(String[] args) {
		
		Test t = new Test();
		IGelloModel g = new HL7Model();
		String output="";
		try {
			output = t.run(g);
		} catch (GelloException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(output);
	}
}
