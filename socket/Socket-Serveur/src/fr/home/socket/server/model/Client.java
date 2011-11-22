package fr.home.socket.server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;

import org.apache.log4j.Logger;

public class Client implements Runnable {

    static Logger logger = Logger.getLogger(Client.class);

    private Socket socket;

    private Server server;

    private BufferedReader bufferedReader;

    public PrintWriter printWriter;

    private Thread thread;

    private String login;

    public Client(Server server, Socket socket) throws IOException {
        this.server = server;
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
        login = bufferedReader.readLine();

        logger.debug("Client :: [" + login + "] Connexion");

        sendToClientConnectedConnection(login);

        thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        try {
            while (socket.isConnected()) {
                String message = bufferedReader.readLine();
                if (message == null)
                    break;
                logger.debug("Run :: [" + login + "] Reception message : " + message);
                sendToClientConnected(message, false);
            }
            close();
        } catch (IOException e) {
            logger.error("Run :: " + e);
            close();
        }
    }

    public synchronized void sendToClientConnected(String msg, boolean connection) {
        for (Client client : server.listClient) {
            client.printWriter.println(login + " : " + msg);
        }
    }

    public synchronized void sendToClientConnectedDisconnection(String msg) {
        for (Client client : server.listClient) {
            client.printWriter.println(login + " s'est deconnecté");
        }
    }

    public synchronized void sendToClientConnectedConnection(String msg) {
        for (Client client : server.listClient) {
            client.printWriter.println(login + " s'est connecté");
        }
    }

    public void close() {
        try {
            bufferedReader.close();
            printWriter.close();
            socket.close();
            logger.debug("Close :: [" + login + "] Deconnexion");
            sendToClientConnectedDisconnection(login);
        } catch (IOException e) {
            logger.error("Run :: close ::" + e);
        }
    }
}
