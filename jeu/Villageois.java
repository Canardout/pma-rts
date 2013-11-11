package jeu;


import java.util.ArrayList;
import java.util.Random;

import madkit.kernel.AbstractAgent;
import madkit.simulation.probe.PropertyProbe;



/**Classe Villageois dÃ©finis l'unitÃ© "Villageoi" et son comportement lors de son activation 
 * 
 * @author fayej
 *
 */
public class Villageois extends Unite {
	
	
	private Cellule curent;
	boolean plein;
	public Coord coord;
	private int quantite =0; // quantite de ressource prise par le villageois
	private Cellule Forum; // cellule de naissance (Forum) du villageois. /!\ Provisoir
	
	
	public Villageois (Cellule c , Alignement a){
		this.curent = c;
		this.coord = c.coord;
		this.Forum = c;
		this.al =a;
		
	}
	

	
	public Villageois (int x , int y){
		this.coord = new Coord(x,y);
	}


	
	protected void activate(){
		// Definis le role de chercheur pour les villageois (Role unique et provisoir)
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR);
		
		
	}
	
	private int nbrAlt(){
		Random r = new Random();
		int valeur = r.nextInt(3)-1;
		return (valeur);
	}
	
	/////////////////// Cette methode et la suivante sont a simplifier en une seul , mais je ne sais comment faire pour le moment ! ///////////////////////////////////
	private ArrayList<Cellule> watchBois(){ // Methode qui renvoie ce que regarde le villageois (les cellules adjaçante a la sienne + la sienne) sans les diagonalles)
		ArrayList<Cellule> autour = curent.env.getenv(curent);
		
		for (int i =0 ; i<autour.size() ; i++){
			
			if (!(autour.get(i).objet instanceof Bois )){
				
				autour.remove(i);
				i--;
			}
		}
		return autour;
	}
	private ArrayList<Cellule> watchForum(){ // Methode qui renvoie ce que regarde le villageois (les cellules adjaçante a la sienne + la sienne) sans les diagonalles)
		ArrayList<Cellule> autour = curent.env.getenv(curent);
		
		for (int i =0 ; i<autour.size() ; i++){
			
			if (!(autour.get(i).objet instanceof Forum )){
				
				autour.remove(i);
				i--;
			}
		}
		return autour;
	}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	private boolean giveForum(){ // Donne une ressource au forum (si le villageois est sur la bonne case) retourne Vrai s'il a reussit , faux sinon.
		if (this.curent.objet instanceof Forum){ 
			Forum b ;
			b = (Forum) this.curent.objet;
			b.addStock();
			this.quantite --;
			if (this.quantite <1){
				this.plein = false;
			}
			return true;
		}else return false;	
	}
	
	private void rapproche(Cellule cible){
		
		if (Math.abs(this.curent.coord.x -cible.coord.x) >= Math.abs(this.curent.coord.y - cible.coord.y)) {
			
		if (this.curent.coord.x != cible.coord.x){ 
		
			if (this.curent.coord.x> cible.coord.x){
				this.curent = this.curent.env.getCellule(this.curent.coord.x-1, this.curent.coord.y);
			}
			else if (this.curent.coord.x< cible.coord.x) this.curent = this.curent.env.getCellule(this.curent.coord.x+1, this.curent.coord.y);
		}
		}
		else if (this.curent.coord.y != cible.coord.y){  if (this.curent.coord.y> cible.coord.y){
				this.curent = this.curent.env.getCellule(this.curent.coord.x, this.curent.coord.y-1);
				
			}
			else if (this.curent.coord.y< cible.coord.y){ this.curent = this.curent.env.getCellule(this.curent.coord.x, this.curent.coord.y+1);}
		
		}
		this.coord = curent.coord;
		}
		
	private Cellule laplusproche(ArrayList<Cellule> a){
		Cellule cel = a.get(0);
		int plusproche = a.get(0).coord.distance(this.curent.coord);
		for (int i =1 ; i<a.size() ; i++){
			if(a.get(i).coord.distance(this.curent.coord)< plusproche){
				cel = a.get(i);
				plusproche = a.get(i).coord.distance(this.curent.coord);
			}
		}
		return cel;
	}
	
	private void presente(ArrayList<Cellule> a){ // Regarde si la cellule est déjà présente dans une liste de cellule
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
	
	@SuppressWarnings("unused")
	private void Chrwood() {
		
		if (plein){ // Si le villageois est plein , alors il cherche un FORUM pour se vider.
			
			if (this.giveForum()){} // s'il est sur une case "Forum" , alors il se vide de 1.
			else { // Sinon il regarde sur les cases adjacentes pour voir s'il n'y a pas un Forum
				if (this.Forum ==null){
				ArrayList<Cellule> autour = this.watchForum();
				if (autour.isEmpty()){ // Si il n'y a pas de Forum , alors il bouge aléatoirement
					this.curent = curent.env.getCellule(curent.coord.x+(nbrAlt()), curent.coord.y+(nbrAlt()));
					this.coord = curent.coord;
				}
				
				else { // S'il y a un Forum , alors il va sur la case.
					this.curent = autour.get(0); // pour le moment : le villageois va à la première ressource définis dans le tableau
					this.Forum = autour.get(0);
				}
				}
				else this.rapproche(this.Forum);	
				
				
			}
		}
		
		
		
		
		
		else{
			ArrayList<Cellule> autour = this.watchBois();
			this.presente(autour);
			if (!this.al.ressource.isEmpty()){ //regarde la liste des ressources répertorié (déjà trouvé)
				Cellule plusproche = this.laplusproche(this.al.ressource);
		
			if (plusproche.objet != null){
				if(plusproche.objet.isAlive()){
					if (this.curent.coord != plusproche.coord){
					this.rapproche(plusproche);
					}
					else 	if (this.curent.objet instanceof Bois){
						Bois b ;
						b = (Bois) this.curent.objet;
						b.decrementeQuantite();
						this.quantite ++;
						if(this.quantite >=10){ // Je mets ici >=10 en cas de bug (qui ne devrai normalement ne pas avoir lieu)
							this.plein = true;
						}
					
				}
				}
				else this.al.ressource.remove(0);
			}
			else this.al.ressource.remove(0);
			
		} 
		else{
			
			////////Le villageois regarde sont environnement
			
				if (this.curent.objet instanceof Bois){
					Bois b ;
					b = (Bois) this.curent.objet;
					b.decrementeQuantite();
					this.quantite ++;
					if(this.quantite >=10){ // Je mets ici >=10 en cas de bug (qui ne devrai normalement ne pas avoir lieu)
						this.plein = true;
					}
				
			}
			
			else{
			//////////////////// Le cas où tout les villageois n'ont rien trouvé , il cherche dans son environnement du bois ////////////////////////////////////////////////////////////// 
				ArrayList<Cellule> autour2 = this.watchBois();
				
				if (autour2.isEmpty()){
					this.curent = curent.env.getCellule(curent.coord.x+(nbrAlt()), curent.coord.y+(nbrAlt()));
					this.coord = curent.coord;
					
				}
				
				else {
					this.curent = autour2.get(0); // pour le moment : le villageois va à la première ressource définis dans le tableau
					this.al.ressource.add(autour2.get(0));
				}
			//////////////////////////////////////////////////////////////////////////////////////
			}
		}
		}
	}

	class AgentsProbe extends PropertyProbe<AbstractAgent, Villageois>{
		
		public AgentsProbe(String community, String group, String role, String fieldName) {
			super(community, group, role, fieldName);
		}

		protected void adding(AbstractAgent agent) {
			super.adding(agent);
			setPropertyValue(agent, Villageois.this);
		}
	}
}

	
	
	
	
	
	
	
	
		
		
	
	
	
	

