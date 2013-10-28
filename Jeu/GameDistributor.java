package jeu;



import madkit.kernel.AbstractAgent;
import madkit.kernel.Scheduler;
import madkit.kernel.Scheduler.SimulationState;
import madkit.simulation.activator.GenericBehaviorActivator;


public class GameDistributor extends Scheduler{
		
	protected GenericBehaviorActivator<AbstractAgent> Villageois;
	protected GenericBehaviorActivator<AbstractAgent> Viewer;
	protected GenericBehaviorActivator<AbstractAgent> Forum;
	protected GenericBehaviorActivator<AbstractAgent> Bois;
	protected GenericBehaviorActivator<AbstractAgent> Rameneur;
	
	@Override
	protected void activate() {

		// 1 : request my role
		requestRole(Societe.SOCIETE,
				Societe.SIMU,
				Societe.SCHEDULER); 
		
		// 3 : initialize the activators
		// by default, they are activated once each in the order they have been added
		
		
		Bois = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,
				Societe.SIMU,
				Societe.BOIS, "wood");
		addActivator(Bois);
		
		Villageois = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,
				Societe.SIMU,
				Societe.CHERCHEUR, "Chrwood");
		addActivator(Villageois);
		Villageois = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.RAMENEUR ,"Retour");
		addActivator(Villageois);
		
		Viewer = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,
				Societe.SIMU,
				Societe.VIEW, "observe");
		addActivator(Viewer);
		Forum = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.FORUM ,"localisation");
		addActivator(Forum);
		Forum = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.FORUM ,"create");
		addActivator(Forum);
		
		
		
		setDelay(20);
		
		//4 : let us start the simulation automatically
		setSimulationState(SimulationState.PAUSED);
		
	}	 
					 
					 
					 
					 
		
}

