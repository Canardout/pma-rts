package jeu;


import java.util.ArrayList;
import java.util.Random;

import madkit.kernel.AbstractAgent;

import java.awt.Color;

import batiment.Forum;


/**
* Cette classe gere l'Environnement , peut renvoye ce qu'il se trouve sur differentes cellules
* adjacente a  une Unite ect ...
*
* TODO redefinire auteur
* @author fayej, powlpy
* @version 1.0
*/
public class Environnement extends AbstractAgent{
	
    /**
     * TODO ecrire commentaire ou pas
     * ps tout devrais etre modifiable a partir des getters
     */
   private Cellule carte[][];
   private int longueur;
   private int largeur;
   private Alignement[] al;
    
    protected void activate(){ // Activation de l'Environnement en creant un tableau de cellule et en lan�ant tous ces agents cellules
    	requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CARTE );
    	
        	 // Cr�e la grille de jeu (grille de cellule)
        	for (int i=0 ; i < this.longueur; i++){
        		for (int j=0 ; j < this.largeur ; j++){
        			Coord actual = new Coord (i,j); //(*10 pour l'affichage)
        			this.carte[i][j] = new Cellule(actual,this);
        			launchAgent(this.carte[i][j]);
        		}
        	}
        	Random r = new Random();
        	int valeur ;
    		int valeur2 ;
        	valeur = r.nextInt(this.longueur-1);
			valeur2 = r.nextInt(this.largeur-1);
			for (int i =0 ; i<this.al.length ; i++){
				this.carte[valeur][valeur2].add(new Forum (this.carte[valeur][valeur2], this.al[i]));
				valeur = r.nextInt(this.longueur-1);
    			valeur2 = r.nextInt(this.largeur-1);
			}
		
        	
	}
    
    
   @SuppressWarnings("null")
public ArrayList<Cellule> getenv(Cellule c){
	   ArrayList<Cellule> l = new ArrayList() ;
	   // Regard d'une unit� "normale" (carr�e de 9)
	   for (int i =-1 ; i<=1 ; i++){
		   for (int j = -1 ; j<=1 ; j++ ){
			   l.add(this.getCellule((c.coord.x+i) , (c.coord.y+j)));
		   }
	   }
	  
		
	return l;
	   
   }

	public Environnement (int longueur, int largeur , int al){ // Genere un environnement avec une longueur , largeur et un nombre d'alignement (nombre de forum en debut de simulation)
		this.carte = new Cellule[longueur][largeur];
		this.longueur = longueur;
		this.largeur = largeur;
		this.al = new Alignement[al];
		//Generation des couleurs des Alignements
		Random rand = new Random();
		float red = rand.nextFloat();
		float grey = rand.nextFloat();
		float blue = rand.nextFloat();
		Color randomColor = new Color(red, grey, blue);
		
		for (int i =0 ; i<al ; i++){
			this.al[i] = new Alignement(randomColor);
			red = rand.nextFloat();
			grey = rand.nextFloat();
			blue = rand.nextFloat();
			randomColor = new Color(red, grey, blue);
			
		}
		this.al[0].IA = 2; //TODO /////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		
    }

	/**
	 * @author nico
	 * @param coordonnée
	 * @return true si les coordonnées sont hors de la carte
	 */
	public boolean horsLimite (Coord c){
		return c.x < 0 || c.y < 0 || c.x >= this.longueur || c.y >= this.largeur;
	}
    
    public Cellule getCellule (int x, int y){
    	if (x <0) x = x*(-1);
    	if (y < 0) y = y*(-1);
    	
            return this.carte[(x)%this.longueur][(y)%this.largeur];
    }
    
    //nico
    public Cellule getCellule (Coord c){
    	if(!horsLimite(c))
            return this.carte[c.x][c.y];
    	else
    		return null;
    }
    
    protected void ressource(){ // s'il n'y a plus d'arbre sur le terrain alors j'en refait !
    	
    	if (!isRole(Societe.SOCIETE,Societe.SIMU,Societe.BOIS)){
    		Random r = new Random();
    		int valeur ;
    		int valeur2 ;
    		for (int i = 0 ; i<10 ; i++){
    			valeur = r.nextInt(this.longueur-1);
    			valeur2 = r.nextInt(this.largeur-1);
    			
    			if (this.carte[valeur][valeur2].objet == null){
        			this.carte[valeur][valeur2].add(new Bois(getCellule(valeur, valeur2)));
    			}
    		
    		}
    		
    	}
    }
    
    public Alignement[] getAlignement (){
    	return this.al;
    }
    
    public void supprimerRessource (Cellule c){
    	for(int i = 0 ; i < this.al.length ; i++){
    		this.al[i].supprimeRessource(c);
    	}
    }
    
    public Coord getDimension (){
    	return new Coord(this.longueur, this.largeur);
    }
}
