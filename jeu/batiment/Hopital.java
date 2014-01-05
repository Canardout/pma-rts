package batiment;

import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;
import unite.Soldat;

public class Hopital extends Batiment //implements Stockable
{

	
	public Cellule curent; //TODO à modifier, porte le même nom que l'environnement et même fonction que curent
	protected int stock ; // L'hopital poss�de des stocks ?
    protected int vie;
    public int statue ;
    public int horloge;

   

	public Hopital (Cellule c , Alignement a){
		super(c, a);
		this.curent.objet = this;
		this.statue =0;
		this.stock =0;
		this.horloge=0;
		this.al.hopital.add(this.curent);
		
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
	private void soin() { //cr�e un villageois
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
			for(int i =0 ; i<this.curent.personne.size() ; i++){
				System.out.print("Avant : "+this.curent.personne.get(i).getvie());
				this.curent.personne.get(i).setvie(1); //donne 1 de vie aux unit�s. (pourra �tre remplacer par un % pour plus d'�quitabilit�)
				System.out.println("Apr�s : "+this.curent.personne.get(i).getvie());
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
		// Donne ces coordonn�es
		
		
		}
	

}
