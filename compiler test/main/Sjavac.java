package oop.ex2.main;

import java.io.IOException;
import oop.ex2.parsers.StructureValidator;
/**
 * The main "compiler" class
 * @author yaelcohen
 * runs the parsers and validators
 */
public class Sjavac{

	/**
	 * The main runner
	 * @param args - expecting the Sjava file to validate
	 */
	public static void main(String[] args) {
		try {
			String filePath= args[0];
			// Reads the file
			FileReader fileReader = new FileReader(filePath);
			// parses the file to java files maps of method and members
			StructureValidator struct = new StructureValidator(fileReader);
			struct.mainMatcher();
			// creates a java file object and validates it
			JavaFile myCode = new JavaFile(struct.getMembers(), struct.getMethods());
			myCode.validate();
		}
		catch (IOException io){
			System.exit(2);
		}
		catch (Exception e1){
			System.exit(1);
		}
		System.exit(0);
	}

}
