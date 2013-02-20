package oop.ex2.variables;

import oop.ex2.parsers.StructureValidator;

public class String extends Variable {
	public static final java.lang.String STRING_REGEXP = "\\\".*\\\"";
	public String(java.lang.String type, Object value, boolean isFinal) {
		super(type, value, isFinal);
	}

	@Override
	public boolean validateType() {
		if(value ==null){
			return true;
		}
		if (StructureValidator.matchStringToRegexp(STRING_REGEXP, value.toString())){
			return true;
		}
		return false;
	}

	@Override
	public java.lang.String getExample() {
		return "\"Example\"";
	}

}
