package com.umlparser;

import java.util.ArrayList;
import java.util.HashMap;

public class GeneratedClass {
	
	static GeneratedClass genCls = new GeneratedClass();	
	
    private String lastString;
	private ArrayList<String> completedConn;
	private HashMap<String, ClassDetails> classMap;
	
	
	private GeneratedClass() {
		
		classMap = new HashMap<String, ClassDetails>();
		completedConn = new ArrayList<String>();
		
	}
	
	public static GeneratedClass getInstance(){
		return genCls;
	}
	
	
	
	public void addConnection(String addConn) {
		completedConn.add(addConn);
	}
    
    
    public void updateClassMap(String hashKey, ClassDetails classDetails){
		classMap.put(hashKey, classDetails);
	}

	public boolean checkConnection(String checkConn){
		
		boolean flagFound = false;
		
		for(String conns : completedConn){
			if(checkConn.equals(conns));
				flagFound = true;
		}
		return flagFound;		
	}
	
	
	public String getlastString(){		
		return lastString;
	}

    public String joinClass(){
        return joinClass;
    }
	
}
