package oop.ex2.variables;

import oop.ex2.parsers.StructureValidator;

public class Char extends Variable {
	private static final java.lang.String CHAR_REGEXP = "\\\'\\S\\\'";
	
	public Char(java.lang.String type, Object value, boolean isFinal) {
		super(type, value, isFinal);
	}

	@Override
	public boolean validateType() {
		if(value ==null){
			return true;
		}
		if (StructureValidator.matchStringToRegexp(CHAR_REGEXP, value.toString())){
			return true;
		}
		return false;
	}

	@Override
	public java.lang.String getExample() {
		return "'c'";
	}


}
