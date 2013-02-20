package oop.ex2.parsers;

import java.util.ArrayList;
import oop.ex2.exceptions.InvalidBlocksInMethodExcetion;
import oop.ex2.main.FileReader;
import static oop.ex2.parsers.Regexps.*;


/**
 * A Method validating class. 
 * Does the parsing from data to a method block of data
 * @author yaelcohen
 */
public class MethodParser {
	private ArrayList<ArrayList<java.lang.String>> methodData;
	private FileReader fileReader;

	/**
	 * The Constructor
	 * @param fileReader - the fileReader to use to find the methods
	 * @param methodData - An array list to save the methods found in 
	 */
	public MethodParser(FileReader fileReader, ArrayList<ArrayList<java.lang.String>> methodData ){
		this.methodData=methodData;
		this.fileReader= fileReader;
	}
	/**
	 * parses a file to method data (array of arrarys)
	 * @param currentLine what is the line we are currently checking to match
	 * @return the current line after running (can be the same if no method was found)
	 * @throws Exception
	 */
	public String parse(java.lang.String currentLine) throws Exception {
		if (StructureValidator.matchStringToRegexp(method_mask_start,currentLine)) {
			ArrayList<String> currentMethod = new ArrayList<String>();
			currentMethod.add(currentLine);
			currentLine = fileReader.readLine();
			// uses a recursive method to find the end of the methods
			currentLine = catchMethod(currentMethod, currentLine);
			methodData.add(currentMethod);
		}
		return currentLine;
	}

	// The recursive method that searches for opening and closing of blocks (method/if/while)
	private String catchMethod(ArrayList<String> currentMethod, java.lang.String currentLine)throws Exception {
		while (!currentLine.equals("-1")) {
			if (StructureValidator.matchStringToRegexp(end_block, currentLine)) {
				currentMethod.add(currentLine);
				currentLine = fileReader.readLine();
				return currentLine;
			}
			CommentParser cParser = new CommentParser(fileReader);
			currentLine = cParser.parse(currentLine);
			if (StructureValidator.matchStringToRegexp(method_mask_start,currentLine)
					|| StructureValidator.matchStringToRegexp(if_start_block,currentLine)
					|| StructureValidator.matchStringToRegexp(while_start_block, currentLine)) {
				currentMethod.add(currentLine);
				currentLine = fileReader.readLine();
				currentLine = catchMethod(currentMethod,currentLine);
				continue;
			}
			currentMethod.add(currentLine);
			currentLine = fileReader.readLine();
		}
		throw new InvalidBlocksInMethodExcetion();
	}

}
