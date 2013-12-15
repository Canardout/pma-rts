package batiment;

import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;
import unite.Soldat;

public class Hopital extends Batiment //implements Stockable
{
	
	
	public Coord coord;
	
	public Cellule env;
	protected int stock ; 
    protected int vie;
    public int statue ;
    public int horloge;

   

	public Hopital (Cellule c , Alignement a){
		
		this.coord = new Coord(c.coord);
		this.env = c;
		this.env.coord = this.coord;
		this.al = a;
		this.env.objet = this;
		this.statue =0;
		this.stock =0;
		this.horloge=0;
		this.al.hopital.add(this.env);
		
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
	
	
	
	protected void activate(){
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.HOPITAL );
		this.activationgeneral();
		
		
	}
	@SuppressWarnings("unused")
	private void soin() { //crï¿½e un villageois
		/*
		if (this.statue < 3){
			if (this.stock >= 150){
				this.statue++;
				this.stock = this.stock-150;
				if (this.statue == 3) {
					this.al.hopital.remove(this.env);
				}
			}
		}
		else {
			if (this.horloge % 500 == 0){
			*/
			for(int i =0 ; i<this.env.personne.size() ; i++){
				System.out.print("Avant : "+this.env.personne.get(i).getvie());
				this.env.personne.get(i).setvie(1); //donne 1 de vie aux unités. (pourra être remplacer par un % pour plus d'équitabilité)
				System.out.println("Après : "+this.env.personne.get(i).getvie());
			}
			/*
			this.horloge++;
			}
			else this.horloge++;
		}
		*/
	}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonnï¿½es
		
		
		}
	

}
