package jeu;

import java.awt.Dimension;

import madkit.message.ObjectMessage;

/**
 * Classe "FORUM" dÃ©finis le batiment Forum et son activitÃ© au cours de son activation.
 * @author fayej
 *
 */

public class Forum extends Batiment {
	
	
	private int x;
	private int y;
	private  Dimension location ;
	private Environnement env;
	int vil=0;
	private Vecteur emplacement;
	
		public Forum(int x , int y)  {
			this.x =x;
			this.y =y;
		 this.location  = new Dimension(y,x);
		 this.emplacement = new Vecteur(x,y);
		}

	
	protected void activate(){
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.FORUM);
		
		
	}
	@SuppressWarnings("unused")
	private void create() { //crée un villageois
		
		
		if (vil % 500 == 0){
			System.out.println("Je lance un villageois");
			Dimension d= new Dimension (x,y);
			launchAgent(new Villageois(d));
			vil++;
		}
		vil++;
		
	}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonnées
		
		ObjectMessage<Vecteur> mesCord = new ObjectMessage<Vecteur>(this.emplacement);
		sendMessage(Societe.SOCIETE , Societe.SIMU , Societe.RAMENEUR, mesCord);
		}

}
