package jeu;
/**Classe définissant les diverses ressources du jeux ..
 * 
 * @author fayej
 *
 */
public class Ressource extends ObjectMap {
	 /**
     * ecrire commentaire ou pas
     */
    protected int quantite;
    
    
    public Ressource(){
    	
    }
    
    public Ressource (Coord c, int q){
            this.coord = c;
            this.quantite = q;
    }
    
    public Ressource (Coord c){
            this(c, 100); //pouet-pouet <-- WHAT IS IT? OMG I DON'T UNDERSTAND
    }

    public int getQuantite (){
            return this.quantite;
    }
    
    public void decrementeQuantite (){
            this.quantite--;
    }
    
    public int diminueQuantite (int n){
            if(this.quantite < n){
                    int t = this.quantite;
                    this.quantite = 0;
                    return t;
            }
            this.quantite -= n;
            return n;
    }
}
