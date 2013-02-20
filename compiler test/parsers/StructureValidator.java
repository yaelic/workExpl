package oop.ex2.parsers;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import oop.ex2.exceptions.UnknownLineFormatException;
import oop.ex2.main.FileReader;
import oop.ex2.methods.Method;
import oop.ex2.variables.Variable;




/**
 * The managing Structure parsing and validating class
 * @author yaelcohen
 * this class uses other parsers and validators in order to
 * validate the structure of the javaS file
 */
public class StructureValidator {
	private ArrayList<ArrayList<String>> methodData;
	private TreeMap<java.lang.String,Variable> memberMap;
	private TreeMap<java.lang.String,Method> methodMap;
	private String currentLine;
	private FileReader fileReader;

	/**
	 * The constructor
	 * @param fileReader - the file to read from and validate
	 * @throws Exception - if there is a problem 
	 */
	public StructureValidator(FileReader fileReader) throws Exception{
		this.fileReader= fileReader;
		currentLine=fileReader.readLine();
		memberMap = new TreeMap<java.lang.String,Variable>();
		methodData = new ArrayList<ArrayList<String>>();
		methodMap = new TreeMap<java.lang.String,Method>();
	}

	/**
	 * A useful static method that checks of a line matches a regexp
	 * @param regexp - regexp to match
	 * @param line - to match to the regexp
	 * @return - true if they match
	 */
	public static boolean matchStringToRegexp(String regexp, String line){
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(line);
		boolean b = m.matches();
		return b;
	}

	/**
	 * Another useful static method the return a matched matcher between a line & regexp
	 * @param regexp - regexp to match
	 * @param line - to match to the regexp
	 * @return - a started matcher
	 */
	public static Matcher stringToRegexpMatcher(String regexp, String line){
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(line);
		m.matches();
		return m;
	}

	public static int stringFindRegexp(String regexp, String line, String startOrEnd){
		Pattern p = Pattern.compile(regexp);
		Matcher m = p.matcher(line);
		m.find();
		if (m.find()){
			if (startOrEnd == "start"){
				return m.start(); 
			}
			return m.end();
		}
		return -1;
	}

	/**
	 * The main matching function which goes over the lines in the programm and:
	 * 1. Divides them to methods and members while validating the member structure
	 * 2. Validates the lines of the found methods
	 * 3. saves the found data into two maps: Variables and methods
	 * @throws Exception
	 */
	public void mainMatcher() throws Exception{
		String lineStartLoop = "";
		MethodParser methodParser = new MethodParser(fileReader, methodData);
		VarParser varParser = new VarParser(memberMap, fileReader);
		CommentParser commentParser = new CommentParser(fileReader);

		while(!currentLine.equals("-1") && currentLine!=lineStartLoop){
			//System.out.println("the line -----" +currentLine);
			lineStartLoop = currentLine;
			if (matchStringToRegexp("\\s*",currentLine)){
				currentLine = fileReader.readLine();
			}
			currentLine = commentParser.parse(currentLine);
			currentLine = methodParser.parse(currentLine);
			currentLine = varParser.parse(currentLine);	
		}
		if (currentLine==lineStartLoop){
			throw new UnknownLineFormatException();
		}
		// only after extracting the methods and global members can we check the methods inside
		MethodValidator methodValidator = new MethodValidator(methodMap, memberMap);
		methodValidator.methodValidate(methodData);
	}

	/**
	 * Gets the Method map
	 * @return the method Map
	 */
	public TreeMap<String, Method> getMethods(){
		return methodMap;
	}

	/**
	 * gets the member Map
	 * @return the member map
	 */
	public TreeMap<String, Variable> getMembers(){
		return memberMap;
	}

}


