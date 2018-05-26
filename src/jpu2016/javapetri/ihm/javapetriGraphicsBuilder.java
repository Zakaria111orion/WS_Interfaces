package jpu2016.javapetri.ihm;

import java.awt.Graphics;

import jpu2016.javapetri.model.*;
import jpu206.javapetri.ihm.EasyFrame;

public class javapetriGraphicsBuilder{
	
		private final JavaPetri javaPetri;
		public javapetriGraphicsBuilder(final JavaPetri javaPetri) {
		 this.javaPetri = javaPetri;
		 }
		private void clearGraphics(final Graphics graphics) {
		 graphics.clearRect(0, 0, this.javaPetri.getWidth() * EasyFrame.ZOOM,
		this.javaPetri.getHeight() * EasyFrame.ZOOM);
		 }
		
		
		public void applyModelToGraphic(final Graphics graphics) {
		 this.clearGraphics(graphics);
		 this.drawAllJavaCells(graphics);
		 
		 //graphics.drawString("Step : " + this.javaPetri.getStep(), 0, 20);
		 //graphics.drawString("JavaCell alive : " + this.javaPetri.getNbJavaCellAlive(), 0, 40);
	     //graphics.drawString("JavaCell dead : " + this.javaPetri.getNbJavaCellDead(),0, 60);
		 }
		
		
		
		public void drawAllJavaCells(Graphics graphics){
			Object[] arrLocal;
	        arrLocal = this.javaPetri.getJavaCells().toArray();
	        for (int i = arrLocal.length-1; i>=0; i--)
	            ((JavaCell)arrLocal[i]).drawCell(graphics);
			
		}
		
		
		
		
		} 
   



