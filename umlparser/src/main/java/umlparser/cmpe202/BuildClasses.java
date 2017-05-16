package umlparser.cmpe202;

import java.util.ArrayList;
import java.util.HashMap;

public class BuildClasses {
	
	static BuildClasses buildClass = new BuildClasses();	
	
    private String lastString;
	private ArrayList<String> completedConn;
	private HashMap<String, ConfigClasses> classMap;
	
	
	private BuildClasses() {
		
		classMap = new HashMap<String, ConfigClasses>();
		completedConn = new ArrayList<String>();
		
	}
	
	public static BuildClasses getInstance(){
		return buildClass;
	}
	
	
	
	public void addConnection(String addConn) {
		completedConn.add(addConn);
	}
    
    
    public void updateClassMap(String hashKey, ConfigClasses classDetails){
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
		lastString = this.joinClasses();		
		return lastString;
	}

	private String joinClasses(){
		
		 final String dec = "Decorator";
		 final String comp = "Component";
		 final String temp = "[Decorator]-[(((interface)));Component],[Decorator]uses-.->[(((interface)));Component],";
		 String result = "";
		
		ArrayList<ConfigClasses> classd = new ArrayList<ConfigClasses>(classMap.values());
		
		for(ConfigClasses cdfor1 : classd){
			result = result + cdfor1.getClassOrInterfaceString() + ",";
		}
		
		for(ConfigClasses cdfor2 : classd){
			String cdforName2 = cdfor2.getName();
			
			for(ConfigClasses cdfor3 : classd){
				String cdforName3 = cdfor3.getName();
				
				if(!(cdforName2.equals(cdforName3))){
					
					String[] to = cdfor2.getRelationWith(cdforName3);
					String[] from = cdfor3.getRelationWith(cdforName2);
					
						if(!checkConnection(cdforName3+cdforName2) && !(to[0].equals(""))){						
							result = result 
									+cdfor2.getName(1)
									+from[1]+to[0]+to[1]+cdfor3.getName(1)+",";
							completedConn.add(cdforName2+cdforName3);
						}
						
						if(cdforName2.equals(dec) && cdforName3.equals(comp)){
							result = result + temp;
						}
						
				}
			}
		}
		if(!(result.equals("")))
		{
		result = result.substring(0,result.length() - 1);
		}
		result =  Notations.URL + result; //Generate URL for tests
		return result;
	}
	
}

