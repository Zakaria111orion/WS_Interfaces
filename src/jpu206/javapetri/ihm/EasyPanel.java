package jpu206.javapetri.ihm;

import java.awt.Graphics;
import java.util.Observable;
import javax.swing.JPanel;

import jpu2016.javapetri.ihm.javapetriGraphicsBuilder;

public class EasyPanel extends JPanel implements Observer{

	private static final long serialVersionUID = 1L;
	final javapetriGraphicsBuilder g;
	
	public EasyPanel(final javapetriGraphicsBuilder graphicsBuilder) {
		 this.g = graphicsBuilder;
		 
		 }
		
		
	protected void paintComponent(final Graphics graphics) {
		 this.g.applyModelToGraphic(graphics);
		 }
		
	
	
	
	
	
	
	public void update() {
	this.repaint();
		
	}

	
	public void update(Observable observable, Object arg) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
	
	
	
	
	

}
