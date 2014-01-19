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
import java.util.Random;

import madkit.kernel.AbstractAgent;

import java.awt.Color;

import batiment.Forum;


/**
* Cette classe gere l'Environnement , peut renvoyé ce qu'il se trouve sur differentes cellules
* adjacente a une Unite ect ...
*
* @author fayej, Nicolas
* @version 1.0
*/
public class Environnement extends AbstractAgent{
	
   private Cellule carte[][];
   private int longueur;
   private int largeur;
   private Alignement[] al;
   private Forum[] forum;
   private GameDistributor game;
   public int nb_arbre;
    
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
        	valeur = r.nextInt(this.longueur-1 - (ObjectMap.vision)) + (ObjectMap.vision / 2);
			valeur2 = r.nextInt(this.largeur-1 - (ObjectMap.vision)) + (ObjectMap.vision / 2);
			for (int i =0 ; i<this.al.length ; i++){
				this.carte[valeur][valeur2].add(new Forum (this.carte[valeur][valeur2], this.al[i]));
				this.forum[i] = (Forum)this.carte[valeur][valeur2].objet;
				valeur = r.nextInt(this.longueur-1 - (ObjectMap.vision)) + (ObjectMap.vision / 2);
				valeur2 = r.nextInt(this.largeur-1 - (ObjectMap.vision)) + (ObjectMap.vision / 2);
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

   public Environnement (GameDistributor game,int longueur, int largeur , int al){ // Genere un environnement avec une longueur , largeur et un nombre d'alignement (nombre de forum en debut de simulation)
		this.carte = new Cellule[longueur][largeur];
		this.longueur = longueur;
		this.largeur = largeur;
		this.al = new Alignement[al];
		this.forum = new Forum[al];
		this.game = game;
		
		//Generation des couleurs des Alignements
		Random rand = new Random();
		float red = rand.nextFloat();
		float grey = rand.nextFloat();
		float blue = rand.nextFloat();
		Color randomColor = new Color(red, grey, blue);
		
		for (int i =0 ; i<al ; i++){
			this.al[i] = new Alignement(randomColor, i);
			red = rand.nextFloat();
			grey = rand.nextFloat();
			blue = rand.nextFloat();
			randomColor = new Color(red, grey, blue);
			
		}
    }

	/**
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
    
    public Cellule getCellule (Coord c){
    	if(!horsLimite(c))
            return this.carte[c.x][c.y];
    	else
    		return null;
    }
    
    protected void ressource(){ // s'il n'y a plus d'arbre sur le terrain alors j'en refait !
    	
    	if (this.nb_arbre < 4){
    		Random r = new Random();
    		int valeur ;
    		int valeur2 ;
    		for (int i = 0 ; i<10 ; i++){
    			valeur = r.nextInt(this.longueur-1);
    			valeur2 = r.nextInt(this.largeur-1);
    			
    			if (this.carte[valeur][valeur2].objet == null){
        			this.carte[valeur][valeur2].add(new Bois(getCellule(valeur, valeur2)));
        			this.nb_arbre++;
    			}
    		}
    		
    		}
    		if (this.forum.length != 1){
    		int cpt = 0;

    		for (int i = 0 ; i<this.forum.length ; i++){
    			if (this.forum[i].perdu){
    				cpt++;
    			}
    		}
    		if (cpt == this.forum.length-1){
    			this.game.stop();
    			System.out.println("Nous avons un gagnant !");
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
    
    public int getLongueur(){
    	return this.longueur;
    }
    
    public int getLargeur(){
    	return this.largeur;
    }
    
    /**
     * Change l'ia d'un alignement.
     * Attention, faite le avant de lancer l'environnement
     * @param ia
     * @param al : l'alignement à changer
     */
    public void changeIA (int ia, int al){
    	if(al < this.al.length){
    		this.al[al].IA = ia;
    	}
    }
    
    /**
     * Change l'ia de tous les alignments.
     * Attention, faite le avant de lancer l'environnement.
     * @param ia
     */
    public void changeIA (int ia){
    	for(int i = 0 ; i < this.al.length ; i++){
    		this.al[i].IA = ia;
    	}
    }
}
