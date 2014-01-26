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
		this.setSelected(this.activ);
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
