package jpu2016.javapetri.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class JavaCell {
	
	//Cette classe d�finit une cellule. A c�t� de chaque ligne (ou bien avant chaque m�thode
	//je mettrai une petite description

	private final JavaPetri	javaPetri; //Chaque Cellule appartient � une seule  bo�te Petri d�finie � travers cet attribut
	private final Position	position; //Chaque Cellule se retrouve � une position pr�cise (x,y) dans la bo�te Petri (voir classe Position)
	private Boolean	alive; //Cet attribut bool�en permet de savoir si la Cellule est en vie ou bien morte
	private final int strength; //Cet attribut d�finit la force d'une Cellule � travers un nombre entier
	private final Color	color; //Chaque Cellule a une couleur: rouge, vert ou bleu 

	
	//Construcuteur 
	public JavaCell(final JavaPetri javaPetri, final Position position, final int strength, final Color color) {
		this.javaPetri = javaPetri;
		this.position = position;
		this.strength = strength;
		this.alive = true; //Chaque cellule est intialement (par d�faut) en vie
		this.color = color;
	}

	//Getters: sont utilis�s pour r�cup�rer les valeurs des attributs d'un objet 
	
	public JavaPetri getJavaPetri() {//Permet de savoir � quelle Petri appartient la cellule
		return this.javaPetri;
	}

	public Position getPosition() {//Permet de r�cup�rer la postion (x, y) d'une cellule
		return this.position;
	}

	public int getStrength() {//Permet de r�cup�rer la force d'une cellule
		return this.strength;
	}
	
	public Color getColor() {//Permet de r�cup�rer la couleur de la cellule
		return this.color; //Le type Color existe par d�faut dans la bib .awt
	}

	//Methods 
	
	public void drawCell(final 	Graphics graphics) {
		graphics.setColor(this.getColor());
		graphics.fillOval( this.getPosition().getY(),this.getPosition().getX(),10,10);
	
	}
	
	private final Boolean isPositionAvailable(final Position position) {
		//Cette m�thode permet de savoir si une postion dans la bo�te Petri est libre ou pas
		//En d'autres termes, si elle est occup�e par une cellule ou non, elle retourne True
		//si la position est libre, non sinon!
		
		return ((position.getX() >= 0) && (position.getX() <= this.getJavaPetri().getWidth()) && (position.getY() >= 0)
				&& (position.getY() <= this.getJavaPetri().getHeight()));
	}
	
	
	//il y a deux m�thodes getPositionNear, une sans arguments, l'autre avec un seul argument
	//l'une �tant une surcharge de l'autre 
	
	private ArrayList<Position> getPositionNear(final Position position) {
		// A partir d'une position pr�cise (param�tre en entr�e), cette m�thode permet
		//de r�cup�rer une liste (utilisation de ArrayList) de positions disponibles 
		//(et proches) dans la bo�te
		
		final ArrayList<Position> positionNear = new ArrayList<>(); //D�claration de la structure dans laquelle on va enregistrer les positions disponibles
		for (int xi = -1; xi <= 1; xi++) {
			for (int yi = -1; yi <= 1; yi++) {
				if ((Math.abs(xi) != Math.abs(yi)) || ((this.getJavaPetri().getStep() % 2) == 1)) {
					final Position p = new Position(position.getX() + xi, position.getY() + yi);
					if (this.isPositionAvailable(p)) {
						positionNear.add(p);
					}
				}
			}
		}
		return positionNear;
	}
	private ArrayList<Position> getPositionNear() {
		//Cette m�thode fait tout simplement appel � la m�thode avec argument. Quand elle est
		//appel�e avec un objet, elle retournera la liste des positions disponibles pr�s de la
		//position o� se trouve cet objet
		return this.getPositionNear(this.getPosition());
	}

	
	protected Boolean okReproduce(final Position position) {
		//Cette m�thode permet de savoir si une reproduction de cellule est faite � une position
		//donn�e en param�tre. Elle retourne un Vrai ou un Faux suite � une compraison de couleur
		//entre la position (donn�e en param�tre) et la couleur "Blac"
		return this.getJavaPetri().getColorFromPosition(position).getRGB() == Color.WHITE.getRGB();
	}
	
	protected Color getReproduceColor(final Position position) {
		//La m�thode permet de d�finir la couleur de la cellule reproduite dans une position
		//pr�cise (donn�e en parm�tre). Comme chaque couleur est d�finie � base des trois couleurs
		//rouge, vert et bleu, on va r�cup�rer la concentration de chaque couleur de la cellule,
		//la multiplier par la force de cette cellule (Strength) afin de cr�er la nouvelle 
		//couleur 
		int red = this.getColor().getRed() * this.getStrength();
		int green = this.getColor().getGreen() * this.getStrength();
		int blue = this.getColor().getBlue() * this.getStrength();
		final ArrayList<Position> positionnear = this.getPositionNear(position);

		for (final Position p : positionnear) {
			final Color c = this.getJavaPetri().getColorFromPosition(p);
			red += c.getRed();
			green += c.getGreen();
			blue += c.getBlue();
		}
		red = (red / (positionnear.size() + this.getStrength()));
		green = (green / (positionnear.size() + this.getStrength()));
		blue = (blue / (positionnear.size() + this.getStrength()));
		return new Color(red, green, blue);
	}
	
	private void reproduce() {
		//Cette m�thode permet de faire une reproduction de cellules. Elle permet, � partir de la
		//de l'objet (qui fait l'appel) de cr�er d'autres cellules dans les positions proches
		// (d�termin�es gr�ce � la m�thode getPositionNear) et qui sont en reproduction 
		for (final Position position : this.getPositionNear()) {
			if (this.okReproduce(position)) {
				this.getJavaPetri().addJavaCell(new JavaCell(this.getJavaPetri(), position, this.getStrength(), this.getReproduceColor(position)));
			}
		}
	}
	
	public void live() {
		this.reproduce(); //Faire une reproduction de la cellule gr�ce � la m�thode reproduce
		this.die(); //tuer la cellule
	}

	
	public Boolean isAlive() {//Tester si la cellule est en vie
		return this.alive;
	}

	public void die() {//Tuer la cellule, c-�-d rendre l'attribut alive � faux
		this.alive = false;
	}

	
	

}