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

public class Client {

    static Logger logger = Logger.getLogger(Client.class);

    private Socket socket;

    private PrintWriter printWriter;

    private boolean modeConsole;

    /**
     * Buffered d'ecoute des saisies
     */
    private BufferedReader dataUser = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Variable permetant l'arret du client
     */
    private boolean stopClient = false;

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
        this.stopClient = false;
        this.modeConsole = modeConsole;

        try {

            // Connexion au serveur
            socket = new Socket(ip, port);
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

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
            logger.debug("run :: envois du message au serveur : " + ligne);
            while (!stopClient) {
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
        logger.debug("run :: envois du message au serveur : " + msg);
    }

    public void stopClient() {
        stopClient = true;
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
            if (socket != null) {
                socket.close();
            }
            stopClient = true;
            logger.debug("close :: client deconnecté");
        } catch (IOException e) {
            logger.error("close :: " + e);
            ecrisVersFenetre(e.getMessage());
        }
    }

    public boolean isStop() {
        return stopClient;
    }

    public void ecrisVersFenetre(String msg) {
        if (!modeConsole) {
            Fenetre.appendToChatBox(msg + "\r\n");
        }
    }
}
