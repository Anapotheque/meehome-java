package fr.home.socket.client;

import javax.swing.SwingUtilities;

import fr.home.socket.client.ui.Fenetre;

public class LauncherClient {

	public static void main(String[] args) throws InterruptedException {
		
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				try {
					Fenetre fenetre = new Fenetre();
					fenetre.setVisible(true);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}	
			}
		});
	}
}
