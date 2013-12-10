package batiment;


import unite.Soldat;
import unite.Villageois;
import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;




/**
 * Classe "FORUM" definis le batiment Forum et son activitee au cours de son activation.
 * @author fayej
 *
 */

public class Caserne extends Batiment {
	
	
	public Coord coord;
	
	private Cellule env;
	protected int stock ; 
    protected int vie;
    public int statue ;
    public int horloge;

	

	public Caserne (Cellule c , Alignement a){
		
		this.coord = new Coord(c.coord);
		this.env = c;
		this.env.coord = this.coord;
		this.al = a;
		this.env.objet = this;
		this.statue =0;
		this.stock =0;
		this.horloge=0;
		this.al.caserne.add(this.env);
		
	}
	public boolean  addStock(){
		this.stock ++;
		return true;
	}
	public boolean deleteStock(){
		if (this.stock-1 < 0){
			return false;
		}
		else {
			this.stock --;
			return true;
			
		}
		
	}
	public int getStock (){
            return this.stock;
    }
	protected void activate(){
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CASERNE );
		this.activationgeneral();
		
		
	}
	@SuppressWarnings("unused")
	private void create() { //cr�e un villageois
		if (this.statue < 3){
			if (this.stock >= 150){
				this.statue++;
				this.stock = this.stock-150;
				if (this.statue == 3) {
					this.al.caserne.remove(this.env);
				}
			}
		}
		else {
			if (this.horloge % 500 == 0){
			launchAgent(new Soldat(this.env,this.al));
			this.horloge++;
			}
			else this.horloge++;
		}
	}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonn�es
		
		
		}
	

	


}
