package jeu;

import madkit.kernel.AbstractAgent;

/**
* Cette classe definis les caracteristiques globale des Objets place sur la carte
* (Cordonnee x,y , vie , resistance ...)
*
* @author fayej, powlpy
* @version 1.0
*/


public class ObjectMap extends AbstractAgent { 
	
	protected Coord coord;
	protected Environnement env;
	public Alignement al;
    
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
    
   

}
