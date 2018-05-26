package jpu206.javapetri.controller;

import java.awt.Color;
import java.sql.SQLException;
import java.util.Random;

import jpu2016.javapetri.dao.DAOJavaPetri;
import jpu2016.javapetri.model.JavaCell;
import jpu2016.javapetri.model.JavaPetri;
import jpu2016.javapetri.model.Position;
import jpu206.javapetri.ihm.EasyFrame;
import jpu206.javapetri.ihm.javapetriConsole;

public class javapetricontroller {
	
	final int WIDTH = 500;
	final int HEIGHT = 500;
	final int STRENGTH = 50;
	final int NB_CELL_START = 20;
	JavaPetri javaPetri;
	javapetriConsole view;
	EasyFrame win;
	DAOJavaPetri dao;
	
	public javapetricontroller() {
	javaPetri = new JavaPetri(500, 500);
    view = new javapetriConsole(javaPetri);
	win = new EasyFrame(javaPetri);
	dao = new DAOJavaPetri();
	
	}
	public void save() throws SQLException {
		if (this.dao.open())
		{this.dao.insertJavaPetri(this.javaPetri);
		}else {
			System.out.println("NO");}
	}
	public void start() throws SQLException {
	final Random rand = new Random();
	for (int i = 0; i < NB_CELL_START; i++) {
		javaPetri.addJavaCell(new JavaCell(javaPetri, new Position(rand.nextInt(WIDTH), rand.nextInt(HEIGHT)), STRENGTH, new Color(rand.nextInt())));
	}
	//javaPetri.animate();
    this.save();
    }
	

}