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
public class Forum extends Batiment implements Stockable
{

	
	private Cellule env; //TODO à modifier, porte le même nom que l'environnement et même fonction que curent
	protected int stock =400; //temporairement place ici
    protected int vie;
    public int limitpop =5;
    public int limitcont = 3;
 
	public static final int MAX_STOCK = Integer.MAX_VALUE; // à voir
	

	public Forum (Cellule c , Alignement a){
		super(c, a);
		this.env = c;
		this.env.coord = this.coord;
		this.env.objet = this;
		
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
		
		if (this.stock-100 >=0){
			
			if(!(this.limitpop <=0)){
			launchAgent(new Villageois(this.env,this.al));
			
			
			this.stock = this.stock-40;
			this.limitpop --;}
							/*
				Random r = new Random();
				int valeur = r.nextInt(3)-1;
				int valeur2 = r.nextInt(3)-1;
				for (int i=0 ; i< this.coord.size() ; i++){
					if (this.stock >400 && this.limitagrand >0){
					if (this.env.env.getCellule(this.coord.get(i).x+valeur, this.coord.get(i).y+valeur2).objet == null){
						this.env.env.getCellule(this.coord.get(i).x+valeur, this.coord.get(i).y+valeur2).objet = this;
						this.stock = this.stock-400;
						this.limitpop = this.limitpop+5;
						Coord c = new Coord(this.coord.get(i).x+valeur, this.coord.get(i).y+valeur2);
						this.coord.add(c);
						this.limitagrand --;
						
					}
					*/
				else if (this.stock -150>= 0 && this.limitcont >0){
					
					launchAgent(new Constructeur(this.env,this.al));
					this.stock = this.stock -150;
					this.limitcont--;
				}}
				
				}
			
		//}
	//}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonn�es
		
		
		}
	

	


}
