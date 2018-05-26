package jpu2016.javapetri.model;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class JavaCell {
	
	//Cette classe définit une cellule. A côté de chaque ligne (ou bien avant chaque méthode
	//je mettrai une petite description

	private final JavaPetri	javaPetri; //Chaque Cellule appartient à une seule  boîte Petri définie à travers cet attribut
	private final Position	position; //Chaque Cellule se retrouve à une position précise (x,y) dans la boîte Petri (voir classe Position)
	private Boolean	alive; //Cet attribut booléen permet de savoir si la Cellule est en vie ou bien morte
	private final int strength; //Cet attribut définit la force d'une Cellule à travers un nombre entier
	private final Color	color; //Chaque Cellule a une couleur: rouge, vert ou bleu 

	
	//Construcuteur 
	public JavaCell(final JavaPetri javaPetri, final Position position, final int strength, final Color color) {
		this.javaPetri = javaPetri;
		this.position = position;
		this.strength = strength;
		this.alive = true; //Chaque cellule est intialement (par défaut) en vie
		this.color = color;
	}

	//Getters: sont utilisés pour récupérer les valeurs des attributs d'un objet 
	
	public JavaPetri getJavaPetri() {//Permet de savoir à quelle Petri appartient la cellule
		return this.javaPetri;
	}

	public Position getPosition() {//Permet de récupérer la postion (x, y) d'une cellule
		return this.position;
	}

	public int getStrength() {//Permet de récupérer la force d'une cellule
		return this.strength;
	}
	
	public Color getColor() {//Permet de récupérer la couleur de la cellule
		return this.color; //Le type Color existe par défaut dans la bib .awt
	}

	//Methods 
	
	public void drawCell(final 	Graphics graphics) {
		graphics.setColor(this.getColor());
		graphics.fillOval( this.getPosition().getY(),this.getPosition().getX(),10,10);
	
	}
	
	private final Boolean isPositionAvailable(final Position position) {
		//Cette méthode permet de savoir si une postion dans la boîte Petri est libre ou pas
		//En d'autres termes, si elle est occupée par une cellule ou non, elle retourne True
		//si la position est libre, non sinon!
		
		return ((position.getX() >= 0) && (position.getX() <= this.getJavaPetri().getWidth()) && (position.getY() >= 0)
				&& (position.getY() <= this.getJavaPetri().getHeight()));
	}
	
	
	//il y a deux méthodes getPositionNear, une sans arguments, l'autre avec un seul argument
	//l'une étant une surcharge de l'autre 
	
	private ArrayList<Position> getPositionNear(final Position position) {
		// A partir d'une position précise (paramètre en entrée), cette méthode permet
		//de récupérer une liste (utilisation de ArrayList) de positions disponibles 
		//(et proches) dans la boîte
		
		final ArrayList<Position> positionNear = new ArrayList<>(); //Déclaration de la structure dans laquelle on va enregistrer les positions disponibles
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
		//Cette méthode fait tout simplement appel à la méthode avec argument. Quand elle est
		//appelée avec un objet, elle retournera la liste des positions disponibles près de la
		//position où se trouve cet objet
		return this.getPositionNear(this.getPosition());
	}

	
	protected Boolean okReproduce(final Position position) {
		//Cette méthode permet de savoir si une reproduction de cellule est faite à une position
		//donnée en paramètre. Elle retourne un Vrai ou un Faux suite à une compraison de couleur
		//entre la position (donnée en paramètre) et la couleur "Blac"
		return this.getJavaPetri().getColorFromPosition(position).getRGB() == Color.WHITE.getRGB();
	}
	
	protected Color getReproduceColor(final Position position) {
		//La méthode permet de définir la couleur de la cellule reproduite dans une position
		//précise (donnée en parmètre). Comme chaque couleur est définie à base des trois couleurs
		//rouge, vert et bleu, on va récupérer la concentration de chaque couleur de la cellule,
		//la multiplier par la force de cette cellule (Strength) afin de créer la nouvelle 
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
		//Cette méthode permet de faire une reproduction de cellules. Elle permet, à partir de la
		//de l'objet (qui fait l'appel) de créer d'autres cellules dans les positions proches
		// (déterminées grâce à la méthode getPositionNear) et qui sont en reproduction 
		for (final Position position : this.getPositionNear()) {
			if (this.okReproduce(position)) {
				this.getJavaPetri().addJavaCell(new JavaCell(this.getJavaPetri(), position, this.getStrength(), this.getReproduceColor(position)));
			}
		}
	}
	
	public void live() {
		this.reproduce(); //Faire une reproduction de la cellule grâce à la méthode reproduce
		this.die(); //tuer la cellule
	}

	
	public Boolean isAlive() {//Tester si la cellule est en vie
		return this.alive;
	}

	public void die() {//Tuer la cellule, c-à-d rendre l'attribut alive à faux
		this.alive = false;
	}

	
	

}