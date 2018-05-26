package jpu2016.javapetri.model;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Observable;

import jpu206.javapetri.ihm.Observer;

public class JavaPetri extends Observable{
	private int	width; //largeur de la bo�te Petri
	private int	height; //hauteur de la bo�te
	private int	step; // Etape d�finissant l'�tat de la bo�te
	private static String label = "26/05/2018 18:24";
	private final ArrayList<JavaCell>	javaCells; //Ensemble de cellules dans la bo�te
    private final ArrayList<Observer>   Obs;
	
	
	//Constructeur 
	public JavaPetri(final int width, final int height) {
		this.setWidth(width);
		this.setHeight(height);
		this.step = 0; //Initialement, la bo�te � son �tape 0
		this.javaCells = new ArrayList<JavaCell>(); //Cr�ation de la structure qui va contenir les cellules
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

	public int getStep() {//R�cup�rer le num�ro d'�tape
		return this.step;
	}
	
	public int getWidth() {//R�cup�rer la largeur de la bo�te Petri 
		return this.width;
	}
	
	public int getHeight() {//R�cup�rer la hauteur de la bo�te
		return this.height;
	}
	
	public synchronized  ArrayList<JavaCell> getJavaCells() {//R�cup�rer la liste des cellules
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
	
	private void setWidth(final int width) {//Modifier la largeur de la bo�te
		this.width = width;
	}
	
	private void setHeight(final int height) {//Modifier la hauteur de la bo�te
		this.height = height;
	}
	
	
	///////////////////////////////////////////////////////////////////////////////////////
	
	//Methods 

	private void incrementStep() {//Incr�menter le num�ro d'�tape: passer � l'�tape suivante
		this.step++;
		setChanged();
		notifyObservers();
	}

	
	public void addJavaCell(final JavaCell javaCell) {
		//La m�thode permet d'ajouter une cellule � la liste des cellules de la bo�te
		this.getJavaCells().add(javaCell);
	}

	public Color getColorFromPosition(final Position position) {
		//Cette m�thode permet de r�cup�rer la couleur de la cellule qui se retrouve dans une
		//position pr�cise (donn�e en param�tre). Si aucune cellule ne se retrouve � cette 
		//position, elle retourne la couleur "blanc"
		
		for (final JavaCell javaCell : this.getJavaCells()) {
			if ((javaCell.getPosition().getX() == position.getX()) && (javaCell.getPosition().getY() == position.getY())) {
				return javaCell.getColor();
			}
		}
		return Color.WHITE;
	}

	private void doLiveOnAllJavaCellAreAlive() {
		//La m�thode permet de faire reproduire toutes les cellules et de les tuer par la suite
		//Elle r�cup�re toutes les cellules dans une strucutre nomm�e oldJavePetri. Apr�s, pour
		//chacune d'elle, elle teste si elle est en vie alors elle lui associe la m�thode live
		//(voir la m�thode dans la classe JavaCell)
		
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
		//Cette m�thode retourne le nombre de cellules en vie, elle parcourt toutes les cellules
		//v�rifie leur �tat gr�ce � la m�thode isAlive, si cette m�thode retourne vraie, alors
		//le nombre de cellules en vie est incr�ment�
		int NbJavaCellAlive = 0;
		for (final JavaCell rgbCell : this.getJavaCells()) {
			if (rgbCell.isAlive()) {
				NbJavaCellAlive++;
			}
		}
		return NbJavaCellAlive;
	}

	public synchronized int getNbJavaCellDead() {
		//m�me raisonnement que la m�thode pr�c�dente mais pour retourner le nombre de cellules
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
