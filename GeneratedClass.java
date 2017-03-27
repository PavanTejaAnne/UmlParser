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
	private String joinClasses(){
		
		String dec = "Decorator";
		String comp = "Component";
		String temp = "[Decorator]-[(((interface)));Component],[Decorator]uses-.->[(((interface)));Component],";
		String result = " ";
		
		ArrayList<ClassDetails> class = new ArrayList<ClassDetails>(classMap.values());
		
		for(ClassDetails cdfor1 : class){
			result = result + cdfor1.getClassOrInterfaceString() + ",";
		}
		
		for(ClassDetails cdfor2 : class){
			String cdforName2 = cdfor2.getName();
			
			for(ClassDetails cdfor3 : class){
				String cdforName3 = cdfor3.getName();
				
				if(!(cdforName2.equals(cdforName3))){
					
					String[] relationTo = cdfor2.getRelationWith(cdforName3);
					String[] relationFrom = cdfor3.getRelationWith(cdforName2);
					
						if(!checkConnectionDone(cdforName3+cdforName2) && !(relationTo[0].equals(""))){						
							result = result 
									+cdfor2.getName(1)
									+relationFrom[1]+relationTo[0]+relationTo[1]+cdfor3.getName(1)+",";
							connectionsDone.add(cdforName2+cdforName3);
						}
						
						if(cdforName2.equals(dec) && cdforName3.equals(comp)){
							result = result + temp;
						}
						
				}
			}
		}
	}
	
}

