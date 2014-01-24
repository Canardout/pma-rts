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


import unite.Soldat;
import unite.Villageois;
import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;

/**
 * Classe "Caserne" definis le batiment Caserne et son activitée au cours de son activation.
 * @author fayej
 *
 */

@SuppressWarnings("serial")
public class Caserne extends Batiment implements Stockable
{

	
	private Cellule curent;
	protected int stock ; 
    protected int vie;
    public int statue ;
 

    public static final int MAX_STOCK = Integer.MAX_VALUE;

	public Caserne (Cellule c , Alignement a){
		super(c, a);
		this.curent = c;
		this.curent.coord = this.coord;
		this.curent.objet = this;
		this.statue =0;
		this.stock =0;
	
		this.al.caserne.add(this.curent);
		
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
	private void create() { //créé un villageois
		if (this.statue < 3){
			if (this.stock >= 150){
				this.statue++;
				this.stock = this.stock-150;
				if (this.statue == 3) {
					this.al.caserne.remove(this.curent);
					this.al.demande_ressource.add(this.curent);
					
				}
			}
		}
		else {
			if (this.stock >= 150){
			launchAgent(new Soldat(this.curent,this.al));
			this.stock = this.stock-150;
			}
			
		}
	}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonnées
		
		
		}
}
