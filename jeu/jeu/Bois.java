package jeu;

import java.util.Random;

import unite.Villageois;



public class Bois extends Ressource{
	
		public Bois(Cellule cel){
			this(cel, 300);
		}
	
		public Bois (Cellule c, int q){
			super(c, q);
			this.curent.objet = this;
		}
		
		public void give(){ //la ressource se décremente et la "donne" au villageois
			this.quantite--; 
			
		}

	
	
	protected void activate(){
		// Defini le role de l'objet "Bois" dans la société
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.BOIS);
		
		
	}
	@SuppressWarnings("unused")
	private void wood() {
		
		if (this.quantite <=0){
			this.curent.objet = null; // On dis à la cellule qu'elle ne contiens maintenant plus rien
			killAgent(this);
		}
		
			
		}
	
	 public void end(){
	    	this.curent.env.nb_arbre--;
	    }
}
