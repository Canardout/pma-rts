
package jeu;

import java.awt.Dimension;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Watcher;
import madkit.simulation.probe.PropertyProbe;




/** Classe gerant les cellules qui constituront l'Environnement et la carte
*
* TODO redefinire auteur
* @author fayej, powlpy
* @version 1.0
*/

public class Cellule extends Watcher {
	

	public ObjectMap objet; // l'objet que la cellule contient?
	private Dimension dimension; // Les dimension de la cellule , doit-on les mettre variable ou pas?
	
	
	public Dimension getDimension() { // retourne les dimenssions de la cellule
		return dimension;
	}
	 public Cellule (ObjectMap o){  // donne un objet ‡ la cellule
         this.objet = o;
	 }
 
	 public Cellule (){ // Constructeur de la cellule
	         this.objet = null;
	 }
	

	@Override
	protected void activate() { // Active la cellule 
		dimension = new Dimension(500, 500); // taille actuel d'une cellule

		// La cellule se fait "capter" par l'affichage
		requestRole(Societe.SOCIETE,
				Societe.SIMU,
				Societe.ENV );
		
		// 2 : Ce capteur est utilis√© pour initialiser les agents du domaine environnement
		
		
		addProbe(new AgentsProbe(Societe.SOCIETE,
				Societe.SIMU,
				Societe.FORUM, "env"));
		addProbe(new AgentsProbe(Societe.SOCIETE,
				Societe.SIMU,
				Societe.CHERCHEUR , "env"));
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
