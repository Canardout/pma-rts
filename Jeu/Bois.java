package jeu;

import java.awt.Dimension;
import java.util.List;

import madkit.kernel.Message;
import madkit.message.ObjectMessage;

public class Bois extends Ressource{
	
	
	private Dimension location ;
	private Environnement env;

	private Vecteur emplacement;
	
	
		public Bois(Dimension location)  {
		
		 this.location= new Dimension(location.height , location.width);
		 this.emplacement = new Vecteur(this.location.height , this.location.width);
		 
		 
		}

	
	protected void activate(){
		// Définir le rôle de l'objet "Villeagois" dans la société
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.BOIS);
		
	}
	@SuppressWarnings("unused")
	private void wood() {
		ObjectMessage<Vecteur> mesCord = new ObjectMessage<Vecteur>(this.emplacement);
		/*
		for (int i =0 ; i<Societe.libre.size() ; i++){
			sendMessage(Societe.libre.get(i).getAgentAddressIn(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR), mesCord);
		}
		*/
		sendMessage(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR, mesCord);
		
		}
		
}
