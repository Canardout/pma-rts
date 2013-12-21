
package jeu;
import java.util.ArrayList;
import java.util.List;

import unite.Unite;
import madkit.kernel.AbstractAgent;
import madkit.kernel.Watcher;
import madkit.simulation.probe.PropertyProbe;

/** Classe gerant les cellules qui constituront l'Environnement et la carte
*
* 
* @author fayej, powlpy
* @version 1.0
*/
public class Cellule extends Watcher {
	public ObjectMap objet; // l'objet que la cellule contient?
	public ArrayList<Unite> personne;
	public Coord coord;
	public Environnement env;
	
	
	 public Cellule (Coord coord , Environnement env){ // Constructeur de la cellule
         this.objet = null;
         this.coord = coord;  
         this.env = env;
         this.personne = new ArrayList<Unite>();
	 }

	 
	 public Cellule(Cellule c) {
		this.coord = c.coord;
		this.env = c.env;
		this.objet = c.objet;
	}


	


	public void add(ObjectMap o){  // donne un objet � la cellule
         this.objet = o;
         launchAgent(o);
	 }
	 

	

	@Override
	protected void activate() { // Active la cellule 
		// La cellule se fait "capter" par l'affichage
		requestRole(Societe.SOCIETE,Societe.SIMU,Societe.ENV );
		
		
		// 2 : Ce capteur est utilise pour initialiser les agents du domaine environnement
		/*
		addProbe(new AgentsProbe(Societe.SOCIETE,Societe.SIMU,Societe.FORUM, "env"));
		addProbe(new AgentsProbe(Societe.SOCIETE,Societe.SIMU,Societe.CHERCHEUR , "curent"));
		*/
	}


        
	class AgentsProbe extends PropertyProbe<AbstractAgent, Cellule>{
	
	public AgentsProbe(String community, String group, String role, String fieldName) {
		super(community, group, role, fieldName);
	}

	protected void adding(AbstractAgent agent) {
		super.adding(agent);
		setPropertyValue(agent, Cellule.this);
	}

	}
	
	/**
	 * @author nico
	 * @return la liste des ObjectMap de la case
	 */
		public ArrayList<ObjectMap> listeObjet (){
			ArrayList<ObjectMap> l = new ArrayList<ObjectMap>(this.personne);
			if(this.objet != null){
				l.add(this.objet);
			}
			return l;
		}
		
		/**
		 * @author nico
		 * @return le nombre d'ObjectMap present sur la case
		 */
		public int nombreObjet (){
			return this.personne.size() + (this.objet == null ? 0 : 1);
		}
		
		/**
		 * @author nico
		 * la cellule de la liste la plus proche de this, 
		 * null si aucune cellule est trouvee
		 * ne retourne pas la cellule courante
		 * @return la cellule la plus proche
		 */
		public Cellule plusProche (List<Cellule> l){

			Cellule c = null;
			int d = Integer.MAX_VALUE;
			for(int i = 0 ; i < l.size() ; i++){
				if(l.get(i) != this){
					int di = l.get(i).distance(this);
					if(di < d){
						c = l.get(i);
						d = di;
					}
				}
			}
			return c;
		}
		
		/**
		 * @author nico
		 * @return la distance entre 2 cellules
		 */
		public int distance (Cellule c){
			return coord.distance(c.coord);
		}
}
