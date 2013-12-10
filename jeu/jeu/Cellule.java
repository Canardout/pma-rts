
package jeu;
import java.util.ArrayList;

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
}
