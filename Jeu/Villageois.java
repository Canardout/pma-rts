package jeu;

import java.awt.Dimension;
import java.util.List;

import madkit.kernel.AgentAddress;
import madkit.kernel.Message;
import madkit.message.ObjectMessage;


/**Classe Villageois définis l'unité "Villageoi" et son comportement lors de son activation 
 * 
 * @author fayej
 *
 */
public class Villageois extends Unite {
	
	private Dimension location ;
	private Environnement env;
	boolean plein;
	private Vecteur deplacement;
	private Vecteur arrive = null;
	private Vecteur unite;
	private int Dispo; 
	
		public Villageois(Dimension location)  {
		 this.location= new Dimension(location.height , location.width);
		 this.unite = new Vecteur(this.location.width, this.location.height);
		}

	
	protected void activate(){
		// Définir le rôle de l'objet "Villeagois" dans la société
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR);
		
	}
	
	
	@SuppressWarnings("unused")
	private void Chrwood() {
		//if (!plein ){
			Dimension envDim = env.getDimension();
			if (this.arrive == null){
				ObjectMessage msg;
				msg=(ObjectMessage)nextMessage();
				
				if (msg != null){
				this.arrive=(Vecteur)msg.getContent();
				
				}
			}
			else{
				deplacement = arrive.soustraction(unite).getDirecteur().multiplication(5); //*vitesse
				unite.additionEgale(deplacement);
				this.location.height = (int) unite.x;
				this.location.width = (int) unite.y;
				if (unite.x > this.arrive.x-5 && unite.x < this.arrive.x+5 && unite.y >this.arrive.y-5 && unite.y< this.arrive.y+5) {
					Societe.libre.remove(this);
					//leaveRole(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR);
					destroyRole(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR);
					plein = true;
					this.arrive = null;
					requestRole(Societe.SOCIETE , Societe.SIMU , Societe.RAMENEUR);
					
				
				}
			}
		}
		
	//}

	@SuppressWarnings("unused")
	private void Retour() {
		//if(plein){
			Dimension envDim = env.getDimension();
			if (this.arrive == null){
				
			ObjectMessage msg;
			msg=(ObjectMessage)nextMessage();
			
			if (msg != null){
			this.arrive=(Vecteur)msg.getContent();
			
			}
			}
			else {
				deplacement = arrive.soustraction(unite).getDirecteur().multiplication(2);
				unite.additionEgale(deplacement);
				this.location.height = (int) unite.x;
				this.location.width = (int) unite.y;
				if (unite.x > arrive.x-5 && unite.x < arrive.x+5 && unite.y >arrive.y-5 && unite.y< arrive.y+5) {
					leaveRole(Societe.SOCIETE , Societe.SIMU , Societe.RAMENEUR);
					plein = false;
					
					this.arrive =null;
					requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR);
				}	
		
			}
		}
	}
//}
	
	
	
	
	
	
	
	
		
		
	
	
	
	

