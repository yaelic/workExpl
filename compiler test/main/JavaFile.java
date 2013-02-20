package oop.ex2.main;

import java.util.TreeMap;

import oop.ex2.exceptions.LogicalMethodException;
import oop.ex2.exceptions.MismatchTypeValueException;
import oop.ex2.exceptions.VoidMemberException;
import oop.ex2.methods.Method;
import oop.ex2.variables.Variable;

/**
 * A class representing a java (s) file 
 * @author yaelcohen
 */
public class JavaFile {
	private TreeMap<String,Variable> members;
	private TreeMap<String,Method> methods;

	/**
	 * The constructor initializing the java file object with its members and methods.
	 * @param members - a map of variables and their names
	 * @param methods - a map of methods and their names
	 */
	public JavaFile(TreeMap<String,Variable> members,TreeMap<String,Method> methods){
		this.members=members;
		this.methods=methods;
	}
	// Ekronit if you would need to to run the methods i would have done this here

	/**
	 * validates the Inner logic of the java s file
	 * @return true if the file is valid
	 * @throws Exception - if somthing is wring in the file
	 */
	public boolean validate() throws Exception{
		for (String member : members.keySet()){
			if (!members.get(member).validateType()){
				throw new MismatchTypeValueException();
			}
			if (members.get(member).getType()=="void"){
				throw new VoidMemberException();
			}
		}
		for (String method : methods.keySet()){
			methods.get(method).validate();
		}
		return true;
	}
}
