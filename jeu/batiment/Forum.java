package batiment;


import java.util.ArrayList;
import java.util.List;

import jeu.Alignement;
import jeu.Batiment;
import jeu.Cellule;
import jeu.Coord;
import jeu.Societe;
import unite.Constructeur;
import unite.Unite;
import unite.Villageois;




/**
 * Classe "FORUM" definis le batiment Forum et son activitee au cours de son activation.
 * @author fayej
 *
 */

@SuppressWarnings("serial")
public class Forum extends Batiment implements Stockable
{

	
	private Cellule curent;
	protected int stock =100; //temporairement place ici
    protected int vie;
    public int limitpop =5;
    public int limitcont = 3;
	/**
	 * Liste utilisée pour gérer la recherche des arbres par les villageois.
	 * Les villageois font des recherches en faisant des cercles autour du forum, le tableau permet de savoir à quel cercle est assigné chaque villageois.
	 */
    private int[] cercleVillageois;
    public static final int NON_FAIT = 0;
    public static final int FAIT = 1;
    public static final int EN_COURS = 2;
    
    // Les 2 listes suivant sont triés par rapport à la distance au forum par ordre croissant
    private List<Cellule> posArbre; // position des arbres connus par le forum
    private List<Cellule> posArbreSuppr; // ancienne position des arbres connues par le forum qui ont été supprimés, est remis à zéro en même temps que cercleVillageois
    private List<Cellule> posForum; // position des forums ennemies connus par le forum
 
	public static final int MAX_STOCK = Integer.MAX_VALUE; // à voir
	

	public Forum (Cellule c , Alignement a){
		super(c, a);
		this.curent = c;
		this.curent.coord = this.coord;
		this.curent.objet = this;

		this.cercleVillageois = new int[nbMaxCercle()];
		for(int i = 0 ; i < this.cercleVillageois.length ; i++){
			this.cercleVillageois[i] = NON_FAIT;
		}
		this.posArbre = new ArrayList<Cellule>();
		this.posArbreSuppr = new ArrayList<Cellule>();
	}
	
	public void addStock(){
		this.stock ++;
	}
	
	public boolean deleteStock(){
		if (this.stock-1 < 0){
			return false;
		}
		else {
			this.stock --;
			return true;
			
		}
		
	}
	
	public int getStock (){
            return this.stock;
    }
	
	public int prendreStock (int demande){
		if(demande > this.stock){
			int s = this.stock;
			this.stock = 0;
			return s;	
		}
		else{
			this.stock -= demande;
			return demande;
		}
	}
	
	public int donnerStock (int dons){
		if(this.stock + dons > MAX_STOCK){
			int r = MAX_STOCK - this.stock;
			this.stock = MAX_STOCK;
			return r;
		}
		else{
			this.stock += dons;
			return 0;
		}
	}
	
	protected void activate(){
		
		requestRole(Societe.SOCIETE , Societe.SIMU , Societe.FORUM );
		this.activationgeneral();
		
		
	}
	
	@SuppressWarnings("unused")
	private void create() { //créé un villageois
		
		if (this.stock-100 >=0){
			
			if(!(this.limitpop <=0)){
			launchAgent(new Villageois(this.curent,this.al));
			
			
			this.stock = this.stock-40;
			this.limitpop --;}
							/*
				Random r = new Random();
				int valeur = r.nextInt(3)-1;
				int valeur2 = r.nextInt(3)-1;
				for (int i=0 ; i< this.coord.size() ; i++){
					if (this.stock >400 && this.limitagrand >0){
					if (this.env.env.getCellule(this.coord.get(i).x+valeur, this.coord.get(i).y+valeur2).objet == null){
						this.env.env.getCellule(this.coord.get(i).x+valeur, this.coord.get(i).y+valeur2).objet = this;
						this.stock = this.stock-400;
						this.limitpop = this.limitpop+5;
						Coord c = new Coord(this.coord.get(i).x+valeur, this.coord.get(i).y+valeur2);
						this.coord.add(c);
						this.limitagrand --;
						
					}
					*/
				else if (this.stock -150>= 0 && this.limitcont >0){
					
					launchAgent(new Constructeur(this.curent,this.al));
					this.stock = this.stock -150;
					this.limitcont--;
				}}
				
				}
			
		//}
	//}
	
	
	@SuppressWarnings("unused")
	private void localisation() {
		// Donne ces coordonnées	
	}
	
	// Les fonctions qui suivent sont dédiés à la gestion des IA 2 et 3 des villageois
	
	/**
	 * @return le nombre maximum de cercle à faire pour visionner toute la map
	 */
	public int nbMaxCercle (){
		Coord dim = this.env.getDimension();
		int[] dist = new int[4];
		dist[0] = this.coord.distance(Coord.NULL);
		dist[1] = this.coord.distance(new Coord(dim.x, 0));
		dist[2] = this.coord.distance(new Coord(0, dim.y));
		dist[3] = this.coord.distance(dim);
			
		int gdist = 0;
		for(int i = 0 ; i < 4 ; i++){
			if(dist[i] > gdist)
				gdist = dist[i];
		}
			
		return cercleChampsVision(gdist);
	}
	
	/**
	 * Retourne le cercle (ou le nombre de cercle) permettant de couvrir la distance maximum d
	 * @param d
	 * @return numero de cercle
	 */
	public static int cercleChampsVision (int d){
		return d / (2 * Villageois.vision + 1) + 
				(((d % (2 * Villageois.vision + 1)) > Villageois.vision) ? 1 : 0);
	}
	
	public static int distanceCercle (int cercle){
		return (2 * Villageois.vision + 1) * cercle;
	}
	
	/**
	 * Permet d'assigner un cercle à un villageois et modifie le tableau de cercles
	 * @return le cercle assigné
	 */
	public int assigneCercle (){
		for(int i = 0 ; i < this.cercleVillageois.length ; i++){
			if(this.cercleVillageois[i] == NON_FAIT){
				this.cercleVillageois[i] = EN_COURS;
				return i + 1;
			}
			else if(this.cercleVillageois[i] == EN_COURS){
				if(i + 1 < this.cercleVillageois.length){
					if(this.cercleVillageois[i + 1] == FAIT){
						return i + 1;
					}
				}
				else{
					return i + 1;
				}
			}
		}
		
		initialiseCercle();
		return 1;
	}
	
	/**
	 * Met un cercle à FAIT à condition que celui était à EN_COURS
	 * @param cercle à modifier
	 * @return true si le cercle à été modifié
	 */
	public boolean cercleFait (int cercle){
		if(this.cercleVillageois[cercle - 1] == EN_COURS){
			this.cercleVillageois[cercle - 1] = FAIT;
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Remet tous les cercles à NON_FAIT
	 */
	protected void initialiseCercle (){
		for(int i = 0 ; i < this.cercleVillageois.length ; i++){
			this.cercleVillageois[i] = NON_FAIT;
		}
		
		this.posArbre = new ArrayList<Cellule>();
		this.posArbreSuppr = new ArrayList<Cellule>();
	}

	/**
	 * Actualise la liste des arbres du forum. 
	 * Remet les liste du villageois à 0
	 * @author nico
	 * @param Villageois
	 */
	public void actualiseListArbre(Villageois v){
		List<Cellule> a = v.getPosArbre();
		List<Cellule> s = v.getPosArbreSuppr();
		
		//suppression
		for(int i = 0 ; i < s.size() ; i++){
			if(!this.posArbreSuppr.contains(s.get(i)))
				this.posArbreSuppr.add(s.get(i));
			if(this.posArbre.contains(s.get(i)))
				this.posArbre.remove(s.get(i));
		}
		
		//ajout
		boolean init = false;
		for(int i = 0 ; i < a.size() ; i++){
			if(!this.posArbre.contains(a.get(i)) && !this.posArbreSuppr.contains(a.get(i))){
				int d = this.coord.distance(a.get(i).coord);
				
				if(this.cercleVillageois[cercleChampsVision(d)] == FAIT){
					init = true;
					break;
				}
				
				boolean mis = false;
				for(int y = 0 ; y < this.posArbre.size() ; y++){
					if(d <= this.coord.distance(this.posArbre.get(y).coord)){
						this.posArbre.add(y, a.get(i));
						mis = true;
						break;
					}
				}
				if(!mis){
					this.posArbre.add(this.posArbre.size(), a.get(i));
				}
			}
		}
		
		if(init){
			initialiseCercle();
			for(int i = 0 ; i < a.size() ; i++){
				this.posArbre.add(a.get(i));
			}
		}
		
		v.initListe();
	}
	
	/**
	 * @return l'arbre le plus proche connus par le forum, null si il n'y a pas d'arbre
	 */
	public Cellule getArbreProche (){
		if(this.posArbre.isEmpty())
			return null;
		else
			return this.posArbre.get(0);
	}
}
