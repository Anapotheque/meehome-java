package fr.home.socket.server.model;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import fr.home.socket.server.util.PropertiesEnum;
import fr.home.socket.server.util.Util;

public class Server {

	private ServerSocket serverSocket;
	private Socket socket;
	private BufferedReader bufferedReader;
	private PrintWriter printWriter;
	private Util util;
	
	public Server() {
		try {
			util = new Util();
			serverSocket = new ServerSocket(Integer.parseInt(util.getData(PropertiesEnum.PORT)));
			affiche("En attente de connexion client...");
			socket = serverSocket.accept();
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			printWriter = new PrintWriter(new BufferedWriter(new OutputStreamWriter(socket.getOutputStream())),true);
			affiche("Client connecté : " + socket.getInetAddress().getHostAddress());
			
		} catch (IOException e) {
			affiche(e.getMessage());
		}
	}
	
	private void close() throws IOException{
		bufferedReader.close();
		printWriter.close();
		socket.close();
	}
	
	private void sendToClient(String message) {
		affiche(message);
		printWriter.println(message);
	}
	
	public void run(){
		try {
			String message = bufferedReader.readLine();
			while(!message.equals(util.getData(PropertiesEnum.STOP_SERVER))) {
				sendToClient(message);
				message = bufferedReader.readLine();
	        }
			close();
		} catch (IOException e) {
			affiche(e.getMessage());
		}
	}
	
	public void affiche(String msg){
		System.out.println(msg);
	}
}