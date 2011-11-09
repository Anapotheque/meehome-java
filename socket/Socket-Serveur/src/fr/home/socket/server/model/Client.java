package fr.home.socket.server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import fr.home.socket.server.util.PropertiesEnum;
import fr.home.socket.server.util.Util;

public class Client implements Runnable {

	private String login;
	private Util util;
	private Thread thread;
	private Socket socket;
	private BufferedReader bufferedReader;
	
	public Client(Socket socket) throws IOException {
		util = new Util();
		
		this.socket = socket;
		bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
		thread = new Thread(this); 
		thread.start();
		login = bufferedReader.readLine();
		System.out.println(login + " s'est connecté...");
	}
	
	@Override
	public void run() {
		try {
			String message = bufferedReader.readLine();
			while(!message.equals(util.getData(PropertiesEnum.STOP_SERVER))) {
				System.out.println(message);
				message = bufferedReader.readLine();
	        }
			close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void close() throws IOException {
		bufferedReader.close();
		socket.close();
		System.out.println(login + " s'est deconnecté...");
	}
}
