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

import javax.swing.JFrame;

import madkit.kernel.AbstractAgent;

/**
 * Classe Societe, classe définissant la méthode main.
 * Des paramètres du programme peuvent être modifié directement dans la méthode activate
 * @author fayej
 */

public class Societe extends AbstractAgent{
	
	public static final String SOCIETE="soc";
	public static final String SIMU="simu";
	public static final String CHERCHEUR = "agent";
	public static final String RAMENEUR ="ramene";
	public static final String ENV = "env";
	public static final String SCHEDULER	= "scheduler";
	public static final String	VIEW	= "viewer";
	public static final String FORUM = "Forum";
	public static final String BOIS ="bois";
	public static final String CARTE="carte";
	public static final String SOLDAT="soldat";
	public static final String OBJECTMAP ="representable";
	public static final String CONSTRUCTEUR ="construit";
	public static final String CASERNE ="cons-soldat";
	public static final String HOPITAL = "HOPITAL";

	public static String[] ALIGNEMENT = new String[8]; // Alignement que 
	//!\\ Les Alignement ne peuvent desormer plus exceder 8 !
	
	
	protected void activate() {
		for (int i = 0 ; i<this.ALIGNEMENT.length ; i++){
			this.ALIGNEMENT[i] = "a"+i;
		}
		
		
		// 1 : create the simulation group
		createGroup(SOCIETE, SIMU);
		
		GameDistributor scheduler = new GameDistributor(this);
		launchAgent(scheduler,false);
		
		// 2 : create the environment
		int longueur = 25;
		int largeur = 25;
		int taille_cellule = 50;
		int alignement = 4;
		
		
		Environnement env = new Environnement(scheduler, longueur, largeur, alignement);
		
		//env.changeIA(3);
		//env.changeIA(2, 0);
		// Les ia vont de 1 à 3
		
		launchAgent(env);

		// 3 : create the viewer
		
		Viewer viewer= new Viewer(scheduler,taille_cellule, longueur , largeur);
		launchAgent(viewer,true);
		
		
		// 2 : launch some simulated agents

		
	}
	
	public static void main(String[] args) {
		executeThisAgent(1,false); //no gui for me
	}
}
	
	

