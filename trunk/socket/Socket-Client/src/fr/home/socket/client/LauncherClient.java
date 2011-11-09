package fr.home.socket.client;

import java.io.IOException;
import java.net.UnknownHostException;

import fr.home.socket.client.model.Client;

public class LauncherClient {

	public static void main(String[] args) throws InterruptedException {
		
		try {
			Client client = new Client();
			while(!client.isStop()){
				if(client.connection()) client.run();
				Thread.sleep(10000);
			}
			client.close();
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
