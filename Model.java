package com.umlparser;
import java.util.List;
import com.github.javaparser.ast.AccessSpecifier;

public class Models
 {

	public final char PUBLIC = '+';
	public final char PRIVATE = '-';
	
	
	public char getAccessModifier(AccessSpecifier accessSpecifier){
		
		if (accessSpecifier == AccessSpecifier.PUBLIC) {
			return PUBLIC;
		}else if(accessSpecifier == AccessSpecifier.PRIVATE){
			return PRIVATE;
		}else{
			return '0';
		}
	}
	
	public String convertParameters(String incomingString){
		String result = " ";
		
		String[] var = incomingString.split(" ");
		result = var[1] + ":" + var[0];
		
		return result;
	}
		
}
