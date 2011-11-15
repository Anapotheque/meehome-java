package fr.home.socket.client.ui;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

@SuppressWarnings("serial")
public class Action extends AbstractAction {
	
	private Fenetre fenetre;
	
	public Action(Fenetre fenetre, String texte){
		super(texte);
		this.fenetre = fenetre;
	}
	
	public void actionPerformed(ActionEvent e) { 
		//Action lors du clic sur le bouton calculer
	} 
}