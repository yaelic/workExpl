package oop.ex2.parsers;

/**
 * orgenazing all the regexps 
 **/
public class Regexps {
	//////////REGEXPs////////////////////////////////
	public static final java.lang.String LONGEST_TYPE = "10";
	public static final java.lang.String SHORTEST_TYPE = "3";
	public static final java.lang.String TYPE_MASK = "([a-zA-Z]{" + SHORTEST_TYPE + ","+ LONGEST_TYPE + "}) ";
	public static final java.lang.String NAME_MASK = "([a-zA-Z0-9]+\\S*)";
	public static final java.lang.String VALUE_MASK = "([-a-zA-Z0-9.-\\\\\"\\\\\']+.*)";
	public static final java.lang.String EOL = " *\\;\\s*";
	public static final java.lang.String BOL = "\\s*";
	
	//MEMBERS 
	public static final java.lang.String member_mask_only_define = BOL+TYPE_MASK +BOL+ NAME_MASK + EOL;
	public static final java.lang.String member_mask_only_define_in_method_param = BOL+TYPE_MASK +BOL+ NAME_MASK ;
	public static final java.lang.String member_mask_define_and_initialize = BOL+TYPE_MASK+BOL
			+ NAME_MASK + " *= *" + VALUE_MASK +EOL;
	public static final java.lang.String member_mask_define_and_initialize_final = BOL+ "final "+member_mask_define_and_initialize;
	public static final java.lang.String member_mask_initialize = BOL +NAME_MASK + " *= *"
			+ VALUE_MASK + EOL;
	
	public static final String type_mask = "([a-zA-Z]{" + SHORTEST_TYPE + ","+ LONGEST_TYPE + "}) ";
	public static final String method_name_mask = "([a-zA-Z]+\\S*)";
	public static final String value_mask = "[-a-zA-Z0-9._\\\\\"\\\\\']+";

	// Methods and Blocks
	public static final String method_mask_start = BOL + type_mask+ BOL +method_name_mask +BOL+ "\\((.*)\\)" +BOL+ "\\{";
	public static final String method_mask_start_only_call = BOL +method_name_mask +BOL+ "\\(";
	public static final String return_mask_no_value = BOL + "return" + EOL;
	public static final String return_mask_with_value = BOL+ "return"+ BOL + " (" + value_mask+")" + EOL;
	public static final String method_call_mask = BOL + method_name_mask + "\\((.*)\\)" + EOL;
	public static final String end_block = BOL + "}";
	public static final String if_start_block = BOL + "if *\\((.*)\\)"+BOL+"\\{";
	public static final String while_start_block = BOL + "while"+BOL+"\\((.*)\\)"+BOL+"\\{";
	
	//COMMENTS
	public static final String comment_mask_1 = "//.*";
	public static final String comment_mask_2 = "/\\*.*";
	public static final String comment_mask_2_end = ".*\\*/";
	public static final String comment_mask_3 = "/\\*{2}.*";
	public static final String comment_mask_3_end = comment_mask_2_end;

	
}
