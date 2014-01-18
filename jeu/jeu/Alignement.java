/*
* Copyright 2013-2014 Jérémie Faye, Nicolas Poelen, Roman Lopez, Alexis Delannoya
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
 * Classe Alignement, représente une nation
 * @author fayej
 */

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Alignement {
	public Color color;
	public int IA;
	public int numero;
	
	public List<Cellule> ressource ;
	public List<Cellule> caserne;
	public List<Cellule> hopital;
	public List<Cellule> demande_ressource; // Liste des cellule où se trouve des batiments voulant des ressources.
	
	public Alignement (Color color, int num){
		this.color = color;
		this.ressource = new ArrayList<Cellule>();
		this.caserne = new ArrayList<Cellule>();
		this.hopital = new ArrayList<Cellule>();
		this.demande_ressource = new ArrayList<Cellule>();
		this.IA = 2;
		this.numero = num;
	}
	
	public void ajouteRessource (List<Cellule> r){
		
	}
	
	public boolean supprimeRessource (Cellule c){
		return this.ressource.remove(c);
	}
}
