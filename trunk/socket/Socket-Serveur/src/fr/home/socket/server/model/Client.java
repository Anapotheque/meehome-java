package fr.home.socket.server.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import org.apache.log4j.Logger;

public class Client implements Runnable {

    static Logger logger = Logger.getLogger(Client.class);

    private Socket socket;

    private BufferedReader bufferedReader;

    private Thread thread;

    private String login;

    boolean stopClient = false;

    public Client(Socket socket) throws IOException {

        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        login = bufferedReader.readLine();

        logger.debug("Client :: [" + login + "] Connexion");

        thread = new Thread(this);
        thread.start();
    }

    public void close() throws IOException {
        bufferedReader.close();
        socket.close();
        logger.debug("close :: [" + login + "] Deconnexion");
    }

    @Override
    public void run() {
        try {
            while (!stopClient) {
                if (bufferedReader.readLine() == null) {
                    break;
                }
                String message = bufferedReader.readLine();
                logger.debug("run :: [" + login + "] Reception message : " + message);
            }
            close();
        } catch (IOException e) {
            logger.error("run :: " + e);
        }
    }
}
