package com.umlparser;

import com.github.javaparser.ast.body.ClassOrInterfaceDeclaration;
import com.github.javaparser.ast.type.ClassOrInterfaceType;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class ClassDetails {
	
	private String name = "";
	public boolean isInterface;
	private String classString = "";
	List<ClassOrInterfaceType> extendsList;
	List<ClassOrInterfaceType> implementsList;
	
	
	HashMap<String, MethodDetails> hashMeth = new HashMap<String, MethodDetails>();
	HashMap<String, VariableDetails> hashVar  = new HashMap<String, VariableDetails>();
	private HashMap<String, String[]> relations;
	
		
	public ClassDetails(ClassOrInterfaceDeclaration clsint) {
		
		this.name = clsint.getName();
		this.isInterface = clsint.isInterface();
		extendsList = clsint.getExtends();
		implementsList = clsint.getImplements();
		relations = new HashMap<String, String[]>();
		
		GeneratedClass.getInstance().updateClassMap(this.name, this);

	}


	public String getName(){
		return this.name;
	}
	
	public String getClassOrInterfaceString() {
		this.extendRelations();
		this.implementRelations();
		return classString;
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
	
