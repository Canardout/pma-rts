package jeu;

import java.util.ArrayList;
import java.util.List;

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
	public Cellule curent;
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
    
    public void activationgeneral(){
    	requestRole(Societe.SOCIETE , Societe.SIMU , Societe.OBJECTMAP);
    }
    
    //nico
  	public static final int vision = 6;
  	/**
  	 * Les premiers éléments de la liste sont les plus proches de l'objets
  	 * @return tous les objets visible par l'unite
  	 */
  	public List<ObjectMap> vision (){
  		List<ObjectMap> l = new ArrayList<ObjectMap>();
  		
  		parcoursVision(l, Coord.NULL, 1, Coord.NULL);
  		l.remove(this);
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
  	 * @author nico
  	 * @param l liste d'objet visible
  	 * @param v position actuel du pointeur
  	 * @param i distance par rapport au centre
  	 * @param dep vecteur de déplacement du pointeur
  	 */
  	private void parcoursVision(List<ObjectMap> l, Coord v, int i, Coord dep){
  		for(int j = 0 ; j < i ; j++){
  			if(this.env.getCellule(this.coord.add(v)) != null){
  				l.addAll(this.env.getCellule(this.coord.add(v)).listeObjet());
  			}
  			v.addEgal(dep);
  		}
  	}	
}
