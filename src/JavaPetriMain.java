import java.sql.SQLException;

import jpu206.javapetri.controller.javapetricontroller;

public abstract class JavaPetriMain {

	public static void main(final String[] args) throws SQLException{
		
		javapetricontroller petri = new javapetricontroller();
		
		petri.start();
		
		
	}
}
