package unite;
	import java.util.ArrayList;
import java.util.Random;

import batiment.Caserne;
import batiment.Forum;
import batiment.Hopital;
import jeu.Alignement;
import jeu.Bois;
import jeu.Cellule;
import jeu.Societe;




	/**Classe constructeur
	 * 
	 * @author fayej
	 *
	 */
	public class Constructeur extends Unite {
		
		
		
		public boolean vide;
		private int quantite =0; // quantite de ressource prise par le villageois
		private Cellule Forum; // cellule de naissance (Forum) du villageois. 
		private boolean action;
		
		
		public Constructeur (Cellule c , Alignement a){
			this.curent = c;
			this.coord = c.coord;
			this.Forum = c;
			this.al =a;
			this.vie =10;
			this.vide = true;
			this.quantite =0;
			this.action = false;
			
		}
		



		
		protected void activate(){
			// Definis le role de chercheur pour les villageois (Role unique et provisoir)
			
			requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CONSTRUCTEUR);
			this.activationgeneral();
			
			
		}
		


		
		private boolean takeForum(){ // Prend une ressource au forum (si le villageois est sur la bonne case) retourne Vrai s'il a reussit , faux sinon.
			if (this.curent.objet instanceof Forum){
				
				Forum b ;
				b = (Forum) this.curent.objet;
				if (b.al != this.al){
					return false;
				}
				else{
				 if (b.deleteStock()){
				this.quantite ++;
				if (this.quantite >=10){
					this.vide = false;
				}
				return true;
				}
				 else return false;
				 }
				
			}else return false;	
		}
		
		
		private void createCaserne(){
			
			launchAgent(new Caserne(this.curent, this.al));
			
			
		}
		
		private void presente(ArrayList<Cellule> a){ // Regarde si la cellule est dï¿½jï¿½ prï¿½sente dans une liste de cellule
			boolean present = true;
			for (int i =0 ; i<a.size() ; i++){
				for (int j = 0 ; j < this.al.ressource.size() ; j++){
					if (a.get(i).coord == this.al.ressource.get(j).coord ){
						present = false;
					}
				}
				if (present){
					this.al.ressource.add(a.get(i));
				}
				present = true;
			}
		}
		
		
		private void se_remplis(){
			if (this.curent.coord != this.Forum.coord){ 
				this.rapproche(this.Forum);
			}
			else {
			if (this.takeForum()){} // s'il est sur une case "Forum" , alors il se charge de 1.
			else { // Sinon il regarde sur les cases adjacentes pour voir s'il n'y a pas un Forum
				if (this.Forum ==null){
					this.killAgent(this); // le constructeur se suicide s'il n'a plus de forum
				}
				
				
				 
				else this.rapproche(this.Forum);	
				
				
			}
		}
			
		}
		
		private void choixconstructeur(){
			System.out.println("Je fais un choix");
			if (!this.action){
				System.out.println("J'essaye de construire et ...");
				this.construit();
			}
			else this.apporte();
		}
		
		
		
		
		@SuppressWarnings("unused")
		private void construit() {
			if(this.vie <= 0){
				
				this.killAgent(this);
			}
			
			if (vide){ // Si le constructeur est vide , alors il cherche un FORUM pour se remplir.
			this.se_remplis();
			}
			
			
			
			
			
			else{
				
				if (!this.al.caserne.isEmpty()){ //regarde la liste des ressources rï¿½pertoriï¿½ (dï¿½jï¿½ trouvï¿½)
					Cellule plusproche = this.laplusproche(this.al.caserne);
			
				if (plusproche.objet != null){
					if(plusproche.objet.isAlive()){
						if (this.curent.coord != plusproche.coord){
						this.rapproche(plusproche);
						}
						else 	if (this.curent.objet instanceof Caserne){
							Caserne b ;
							b = (Caserne) this.curent.objet;
							b.addStock();
							this.quantite --;
							if(this.quantite >=0){ // Je mets ici >=10 en cas de bug (qui ne devrai normalement ne pas avoir lieu)
								this.vide = true;
								this.action = true;
							}
						
					}
					}
					else this.al.caserne.remove(0);
				}
				else this.al.caserne.remove(0);
				
			} 
			else{
				
				////////Le villageois regarde sont environnement
				if (this.curent.objet != null){
					this.deplacement_aleatoire();
					
				}
				
				else{
					if(this.quantite >= 10){
						this.createCaserne();
						this.quantite = this.quantite -10;
						this.vide = true;
					}
					else this.vide = true;
					
				
				}
			}
			}
		}
		
		
		
		private void apporte() {
			if (this.vide){
				this.se_remplis();
			}
			else {
			if (!(this.al.demande_ressource.isEmpty())){
				Cellule plusproche = this.al.demande_ressource.get(0);
				if (this.curent.coord != plusproche.coord){
					this.rapproche(plusproche);
					}
				else 	if (this.curent.objet instanceof Caserne){
					Caserne b ;
					b = (Caserne) this.curent.objet;
					b.addStock();
					this.quantite --;
					if(this.quantite >=0){ // Je mets ici >=10 en cas de bug (qui ne devrai normalement ne pas avoir lieu)
						this.vide = true;
						action = false;
						this.curent.objet.al.demande_ressource.remove(this.curent); // On enleve l'objet qui demande des ressources pour le remettre à la fin de la liste 
						this.curent.objet.al.demande_ressource.add(this.curent);
					}
			}
		}
			else action = false;
			}

	}
	}

