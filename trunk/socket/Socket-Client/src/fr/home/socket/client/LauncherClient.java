package fr.home.socket.client;

import fr.home.socket.client.model.Client;

public class LauncherClient {

	public static void main(String[] args) throws InterruptedException {
		
		Client client = new Client();
		
		// Tant que le Client n'est pas connecté au serveur
		while(!client.isStop()){
			
			// Si on est connecté on lance le serveur
			if(client.connection()) client.run();
			
			// Pause de 10 secondes entre chaques tentatives
			Thread.sleep(10000);
		}
	}
}
