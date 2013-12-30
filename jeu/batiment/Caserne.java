package batiment;


import unite.Soldat;
import unite.Villageois;
import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;




/**
 * Classe "Caserne" definis le batiment Caserne et son activitee au cours de son activation.
 * @author fayej
 *
 */

@SuppressWarnings("serial")
public class Caserne extends Batiment implements Stockable
{

	
	private Cellule env; //TODO à modifier, porte le même nom que l'environnement et même fonction que curent
	protected int stock ; 
    protected int vie;
    public int statue ;
 

    public static final int MAX_STOCK = Integer.MAX_VALUE; // � voir

	public Caserne (Cellule c , Alignement a){
		
		this.coord = new Coord(c.coord);
		this.env = c;
		this.env.coord = this.coord;
		this.al = a;
		this.env.objet = this;
		this.statue =0;
		this.stock =0;
	
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
	
	public int prendreStock (int demande){
		if(demande > this.stock){
			int s = this.stock;
			this.stock = 0;
			return s;	
		}
		else{
			this.stock -= demande;
			return demande;
		}
	}
	
	public int donnerStock (int dons){
		if(this.stock + dons > MAX_STOCK){
			int r = MAX_STOCK - this.stock;
			this.stock = MAX_STOCK;
			return r;
		}
		else{
			this.stock += dons;
			return 0;
		}
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
					this.al.demande_ressource.add(this.env);
					
				}
			}
		}
		else {
			if (this.stock >= 150){
			launchAgent(new Soldat(this.env,this.al));
			this.stock = this.stock-150;
			}
			
		}
	}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonn�es
		
		
		}
	

	


}
