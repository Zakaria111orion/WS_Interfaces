package jpu2016.javapetri.model;

public class Position {
	
	/*Cette classe d�finit tout simplement la position d'une cellule dans une bo�te de Petri
	Pour cela, les deux attributs x et y vont sp�cifier la distance entre la cellule et 
	le bord & la cellule et la hauteur de la bo�te, respectivement!
	Les attributs sont "final", cela veut dire qu'ils ne peuvent �tre modifi�s une fois qu'ils 
	sont initialis�s*/
	
	//Attributs
	private final int x; //Quand un attribut est "private", on ne peut lui acc�der qu'� travers un Getter public
	private final int y;

	//Constructeur: permet d'instanier la classe, c-�-d cr�er des objets
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
