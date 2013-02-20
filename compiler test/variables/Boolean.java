package oop.ex2.variables;

import oop.ex2.parsers.StructureValidator;

public class Boolean extends Variable {
	public static final java.lang.String BOOLEAN_TRUE = "true";
	public static final java.lang.String BOOLEAN_FALSE = "false";
	
	public Boolean(java.lang.String type, Object value, boolean isFinal) {
		super(type, value, isFinal);
	}

	@Override
	public boolean validateType() {
		if(value ==null){
			return true;
		}
		if (value.toString().equals(BOOLEAN_FALSE) || value.toString().equals(BOOLEAN_TRUE)){
			return true;
		}
		if (StructureValidator.matchStringToRegexp(Int.INT_REGEXP, value.toString()) || StructureValidator.matchStringToRegexp(Double.DOUBLE_REGEXP, value.toString())){
			return true;
		}
		return false;
	}

	@Override
	public java.lang.String getExample() {
		return "true";
	}

}
