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

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import batiment.Caserne;
import batiment.Forum;
import batiment.Hopital;
import unite.Constructeur;
import unite.Soldat;
import unite.Villageois;
import madkit.simulation.probe.PropertyProbe;
import madkit.simulation.probe.SingleAgentProbe;
import madkit.simulation.viewer.SwingViewer;

/**
 * Classe Viewer, gère l'affichage du programme
 * @author fayej
 */

public class Viewer extends SwingViewer{


		private Coord envSize;
		private GameDistributor g;
		public int taille_cel;
		public int longueur;
		public int largeur;
		public boolean a;
		public boolean b;
		public Checkbox chek1;
		public Checkbox2 chek2;
		public Image herbe;
		public Image bois;
		public Image bois1;
		public Image bois2;
		
		public Image forum;
		public Image forum_perte;
		public Image forum1;
		public Image forum_perte1;
		public Image forum2;
		public Image forum_perte2;
		public Image forum3;
		public Image forum_perte3;
		public Image forum4;
		public Image forum_perte4;

		public Image villageois_vide;
		public Image villageois_vide1;
		public Image villageois_vide2;
		public Image villageois_vide3;
		public Image villageois_vide4;
		public Image villageois_plein;
		public Image villageois_plein1;
		public Image villageois_plein2;
		public Image villageois_plein3;
		public Image villageois_plein4;
		
		public Image caserne1;
		public Image caserne2;
		public Image caserne3;
		public Image caserne4;
		public Image caserne4_1;
		public Image caserne4_2;
		public Image caserne4_3;
		public Image caserne4_4;
		
		
		public Image hopital;
		public Image constructeur;
		public Image constructeur1;
		public Image constructeur2;
		public Image constructeur3;
		public Image constructeur4;
		public Image soldat;
		public Image soldat1;
		public Image soldat2;
		public Image soldat3;
		public Image soldat4;
		public Image soldat_fatiguer;
		public Image soldat_fatiguer1;
		public Image soldat_fatiguer2;
		public Image soldat_fatiguer3;
		public Image soldat_fatiguer4;
		protected PropertyProbe<Batiment , Coord> aff ;
		protected PropertyProbe <Cellule , Coord> cellule;
		protected PropertyProbe <Ressource , Coord> ressource;
		 
		 
		
		public Viewer(GameDistributor g ,int taille_cel, int longueur , int largeur){
			
			this.g = g;
			this.longueur = longueur;
			this.largeur = largeur;
			this.taille_cel = taille_cel;
			this.herbe = new ImageIcon(getClass().getResource("/jeu/Ressource/herbe.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.bois = new ImageIcon(getClass().getResource("/jeu/Ressource/arbre.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.bois1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Arbre 2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.bois2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Arbre 3.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.villageois_vide = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 1/V1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_vide1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 2/V1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_vide2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 3/V1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_vide3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 4/V1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_vide4 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 5/V1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.villageois_plein = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 1/V2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_plein1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 2/V2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_plein2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 3/V2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_plein3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 4/V2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.villageois_plein4 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 5/V2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.soldat = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 1/S1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 2/S1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 3/S1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 4/S1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat4 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 5/S1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.soldat_fatiguer = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 1/S2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat_fatiguer1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 2/S2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat_fatiguer2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 3/S2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat_fatiguer3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 4/S2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.soldat_fatiguer4 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 5/S2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.constructeur = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 1/C0.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.constructeur1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 2/C0.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.constructeur2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 3/C0.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.constructeur3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 4/C0.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.constructeur4 = new ImageIcon(getClass().getResource("/jeu/Ressource/Persos/Equipe 5/C0.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.hopital = new ImageIcon(getClass().getResource("/jeu/Ressource/hopital.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum = new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 1/Village 1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 2/Village 1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 3/Village 1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 4/Village 1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum4 = new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 5/Village 1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			
			this.forum_perte =  new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 1/Village 2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum_perte1 =  new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 2/Village 2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum_perte2 =  new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 3/Village 2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum_perte3 =  new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 4/Village 2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.forum_perte4 =  new ImageIcon(getClass().getResource("/jeu/Ressource/Village/Equipe 5/Village 2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.caserne1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Caserne/Caserne 1.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.caserne2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Caserne/Caserne 2.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.caserne3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Caserne/Caserne 3.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.caserne4 = new ImageIcon(getClass().getResource("/jeu/Ressource/Caserne/E1Caserne 4.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.caserne4_1 = new ImageIcon(getClass().getResource("/jeu/Ressource/Caserne/E2Caserne 4.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.caserne4_2 = new ImageIcon(getClass().getResource("/jeu/Ressource/Caserne/E3Caserne 4.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.caserne4_3 = new ImageIcon(getClass().getResource("/jeu/Ressource/Caserne/E4Caserne 4.png")).getImage().getScaledInstance(this.taille_cel, this.taille_cel, Image.SCALE_SMOOTH);
			this.a = true;
			this.b = true;
			
			this.chek1 = new Checkbox("Active", this, this.a);
			this.chek2 = new Checkbox2("Active" , this , this.b);
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
			aff = new PropertyProbe<Batiment, Coord>(Societe.SOCIETE, Societe.SIMU, Societe.OBJECTMAP ,"coord");
			cellule = new PropertyProbe <Cellule , Coord>(Societe.SOCIETE,Societe.SIMU,Societe.ENV , "coord");
			ressource = new PropertyProbe <Ressource , Coord>(Societe.SOCIETE , Societe.SIMU , Societe.BOIS , "coord");
			
			getFrame().pack();
			getFrame().setLocation(500, 500);
			getFrame().setTitle("Projet Multi-Agents : Simulation RTS");
			getFrame().setSize(new Dimension(largeur*taille_cel,(longueur*taille_cel)+115));
			getFrame().setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			setSynchronousPainting(true);
			
			
			getFrame().getJMenuBar().add(this.g.getSchedulerToolBar(),5);
			getFrame().getJMenuBar().add(this.chek1, 5);
			getFrame().getJMenuBar().add(this.chek2, 5);
			addProbe(aff);
			addProbe(cellule);
			addProbe(ressource);
			
			
			
		}
		public void setnum(){
			this.b = ! this.b;
			getFrame().repaint();
		}
		
		public void setgrille(){
			this.a = !this.a;
			getFrame().repaint();
			
		}
		@Override
		protected void render(Graphics g) {
			
			for (Cellule a :  cellule.getCurrentAgentsList()){
				g.setColor(Color.GRAY);
				Coord coord1 = cellule.getPropertyValue(a); // Prend les coordonn�e des agents "capturer" dans les listes d'affichage
				Coord coord = coord1.multiple(taille_cel); 
				//g.drawImage(fond, coord.x,coord.y, pane );
				g.drawImage(herbe,coord.x,coord.y, null);
				if (this.b){
				g.drawRect(coord.x,coord.y, taille_cel, taille_cel);
				}
				if (a.personne.size() !=0){
				for (int i =0 ; i< a.personne.size() ; i++){
					if ( i != a.personne.size()){
					if (a.personne.get(i) instanceof Constructeur){
						Constructeur b = (Constructeur) a.personne.get(i);
						if (b.al.numero ==0){
							g.drawImage(constructeur,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==1){
								g.drawImage(constructeur1,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==2){
								g.drawImage(constructeur2,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==3){
								g.drawImage(constructeur3,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==4){
								g.drawImage(constructeur4,coord.x+(i*3),coord.y+(i*3), null);
							
							}
						
					}
					else if ( i != a.personne.size()){ 
						if (a.personne.get(i) instanceof Villageois){ // repr�sente les villageois
						Villageois b = (Villageois) a.personne.get(i);
						
						if (b.get_quantite() <10){
							if (b.al.numero ==0){
						g.drawImage(villageois_vide,coord.x+(i*3),coord.y+(i*3), null);
						}
						else if (b.al.numero ==1){
							g.drawImage(villageois_vide1,coord.x+(i*3),coord.y+(i*3), null);
						}
						else if (b.al.numero ==2){
							g.drawImage(villageois_vide2,coord.x+(i*3),coord.y+(i*3), null);
						}
						else if (b.al.numero ==3){
							g.drawImage(villageois_vide3,coord.x+(i*3),coord.y+(i*3), null);
						}
						else if (b.al.numero ==4){
							g.drawImage(villageois_vide4,coord.x+(i*3),coord.y+(i*3), null);
						
						}
						}
						else if (b.al.numero ==0){
							g.drawImage(villageois_plein,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==1){
								g.drawImage(villageois_plein1,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==2){
								g.drawImage(villageois_plein2,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==3){
								g.drawImage(villageois_plein3,coord.x+(i*3),coord.y+(i*3), null);
							}
							else if (b.al.numero ==4){
								g.drawImage(villageois_plein4,coord.x+(i*3),coord.y+(i*3), null);
							}
						
					}
					else if (a.personne.get(i) instanceof Soldat){
						Soldat b = (Soldat) a.personne.get(i);
						if (b.epuise){
							
							if(b.al.numero ==0){
								g.drawImage(soldat_fatiguer,coord.x,coord.y, null);
								}
								else if (b.al.numero ==1){
									g.drawImage(soldat_fatiguer1,coord.x,coord.y, null);
								}
								else if (b.al.numero ==2){
									g.drawImage(soldat_fatiguer2,coord.x,coord.y, null);
								}
								else if (b.al.numero ==3){
									g.drawImage(soldat_fatiguer3,coord.x,coord.y, null);
								}
								else if (b.al.numero ==4){
									g.drawImage(soldat_fatiguer4,coord.x,coord.y, null);
								}
						}
						else{
							if(b.al.numero ==0){
								g.drawImage(soldat,coord.x,coord.y, null);
								}
								else if (b.al.numero ==1){
									g.drawImage(soldat1,coord.x,coord.y, null);
								}
								else if (b.al.numero ==2){
									g.drawImage(soldat2,coord.x,coord.y, null);
								}
								else if (b.al.numero ==3){
									g.drawImage(soldat3,coord.x,coord.y, null);
								}
								else if (b.al.numero ==4){
									g.drawImage(soldat4,coord.x,coord.y, null);
								}
							
						}
					}
					}
					}
				}
				}
				
				
			}
			
			for (Batiment a : aff.getCurrentAgentsList()) {
				Coord coord1 = aff.getPropertyValue(a); // Prend les coordonn�e des agents "capturer" dans les listes d'affichage
				Coord coord = coord1.multiple(taille_cel); 
				if (a instanceof Forum){
					Forum b = (Forum)a;
					if(!b.perdu){
						
						if(b.al.numero ==0){
							g.drawImage(forum,coord.x,coord.y, null);
							}
							else if (b.al.numero ==1){
								g.drawImage(forum1,coord.x,coord.y, null);
							}
							else if (b.al.numero ==2){
								g.drawImage(forum2,coord.x,coord.y, null);
							}
							else if (b.al.numero ==3){
								g.drawImage(forum3,coord.x,coord.y, null);
							}
							else if (b.al.numero ==4){
								g.drawImage(forum4,coord.x,coord.y, null);
							}
					}
					else 
					if(b.al.numero ==0){
						g.drawImage(forum_perte,coord.x,coord.y, null);
						}
						else if (b.al.numero ==1){
							g.drawImage(forum_perte1,coord.x,coord.y, null);
						}
						else if (b.al.numero ==2){
							g.drawImage(forum_perte2,coord.x,coord.y, null);
						}
						else if (b.al.numero ==3){
							g.drawImage(forum_perte3,coord.x,coord.y, null);
						}
						else if (b.al.numero ==4){
							g.drawImage(forum_perte4,coord.x,coord.y, null);
						}
							
				}
				else if (a instanceof Caserne){
					Caserne b = (Caserne) a;
					g.setColor(a.al.color.darker());
					if (b.statue == 0 ){
						g.drawImage(caserne1,coord.x,coord.y, null);
					}
					else if (b.statue == 1){
						g.drawImage(caserne2,coord.x,coord.y, null);
					}
					else if (b.statue == 2) {
						g.drawImage(caserne3,coord.x,coord.y, null);
					}
					else {
					
						if(b.al.numero ==0){
							g.drawImage(caserne4,coord.x,coord.y, null);
							}
							else if (b.al.numero ==1){
								g.drawImage(caserne4_1,coord.x,coord.y, null);
							}
							else if (b.al.numero ==2){
								g.drawImage(caserne4_2,coord.x,coord.y, null);
							}
							else if (b.al.numero ==3){
								g.drawImage(caserne4_3,coord.x,coord.y, null);
							}
							else if (b.al.numero ==4){
								g.drawImage(caserne4_4,coord.x,coord.y, null);
							}
					}
				}
				else if (a instanceof Hopital){
					g.drawImage(hopital,coord.x,coord.y, null);
				}
			}
					
				
				for (Ressource a : ressource.getCurrentAgentsList()) {
					Coord coord1 = ressource.getPropertyValue(a); // Prend les coordonn�e des agents "capturer" dans les listes d'affichage
					Coord coord = coord1.multiple(taille_cel); 
					if (a instanceof Bois){ // repr�sente le bois
						if (a.getQuantite()> 220){
						g.drawImage(bois,coord.x,coord.y, null);
						}
						else if (a.getQuantite()>100){
							g.drawImage(bois1,coord.x,coord.y, null);
						}
						else {
							g.drawImage(bois2,coord.x,coord.y, null);
						}
						//g.fillOval(coord.x,coord.y, 8, 8);
					}
					
				
					
					
				}
				if (this.a){
				for (Cellule a :  cellule.getCurrentAgentsList()){
					g.setColor(Color.GRAY);
					Coord coord1 = cellule.getPropertyValue(a); // Prend les coordonn�e des agents "capturer" dans les listes d'affichage
					Coord coord = coord1.multiple(taille_cel); 
					g.drawString(""+a.personne.size(),coord.x,coord.y);
					
				}
				}
			/*
			
			*/
				}
			}

		
			

	



