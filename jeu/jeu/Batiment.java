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

/**
* Classe qui cree les Batiments , chaque batiments herite de cette classe
* @author fayej, Nicolas
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