package fr.home.socket.server;

import fr.home.socket.server.model.Server;

public class LauncherServer {

	public static void main(String[] args) {
		Server serveur = new Server();
		serveur.run();
	}
}
