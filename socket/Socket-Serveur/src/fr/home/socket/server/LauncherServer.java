package fr.home.socket.server;

import java.io.IOException;

import fr.home.socket.server.model.Server;

public class LauncherServer {

	public static void main(String[] args) {
		try {
			Server serveur = new Server();
			serveur.runServeur();
			serveur.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
