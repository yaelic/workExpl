package oop.ex2.variables;

import oop.ex2.exceptions.UnknownTypeException;

public class VarFactory {
	
	private static final java.lang.String VOID = "void";
	private static final java.lang.String BOOLEAN_TYPE_NAME = "boolean";
	private static final java.lang.String CHAR_TYPE_NAME = "char";
	private static final java.lang.String DOUBLE_TYPE_NAME = "double";
	private static final java.lang.String INT_TYPE_NAME = "int";
	private static final java.lang.String STRING_TYPE_NAME = "String";

	public static Variable create(java.lang.String type, Object value, boolean isFinal ) throws Exception{
		Variable var ;
		//System.out.println(type);
		if (type.equals(STRING_TYPE_NAME)){
			var = new String(STRING_TYPE_NAME, value, isFinal);
		}
		else if (type.equals(INT_TYPE_NAME)){
			var = new Int(INT_TYPE_NAME, value, isFinal);
		}
		else if (type.equals(DOUBLE_TYPE_NAME)){
			var = new Double(DOUBLE_TYPE_NAME, value, isFinal);
		}
		else if (type.equals(CHAR_TYPE_NAME)){
			var = new Char(CHAR_TYPE_NAME, value, isFinal);
		}
		else if (type.equals(BOOLEAN_TYPE_NAME)){
			var = new Boolean(BOOLEAN_TYPE_NAME, value, isFinal);
		}
		else if (type.equals(VOID)){
			var = new Void(VOID, value, isFinal);
		}
		else {
			throw new UnknownTypeException();
		}
		return var;
	}

}
