package jeu;


/**
* Cette classe gere l'Environnement , peut renvoye ce qu'il se trouve sur differentes cellules
* adjacente a  une Unite ect ...
*
* TODO redefinire auteur
* @author fayej, powlpy
* @version 1.0
*/
public class Environnement {
	
    /**
     * TODO ecrire commentaire ou pas
     * ps tout devrais etre modifiable a partir des getters
     */
    protected Cellule carte[][];
    
    public Environnement (int longueur, int largeur){
            
    }
    
    public Environnement (int longueur, int largeur, String carte){
            
    }
    
    public Cellule getCellule (int x, int y){
            return this.carte[x][y];
    }
    
    public Cellule getCellule (Coord c){
            return this.carte[(int)c.x][(int)c.y];
    }
}