package fr.home.socket.client.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.apache.log4j.Logger;

import fr.home.socket.client.ui.Fenetre;

public class Client implements Runnable {

    static Logger logger = Logger.getLogger(Client.class);

    private Socket socket;

    private PrintWriter printWriter;

    private BufferedReader bufferedReader;

    private boolean modeConsole;

    private Thread thread;

    /**
     * Buffered d'ecoute des saisies
     */
    private BufferedReader dataUser = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Variable permetant l'arret du client
     */
    private boolean running = true;

    /**
     * Methode permetant la connexion à un serveur distant
     * 
     * @param ip
     * @param port
     * @return
     * @throws NumberFormatException
     * @throws UnknownHostException
     * @throws IOException
     */
    public boolean connection(String ip, int port, String login, boolean modeConsole) {

        logger.debug("connection :: tentative de connexion de '" + login + "' {" + ip + ":" + port + "}");

        // Initialisation du client
        this.running = true;
        this.modeConsole = modeConsole;

        try {

            // Connexion au serveur
            this.socket = new Socket(ip, port);
            this.printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            thread = new Thread(this);
            thread.start();

            // Envois au serveur le login
            sendToServer(login);

        } catch (IOException e) {
            logger.error("connection :: " + e);
            ecrisVersFenetre(e.getMessage());
            return false;
        }
        logger.debug("connection :: client connecté au serveur");
        return true;
    }

    /**
     * Permet d'envoyer au serveur toutes les infos saisies
     */
    public void runConsole() {
        try {
            String ligne = dataUser.readLine();
            while (running) {
                sendToServer(ligne);
                ligne = dataUser.readLine();
            }
            close();
        } catch (IOException e) {
            logger.error("run :: " + e);
            ecrisVersFenetre(e.getMessage());
        }
    }

    public void sendToServer(String msg) {
        printWriter.println(msg);
    }

    public void stopClient() {
        running = false;
    }

    /**
     * Fermeture des buffers et socket
     * 
     * @throws IOException
     */
    public void close() {
        try {
            dataUser.close();
            if (printWriter != null) {
                printWriter.close();
            }
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (socket != null) {
                socket.close();
            }
            running = false;
            logger.debug("close :: client deconnecté");
        } catch (IOException e) {
            logger.error("close :: " + e);
            ecrisVersFenetre(e.getMessage());
        }
    }

    public void ecrisVersFenetre(String msg) {
        if (!modeConsole) {
            Fenetre.appendToChatBox(msg + "\r\n");
        }
    }

    @Override
    public void run() {
        try {
            while (running) {
                ecrisVersFenetre(bufferedReader.readLine());
            }
        } catch (IOException e) {
            logger.error("close :: " + e);
        }
    }
}
