package jeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;






import madkit.kernel.AbstractAgent;
import madkit.kernel.Scheduler.SimulationState;
import madkit.simulation.probe.PropertyProbe;
import madkit.simulation.probe.SingleAgentProbe;
import madkit.simulation.viewer.SwingViewer;
public class Viewer extends SwingViewer{


		private Coord envSize;

		
		protected PropertyProbe<AbstractAgent, Coord> affichage[] = new PropertyProbe[5];
		
		@Override
		protected void activate() {
			
			requestRole(Societe.SOCIETE, Societe.SIMU,Societe.VIEW);

			SingleAgentProbe<Cellule, Coord> envProbe = new SingleAgentProbe<Cellule, Coord>(
					Societe.SOCIETE, 
					Societe.SIMU,
					Societe.ENV, 
					"coord") {
					protected void adding(Cellule agent) {
						super.adding(agent);
						envSize = getPropertyValue();
						
					}
					
					
			};
			addProbe(envProbe);
			
			affichage[0]= new PropertyProbe<AbstractAgent, Coord>( // Rassemble tous les agents "FORUM"
					Societe.SOCIETE, Societe.SIMU,
					Societe.FORUM, "coord");
			
			
			 
			affichage[1]  = new PropertyProbe<AbstractAgent, Coord>( //Rassemble tous les agents "VILLAGEOIS qui cherche du bois"
					Societe.SOCIETE, Societe.SIMU,
					Societe.CHERCHEUR, "coord");
			
			 
			affichage[2] = new PropertyProbe<AbstractAgent, Coord>( // Rassemble tous les agents "BOIS"
					Societe.SOCIETE, Societe.SIMU,
					Societe.BOIS, "coord");
			 
			affichage[3] = new PropertyProbe<AbstractAgent, Coord>( // Rassemble tous les agents "VILLAGEOIS qui ramène une ressource "
					Societe.SOCIETE, Societe.SIMU,
					Societe.RAMENEUR, "coord");
			affichage[4] = new PropertyProbe<AbstractAgent, Coord>( // Rassemble tous les agents "VILLAGEOIS qui ramène une ressource "
					Societe.SOCIETE, Societe.SIMU,
					Societe.ENV, "coord");
			for (int i = 0 ; i<this.affichage.length ; i++){
				addProbe(affichage[i]);
			}

			
	
			
		
			
			
			getFrame().pack();
			getFrame().setLocation(500, 500);
			getFrame().setSize(new Dimension(270,355));
			
			setSynchronousPainting(true);
			
		}


		@Override
		protected void render(Graphics g) {
			
			
		
			for (int i = 0 ; i< this.affichage.length ; i++){
				
				for (AbstractAgent a : affichage[i].getCurrentAgentsList()) {
					Coord coord1 = affichage[i].getPropertyValue(a); // Prend les coordonnée des agents "capturer" dans les listes d'affichage
					Coord coord = coord1.multiple(10); // utile juste pour rendre l'affichage "visible" (zoom)
					
					
					if (i == 0){
						g.setColor(Color.blue);
						g.fillRect(coord.x,coord.y, 10, 10);
					}
					
					if (i == 1){
						g.setColor(Color.RED);
						g.fillOval(coord.x,coord.y, 4, 4);
					}
					if (i == 2){
						g.setColor(Color.GREEN);
						g.fillOval(coord.x,coord.y, 8, 8);
						
					}
					if (i == 3){
						g.setColor(Color.BLUE);
						g.fillOval(coord.x,coord.y, 4, 4);
					}
					if (i == 4){
						g.setColor(Color.GRAY);
						g.drawRect(coord.x,coord.y, 10, 10);
					}
				}
				
			}
		}
}


