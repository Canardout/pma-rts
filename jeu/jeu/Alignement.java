package jeu;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Alignement {
	public Color color;
	public int IA;
	public int numero;
	
	public List<Cellule> ressource ;
	public List<Cellule> caserne;
	public List<Cellule> hopital;
	public List<Cellule> demande_ressource; // Liste des cellule où se trouve des batiments voulant des ressources.
	
	public Alignement (Color color, int num){
		this.color = color;
		this.ressource = new ArrayList<Cellule>();
		this.caserne = new ArrayList<Cellule>();
		this.hopital = new ArrayList<Cellule>();
		this.demande_ressource = new ArrayList<Cellule>();
		this.IA = 2;
		this.numero = num;
	}
	
	public void ajouteRessource (List<Cellule> r){
		
	}
	
	public boolean supprimeRessource (Cellule c){
		return this.ressource.remove(c);
	}
}
