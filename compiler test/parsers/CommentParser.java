package oop.ex2.parsers;


import java.io.IOException;
import oop.ex2.main.FileReader;
import static oop.ex2.parsers.Regexps.*;

/**
 * Parses comments out of a javas file
 * @author yaelcohen
 */
public class CommentParser {

	private FileReader fileReader;

	/**
	 * Constructor - initializing with a file Reader
	 * @param fileReader from with to read the file and throw away comments
	 */
	public CommentParser(FileReader fileReader){
		this.fileReader = fileReader;
	}

	/**
	 * parses away the comments from the file
	 * does not save them anywhere
	 * @param currentLine gets the current line in the file we are working on
	 * @return the Current line in the file he stopped parsing at
	 * @throws IOException if there is a problem reading the file
	 */
	public String parse(java.lang.String currentLine) throws IOException{
		if (StructureValidator.matchStringToRegexp(comment_mask_1,currentLine)){
			currentLine = fileReader.readLine();
			return currentLine;
		}
		if (StructureValidator.matchStringToRegexp(comment_mask_2,currentLine)){
			while(!StructureValidator.matchStringToRegexp(comment_mask_2_end, currentLine)){
				currentLine = fileReader.readLine();
			}
			currentLine = fileReader.readLine();
			return currentLine;
		}
		if (StructureValidator.matchStringToRegexp(comment_mask_3, currentLine)){
			while(!StructureValidator.matchStringToRegexp(comment_mask_3_end,currentLine)){
				currentLine = fileReader.readLine();
			}
			currentLine = fileReader.readLine();
			return currentLine;
		}
		return currentLine;
	}
}
