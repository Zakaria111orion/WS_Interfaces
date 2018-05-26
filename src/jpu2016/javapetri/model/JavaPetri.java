package jpu2016.javapetri.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import jpu206.javapetri.ihm.Observer;

public class JavaPetri extends Observable{
	private int	width; //largeur de la boîte Petri
	private int	height; //hauteur de la boîte
	private int	step; // Etape définissant l'état de la boîte
	private static String label = "26/05/2018 18:24";
	private final ArrayList<JavaCell>	javaCells; //Ensemble de cellules dans la boîte
    private final ArrayList<Observer>   Obs;
	
	
	//Constructeur 
	public JavaPetri(final int width, final int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.step = 0; //Initialement, la boîte à son étape 0
		this.javaCells = new ArrayList<JavaCell>(); //Création de la structure qui va contenir les cellules
		this.Obs = new ArrayList<Observer>();
	}
	
	public void addObserver(Observer observer) {
		if (!Obs.contains(observer)) {
		    Obs.add(observer);
		}
	}

	public void notifyObservers() {
        Object[] arrLocal;
        arrLocal = Obs.toArray();
        for (int i = arrLocal.length-1; i>=0; i--)
            ((Observer)arrLocal[i]).update();
	}
	

	
	//Getters

	public int getStep() {//Récupérer le numéro d'étape
		return this.step;
	}
	
	public int getWidth() {//Récupérer la largeur de la boîte Petri 
		return this.width;
	}
	
	public int getHeight() {//Récupérer la hauteur de la boîte
		return this.height;
	}
	
	public synchronized  ArrayList<JavaCell> getJavaCells() {//Récupérer la liste des cellules
		return this.javaCells;
	}
	
	////////////////////////////////////////////////////////////////////////////////////
	 public synchronized ArrayList<JavaCell> getCopyOfJavaCells() {
		 final ArrayList<JavaCell> copyOfJavaPetri = new ArrayList<JavaCell>();
		 for (final JavaCell rgbCell : this.getJavaCells()) {
		 copyOfJavaPetri.add(rgbCell);
		 }
		 return copyOfJavaPetri;
		 } 

	//Setters
	
	private void setWidth(final int width) {//Modifier la largeur de la boîte
		this.width = width;
	}
	
	private void setHeight(final int height) {//Modifier la hauteur de la boîte
		this.height = height;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	//Methods 

	private void incrementStep() {//Incrémenter le numéro d'étape: passer à l'étape suivante
		this.step++;
		setChanged();
		notifyObservers();
	}

	
	public void addJavaCell(final JavaCell javaCell) {
		//La méthode permet d'ajouter une cellule à la liste des cellules de la boîte
		this.getJavaCells().add(javaCell);
	}

	public Color getColorFromPosition(final Position position) {
		//Cette méthode permet de récupérer la couleur de la cellule qui se retrouve dans une
		//position précise (donnée en paramètre). Si aucune cellule ne se retrouve à cette 
		//position, elle retourne la couleur "blanc"
		
		for (final JavaCell javaCell : this.getJavaCells()) {
			if ((javaCell.getPosition().getX() == position.getX()) && (javaCell.getPosition().getY() == position.getY())) {
				return javaCell.getColor();
			}
		}
		return Color.WHITE;
	}

	private void doLiveOnAllJavaCellAreAlive() {
		//La méthode permet de faire reproduire toutes les cellules et de les tuer par la suite
		//Elle récupère toutes les cellules dans une strucutre nommée oldJavePetri. Après, pour
		//chacune d'elle, elle teste si elle est en vie alors elle lui associe la méthode live
		//(voir la méthode dans la classe JavaCell)
		
		final ArrayList<JavaCell> oldJavaPetri = new ArrayList<>();

		for (final JavaCell rgbCell : this.getJavaCells()) {
			oldJavaPetri.add(rgbCell);
		}

		for (final JavaCell rgbCell : oldJavaPetri) {
			if (rgbCell.isAlive()) {
				rgbCell.live();
			}
		}
	}

	public synchronized int getNbJavaCellAlive() {
		//Cette méthode retourne le nombre de cellules en vie, elle parcourt toutes les cellules
		//vérifie leur état grâce à la méthode isAlive, si cette méthode retourne vraie, alors
		//le nombre de cellules en vie est incrémenté
		int NbJavaCellAlive = 0;
		for (final JavaCell rgbCell : this.getJavaCells()) {
			if (rgbCell.isAlive()) {
				NbJavaCellAlive++;
			}
		}
		return NbJavaCellAlive;
	}

	public synchronized int getNbJavaCellDead() {
		//même raisonnement que la méthode précédente mais pour retourner le nombre de cellules
		//mortes
		int NbJavaCellDead = 0;
		for (final JavaCell rgbCell : this.getJavaCells()) {
			if (!rgbCell.isAlive()) {
				NbJavaCellDead++;
			}
		}
		return NbJavaCellDead;
	}

	public void animate() {
		for (this.incrementStep(); this.getNbJavaCellAlive() > 0; this.incrementStep()) {
			this.doLiveOnAllJavaCellAreAlive();
		}
	}

	public String getLabel() {
		// TODO Auto-generated method stub
		return this.label;
	}
	
	
	
}
