package oop.ex2.variables;

import oop.ex2.parsers.StructureValidator;

public class Int extends Variable {
	public static final java.lang.String INT_REGEXP = "-??[0-9]+";

	public Int(java.lang.String type, Object value, boolean isFinal) {
		super(type, value, isFinal);
	}

	@Override
	public boolean validateType() {
		if(value ==null){
			return true;
		}
		if (StructureValidator.matchStringToRegexp(INT_REGEXP, value.toString())){
			return true;
		}
		return false;
	}

	@Override
	public java.lang.String getExample() {
		return "2";
	}


}
