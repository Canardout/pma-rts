package jeu;


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
	public static final String CARTE="carte";

	
	
	protected void activate() {
		// 1 : create the simulation group
		createGroup(SOCIETE, SIMU);
		

		// 2 : create the environment
		Environnement env = new Environnement(25,25);
		launchAgent(env);
		
		// 3 : create the scheduler
		GameDistributor scheduler = new GameDistributor();
		launchAgent(scheduler,true);

		// 3 : create the viewer
		Viewer viewer= new Viewer();
		launchAgent(viewer,true);

		// 2 : launch some simulated agents

		
	}
	
	public static void main(String[] args) {
		executeThisAgent(1,false); //no gui for me
	}
}
	
	

