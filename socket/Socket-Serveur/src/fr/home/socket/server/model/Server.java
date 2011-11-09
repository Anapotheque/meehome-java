package fr.home.socket.server.model;
import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import fr.home.socket.server.util.PropertiesEnum;
import fr.home.socket.server.util.Util;

public class Server {

	private ServerSocket serverSocket;
	private Util util;
	private List<Client> listClient;
	
	public Server() throws NumberFormatException, IOException {
		util = new Util();
		listClient = new ArrayList<Client>();
		serverSocket = new ServerSocket(Integer.parseInt(util.getData(PropertiesEnum.PORT)));
		System.out.println("En attente de connexion client...");
	}
	
	public void runServeur() throws IOException{
		while(true){
			listClient.add(new Client(serverSocket.accept()));
		}
	}
	
	public void close() throws IOException{
		serverSocket.close();
	}
}