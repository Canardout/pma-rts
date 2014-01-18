/*
* Copyright 2013-2014 Jérémie Faye, Nicolas Poelen, Roman Lopez, Alexis Delannoya
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

package jeu;

/**
 * Classe Bois, représente un arbre
 * @author fayej
 *
 */

public class Bois extends Ressource{
	
		public Bois(Cellule cel){
			this(cel, 300);
		}
	
		public Bois (Cellule c, int q){
			super(c, q);
			this.curent.objet = this;
		}
		
		public void give(){ //la ressource se décremente et la "donne" au villageois
			this.quantite--; 
			
		}

	
	
	protected void activate(){
		// Defini le role de l'objet "Bois" dans la société
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.BOIS);
		
		
	}
	@SuppressWarnings("unused")
	private void wood() {
		
		if (this.quantite <=0){
			this.curent.objet = null; // On dis à la cellule qu'elle ne contiens maintenant plus rien
			killAgent(this);
		}
		
			
		}
	
	 public void end(){
	    	this.curent.env.nb_arbre--;
	    }
}
