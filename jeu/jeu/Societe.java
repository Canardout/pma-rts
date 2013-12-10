package jeu;


import madkit.kernel.AbstractAgent;

public class Societe extends AbstractAgent{
	
	public static final String SOCIETE="soc";
	public static final String SIMU="simu";
	public static final String CHERCHEUR = "agent";
	public static final String RAMENEUR ="ramene";
	public static final String ENV = "env";
	public static final String SCHEDULER	= "scheduler";
	public static final String	VIEW	= "viewer";
	public static final String FORUM = "Forum";
	public static final String BOIS ="bois";
	public static final String CARTE="carte";
	public static final String SOLDAT="soldat";
	public static final String OBJECTMAP ="representable";
	public static final String CONSTRUCTEUR ="construit";
	public static final String CASERNE ="cons-soldat";

	
	
	protected void activate() {
		// 1 : create the simulation group
		createGroup(SOCIETE, SIMU);
		

		// 2 : create the environment
		int longueur;
		int largeur;
		int taille_cellule;
		longueur = 25;
		largeur = 25;
		taille_cellule = 20;
		Environnement env = new Environnement(longueur,largeur,2);
		launchAgent(env);
		
		// 3 : create the scheduler
		GameDistributor scheduler = new GameDistributor();
		launchAgent(scheduler,false);

		// 3 : create the viewer
		
		Viewer viewer= new Viewer(scheduler,taille_cellule, longueur , largeur);
		launchAgent(viewer,true);
		Viewer viewer2= new Viewer(scheduler,taille_cellule, longueur , largeur);
		launchAgent(viewer,true);

		// 2 : launch some simulated agents

		
	}
	
	public static void main(String[] args) {
		executeThisAgent(1,false); //no gui for me
	}
}
	
	

