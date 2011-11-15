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

    // private static Fenetre ui;

    private String login;

    boolean stopClient = false;

    public Client(Socket socket/* , Fenetre ui */) throws IOException {
        // this.ui = ui;
        this.socket = socket;
        this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        login = bufferedReader.readLine();

        logger.debug("Client :: [" + login + "] Connexion");

        thread = new Thread(this);
        thread.start();

        // this.ui.afficher(login + " s'est connecté...");
    }

    @Override
    public void run() {
        try {
            while (!stopClient) {
                // this.ui.afficher(message);
                String message = bufferedReader.readLine();
                logger.debug("run :: [" + login + "] Reception message : " + message);
            }
            close();
        } catch (IOException e) {
            logger.error("run :: " + e);
            // ui.afficher(e.getMessage());
        }
    }

    public void close() {
        try {
            bufferedReader.close();
            socket.close();
            logger.debug("close :: [" + login + "] Deconnexion");
            // ui.afficher(login + " s'est deconnecté...");
        } catch (IOException e) {
            logger.error("close :: " + e);
        }
    }
}
