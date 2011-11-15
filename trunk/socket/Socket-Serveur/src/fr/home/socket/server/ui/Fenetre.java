package fr.home.socket.server.ui;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import fr.home.socket.server.model.Server;

@SuppressWarnings("serial")
public class Fenetre extends JFrame{
	
	private JLabel label;
	private Server server;
	
	public Fenetre(){
		super();
		build();
	}
	
	private void build(){
		setTitle("Serveur"); //On donne un titre à l'application
		setSize(400,200); //On donne une taille à notre fenêtre
		setLocationRelativeTo(null); //On centre la fenêtre sur l'écran
		setResizable(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
		setContentPane(buildContentPane());
	}
	
	private JPanel buildContentPane(){
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout());
		panel.setBackground(Color.white);
		
		label = new JLabel("test");
		panel.add(label);
		return panel;
	}
	
	public void afficher(String text){
		this.label.setText(text);
	}
}