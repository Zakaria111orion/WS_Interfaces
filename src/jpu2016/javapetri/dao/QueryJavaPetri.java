package jpu2016.javapetri.dao;

import jpu2016.javapetri.model.JavaPetri;

class QueryJavaPetri {
	
	
	
	public static String getQueryInsert(JavaPetri jp) {
		
			 return "INSERT INTO `javapetri` (`ID`, `LABEL`,`WIDTH`, `HEIGHT`)" + 
			 " VALUES (NULL, '"+jp.getLabel()+"'," + jp.getWidth() + ", " + jp.getHeight() + ");";
			 
	}
	
	
	public String getQuerySelectByld(int id) {
		return "SELECT * FROM `javapetri` WHERE ID="+id+";";
		
	}
	
	
	
	
	public String getQuerySelectAll() {
		return "SELECT * FROM `javapetri`;";}

}

