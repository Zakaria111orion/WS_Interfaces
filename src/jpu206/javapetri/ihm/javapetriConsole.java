package jpu206.javapetri.ihm;

import java.util.Observable;

import jpu2016.javapetri.model.JavaPetri;

public class javapetriConsole implements Observer{
    
	private JavaPetri javapetri ;	
	
	public javapetriConsole(JavaPetri d){
	  this.javapetri = d;
	  javapetri.addObserver(this);
	}

	
	
	public void show() {
		
		System.out.println("Step : " + this.javapetri.getStep() + "\tJavaCell alive : " + this.javapetri.getNbJavaCellAlive() + "\tdead : " + this.javapetri.getNbJavaCellDead());
		
		}



	public void update() {
		this.show();
	 }


	public void update(Observable observable, Object arg) {
		
	}
	
	
	
	
}
