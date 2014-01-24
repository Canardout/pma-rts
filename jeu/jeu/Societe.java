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

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import javax.swing.JFrame;

import madkit.kernel.AbstractAgent;
import madkit.kernel.Madkit;

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

	public static String[] ALIGNEMENT = new String[5]; // Alignement que 
	//!\\ Les Alignement ne peuvent desormer plus exceder 5 !
	
	protected int longueur = 25;
	protected int largeur = 25;
	protected int taille_cellule = 20;
	protected int alignement = 4;
	protected int nb_arbre_max = 20;
	protected int nb_arbre_min = 4;
	protected int ia[] = {3, 3, 3, 3, 3};
	
	public void newGame(){
		
		
	}
	
	protected boolean commande (String str){
		String cmd[] = {"longueur", "largeur", "taille_cellule", "alignement", "nb_arbre_max", "nb_arbre_min", "IA_general", "IA_", "init"};
		int ia = 0;
		for(int i = 0 ; i < cmd.length ; i++){
			boolean isCmd = true;
			int j;
			for(j = 0 ; j < cmd[i].length() && j < str.length() ; j++){
				if(str.charAt(j) != cmd[i].charAt(j)){
					isCmd = false;
					break;
				}
			}			
			
			if(isCmd && i == 7){
				if(j < str.length() && Character.isDigit(str.charAt(j))){
					String s = new String(str.charAt(j) + "");
					ia = Integer.parseInt(s) - 1;
					j++;
				}
			}
			
			if(isCmd && j < str.length() && str.charAt(j) == '='){
				j++;
				str = str.substring(str.lastIndexOf("="));
				str = str.substring(1, str.length());

				for(int k = 0 ; k < str.length(); k++){
					char c = str.charAt(k);
					
					if(c == ' ' || c == '\t' || (c == '/' && (k + 1) < str.length() && str.charAt(k + 1) == '/')){
						str = str.substring(0, k);
						break;
					}
				}
				
			    try {
			        int valeur = Integer.parseInt(str);
			        switch(i){
			        case 0 : this.longueur = valeur; break;
			        case 1 : this.largeur = valeur; break;
			        case 2 : this.taille_cellule = valeur; break;
			        case 3 : this.alignement = valeur; break;
			        case 4 : this.nb_arbre_max = valeur; break;
			        case 5 : this.nb_arbre_min = valeur; break;
			        case 6 : 
			        	for(int k = 0 ; k < this.ia.length ; k++)
			        		this.ia[k] = valeur;
			        	break;
			        case 7 :
			        	if(ia >= 0 && ia < 5 && valeur >= 1 && valeur <= 3)
			        		this.ia[ia] = valeur;
			        	break;
			        case 8 : return (valeur == 1) ? true : false;
			        }
			    } catch (NumberFormatException nfe) {
			        // ce n'est pas un nombre
			    }
			    return false;
			}
		}
		
		return false;
	}
	
	protected void activate() {
		for (int i = 0 ; i<this.ALIGNEMENT.length ; i++){
			this.ALIGNEMENT[i] = "a"+i;
		}
		
		// 1 : create the simulation group
		createGroup(SOCIETE, SIMU);
		
		GameDistributor scheduler = new GameDistributor(this);
		launchAgent(scheduler,false);
		
		// 2 : create the environment
		boolean init = false;
		try{
			InputStream ips =new FileInputStream("config.txt"); 
			InputStreamReader ipsr= new InputStreamReader(ips);
			BufferedReader br= new BufferedReader(ipsr);
			String ligne;
			while ((ligne = br.readLine()) != null){
				init = commande(ligne);
				if(init)
					break;
			}
			br.close(); 
		}		
		catch (Exception e){
			init = true;
		}
		
		if(init){
			try{
			PrintWriter w = new PrintWriter(new BufferedWriter
			   (new FileWriter("config.txt")));
		   
		    w.println("/////////////////////////////////////////////////////////////////////////////////////////");
		    w.println("////////////////////////////////////// CONFIGURATION ////////////////////////////////////");
    		w.println("/////////////////////////////////////////////////////////////////////////////////////////");
    		w.println();
		    w.println("// Taper les commandes ligne par ligne sous la forme \"variable=valeur\" sans espaces.");
		    w.println("// Si les valeurs sont trop grande ou trop petite, elle seront ajusté ou ignoré.");
		    w.println("// Les valeurs ne sont prise en compte qu'au lancement de l'application.");
		    w.println("// Pour réinitiliser ce fichier, donner la valeur 1 à init.");
		    w.println("// Si vous affecter valeur à une variable, seul la dernière valeur sera prise en compte.");
		    w.println("// Les IA vont de 1 à 3 (si ignoré IA=3)");
		    w.println();
		    w.println("longueur=25");
		    w.println("largeur=25");
		    w.println("taille_cellule=20");
		    w.println("alignement=4");
		    w.println("nb_arbre_max=20");
		    w.println("nb_arbre_min=4");
		    w.println();
			w.println("IA_general=3");
		    w.println("//IA_1=2");
		    w.println();
			w.println("init=0");
			w.println();
		   	w.println("// longueur : nombre de cellule horizontale de l'environnement");
		    w.println("// largeur : nombre de cellule verticale de l'environnement");
		    w.println("// taille_cellule : taille d'une cellule en pixel");
		    w.println("// alignement : nombre d'équipe en compétition");
		    w.println("// nb_arbre_max : nombre d'arbre maximum présent sur le terrain");
		    w.println("// nb_arbre_min : nombre d'arbre minimum présent sur le terrain");
		    w.println("// IA_general : change l'IA de toutes les équipe");
		    w.println("// IA_n : change l'IA d'un alignement en remplaçant n par le numéro de l'alignement");
		    w.println("// init : si init=1 le fichier est réinitialisé à son état d'origine");
			w.close();
			}catch(IOException e){
				System.out.println(e.toString());
			}
			
			this.longueur = 25;
			this.largeur = 25;
			this.taille_cellule = 20;
			this.alignement = 4;
			this.nb_arbre_max = 20;
			this.nb_arbre_min = 4;
			for(int i = 0 ; i < this.ia.length ; i++)
				this.ia[i] = 3;
		}
		
		if(this.longueur < 10)
			this.longueur = 10;
		else if(this.longueur > 500)
			this.longueur = 500;
		
		if(this.largeur < 10)
			this.largeur = 10;
		else if(this.largeur > 500)
			this.largeur = 500;
		
		if(this.taille_cellule < 4)
			this.taille_cellule = 4;
		else if(this.taille_cellule * this.longueur > 2800 || this.taille_cellule * this.largeur > 2800){
			if(this.longueur > this.largeur)
				this.taille_cellule = 2800 / this.longueur;
			else
				this.taille_cellule = 2800 / this.largeur;
		}
		
		if(this.alignement < 1)
			this.alignement = 1;
		else if(this.alignement > 5)
			this.alignement = 5;
		
		if(this.nb_arbre_min < 1)
			this.nb_arbre_min = 1;
		else if(this.nb_arbre_min > this.longueur * this.largeur - 3 * this.alignement)
			this.nb_arbre_min = this.longueur * this.largeur - 3 * this.alignement;
		
		if(this.nb_arbre_max < this.nb_arbre_min)
			this.nb_arbre_max = this.nb_arbre_min;
		else if(this.nb_arbre_max > this.longueur * this.largeur - 3 * this.alignement)
			this.nb_arbre_max = this.longueur * this.largeur - 3 * this.alignement;
		
		Environnement env = new Environnement(scheduler, longueur, largeur, alignement, nb_arbre_max, nb_arbre_min);
		
		System.out.println("longueur=" + this.longueur);
		System.out.println("largeur=" + this.largeur);
		System.out.println("taille_cellule=" + this.taille_cellule);
		System.out.println("alignement=" + this.alignement);
		System.out.println("nb_arbre_max=" + this.nb_arbre_max);
		System.out.println("nb_arbre_min=" + this.nb_arbre_min);
		for(int i = 0 ; i < alignement ; i++){
			env.changeIA(this.ia[i], i);
			System.out.println("IA_" + (i + 1) + "=" + env.getIA(i));
		}
		
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
	
	

