package jeu;
/**Classe définissant les diverses ressources du jeux ..
 * 
 * @author Nicolas, fayej
 *
 */
public class Ressource extends ObjectMap {
    protected int quantite;
    
    
    public Ressource (Cellule c, int q){
            super(c, null);
            this.quantite = q;
    }
    
    public Ressource (Cellule c){
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
