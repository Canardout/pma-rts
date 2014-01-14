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
	private int quantite; // quantite de ressource prise par le villageois
	private List<Cellule> posArbre; // position des arbres connus par le villageois
	private List<Cellule> posArbreSuppr; // ancienne position des arbres connus par le villageois qui ne sont plus
	public int indexMajPosArbre; // index de mise à jour de la position des arbres
	
	public static final int MAX_VIE = 10;
	public static final int MAX_STOCK = 10;
	
	
	public Villageois (Cellule c , Alignement a){
		super(c, a, MAX_VIE);
		this.posArbre = new ArrayList<Cellule>();
		this.posArbreSuppr = new ArrayList<Cellule>();
		this.quantite = 0;
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
		case 3 : IA3(); break;
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
	
	private void IA2(){
		IAn(1);
	}
	
	private void IA3(){
		IAn(2);
	}
	
	// par Nicolas
	private String etat; // Ce que fait le Villageois actuellement
	private int cercle; // cercle ou est situe le villageois lors de la recherche
	private Coord direction; // direction du villageois lors de la recherche du Bois
	private void IAn (int ia){
		System.out.println(this.etat);
		switch(this.etat){
		case "recherche" : 
			List<ObjectMap> objetProche = vision();
			if((actualiseListeArbre(objetProche) & VUE) == 0)
				switch(ia){
				/////////////////////////////////////////////////////////////////////////////////////////////////// IA2
				case 1 : deplacement_aleatoire(); break;
				/////////////////////////////////////////////////////////////////////////////////////////////////// IA3
				case 2 : 
					
					break;
				///////////////////////////////////////////////////////////////////////////////////////////////////
				}
			else{
				this.etat = "recolte";
				this.objetCible = arbrePlusProcheO(objetProche);
				System.out.println(this.objetCible);
				IA2();
			}
			break;
			
		case "ramene" : 
			actualiseListeArbre(vision());
			if(distance(this.forum) > 0)
				rapproche(this.forum);
			else{
				donnerRessource((Stockable)this.forum.objet); //attention aux erreurs
				this.etat = "recolte";
			}
			break;
			
		case "recolte" :
			if(estPlein()){
				this.etat = "ramene";
				IA2();
			}
			else if(this.curent.objet == null || !this.curent.objet.isAlive() || !(this.curent.objet instanceof Bois)){
				actualiseListeArbre(vision());
				
				if(this.objetCible != null && this.objetCible.isAlive()){
					rapproche(this.objetCible.curent);
				}
				else if(!this.posArbre.isEmpty()){
					Cellule arbreProche = arbrePlusProche();
					if(arbreProche != null){
					this.objetCible = arbreProche.objet;
					rapproche(this.objetCible.curent);
					}
					else{
						this.etat = "recherche";
						IA2();
					}
				}
				else{
					this.etat = "recherche";
					IA2();
				}
			}
			else{
				recolte((Ressource)this.curent.objet);
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
		int etat = 0; 
		boolean modif = false;
		boolean vue = false;
		
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
				vue = true;
				if(!this.posArbre.contains(l.get(i).curent)){
					this.posArbre.add(l.get(i).curent);
				}
			}
		}
		
		if(modif){
			etat |= AJOUT;
		}
		
		if(vue){
			etat |= VUE;
		}
		
		return etat;
	}
	
	/**
	 * @return l'arbre le plus proche contenue dans la liste d'arbre du villageois, null si non trouvé
	 */
	public Cellule arbrePlusProche (){
		return arbrePlusProche(this.posArbre);
	}
	
	/**
	 * @param liste
	 * @return l'arbre le plus proche parmi une liste de Cellule, null si non trouvé
	 */
	public Cellule arbrePlusProche (List<Cellule> l){
		int distance = Integer.MAX_VALUE;
		Cellule c = null;
		
		for(int i = 0 ; i <l.size() ; i++){
			if(l.get(i).objet instanceof Bois){
				int dist = l.get(i).distance(this.curent);
				if(dist < distance){
					distance = dist;
					c = l.get(i);
				}
			}
		}
		return c;
	}
	
	/**
	 * @param liste d'ObjectMap donné par vision (déjà trié)
	 * @return l'arbre le plus proche parmi la liste d'ObjectMap donné par la vision, null si vide
	 */
	public ObjectMap arbrePlusProcheO (List<ObjectMap> l){
		for(int i = 0 ; i < l.size() ; i++){
			if(l.get(i) instanceof Bois){
				return l.get(i);
			}
		}
		
		return null;
	}
	
	public List<Cellule> getPosArbre (){
		return this.posArbre;
	}
	
	public List<Cellule> getPosArbreSuppr (){
		return this.posArbreSuppr;
	}
	
	public void initListe (){
		this.posArbre = new ArrayList<Cellule>();
		this.posArbreSuppr = new ArrayList<Cellule>();
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
