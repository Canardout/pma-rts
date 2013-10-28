package jeu;

import java.awt.Dimension;


import madkit.kernel.AbstractAgent;
import madkit.kernel.Watcher;
import madkit.simulation.probe.PropertyProbe;

/**

 * @author fayej
 *
 */
public class Environnement extends Watcher {
	
	private Dimension	 dimension;
	public Dimension getDimension() {
		return dimension;
	}

	@Override
	protected void activate() {
		dimension = new Dimension(500, 500);

		// 1 : L'environnement demande son Rôle , il peut donc se faire "Capter" par le Viewer
		requestRole(Societe.SOCIETE,
				Societe.SIMU,
				Societe.ENV);
		
		// 2 : Ce capteur est utilisé pour initialiser les agents du domaine environnement
		
		
		addProbe(new AgentsProbe(Societe.SOCIETE,
				Societe.SIMU,
				Societe.FORUM, "env"));
		addProbe(new AgentsProbe(Societe.SOCIETE,
				Societe.SIMU,
				Societe.CHERCHEUR , "env"));
	}

	
	class AgentsProbe extends PropertyProbe<AbstractAgent, Environnement>{
		
		public AgentsProbe(String community, String group, String role, String fieldName) {
			super(community, group, role, fieldName);
		}

		protected void adding(AbstractAgent agent) {
			super.adding(agent);
			setPropertyValue(agent, Environnement.this);
		}

}
	}
