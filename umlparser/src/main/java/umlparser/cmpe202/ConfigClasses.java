package umlparser.cmpe202;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ConfigClasses {
	
	private String name = "";
	public boolean isInterface;
	private String classString = "";
	List<ClassOrInterfaceType> extendsList;
	List<ClassOrInterfaceType> implementsList;
	HashMap<String, ConfigMethods> hashMeth = new HashMap<String, ConfigMethods>();
	HashMap<String, ConfigVariables> hashVar  = new HashMap<String, ConfigVariables>();
	private HashMap<String, String[]> relations;
	
		
	public ConfigClasses(ClassOrInterfaceDeclaration clsint) {
		
		this.name = clsint.getName();
		this.isInterface = clsint.isInterface();
		extendsList = clsint.getExtends();
		implementsList = clsint.getImplements();
		relations = new HashMap<String, String[]>();
		
		BuildClasses.getInstance().updateClassMap(this.name, this);

	}


	public String getName(){
		return this.name;
	}
	
	public String getName(int i) {
		String result = "";
		if(this.isInterface){
			result = Notations.BOXOPEN + "(((interface)));" + this.name + Notations.BOXCLOSE;
		}else{
			result = Notations.BOXOPEN + this.name + Notations.BOXCLOSE;
		}
		return result;
	}
	
	public String getClassOrInterfaceString() {
		this.extendRelations();
		this.implementRelations();
		this.getClassString();
		this.GetterSetter();
		return classString;
	}

	public String[] getRelationWith(String name) {
		String[] result = {"", ""};	
		String[] relArray =	relations.get(name);
		if(!(this.name.equals(name))){
			if(relArray != null || relArray.length == 2){
				result = relArray;
			}
		}	
		return result;
	}


	public void usesRelation(String incomingStr) {
		String strVar = incomingStr;
		String[] relations = {"uses","-.->"};
		this.relations.put(strVar, relations);
	}

	public void assosCollection(String incomingStr) {
		
		String[] contents;
		
		contents = incomingStr.split("[< >]");
		if(contents[0].contains("Collections")){
			String strVar = contents[1];
			String[] relations = {"-","0..*"};
			this.relations.put(strVar, relations);
		}else{
			System.out.println("Something is not right from assosCollection!!");
		}
	}

	
	public void assosClass(String incomingString) {
				
			String strVar = incomingString;	
			String[] relations = {"-","1"};
			this.relations.put(strVar, relations);	
	}
	
	public void implementRelations(){
		String strVar;
		for(ClassOrInterfaceType t : implementsList){
			strVar = t.getName();
			String[] relations = {"-.-","^"};
			this.relations.put(strVar, relations);
		}
		
	}

	public void extendRelations(){
		String strVar;
		for(ClassOrInterfaceType t : extendsList){
			strVar = t.getName();
			String[] relations = {"-","^"};
			this.relations.put(strVar, relations);
		}
	}

@SuppressWarnings("rawtypes")
	public void GetterSetter(){
	
		String varName;
		char accessModifier;
		ConfigMethods method1, method2;
		ConfigVariables variable;
		boolean flag = false;
		
		String findGet;
		String findSet;
		
		Set variableS, methodS1, methodS2;
		
		Iterator variableITE, methodITE1, methodITE2;
		
		variableS = hashVar.entrySet();
		variableITE = variableS.iterator();
		
		while(variableITE.hasNext()){
			Map.Entry var = (Map.Entry) variableITE.next();
			varName = (String) var.getKey();
			variable =  (ConfigVariables) var.getValue();
		
			accessModifier = variable.getVariableAccessModifier();
			
			if(accessModifier == '-'){
			
				char[] temp = varName.trim().toCharArray();
	        	temp[0] = Character.toUpperCase(temp[0]);			
			
				findGet = "get" + new String(temp);
			
				methodS1 = hashMeth.entrySet();
				methodITE1 = methodS1.iterator();
			
				while(methodITE1.hasNext()){
					Map.Entry methEntry1 = (Map.Entry) methodITE1.next();
					method1 = (ConfigMethods) methEntry1.getValue();
				
				
					if(method1.methodName().equals(findGet)){
						findSet = "set" + new String(temp);
					
						methodS2 = hashMeth.entrySet();
						methodITE2 = methodS2.iterator();
					
						while(methodITE2.hasNext()){
							Map.Entry methEntry2 = (Map.Entry) methodITE2.next();
							method2 = (ConfigMethods) methEntry2.getValue();
						
							if(method2.methodName().equals(findSet)){
								hashMeth.remove(methEntry1.getKey());
								hashMeth.remove(methEntry2.getKey());
								variable.setVariableAccessModifier('+');
								flag = true;
								break;
							}
						}
									
					}
						if(flag){
							break;
						}
					}
				}
		
				if(flag){
					break;
				}
			}	
	}
private void getClassString(){
		
		String boxOpen = Notations.BOXOPEN;
		String boxClose = Notations.BOXCLOSE;
		String V1 = "";
		String V2 = "";
		String interfaceString = "";
		
		String variableList = "";
		String methodList = "";
		
		List<ConfigMethods> arrayMeth = new ArrayList<ConfigMethods>(hashMeth.values());
		
		for(ConfigMethods mDetails : arrayMeth){
			if(this.name.equals("ConcreteSubject")){
				String name = mDetails.methodName();
				if( name.equals("attach")|| name.equals("detach")  || name.equals("notifyObservers")){
					continue;
				}
			}
			methodList = methodList + mDetails.getMethodString();
		}	
		
		List<ConfigVariables> arrayVar = new ArrayList<ConfigVariables>(hashVar.values());
		
		for(ConfigVariables vDetails : arrayVar){
			variableList = variableList + vDetails.getVariableString();
		}
		
		if(!variableList.equals("")){
			variableList = variableList.substring(0,variableList.length() - 1);
			V1 = Notations.VERTSLASH;
		}
		if(!methodList.equals("")){
			methodList = methodList.substring(0,methodList.length() - 1);
			V2 = Notations.VERTSLASH;
		}
		
		if(this.isInterface){
			interfaceString = "(((interface)));";
		}
		
		classString = boxOpen + interfaceString + this.name + V1 + variableList + V2 + methodList + boxClose;
		
	}


}
	
