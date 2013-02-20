package oop.ex2.parsers;

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.regex.Matcher;
import static oop.ex2.parsers.Regexps.*;
import oop.ex2.exceptions.BadConditionException;
import oop.ex2.exceptions.BadMethodParamsException;
import oop.ex2.exceptions.CallToNonExisintMethodException;
import oop.ex2.exceptions.InvaidLineInMethodException;
import oop.ex2.exceptions.MethodNameExistsException;
import oop.ex2.exceptions.MissingReutnFinalLine;
import oop.ex2.methods.Method;
import oop.ex2.variables.VarFactory;
import oop.ex2.variables.Variable;

/**
 * A method validating class.
 * has mostly static methods that help validating the sinner structure of the method
 * only works once we have block data of the method
 * @author yaelcohen
 */
public class MethodValidator {
	private TreeMap<String, oop.ex2.methods.Method> methodMap;
	private TreeMap<String, Variable> globalMembers;
	private ArrayList<ArrayList<String>> methodData;

	/**
	 * Constructor gets maps on order to fill them
	 * @param methodMap a method map (can be empty) that will be files with the parsed methods
	 * @param globalMembers - the global membered that were already parsed out of the java file
	 */
	public MethodValidator(TreeMap<String, oop.ex2.methods.Method> methodMap ,TreeMap<String, Variable> globalMembers){
		this.globalMembers =globalMembers;
		this.methodMap =methodMap;
	}


	/**
	 * The main validator - gets the block of method data and parses it to a Method object
	 * them validates the line in the method 
	 * @param methodData - and array of String holding all the method Data
	 * @throws Exception
	 */
	public void methodValidate(ArrayList<ArrayList<String>> methodData) 
			throws Exception {
		this.methodData=methodData;
		// parsers first all the methods in the program from the first line
		parseMethodFirstLine();
		for (ArrayList<java.lang.String> method : methodData) {
			Method thisMethod = methodMap.get(parseMethodName(method.get(0)));
			// validates the return structure
			validateMethodLastReturn(method);
			// parses the rest of the lines one by one
			validateMethodLineByLine(thisMethod, method);
		}
	}
	// a small helper to get just the name of the method from the first line
	private static String parseMethodName(java.lang.String firstLine){
		Matcher m = StructureValidator.stringToRegexpMatcher(method_mask_start, firstLine);
		java.lang.String methodName = m.group(2).trim();
		return methodName;
	}
	// parsers first all the methods in the program from the first line
	private void parseMethodFirstLine() throws Exception {
		for (ArrayList<String> method : methodData) {
			String firstLine = method.get(0);
			TreeMap<String, Variable> neededParams = new TreeMap<String, Variable>();
			Matcher m = StructureValidator.stringToRegexpMatcher(method_mask_start, firstLine);
			java.lang.String retrunType = m.group(1).trim();
			java.lang.String methodName = m.group(2).trim();
			java.lang.String methodParams = m.group(3).trim();
			String[] methodParamList = methodParams.split(",");
			ArrayList<String> paramsNeededTypes = new ArrayList<String>();
			for (String param : methodParamList) {
				VarParser.parseVarsFromMehotdParams(neededParams,paramsNeededTypes, param);
			}
			Variable returnTypeVar = VarFactory.create(retrunType, null, false);
			Method thisMethod = new Method(returnTypeVar, neededParams, paramsNeededTypes);
			if(methodMap.containsKey(methodName)){
				throw new MethodNameExistsException();
			}
			methodMap.put(methodName, thisMethod);
		}
	}

	// validates that line before the } contains a return statement
	private void validateMethodLastReturn(ArrayList<String> methodData) throws Exception {
		int length = methodData.size();
		if (!StructureValidator.matchStringToRegexp(return_mask_no_value, methodData.get(length-2)) 
				&& !StructureValidator.matchStringToRegexp(return_mask_with_value, methodData.get(length-2))){
			throw new MissingReutnFinalLine();
		}
	}

	// goes over the lines in the method and checks they are valid line- main validation method
	private void validateMethodLineByLine(Method thisMethod,ArrayList<String> methodData) throws Exception {
		TreeMap<java.lang.String, Variable> localVars = thisMethod.getLocalVars();
		for (String line : methodData) {
			if (StructureValidator.matchStringToRegexp(if_start_block, line)
					|| StructureValidator.matchStringToRegexp(while_start_block, line)) {
				validateCondition(line, localVars);
			} else if (StructureValidator.matchStringToRegexp(method_call_mask,
					line)) {
				validateMethodCall(line, localVars);
			} else if (StructureValidator.matchStringToRegexp(end_block, line)
					|| StructureValidator.matchStringToRegexp(BOL, line)) {
				continue;
			} else if (StructureValidator.matchStringToRegexp(return_mask_no_value, line)
					|| StructureValidator.matchStringToRegexp(return_mask_with_value, line)) {
				handleReturnValue(thisMethod, line);
			} else if (VarParser.memberMatcherOneLine(globalMembers,localVars,line)) {
				continue;
			} else {
				throw new InvaidLineInMethodException();
			}
		}
	}

	private  void handleReturnValue(Method thisMethod, String line) {
		Object returnValue;
		if(StructureValidator.matchStringToRegexp(return_mask_with_value, line)){
			Matcher matcher = StructureValidator.stringToRegexpMatcher(return_mask_with_value, line);
			returnValue = matcher.group(1).trim();
			thisMethod.setReturnValue(returnValue);
		}
	}
	/**
	//gets the whole parameter block when greedy searching 
	private java.lang.String parseCallMethodParams(String methodCallLineParams,TreeMap<String, Variable> localVars) throws Exception {
		int methodIndexNameEnd =  StructureValidator.stringFindRegexp(method_mask_start_only_call,methodCallLineParams, "end");
		java.lang.String methodHead =methodCallLineParams.substring(0,methodIndexNameEnd);
		java.lang.String methodNoHead =methodCallLineParams.substring(methodIndexNameEnd);
		System.out.println("my ugly method");
		System.out.println(methodNoHead);
		System.out.println(methodHead);
		System.out.println("-----");
		System.out.println(StructureValidator.stringFindRegexp(methodNoHead, method_mask_start_only_call, "start"));
		while(StructureValidator.stringFindRegexp(methodNoHead, method_mask_start_only_call, "start") != -1){
			System.out.println("1");
			int anotherMethodIndex = StructureValidator.stringFindRegexp(methodNoHead, methodCallLineParams, "start");
			System.out.println("2");
			if (anotherMethodIndex < methodCallLineParams.indexOf(')')) {
				System.out.println("3");
				methodCallLineParams = parseCallMethodParams(methodCallLineParams.substring(anotherMethodIndex),localVars);
				continue;
			} 
			String currentPart = methodNoHead.substring(StructureValidator.stringFindRegexp(methodNoHead, method_mask_start_only_call, "start"), methodNoHead.indexOf(')'));
			Variable returnType= validateMethodCall((currentPart).trim(), localVars);
			methodNoHead = methodNoHead.replace(currentPart, returnType.getExample());
			System.out.println(methodCallLineParams);
		}
		System.out.println(methodNoHead);
		System.out.println(methodHead);
		Variable returnType= validateMethodCall(methodHead+methodNoHead, localVars);
		return returnType.getType();
	}
	*/

	private Variable validateMethodCall(String line,TreeMap<String, Variable> localVars) throws Exception {
		Matcher matcher = StructureValidator.stringToRegexpMatcher(method_call_mask, line);
		String methodName= matcher.group(1).trim();
		String callingParams = matcher.group(2).trim();
		String[] callingParamsList = callingParams.split(",");
		if (!methodMap.containsKey(methodName)){
			throw new CallToNonExisintMethodException();
		}
		Method calledMethod = methodMap.get(methodName);
		if (calledMethod.getParamsNeededTypes().size() != callingParamsList.length){
			throw new BadMethodParamsException();
		}
		//goes over all the accepted vars and checks id they match the methods needs
		// if they do it return the methods return type variable
		for (int i=0; i<callingParamsList.length; i++){
			if (localVars.containsKey(callingParamsList[i]) || globalMembers.containsKey(callingParamsList[i])){
				Variable var =  localVars.containsKey(callingParamsList[i]) ? localVars.get(callingParamsList[i]): globalMembers.get(callingParamsList[i]);
				if (var.getValue() == null){
					throw new BadMethodParamsException();
				}
				try{
					Variable varTest = VarFactory.create(calledMethod.getParamsNeededTypes().get(i),var.getValue(),false);
					varTest.validateType();
				}
				catch(Exception e){
					throw new BadMethodParamsException();
				}
			}
			try{
				Variable var = VarFactory.create(calledMethod.getParamsNeededTypes().get(i),callingParamsList[i],false);
				var.validateType();
			}
			catch(Exception e){
				throw new BadMethodParamsException();
			}
		}
		return calledMethod.getReturnType();

	}
	// validaes the condition params
	private void validateCondition(java.lang.String line,TreeMap<java.lang.String, Variable> localVars) throws Exception {
		Matcher matcher;
		if (StructureValidator.matchStringToRegexp(if_start_block, line)){
			matcher =StructureValidator.stringToRegexpMatcher(if_start_block, line);
		}
		else {
			matcher =StructureValidator.stringToRegexpMatcher(while_start_block, line);
		}
		String condition = matcher.group(1).trim();
		if (condition.equals("true") || condition.equals("false")){
			return;
		}
		if (localVars.containsKey(condition)){
			Variable thisVar = localVars.get(condition);
			String thisVarType = localVars.get(condition).getType();
			if (thisVarType.equals("boolean") || thisVarType.equals("int") || thisVarType.equals("double")){
				if (!(thisVar.getValue()==null)){
					return;
				}
			}
		}
		if (globalMembers.containsKey(condition)){
			Variable thisVar = globalMembers.get(condition);
			String thisVarType = thisVar.getType();
			if (thisVarType.equals("boolean") || thisVarType.equals("int") || thisVarType.equals("double")){
				if (!(thisVar.getValue()==null)){
					return;
				}
			}
		}
		if(StructureValidator.matchStringToRegexp(method_call_mask, condition)){
			String returnType = validateMethodCall(condition, localVars).getType();
			if(returnType!= null){
				//Matcher matcherMathod = StructureValidator.stringToRegexpMatcher(method_call_mask, line);
				//String methodName= matcherMathod.group(1).trim();
				//String returnType= methodMap.get(methodName).getReturnType().getType();
				if(returnType.equals("boolean")|| returnType.equals("int") || returnType.equals("double")){
					return;
				}	
			}
		}
		throw new BadConditionException();
	}

}
