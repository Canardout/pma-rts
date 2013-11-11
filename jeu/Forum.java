package jeu;



/**
 * Classe "FORUM" definis le batiment Forum et son activitee au cours de son activation.
 * @author fayej
 *
 */

public class Forum extends Batiment {
	
	
	private Coord coord;
	private Cellule env;
	protected int stock =40; //temporairement place ici
    protected int vie;
	

	public Forum (Cellule c , Alignement a){
		this.coord = c.coord;
		this.env = c;
		this.env.coord = this.coord;
		this.al = a;
	}
	public void addStock(){
		this.stock ++;
	}
	public int getStock (){
            return this.stock;
    }
	protected void activate(){
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.FORUM );
		
		
	}
	@SuppressWarnings("unused")
	private void create() { //crée un villageois
		this.env = env.env.getCellule(this.coord);
		if (this.stock-40 >=0){
			launchAgent(new Villageois(this.env,this.al));
			this.stock = this.stock-40;
		}
	}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonnées
		
		
		}

}
