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

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
 
/**
 * 
 * @author fayej
 */

public class EditPixel
{
	Image a;
	public EditPixel(Image a){
		this.a = a;
	}
 public void changecouleur(){
  	// Définition d'un objet BufferedImage
    this.a= new ImageIcon("/jeu/Ressource/Villageois2.png").getImage();
    BufferedImage bufferedImage = new BufferedImage(200,
    	                                            200,
                                                    BufferedImage.TYPE_INT_RGB);
 
    int rgb = bufferedImage.getRGB(1, 1);
    int w = bufferedImage.getWidth(null);
    int h = bufferedImage.getHeight(null);
    int[] rgbs = new int[w * h];
    // Obtenir la couleur actuelle de l'image
    bufferedImage.getRGB(0, 0, w, h, rgbs, 0, w);
 
    // D�finir la nouvelle couleur en modifiant les pixels
    rgb = 0xFF00FF;
    bufferedImage.setRGB(1, 1, rgb);
    
  }
  protected void render(Graphics g) {
	  
  }
  
}