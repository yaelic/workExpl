package oop.ex2.parsers;

import java.util.TreeMap;
import java.util.regex.Matcher;

import oop.ex2.exceptions.ChangingFinalVarException;
import oop.ex2.exceptions.NonInitializedVarException;
import oop.ex2.exceptions.VariableNameExistsException;
import oop.ex2.variables.VarFactory;
import oop.ex2.variables.Variable;

public class VariableValidator {
	
	/**
	 * validates the mmebrrs one by one- for different regexps (structures)
	 * @param matcher
	 * @param memberMap
	 * @param memberMapScope
	 * @throws Exception
	 */
	public static void validateMember1(Matcher matcher,TreeMap<java.lang.String,Variable> memberMap, TreeMap<java.lang.String,Variable> memberMapScope) throws Exception{
		String type = matcher.group(1).trim();
		String name = matcher.group(2).trim();
		Object value = matcher.group(3).trim();
		if (memberMap.containsKey(name)){
			throw new VariableNameExistsException();
		}
		if (memberMapScope!=null && memberMapScope.containsKey(name)){
			throw new VariableNameExistsException();
		}
		Variable var = VarFactory.create(type,value,false);
		memberMap.put(name, var);
	}
	/**
	 * validates the mmebrrs one by one- for different regexps (structures)
	 * @param matcher
	 * @param memberMap
	 * @param memberMapScope
	 * @throws Exception
	 */
	public static void validateMember2(Matcher matcher,TreeMap<java.lang.String,Variable> memberMap, TreeMap<java.lang.String,Variable> memberMapScope) throws Exception{
		String type = matcher.group(1).trim();
		String name = matcher.group(2).trim();
		Object value = matcher.group(3).trim();
		boolean isFinal = true;
		if (memberMap.containsKey(name)){
			throw new VariableNameExistsException();
		}
		if (memberMapScope!=null && memberMapScope.containsKey(name)){
			throw new VariableNameExistsException();
		}
		Variable var = VarFactory.create(type,value,isFinal);
		memberMap.put(name, var);
	}
	/**
	 * validates the mmebrrs one by one- for different regexps (structures)
	 * @param matcher
	 * @param memberMap
	 * @param memberMapScope
	 * @throws Exception
	 */
	public static void validateMember3(Matcher matcher,TreeMap<java.lang.String,Variable> memberMap, TreeMap<java.lang.String,Variable> memberMapScope) throws Exception{
		String type = matcher.group(1).trim();
		String name = matcher.group(2).trim();
		if (memberMap.containsKey(name)){
			throw new VariableNameExistsException();
		}
		if (memberMapScope!=null && memberMapScope.containsKey(name)){
			throw new VariableNameExistsException();
		}
		Variable var = VarFactory.create(type,null,false);
		memberMap.put(name, var);
	}
	/**
	 * validates the mmebrrs one by one- for different regexps (structures)
	 * @param matcher
	 * @param memberMap
	 * @param memberMapScope
	 * @throws Exception
	 */
	public static void validateMember4(Matcher matcher,TreeMap<java.lang.String,Variable> memberMap, TreeMap<java.lang.String,Variable> memberMapScope) throws Exception{
		String name = matcher.group(1).trim();
		Object value = matcher.group(2).trim();
		if (memberMap.containsKey(name)){
			if (memberMap.get(name).isFinal()){
				throw new ChangingFinalVarException();
			}
			memberMap.get(name).setValue(value);
			return;
		}
		if (memberMapScope!=null && memberMapScope.containsKey(name)){
			if (memberMap.get(name).isFinal()){
				throw new ChangingFinalVarException();
			}
			memberMapScope.get(name).setValue(value);
			return;
		}
		throw new NonInitializedVarException();
	}
}
