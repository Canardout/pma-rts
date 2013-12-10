package jeu;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
 
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
 
    // Définir la nouvelle couleur en modifiant les pixels
    rgb = 0xFF00FF;
    bufferedImage.setRGB(1, 1, rgb);
    
  }
  protected void render(Graphics g) {
	  
  }
  
}