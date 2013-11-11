package jeu;

import java.awt.Dimension;

import madkit.kernel.AbstractAgent;
import madkit.simulation.probe.PropertyProbe;


/** Classe qui définis les Unités , chaque Unité (hors batiment) hérite de cette classe.
 * 
 * @author fayej
 *
 */
public class Unite extends ObjectMap {
	
	 /**
     * Represente l'objet ou l'unite doit se dirige lorsqu'il se deplace.
     * Si l'unite ne se dirige pas vers un objet, indique null.
     * Verifier souvent l'etat de l'objet pour savoir
     * si il ne s'est pas deplace ou n'a pas ete detruit.
     */
    protected ObjectMap objetCible;
    /**
     * Represente le point ou l'unite doit se dirige lorsqu'elle doit faire un mouvement.
     * Si l'unite n'est pas en mouvement, cette valeur n'est pas utilise.
     * La positionCible n'est pas forcement egale a la position de l'objetCible.
     */
    protected Coord positionCible;
    /**
     * Represente le vecteur de deplacement de l'unite.
     * Cet attribut permet d'evite de recalculer le vecteur de deplacement
     * lorsque la positionCible ne change pas.
     * Si l'unite n'est pas en mouvement, indique Vecteur.NULL.
     */
    protected Vecteur deplacement;
    /**
     * Represente l'action que l'unite est en train d'effectuer sous la forme d'une chaine de caractere.
     * TODO a definir
     */
    protected String action;
    
    /**
     * Ordonne � l'unite de se deplace vers un point cible.
     * Cette methode redefinie objetCible a null.
     * TODO Cette specificite n'est definitif, il faut savoir si cette methode est faite
     * pour etre utilise en interne ou en externe.
     * @param c positionCible
     */
    public void seDeplace (Coord c){
            //TODO a faire
    }
    
    /**
     * Ordonne a l'unite de se diriger vers un objet cible.
     * Dans le cas ou l'objet se deplace, la position devra etre redefinis
     * TODO La verfication devra surement se faire dans une autre methode.
     * @param o objetCible
     */
    public void SeDirige (ObjectMap o){
            //TODO a faire
    }
	
	
}
