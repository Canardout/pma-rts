package jeu;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Scheduler;

import madkit.simulation.activator.GenericBehaviorActivator;


public class GameDistributor extends Scheduler{
		
	protected GenericBehaviorActivator<AbstractAgent> [] Activateur;
	
	@Override
	protected void activate() {
		this.Activateur = new GenericBehaviorActivator[6];
		// Le Scheduler prend son rôle
		requestRole(Societe.SOCIETE,Societe.SIMU,Societe.SCHEDULER); 
		
		// Activateur pour : l'agent qui gère toute la carte (ENVIRONNEMENT)
		this.Activateur[0] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.CARTE, "ressource"); ///!\ Provisoir /!\ lance de nouveau agents Bois quand 
		// Activateur pour : le bois (Augmentation de leur ressources)                                                               // Ils sont tous mort
		this.Activateur[1] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.BOIS, "wood");
		// Activateur pour : les villageois (Recherche ressource Bois)
		this.Activateur[2] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.CHERCHEUR, "Chrwood");
		// Activateur pour : les villageois (Rentre au forum)
		this.Activateur[3] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.RAMENEUR ,"Retour");
		// Activateur pour : Le rendu graphique de la simulation (viewer)
		this.Activateur[4] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.VIEW, "observe");
		// Activateur pour : Le Forum (création de villageois)
		this.Activateur[5] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.FORUM ,"create");
		
		
		for (int i = 0 ; i< this.Activateur.length ; i++){
			addActivator(this.Activateur[i]);
		}
		
		
		setDelay(200);
		
		//4 : let us start the simulation automatically
		setSimulationState(SimulationState.PAUSED);
		
	}	 
					 
					 
					 
					 
		
}

