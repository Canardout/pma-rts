package jeu;

import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JCheckBox;

public class Checkbox extends JCheckBox implements MouseListener, ComponentListener{
	String nom;
	Viewer cible;
	boolean activ;
	public Checkbox(String nom , Viewer cible, boolean activ){
		this.nom = nom;
		this.cible = cible;
		this.activ = activ;
		this.addMouseListener(this);
	}
	@Override
	public void mouseClicked(MouseEvent arg0) {
		this.activ = !this.activ;
		this.cible.setgrille();
		this.setSelected(this.activ);
		this.updateUI();
		
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	@Override
	public void componentHidden(ComponentEvent arg0) {
		
		
	}
	@Override
	public void componentMoved(ComponentEvent arg0) {
		
		
	}
	@Override
	public void componentResized(ComponentEvent arg0) {
		
		
	}
	@Override
	public void componentShown(ComponentEvent arg0) {
		
		
	}

}
