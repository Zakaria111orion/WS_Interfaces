package jpu2016.javapetri.dao;

import jpu2016.javapetri.model.JavaCell;

class QueryJavaCell {
	
	public static String getQueryInsert(JavaCell jc, int idJp) {
		 return "INSERT INTO `rgbcell` (`ID`, `X`, `Y`, `STRENGTH`, `COLOR`, `ID_JAVAPETRI`)" + 
				 " VALUES (NULL, "+jc.getPosition().getX()+"," + jc.getPosition().getY() + ", " + jc.getStrength()+"," + jc.getColor().getRGB()+"," +idJp +");";
		 
	}

}
