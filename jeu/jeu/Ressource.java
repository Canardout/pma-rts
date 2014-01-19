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
