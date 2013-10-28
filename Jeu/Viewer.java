package jeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.util.Collection;

import javax.swing.JFrame;




import madkit.kernel.AbstractAgent;
import madkit.kernel.Watcher;
import madkit.simulation.probe.PropertyProbe;
import madkit.simulation.probe.SingleAgentProbe;
import madkit.simulation.viewer.SwingViewer;
public class Viewer extends SwingViewer{


		private Dimension envSize;

		
		protected PropertyProbe<AbstractAgent, Dimension> affichage[] = new PropertyProbe[4];
		
		@Override
		protected void activate() {
			
			requestRole(Societe.SOCIETE, Societe.SIMU,Societe.VIEW);

			SingleAgentProbe<Environnement, Dimension> envProbe = new SingleAgentProbe<Environnement, Dimension>(
					Societe.SOCIETE, 
					Societe.SIMU,
					Societe.ENV, 
					"dimension") {
					protected void adding(Environnement agent) {
						super.adding(agent);
						envSize = getPropertyValue();
					}
					
					
			};
			addProbe(envProbe);
			
			affichage[0]= new PropertyProbe<AbstractAgent, Dimension>( // Rassemble tous les agents "FORUM"
					Societe.SOCIETE, Societe.SIMU,
					Societe.FORUM, "location");
			
			
			 
			affichage[1]  = new PropertyProbe<AbstractAgent, Dimension>( //Rassemble tous les agents "VILLAGEOIS qui cherche du bois"
					Societe.SOCIETE, Societe.SIMU,
					Societe.CHERCHEUR, "location");
			
			 
			affichage[2] = new PropertyProbe<AbstractAgent, Dimension>( // Rassemble tous les agents "BOIS"
					Societe.SOCIETE, Societe.SIMU,
					Societe.BOIS, "location");
			 
			affichage[3] = new PropertyProbe<AbstractAgent, Dimension>( // Rassemble tous les agents "VILLAGEOIS qui ramène une ressource "
					Societe.SOCIETE, Societe.SIMU,
					Societe.RAMENEUR, "location");
			for (int i = 0 ; i<this.affichage.length ; i++){
				addProbe(affichage[i]);
			}

			getDisplayPane().setPreferredSize(envSize);
			getFrame().pack();


			setSynchronousPainting(true);
			
		}


		@Override
		protected void render(Graphics g) {
			g.setColor(Color.BLACK);
			 g.fillRect(0, 0, 4000, 4000);
		
			for (int i = 0 ; i< this.affichage.length ; i++){
				for (AbstractAgent a : affichage[i].getCurrentAgentsList()) {
					Dimension location = affichage[i].getPropertyValue(a);
					if (i == 0){
						g.setColor(Color.PINK);
						g.fillRect(location.width, location.height, 15, 15);
					}
					
					if (i == 1){
						g.setColor(Color.RED);
						g.fillOval(location.width, location.height, 4, 4);
					}
					if (i == 2){
						g.setColor(Color.GREEN);
						g.fillOval(location.width, location.height, 8, 8);
						
					}
					if (i == 3){
						g.setColor(Color.BLUE);
						g.fillOval(location.width, location.height, 4, 4);
					}
				}
				
			}
		}
}


