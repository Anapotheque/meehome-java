package fr.home.socket.client.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.Util;

public class Client {
	
	private Socket socket;
	private BufferedReader dataUser;
	private PrintWriter in;
	private Util util;
	private boolean stop;
	
	public Client(){
		stop = false;
	}
	
	public boolean connection() throws NumberFormatException, UnknownHostException, IOException{
		boolean isConnect = false;
		util = new Util();
		socket = new Socket(util.getData(PropertiesEnum.IP), Integer.parseInt(util.getData(PropertiesEnum.PORT)));
		dataUser = new BufferedReader(new InputStreamReader(System.in));
		in = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
		sendToServer(util.getData(PropertiesEnum.LOGIN));
		isConnect = true;		
		return isConnect;
	}
	
	public void run() throws IOException {
		String ligne = dataUser.readLine();
		while(!ligne.equals(util.getData(PropertiesEnum.STOP_CLIENT))){
			sendToServer(ligne);
			ligne = dataUser.readLine();
		}
		close();
	}
	
	public void sendToServer(String msg) throws IOException{
		String login = util.getData(PropertiesEnum.LOGIN);
		in.println(login + " : " + msg);
	}

	public void close() throws IOException{
		dataUser.close();
		in.close();
		socket.close();
		stop = true;
	}

	public boolean isStop() {
		return stop;
	}
}
