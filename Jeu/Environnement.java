package jeu;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import madkit.gui.AgentFrame;
import madkit.kernel.AbstractAgent;
import madkit.kernel.AgentAddress;

import java.lang.Math;

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
    
    protected void activate(){ // Activation de l'Environnement en creant un tableau de cellule et en lançant tous ces agents cellules
    	requestRole(Societe.SOCIETE , Societe.SIMU , Societe.CARTE );
    	
        	 // Crée la grille de jeu (grille de cellule)
        	for (int i=0 ; i < this.carte.length ; i++){
        		for (int j=0 ; j < this.carte.length ; j++){
        			Coord actual = new Coord (i,j); //(*10 pour l'affichage)
        			this.carte[i][j] = new Cellule(actual,this);
        			launchAgent(this.carte[i][j]);
        		}
        	}
        	this.carte[1][1].add(new Bois(this.carte[1][1])); // Génération des objets dans les Cellules , mis ici PROVISOIREMENT
        	this.carte[3][5].add(new Bois(this.carte[3][5]));
        	this.carte[8][20].add(new Bois(this.carte[8][20]));
        	this.carte[18][18].add(new Bois(this.carte[18][15]));
        	this.carte[12][15].add(new Forum (this.carte[12][15]));
	}
    
    
    public Environnement (){
    }
    
   @SuppressWarnings("null")
public ArrayList<Cellule> getenv(Cellule c){
	   ArrayList<Cellule> l = new ArrayList() ;
	   // peut être simplifier à l'aide du boucle for , mais laisser ici comme ça pour plus de clareter
	   l.add(this.getCellule(c.coord.x+1 , c.coord.y)); // regarde la case à droite
	   l.add(this.getCellule((c.coord.x-1) , c.coord.y));// regarde la case à gauche
	   l.add(this.getCellule(c.coord.x , c.coord.y+1)); //regarde la case en haut
	   l.add(this.getCellule(c.coord.x , (c.coord.y-1))); //regarde la case en bas
	   l.add(this.getCellule(c.coord.x, c.coord.y));
		
	return l;
	   
   }

	public Environnement (int longueur, int largeur){
		this.carte = new Cellule[longueur][largeur];
		this.longueur = longueur;
		this.largeur = largeur;
    }
    
    public Cellule getCellule (int x, int y){
    	if (x <0) x = x*(-1);
    	if (y< 0) y = y*(-1);
            return this.carte[(x)%this.longueur][(y)%this.largeur];
    }
    
    public Cellule getCellule (Coord c){
            return this.carte[(c.x)%this.longueur][(c.y)%this.largeur];
    }
    
    protected void ressource(){ // s'il n'y a plus d'arbre sur le terrain alors j'en refait !
    	
    	if (!isRole(Societe.SOCIETE,Societe.SIMU,Societe.BOIS)){
    		Random r = new Random();
    		int valeur ;
    		int valeur2 ;
    		for (int i = 0 ; i<5 ; i++){
    			valeur = r.nextInt(this.longueur-1);
    			valeur2 = r.nextInt(this.longueur-1);
    			if (this.carte[valeur][valeur2].objet == null){
        			this.carte[valeur][valeur2].add(new Bois(this.carte[valeur][valeur2]));
    		}
    		
    		}
    		
    		
    	}
    }
    
}