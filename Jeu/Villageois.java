package jeu;


import java.util.ArrayList;

import java.util.Random;



/**Classe Villageois dÃ©finis l'unitÃ© "Villageoi" et son comportement lors de son activation 
 * 
 * @author fayej
 *
 */
public class Villageois extends Unite {
	
	
	private Cellule curent;
	boolean plein;
	private Coord coord;
	private int quantite =0; // quantite de ressource prise par le villageois
	private Cellule Forum; // cellule de naissance (Forum) du villageois. /!\ Provisoir
	static ArrayList<Cellule> ressource = new ArrayList(); 
	
	public Villageois (Cellule c){
		this.curent = c;
		this.coord = c.coord;
		this.Forum = c;
		
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
			if (!ressource.isEmpty()){ //regarde la liste des ressources répertorié (déjà trouvé)
			if (ressource.get(0).objet != null){
				if(ressource.get(0).objet.isAlive()){
					if (this.curent.coord != ressource.get(0).coord){
					this.rapproche(ressource.get(0));
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
				else ressource.remove(0);
			}
			else ressource.remove(0);
			
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
				ArrayList<Cellule> autour = this.watchBois();
				
				if (autour.isEmpty()){
					this.curent = curent.env.getCellule(curent.coord.x+(nbrAlt()), curent.coord.y+(nbrAlt()));
					this.coord = curent.coord;
					
				}
				
				else {
					this.curent = autour.get(0); // pour le moment : le villageois va à la première ressource définis dans le tableau
					ressource.add(autour.get(0));
				}
			//////////////////////////////////////////////////////////////////////////////////////
			}
		}
		}
	}

	@SuppressWarnings("unused")
	private void Retour() {
		
		}
	}

	
	
	
	
	
	
	
	
		
		
	
	
	
	

