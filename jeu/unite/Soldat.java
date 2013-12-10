package unite;


import java.awt.Color;
import java.util.ArrayList;
import java.util.Random;

import jeu.Alignement;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;
import madkit.kernel.AbstractAgent;
import madkit.simulation.probe.PropertyProbe;



/**Classe Villageois d√©finis l'unit√© "Villageoi" et son comportement lors de son activation 
 * 
 * @author fayej
 *
 */
public class Soldat extends Unite {
	
	
	
	public boolean epuise; // Indique si le soldat ‡ besoin de se reposer ou non
	int repos; // Mesure le repos que doit prendre un Soldat aprËs avoir tuer quelque-chose.
	private Cellule Caserne; // cellule de naissance (Forum) du villageois. /!\ Provisoir
	
	
	public Soldat (Cellule c , Alignement a){
		this.curent = c;
		this.coord = c.coord;
		this.Caserne = c;
		this.al =a;
		this.vie = 15;
		this.epuise = false;
		
	}
	



	
	protected void activate(){
		// Definis le role de chercheur pour les villageois (Role unique et provisoir)
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.SOLDAT);
		this.activationgeneral();
		
		
	}
	



	

		private ArrayList<Cellule> cherche(){ 
			ArrayList<Cellule> autour = curent.env.getenv(curent);
			
			for (int i =0 ; i<autour.size() ; i++){
				
				if(!(autour.get(i).personne.size() == 0)){
				
				for (int j =0 ; j < autour.get(i).personne.size() ; j++){
					 if (!this.ennemi(autour.get(i).personne)){
						 autour.remove(autour.get(i));
						 i--;
						 break;
					}
				}
				
				}
				else {
					
					autour.remove(autour.get(i));
					i--;
					
				}
				
				
			}
			
			return autour;
		}
		private boolean ennemi(ArrayList<Unite> personne){
			if (!personne.isEmpty()){
			for(int i =0 ; i<personne.size(); i++){
				if (personne.get(i).al != this.al){
					return true;
				}
			}
			return false;
			}
			return false;
		}
		private boolean ennemi(Cellule personne){
			if (!personne.personne.isEmpty()){
			for(int i =0 ; i<personne.personne.size(); i++){
				if (personne.personne.get(i).al != this.al){
					return true;
				}
			}
			return false;
			}
			return false;
		}
		private void tape(){
			boolean effectuer =false; // represente si le soldat a deja frapper ou non.
			for(int i =0 ; i<this.curent.personne.size(); i++){
				if(!effectuer){
					if (this.curent.personne.get(i).al != this.al){
						this.killAgent(curent.personne.get(i));
						effectuer = true;	
					}
				}
				
			}
		}
	
	@SuppressWarnings("unused")
	private void attaque() {
	
		if (!this.epuise){
			ArrayList<Cellule> autour = this.cherche();
			if (!autour.isEmpty() && (autour.size() != 0)){
				this.rapproche(this.laplusproche(autour));
				if (this.curent.coord == this.laplusproche(autour).coord){
					this.tape();
					//this.deplacement_aleatoire();
					Random r = new Random();
					this.epuise = true;
					this.repos = r.nextInt(50);
				}
			}
			else {
				this.deplacement_aleatoire();
			}
		}
			else {
				if (this.curent.coord != this.Caserne.coord){
					this.rapproche(this.Caserne);
				}
				else {
				
				if (this.repos != 0){
					this.repos--;
				}
				else this.epuise = false;
				}
		}
}
}

	
	
	
	
	
	
	
	
		
		
	
	
	
	

