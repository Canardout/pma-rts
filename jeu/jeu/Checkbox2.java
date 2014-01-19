package jeu;

import java.awt.event.MouseEvent;

public class Checkbox2 extends Checkbox{

	public Checkbox2(String nom, Viewer cible, boolean activ) {
		super(nom, cible, activ);
		// TODO Auto-generated constructor stub
	}

	public void mouseClicked(MouseEvent arg0) {
		this.activ = !this.activ;
		this.cible.setnum();
		this.setSelected(this.activ);
		this.updateUI();
		
	}
}
