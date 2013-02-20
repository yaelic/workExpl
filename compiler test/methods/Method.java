package oop.ex2.methods;

import java.util.*;

import oop.ex2.exceptions.MismatchTypeValueException;
import oop.ex2.exceptions.NoReturnValueException;
import oop.ex2.exceptions.ReturningValueForVoidMethod;
import oop.ex2.exceptions.VoidMemberException;
import oop.ex2.variables.VarFactory;
import oop.ex2.variables.Variable;

/**
 * Method class- defined a method and its inner members
 * @author yaelcohen
 */
public class Method{
	private Variable returnType;
	private TreeMap<String, Variable> paramsNeeded;
	private ArrayList<String> paramsNeededTypes;
	private TreeMap<String, Variable> localVars;
	private ArrayList<Object> returnValues;

	/**
	 * The constructor needed to build a method
	 * @param retrunType - a Variable describing the return type
	 * @param paramsNeeded - a Map of the Variables needed and their local names 
	 * @param paramsNeededTypes - a list representing the order in with to expect the parameters types
	 */
	public Method(Variable retrunType, TreeMap<String, Variable> paramsNeeded,ArrayList<String> paramsNeededTypes) {
		this.returnType=retrunType;
		this.paramsNeeded=paramsNeeded;
		this.localVars= new TreeMap<String, Variable>();
		this.returnValues = new ArrayList<Object>();
		this.paramsNeededTypes = paramsNeededTypes;
		localVars.putAll(paramsNeeded);
	}
	
	/**
	 * gets the local variable map
	 * @return A map with the names and local variables
	 */
	public TreeMap<String, Variable> getLocalVars() {
		return localVars;
	}
	
	/**
	 * Sets the return values ( a list of objects)
	 * @param returnValue
	 */
	public void setReturnValue(Object returnValue){
		this.returnValues.add(returnValue);
	}
	
	/**
	 * gets the expected return type variable
	 * @return the return type variable
	 */
	public Variable getReturnType(){
		return returnType;
	}
	
	/**
	 * gets the Map of the needed params when calling the method
	 * @return map of the needed params for calling this method
	 */
	public TreeMap<String, Variable> getParamsNeeded(){
		return paramsNeeded;
	}
	
	/**
	 * gets the ordered list of the params types needed
	 * @return the list of ordered types needed
	 */
	public ArrayList<String> getParamsNeededTypes(){
		return paramsNeededTypes;
	}
	
	// validates the local members are correct
	private boolean validateMembers() throws MismatchTypeValueException, VoidMemberException{
		for (String member : localVars.keySet()){
			if (!localVars.get(member).validateType()){
				throw new MismatchTypeValueException();
			}
			if (localVars.get(member).getType()=="void"){
				throw new VoidMemberException();
			}
		}
		return true;
	}
	
	// validates the return values match the return types
	private boolean validateReturnValues() throws Exception{
		if (returnType.getType().equals("void")){
			if (returnValues.size()!=0){
				throw new ReturningValueForVoidMethod();
			}
			return true;
		}
		for (Object returnValue:returnValues){
			if (returnValues.size()==0){
				throw new NoReturnValueException();
			}
			Variable varTest = VarFactory.create(returnType.getType(), returnValue, false);
			varTest.validateType();
			if (varTest.getValue() ==null){
				throw new NoReturnValueException();
			}
		}
		return true;
	}
	 
	/**
	 * validates the logical level of the method
	 * @returns true if the file is valid 
	 * @throws Exception if there is a logical problem in the file
	 */
	public boolean validate() throws Exception{
		if (validateMembers() && validateReturnValues()){
		return true;
		}
		return false;
	}
	

}
