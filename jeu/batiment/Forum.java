package batiment;


import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;
import unite.Constructeur;
import unite.Villageois;




/**
 * Classe "FORUM" definis le batiment Forum et son activitee au cours de son activation.
 * @author fayej
 *
 */

@SuppressWarnings("serial")
public class Forum extends Batiment //implements Stockable
{
	
	
	public Coord coord;
	
	private Cellule env;
	protected int stock =400; //temporairement place ici
    protected int vie;
    public int limitpop =5;
    public int limitcont = 3;
    public boolean perdu; // indique sir l'alignement a perdu ou non.
 
	public static final int MAX_STOCK = Integer.MAX_VALUE; // � voir
	

	public Forum (Cellule c , Alignement a){
		this.coord = new Coord(c.coord);
		this.env = c;
		this.env.coord = this.coord;
		this.al = a;
		this.env.objet = this;
		this.perdu = false;
		
	}
	public void addStock(){
		this.stock ++;
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
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.FORUM );
		this.activationgeneral();
		
		
	}
	@SuppressWarnings("unused")
	private void create() { //cr�e un villageois
		if (!this.perdu){
		if (this.stock-100 >=0){
			
			if(!(this.limitpop <=0)){
			launchAgent(new Villageois(this.env,this.al));
			
			
			this.stock = this.stock-40;
			this.limitpop --;}
		
				else if (this.stock -150>= 0 && this.limitcont >0){
					
					launchAgent(new Constructeur(this.env,this.al));
					this.stock = this.stock -150;
					this.limitcont--;
				}}
				if (this.perdu()) {
					
					for (int i =0 ; i<this.al.caserne.size() ; i++){
						killAgent(this.al.caserne.get(i).objet);
					}
					for(int j = 0 ; j<this.al.demande_ressource.size() ; j++){
						killAgent(this.al.demande_ressource.get(j).objet);
					}
					this.perdu = true;;
				}
				}
	}
	

	public boolean perdu(){ // verifie si l'alignement est concidere comme perdant (plus d'unite) ou non.

    		
    		return (!isRole(Societe.SOCIETE,Societe.SIMU,Societe.ALIGNEMENT[this.al.numero]) && (this.stock<100));
    	
	}
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonn�es
		
		
		}
	

	


}
