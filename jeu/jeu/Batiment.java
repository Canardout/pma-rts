package jeu;

/**
* Classe qui cree les Batiments , chaque batiments herite de cette classe
*
* TODO redefinire auteur
* @author fayej, powlpy
* @version 1.0
*/
@SuppressWarnings("serial")
public abstract class Batiment extends ObjectMap {
        /**
         * finalement, tous les bâtiments font 1 de long
         */
        protected int longueur;
        protected int largeur;
        
        public Batiment (Cellule c, Alignement a){
        	super(c, a);
        }
        
        public void activationgeneral(){
        	requestRole(Societe.SOCIETE , Societe.SIMU , Societe.OBJECTMAP);
        }
        
}