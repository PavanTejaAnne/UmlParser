package umlparser.cmpe202;

import java.util.List;
import com.github.javaparser.ast.AccessSpecifier;

public class Decorate
 {

	public static final char PUBLIC = '+';
	public static final char PRIVATE = '-';
	
	
	public char getAccessModifier(AccessSpecifier accessSpecifier){
		
		if (accessSpecifier == AccessSpecifier.PUBLIC) {
			return PUBLIC;
		}else if(accessSpecifier == AccessSpecifier.PRIVATE){
			return PRIVATE;
		}else{
			return '0';
		}
	}
    public String buildProcessType(String string ,int integer, ConfigClasses classd) {
		String result = "";
		
			if(string.contains(Notations.SQRBRACKET)){
				int index = string.indexOf('[');
				string = string.substring(0, index);
				string = string + Notations.ARRBRACKET;
				result = string;
				result = ":" + result;
				
			} else if(!(string.contains(Notations.INTEGER)) && !(string.contains(Notations.STRING))
					&& !(string.contains(Notations.VOID))){
				if(integer == 1){
					
					classd.assosClass(string);
					result = null;
				}
				if(integer == 2 && !string.equals("")){
					String[] var = string.split(" ");
					
					classd.usesRelation(var[0]);
					
					result = var[1]+":"+var[0];
				}
			}else if (string.contains(Notations.ANGLEBRKTOPEN)){
				
				classd.assosCollection(string);
				result = null;
			} else{
				result = string;
				result = ":" + result;
			}		
		
		return result;
	}
	
	
	public <list> String adjustSqrBrackets(List<list> inputList) {
		String result = " ";
		result = inputList.toString();
		result = result.substring(1, result.length() - 1);
		return result;
	}
	
	public String convertParameters(String incomingString){
		String result = " ";
		String[] var = incomingString.split(" ");
		result = var[1] + ":" + var[0];	
		return result;
	}
		
}
