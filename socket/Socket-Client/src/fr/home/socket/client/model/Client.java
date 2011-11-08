package fr.home.socket.client.model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.Util;

public class Client {
	
	private Socket socket;
	private BufferedReader bufferedReader;
	private BufferedReader dataUser;
	private PrintWriter printWriter;
	private Util util;
	private boolean stop;
	
	public Client(){
		stop = false;
	}
	
	public boolean connection(){
		boolean isConnect = false;
		try {
			util = new Util();
			socket = new Socket(util.getData(PropertiesEnum.IP), Integer.parseInt(util.getData(PropertiesEnum.PORT)));
			bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			dataUser = new BufferedReader(new InputStreamReader(System.in));
			printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()),true);
			isConnect = true;
		} catch (IOException e) {
			System.out.println(e);
		}
		return isConnect;
	}
	
	public void run() {
		try {
			String ligne = dataUser.readLine();
			while(!ligne.equals(util.getData(PropertiesEnum.STOP_CLIENT))){
				System.out.println(sendToServer(ligne));
				ligne = dataUser.readLine();
			}
			close();
		}catch (IOException e) {
			System.out.println(e);
		}
	}
	
	public String sendToServer(String msg) throws IOException{
		String login = util.getData(PropertiesEnum.LOGIN);
		printWriter.println(login + " >> " + msg);
		return " << " + bufferedReader.readLine();
	}

	private void close() throws IOException{
		bufferedReader.close();
		dataUser.close();
		printWriter.close();
		socket.close();
		stop = true;
	}

	public void setStop(boolean stop) {
		this.stop = stop;
	}

	public boolean isStop() {
		return stop;
	}
}
