/*
* Copyright 2013-2014 Jérémie Faye, Nicolas Poelen, Roman Lopez, Alexis Delannoy
*
* This program is free software: you can redistribute it and/or modify it under the
* terms of the GNU General Public License as published by the Free
* Software Foundation, either version 3 of the License, or (at your option) any
* later version.
*
* This program is distributed in the hope that it will be useful, but WITHOUT ANY
* WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR
* A PARTICULAR PURPOSE. See the GNU General Public License for more
* details.
*
* You should have received a copy of the GNU General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/>.
*/

package batiment;

import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;
import unite.Soldat;

public class Hopital extends Batiment //implements Stockable
{

	
	public Cellule curent;
	protected int stock ;
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
	private void soin() {
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
				this.curent.personne.get(i).setvie(1); //donne 1 de vie aux unités. (pourra être remplacer par un % pour plus d'équitabilité)
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
		// Donne ces coordonnées
		
		
		}
	

}
