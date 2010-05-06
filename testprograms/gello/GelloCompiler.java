/* Info:
 * Diese Software steht unter der GNU Lesser General Public License.
 * Die Lizenzbedingungen koennen im beigefuegten Textdokument nachgelesen werden.
 *
 * This software is distributed under the GNU Lesser General Public License.
 * You may read the terms of the licence in the attached text file.
 *
 * Autor: Alfred Franz
 * Heidelberg den 30.04.2010
 */
import org.gello.runtime.IGelloModel;
import org.gello.model.HL7RIM.HL7Model;
import org.gello.compiler.*;

/**
 * This class offers a command line gello-to-java-compiler.
 */
class GelloCompiler
	{

	public static void main(String[] args)
		{
		if  (args.length != 2)
			{
			System.out.println("Error - 2 parmeters needed: (1) sourcefile, (2) output class name");
			return;
			}


		//load source from file
		String source = readFile(args[0]);
		if (source==null)
			{
			System.out.println("Error while reading source file.");
			return;
			}
		source.replaceAll("\n", "\r\n"); // This replacement is also done in the gello client,
										 // so we do it here as well. Is this a hint to character set
										 // problems?
		System.out.println("Successfully loaded file:\n"+source);

		//create HL7RIM model
		IGelloModel myHL7Model = new HL7Model();

		//parse needed strings
		String fileSeparator = System.getProperty("file.separator");
     	String packageName = args[0]+"_package";
		String className = args[1];
		String path = "./";
		String javaClassPath = "./"; //perhaps we need more libraries later, then make a subdirectory .\\libs for example

		//create compiler object
		org.gello.compiler.Compiler myCompiler = new org.gello.compiler.Compiler();
		System.out.println("Trying to compile...");

		// call the compiler method, the params of this method are as follows:
		// param 1 (source):	 	The Unicode GELLO text to be compiled.
     	// param 2 (model):		 	The GELLO data model to compile against, usually the HL7 RIM.
     	// param 3 (packageName):	The Java package name to emit in the compiled code.
     	// param 4 (className):		The name of the Java class to be generated.
     	// param 5 (path): 			The absolute or relative filesystem path where the generated Java
     	//							and class files should be stored.
     	// param 6 (javacClassPath):The compile-time classpath for the Java compiler. This should
     	// 							include the org.gello.runtime package at a minimum.
     	CompileResult result = myCompiler.compile(source,myHL7Model, packageName, className, path, javaClassPath);
     	
     	if (result.isSuccessful()) 
     		System.out.println("\n \nFinished! Generated file " + className + ".java, which you may now compile with a java compiler.");
     	else System.out.println("\n \nError during compile process!");
		}

	/**
	 * This method reads all charakters from a text file and return them as a string.
	 */
	static String readFile(String filename)
		{
		String returnValue = "";

		/* First variant of file reading, comment in to activate
		try
			{
    		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream(filename), "UTF8"));
    		int charakter = in.read();
    		while (charakter!=-1)
    			{

    			returnValue += (char)charakter;
    			charakter = in.read();
    			}
			}
		catch (Exception e) {return null;}
		*/

		//second variant of file reading
		java.io.RandomAccessFile Datei;
		try
			{
			Datei = new java.io.RandomAccessFile(filename, "rw");
			Datei.seek(0);

			while (Datei.getFilePointer() < Datei.length())
				{
				returnValue += Datei.readLine() + "\n";
				}

			}
        catch (Exception e){ return null; }

        return returnValue;
		}
	}