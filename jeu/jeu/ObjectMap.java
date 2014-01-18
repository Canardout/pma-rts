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

package jeu;

import java.util.ArrayList;
import java.util.List;

import madkit.kernel.AbstractAgent;

/**
* Cette classe definis les caracteristiques globale des Objets place sur la carte
* (Cordonnee x,y , vie , resistance ...)
*
* @author fayej, Nicolas
* @version 1.0
*/


public class ObjectMap extends AbstractAgent { 
	
	protected Coord coord;
	protected Environnement env;
	public Cellule curent;
	public Alignement al;
	
  	public static final int vision = 4;
    
	public ObjectMap (Cellule c , Alignement a){
		this.curent = c;
		this.coord = c.coord;
		this.al =a;
		this.env = c.env;
	}
	
    /**
     * getter de coord
     * @return coord
     */
    public Coord getCoord (){
            return this.coord;
    }
    
    /**
     * Distance entre 2 objet en nombre de cellule � parcourire
     */
    public int distance (ObjectMap o){
    	return distance(o.getCoord());
    }
    
    public int distance (Coord c){
    	return Math.abs(this.coord.x - c.x) + Math.abs(this.coord.y - c.y);
    }
    
    public void activationgeneral(){
    	requestRole(Societe.SOCIETE , Societe.SIMU , Societe.OBJECTMAP);
    }
    
  	/**
  	 * Les premiers éléments de la liste sont les plus proches de l'objets
  	 * @return tous les objets visible par l'unite
  	 */
  	public List<ObjectMap> vision (){
  		List<ObjectMap> l = new ArrayList<ObjectMap>();
  		
  		parcoursVision(l, Coord.NULL, 1, Coord.NULL);
  		//l.remove(this); //TODO bug à corrigé
  		for(int i = 1 ; i <= vision ; i++){
  			Coord v = new Coord(i, 0);
  			parcoursVision(l, v, i, new Coord(-1, 1));
  			parcoursVision(l, v, i, new Coord(-1, -1));
  			parcoursVision(l, v, i, new Coord(1, -1));
  			parcoursVision(l, v, i, new Coord(1, 1));
  		}
  		return l;
  	}
  	/**
  	 * @param l liste d'objet visible
  	 * @param v position actuel du pointeur
  	 * @param i distance par rapport au centre
  	 * @param dep vecteur de déplacement du pointeur
  	 */
  	private void parcoursVision(List<ObjectMap> l, Coord v, int i, Coord dep){
  		//System.out.println(env);
  		for(int j = 0 ; j < i ; j++){
  			if(this.env.getCellule(this.coord.add(v)) != null){
  				l.addAll(this.env.getCellule(this.coord.add(v)).listeObjet());
  			}
  			v.addEgal(dep);
  		}
  	}	
}
