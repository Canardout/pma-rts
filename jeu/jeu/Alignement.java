package jeu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Alignement {
	public Color color;
	
	public List<Cellule> ressource ;
	public List<Cellule> caserne;
	public Alignement (Color color){
		this.color = color;
		this.ressource = new ArrayList<Cellule>();
		this.caserne = new ArrayList<Cellule>();
		
	}
	
}
