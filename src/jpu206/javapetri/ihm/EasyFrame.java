package jpu206.javapetri.ihm;

import javax.swing.JFrame;

import jpu2016.javapetri.model.JavaPetri;
import jpu2016.javapetri.ihm.javapetriGraphicsBuilder;

public class EasyFrame extends JFrame{

	private static final long serialVersionUID = 1L;
	public static int ZOOM = 10;
	
	
	public EasyFrame(JavaPetri d) {
		super("EasyFrame");
		javapetriGraphicsBuilder g = new javapetriGraphicsBuilder(d);
		EasyPanel EZP = new EasyPanel(g);
		d.addObserver(EZP);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setContentPane(EZP) ;
		setSize(500, 500);
		setVisible(true);
		
		
	}
	
	
	
	
	

}
