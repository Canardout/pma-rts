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

import java.util.logging.Level;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Scheduler;
import madkit.simulation.activator.GenericBehaviorActivator;
import unite.Constructeur;
import unite.Soldat;
import unite.Villageois;

/**
 * Classe GameDistributor, gère les activateurs
 * @author fayej
 */

public class GameDistributor extends madkit.kernel.Scheduler{
		
	protected GenericBehaviorActivator<AbstractAgent> [] Activateur;
	
	
	@Override
	protected void activate() {
		
		this.Activateur = new GenericBehaviorActivator[10];
		// Le Scheduler prend son rôle

		requestRole(Societe.SOCIETE,Societe.SIMU,Societe.SCHEDULER); 
		
		// Activateur pour : l'agent qui g�re toute la carte (ENVIRONNEMENT)
		this.Activateur[0] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.CARTE, "ressource"); ///!\ Provisoir /!\ lance de nouveau agents Bois quand 
		// Activateur pour : le bois (Augmentation de leur ressources)                                                               // Ils sont tous mort
		this.Activateur[1] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.BOIS, "wood");
		// Activateur pour : les villageois (Recherche ressource Bois)
		this.Activateur[2] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.CHERCHEUR, "Chrwood");
		// Activateur pour : les villageois (Rentre au forum)
		this.Activateur[3] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.RAMENEUR ,"Retour");
	
		// Activateur pour : Le Forum (cr�ation de villageois)
		this.Activateur[5] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.FORUM ,"create");
		this.Activateur[6] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.SOLDAT ,"attaque");
		this.Activateur[7] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.CONSTRUCTEUR ,"choixconstructeur");
		this.Activateur[8] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.CASERNE ,"create");
		this.Activateur[9] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE , Societe.SIMU , Societe.HOPITAL ,"soin");
		
		// Activateur pour : Le rendu graphique de la simulation (viewer)
		this.Activateur[4] = new GenericBehaviorActivator<AbstractAgent>(Societe.SOCIETE,Societe.SIMU,Societe.VIEW, "observe");
		
		for (int i = 0 ; i< this.Activateur.length ; i++){
			addActivator(this.Activateur[i]);
		}
		
		
		setDelay(200);
		
		//4 : let us start the simulation automatically
		setSimulationState(SimulationState.PAUSED);
		
	}	 
					 
	public void stop(){
		setSimulationState(SimulationState.PAUSED);
	}	 
					 
	
}

