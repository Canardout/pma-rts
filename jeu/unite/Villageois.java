package unite;


import java.util.ArrayList;
import java.util.List;

import batiment.Forum;
import batiment.Stockable;
import jeu.Alignement;
import jeu.Bois;
import jeu.Cellule;
import jeu.Coord;
import jeu.ObjectMap;
import jeu.Societe;
import jeu.Ressource;
import madkit.kernel.AbstractAgent;
import madkit.simulation.probe.PropertyProbe;



/**Classe Villageois definis l'unite "Villageois" et son comportement lors de son activation 
 * 
 * @author fayej
 *
 */

@SuppressWarnings("serial")
public class Villageois extends Unite {
	
	public boolean plein;
	private int quantite =0; // quantite de ressource prise par le villageois
	private Cellule forum; // cellule de naissance (Forum) du villageois. /!\ Provisoir
	private List<Cellule> posArbre; // position des arbres connus par le villageois
	
	public static final int MAX_STOCK = 10;
	
	
	public Villageois (Cellule c , Alignement a){
		this.curent = c;
		this.coord = c.coord; //inutilisé
		this.forum = c;
		this.al =a;
		this.vie =10;
		this.posArbre = new ArrayList<Cellule>();
	}

	
	protected void activate(){
		// Definis le role de chercheur pour les villageois (Role unique et provisoir)
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CHERCHEUR);
		this.activationgeneral();
		
		this.etat = "recherche";
		this.direction = Coord.NULL;
		this.cercle = 0;	
	}
	
	
	/////////////////// Cette methode et la suivante sont a simplifier en une seul , mais je ne sais comment faire pour le moment ! ///////////////////////////////////
	private ArrayList<Cellule> watchBois(){ // Methode qui renvoie ce que regarde le villageois (les cellules adjaçantes à la sienne + la sienne) sans les diagonalles)
		ArrayList<Cellule> autour = curent.env.getenv(curent);
		
		for (int i =0 ; i<autour.size() ; i++){
			if (autour.get(i) ==null){
				autour.remove(i);
				i--;
			}
			else if (!(autour.get(i).objet instanceof Bois )){
				
				autour.remove(i);
				i--;
			}
		}
		return autour;
	}
	private ArrayList<Cellule> watchForum(){ // Methode qui renvoie ce que regarde le villageois (les cellules adjacante a la sienne + la sienne) sans les diagonalles)
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
	
	
	public void end(){
		this.curent.personne.remove(this);
		Forum b = (Forum)this.forum.objet;
		b.limitpop++;
		
	}
	
	
	private void presente(ArrayList<Cellule> a){ // Regarde si la cellule est deja presente dans une liste de cellule
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
		
		if(this.vie <= 0){		
			this.killAgent(this);
			this.curent.personne.remove(this);
		}
		
		switch(this.al.IA){
		case 2 : IA2(); break;
		default : IA1(); break;
		}
	}
	
	
	// par Jeremie
	private void IA1 (){
		
		if (plein){ // Si le villageois est plein , alors il cherche un FORUM pour se vider.
			if (this.curent.coord != this.forum.coord){ 
				this.rapproche(this.forum);
			}
			else {
			if (this.giveForum()){} // s'il est sur une case "Forum" , alors il se vide de 1.
			else { // Sinon il regarde sur les cases adjacentes pour voir s'il n'y a pas un Forum
				if (this.forum ==null){
					this.killAgent(this);
				}
				
				
				 
				else this.rapproche(this.forum);	
				
				
			}
		}
		}
		
		
		
		
		
		else{
			ArrayList<Cellule> autour = this.watchBois();
			this.presente(autour); //regarde si une ressource potentiellement trouve est deja referencer dans la liste des ressource trouve par l'alignement.
			if (!this.al.ressource.isEmpty()){ //regarde la liste des ressources repertorier de lalignement.
				Cellule plusproche = this.laplusproche(this.al.ressource); // On prend la cellule repertorier la plusproche de la position actuel du villageois
		
			if (plusproche.objet != null){ // si l'objet n'est pas passer a null (destruction de l'agent)
				if(plusproche.objet.isAlive()){ // si l'objet est toujours en vie
					if (this.curent.coord != plusproche.coord){ // si le villageois n'est pas deja sur la cellule concerner
					this.rapproche(plusproche); // alors il se rapproche de la cellule en question
					}
					else 	if (this.curent.objet instanceof Bois){ // si le villageois est bien sur la case , on verifie une derniere fois que lobjet est bien un arbre
						Bois b ;
						b = (Bois) this.curent.objet; // on caste lobjet en Bois
						b.decrementeQuantite(); // on prend ici une ressource.
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
		else{ // Si aucune ressource nest actuellement exploite par l'alignement :
			
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
			//////////////////// Le cas ou tout les villageois n'ont rien trouve , il cherche dans son environnement du bois ////////////////////////////////////////////////////////////// 
				ArrayList<Cellule> autour2 = this.watchBois();
				
				if (autour2.isEmpty()){
					this.deplacement_aleatoire();
					
				}
				
				else {
					this.curent = autour2.get(0); // pour le moment : le villageois va a la premiere ressource definis dans le tableau
					this.al.ressource.add(autour2.get(0));
				}
			//////////////////////////////////////////////////////////////////////////////////////
			}
		}
		}
		
	}
	
	// TODO prendre en compte que la memoire ne sera plus partage a l'avenir
	// TODO supprimer arbre quand on les voit plus
	// par Nicolas
	private String etat; // Ce que fait le Villageois actuellement
	private int cercle; // cercle ou est situe le villageois lors de la recherche
	private Coord direction; // direction du villageois lors de la recherche du Bois
	private void IA2 (){
		/*
		 * recherche -> quand on sait la position des arbres, recolte ?
		 * ramene
		 * recolte
		 */
		switch(this.etat){
		case "recherche" : 
			if((actualiseListeArbre(vision()) & VUE) != 0)
				deplacement_aleatoire(); //TODO à modifier
			else{
				this.etat = "recolte";
				//TODO cible = ressource la plus proche
			}
			break;
			
		case "ramene" : 
			if(distance(this.forum) > 0)
				rapproche(this.forum);
			else{
				donnerRessource((Stockable)this.forum.objet); //attention aux erreurs
				this.etat = "recolte";
			}
			break;
			
		case "recolte" : //TODO supprimmer arbre quand il n'existe plus
			if(estPlein()){
				this.etat = "ramene";
				IA2();
			}
			else if(this.curent.objet == null || !this.curent.objet.isAlive()){
				this.etat = "recherche";
				IA2();
			}
			else if(this.curent.objet instanceof Ressource && this.curent.objet.isAlive()){
				//Cellule c = laplusproche(this.al.ressource); //TODO ne verifie pas si vide, creer methode dans Cellule
				//this.rapproche(c);
			}
			else{
				//recolte((Ressource)this.curent.objet); //attention aux erreurs
			}
				
			break;
			
		default : 
			System.out.println("Etat inconnue : " + this.etat);
			break;
		}
	}

	public void donnerRessource (Stockable bat){
		this.quantite = bat.donnerStock(this.quantite);
	}
	
	public void recolte (Ressource r){
		r.decrementeQuantite();
		this.quantite++;
	}
	
	public boolean estPlein (){
		return this.quantite >= MAX_STOCK;
	}
	
	/**
	 * Met à jour la liste des Arbre connue par le villageois selon ce qu'il voit autour.
	 * Permet aussi de savoir si il y a des arbres en vue et si il y a eu des ajout dans la liste grâce à la valeur retourné.
	 * Utilisé les opérateur bit à bit : | & ^
	 * @return champ de 3 bit, suppression:ajout:ArbreEnVue
	 */
	public static final int VUE = 1;   // 1 << 0
	public static final int AJOUT = 2; // 1 << 1
	public static final int SUPPR = 4; // 1 << 2
	public int actualiseListeArbre (List<ObjectMap> l){ //méthode public mais plutôt créer pour une utilisation privée
		int etat = l.isEmpty() ? 0 : 1; //VUE
		boolean modif = false;
		
		//suppression
		for(int i = 0 ; i < this.posArbre.size() ; i++){
			if(this.curent.distance(this.posArbre.get(i)) <= ObjectMap.vision){
				if(!l.contains(this.posArbre.get(i).objet)){
					this.posArbre.remove(i);
					i--; // élément supprimé, on décale l'index
					modif = true;
				}
			}
		}
		
		if(modif){
			etat |= SUPPR;
			modif = false;
		}
		
		//ajout
		for(int i = 0 ; i < l.size() ; i++){
			if(l.get(i) instanceof Bois){
				if(!this.posArbre.contains(l.get(i).curent)){
					this.posArbre.add(l.get(i).curent);
				}
			}
		}
		
		if(modif){
			etat |= AJOUT;
		}
		
		return etat;
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
