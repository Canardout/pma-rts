package jeu;

import java.util.Random;

import unite.Villageois;



public class Bois extends Ressource{
	
	private Coord coord;
	private Cellule curent;
	
		public Bois(Cellule cel){
			this.curent = cel;
			this.coord = cel.coord;
			this.quantite = 300;
			this.curent.objet=this;
		}
	
		public Bois (Coord c, int q){
	        this.coord = c;
	        this.quantite = q;
		}

		public Bois (Coord c){
		        this(c, 100); //Définis une ressource avec 100 de quantité 
		}
		
		public void give(){ //la ressource se décremente et la "donne" au villageois
			this.quantite--; 
			
		}

	
	
	protected void activate(){
		// Defini le role de l'objet "Bois" dans la société
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.BOIS);
		this.activationgeneral();
		
	}
	@SuppressWarnings("unused")
	private void wood() {
		
		if (this.quantite <=0){
			this.curent.objet = null; // On dis à la cellule qu'elle ne contiens maintenant plus rien
			killAgent(this);
		}
		
			
		}
		
}
