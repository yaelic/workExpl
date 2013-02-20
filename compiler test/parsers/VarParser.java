package oop.ex2.parsers;

import java.util.*;

import static oop.ex2.parsers.Regexps.*;

import java.util.regex.Matcher;
import oop.ex2.variables.Variable;

/**
 * parses the variables out of data lines
 * @author yaelcohen
 *
 */
public class VarParser {
	TreeMap<java.lang.String,Variable> memberMap;
	oop.ex2.main.FileReader  fileReader;
	
	public VarParser(TreeMap<java.lang.String,Variable> memberMap, oop.ex2.main.FileReader fileReader){
		this.memberMap = memberMap;
		this.fileReader= fileReader;
	}
	
	/**
	 * the main oarse method
	 * @param currentLine
	 * @return
	 * @throws Exception
	 */
	public java.lang.String parse(java.lang.String currentLine) throws Exception{
		if(StructureValidator.matchStringToRegexp(member_mask_define_and_initialize, currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_define_and_initialize, currentLine);
			VariableValidator.validateMember1(m, memberMap,null);
			currentLine = fileReader.readLine();
		}
		else if(StructureValidator.matchStringToRegexp(member_mask_define_and_initialize_final,currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_define_and_initialize_final, currentLine);
			VariableValidator.validateMember2(m, memberMap, null);
			currentLine = fileReader.readLine();
		}
		else if (StructureValidator.matchStringToRegexp(member_mask_only_define ,currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_only_define, currentLine);
			VariableValidator.validateMember3(m, memberMap, null);
			currentLine = fileReader.readLine();
		}
		else if (StructureValidator.matchStringToRegexp(member_mask_initialize,currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_initialize, currentLine);
			VariableValidator.validateMember4(m, memberMap, null);
			currentLine = fileReader.readLine();
		}
		return currentLine;
		}
    

    public static void parseVarsFromMehotdParams(TreeMap<String, Variable> neededParamsForMethod, ArrayList<String> paramsNeededTypes, String param) throws Exception{
    	param = param.trim();
    	if (param == null || param.equals("")){
    		return;
    	}
		Matcher matcher = StructureValidator.stringToRegexpMatcher(member_mask_only_define_in_method_param, param);
		paramsNeededTypes.add(matcher.group(1));
		VariableValidator.validateMember3(matcher,neededParamsForMethod, null);
    }

	
	public static boolean memberMatcherOneLine(TreeMap<java.lang.String,Variable> globalVars,TreeMap<java.lang.String,Variable> localVars, java.lang.String currentLine) throws Exception{
		if(StructureValidator.matchStringToRegexp(member_mask_define_and_initialize, currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_define_and_initialize, currentLine);
			VariableValidator.validateMember1(m, localVars, globalVars);
		}
		else if(StructureValidator.matchStringToRegexp(member_mask_define_and_initialize_final,currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_define_and_initialize_final, currentLine);
			VariableValidator.validateMember2(m, localVars, globalVars);
		}
		else if (StructureValidator.matchStringToRegexp(member_mask_only_define ,currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_only_define, currentLine);
			VariableValidator.validateMember3(m, localVars, globalVars);
		}
		else if (StructureValidator.matchStringToRegexp(member_mask_initialize,currentLine)){
			Matcher m = StructureValidator.stringToRegexpMatcher(member_mask_initialize, currentLine);
			VariableValidator.validateMember4(m, localVars, globalVars);
		}
		return true;
		}	
}
