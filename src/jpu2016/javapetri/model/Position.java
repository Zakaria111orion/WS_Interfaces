package jpu2016.javapetri.model;

public class Position {
	
	/*Cette classe définit tout simplement la position d'une cellule dans une boîte de Petri
	Pour cela, les deux attributs x et y vont spécifier la distance entre la cellule et 
	le bord & la cellule et la hauteur de la boîte, respectivement!
	Les attributs sont "final", cela veut dire qu'ils ne peuvent être modifiés une fois qu'ils 
	sont initialisés*/
	
	//Attributs
	private final int x; //Quand un attribut est "private", on ne peut lui accéder qu'à travers un Getter public
	private final int y;

	//Constructeur: permet d'instanier la classe, c-à-d créer des objets
	public Position(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	
	//Getters 
	public int getX() {
		return this.x;
	}

	public int getY() {
		return this.y;
	}
}
