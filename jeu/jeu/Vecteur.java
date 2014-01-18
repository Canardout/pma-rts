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
 * Classe Vecteur, permet de définir des vecteurs
 * @author Nicolas
 */

public class Vecteur {
	public float x, y;
	
	public Vecteur(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public Vecteur(Coord c){
		this.x = c.x;
		this.y = c.y;
	}
	
	public float norme (){
		return (float) Math.sqrt(x*x + y*y);
	}
	
	public Vecteur addition (Vecteur v){
		return new Vecteur(x + v.x, y + v.y);
	}
	
	public void additionEgale (Vecteur v){
		x += v.x;
		y += v.y;
	}
	
	public Vecteur soustraction (Vecteur v){
		return new Vecteur(x - v.x, y - v.y);
	}

	public void soustractionEgale (Vecteur v){
		x -= v.x;
		y -= v.y;
	}
	
	public Vecteur multiplication (float m){
		return new Vecteur(x * m, y * m);
	}
	
	//multiplcationEgale
	//division
	

	
	public Vecteur getDirecteur (){
		float n = this.norme();
		return new Vecteur(x / n, y / n);
	}
}
/*
deplacement = (arrivee - unite).getDirecteur * vitesse;
unite += ()
*/