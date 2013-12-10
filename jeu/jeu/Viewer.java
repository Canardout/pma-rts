package jeu;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollBar;

import batiment.Caserne;
import batiment.Forum;
import unite.Constructeur;
import unite.Soldat;
import unite.Villageois;
import madkit.simulation.probe.PropertyProbe;
import madkit.simulation.probe.SingleAgentProbe;
import madkit.simulation.viewer.SwingViewer;
public class Viewer extends SwingViewer{


		private Coord envSize;
		private GameDistributor g;
		public int taille_cel;
		public int longueur;
		public int largeur;
		public Image herbe;
		public Image bois;
		public Image forum;
		public Image villageois;
		public Image soldat;
		
		protected PropertyProbe<ObjectMap , Coord> aff ;
		protected PropertyProbe <Cellule , Coord> cellule;
		 
		 
		
		public Viewer(GameDistributor g ,int taille_cel, int longueur , int largeur){
			
			this.g = g;
			this.longueur = longueur;
			this.largeur = largeur;
			this.taille_cel = taille_cel;
			
			this.herbe = new ImageIcon(getClass().getResource("/jeu/Ressource/herbe.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.bois = new ImageIcon(getClass().getResource("/jeu/Ressource/arbre.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum = new ImageIcon(getClass().getResource("/jeu/Ressource/forum.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois = new ImageIcon(getClass().getResource("/jeu/Ressource/Villageois2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat = new ImageIcon(getClass().getResource("/jeu/Ressource/vill.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
		}

		
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
			aff = new PropertyProbe<ObjectMap, Coord>(Societe.SOCIETE, Societe.SIMU, Societe.OBJECTMAP ,"coord");
			cellule = new PropertyProbe <Cellule , Coord>(Societe.SOCIETE,Societe.SIMU,Societe.ENV , "coord");
			getFrame().pack();
			getFrame().setLocation(500, 500);
			getFrame().setSize(new Dimension(largeur*taille_cel,(longueur*taille_cel)+115));
			getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSynchronousPainting(true);
			
			
			getFrame().getJMenuBar().add(this.g.getSchedulerToolBar(),5);
			getFrame().getJMenuBar();
			addProbe(aff);
			addProbe(cellule);
			
			
		}
		@Override
		protected void render(Graphics g) {
			for (Cellule a :  cellule.getCurrentAgentsList()){
				g.setColor(Color.GRAY);
				Coord coord1 = cellule.getPropertyValue(a); // Prend les coordonn�e des agents "capturer" dans les listes d'affichage
				Coord coord = coord1.multiple(taille_cel); 
				//g.drawImage(fond, coord.x,coord.y, pane );
				g.drawImage(herbe,coord.x,coord.y, null);
				g.drawRect(coord.x,coord.y, taille_cel, taille_cel);
				
				
			}
			
			for (ObjectMap a : aff.getCurrentAgentsList()) {
				Coord coord1 = aff.getPropertyValue(a); // Prend les coordonn�e des agents "capturer" dans les listes d'affichage
				Coord coord = coord1.multiple(taille_cel); 
				if (a instanceof Forum){
					
							g.setColor(a.al.color);
							g.drawImage(forum,coord.x,coord.y, null);
							//g.fillRect(coord.x,coord.y, 10, 10);
						}
				else if (a instanceof Caserne){
					Caserne b = (Caserne) a;
					g.setColor(a.al.color.darker());
					if (b.statue == 0 ){
						g.fillRect(coord.x,coord.y, 2, 2);
					}
					else if (b.statue == 1){
						g.fillRect(coord.x,coord.y, 4, 4);
					}
					else if (b.statue == 2) {
						g.fillRect(coord.x,coord.y, 6, 6);
					}
					else {
					
					g.fillRect(coord.x,coord.y, 8, 8);
					}
				}
					
				
				else if (a instanceof Constructeur){
						g.setColor(Color.ORANGE);
						g.fillRect(coord.x,coord.y, 5, 5);
					}
					
					
					else if (a instanceof Bois){ // repr�sente le bois
						g.setColor(Color.GREEN);
						g.drawImage(bois,coord.x,coord.y, null);
						//g.fillOval(coord.x,coord.y, 8, 8);
					}
					else if (a instanceof Villageois){ // repr�sente les villageois
						g.drawImage(villageois,coord.x,coord.y, null);
						/*
						Villageois b = (Villageois) a;
						if (!b.plein){
							g.setColor(a.al.color);
							g.fillOval(coord.x,coord.y, 4, 4);
							
						}
						else {
							
							g.setColor(a.al.color.darker());
							g.fillOval(coord.x,coord.y, 4, 4);
						}
						*/
					}
					else if (a instanceof Soldat){
						Soldat b = (Soldat) a;
						if (b.epuise){
							g.drawImage(soldat,coord.x,coord.y, null);
						}
						else{
						g.setColor(a.al.color.darker().darker());
						g.fillRect(coord.x,coord.y, 6, 6);
						}
					}
					
					
				}
			
			for (Cellule a :  cellule.getCurrentAgentsList()){
				g.setColor(Color.GRAY);
				Coord coord1 = cellule.getPropertyValue(a); // Prend les coordonn�e des agents "capturer" dans les listes d'affichage
				Coord coord = coord1.multiple(taille_cel); 
				g.drawString(""+a.personne.size(),coord.x,coord.y);
				
			}
				}
			}

		
			

	



