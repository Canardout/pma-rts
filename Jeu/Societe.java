package jeu;


import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import madkit.kernel.AbstractAgent;

public class Societe extends AbstractAgent{
	
	public static final String SOCIETE="simu";
	public static final String SIMU="simu";
	public static final String CHERCHEUR = "agent";
	public static final String RAMENEUR ="ramene";
	public static final String ENV = "env";
	public static final String SCHEDULER	= "scheduler";
	public static final String	VIEW	= "viewer";
	public static final String FORUM = "Forum";
	public static final String BOIS ="bois";
	public static ArrayList <Villageois> libre ;

	
	
	protected void activate() {
		// 1 : create the simulation group
		createGroup(SOCIETE, SIMU);
		libre = new ArrayList();

		// 2 : create the environment
		Environnement environment = new Environnement();
		launchAgent(environment);
		
		// 3 : create the scheduler
		GameDistributor scheduler = new GameDistributor();
		launchAgent(scheduler,true);

		// 3 : create the viewer
		Viewer viewer= new Viewer();
		launchAgent(viewer,true);

		// 2 : launch some simulated agents
		
		Dimension bois = new Dimension (540,200);
		Dimension bois2 = new Dimension (50,200);
		Dimension bois3 = new Dimension (50,25);
			launchAgent(new Forum(20,20));
			
			
			launchAgent(new Bois(bois));
			launchAgent(new Bois(bois2));
			launchAgent(new Bois(bois3));
		
	}
	
	public static void main(String[] args) {
		executeThisAgent(1,false); //no gui for me
	}
}
	
	

