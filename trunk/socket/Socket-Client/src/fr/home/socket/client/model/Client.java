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
import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.Util;

public class Client {

    static Logger logger = Logger.getLogger(Client.class);

    private Socket socket;

    private PrintWriter printWriter;

    /**
     * Buffered d'ecoute des saisies
     */
    private BufferedReader dataUser = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Variable permetant l'arret du client
     */
    private boolean stopClient = false;

    private String stopClientString = Util.getData(PropertiesEnum.STOP_CLIENT);

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
    public boolean connection(String ip, int port, String login) {
        try {
            // Connexion au serveur
            socket = new Socket(ip, port);
            printWriter = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true);

            // Envois au serveur du login connecté
            printWriter.println(login);
        } catch (UnknownHostException e) {
            logger.error("connection :: " + e);
            ecrisVersFenetre(e.getMessage());
            return false;
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
    public void run() {
        try {
            String ligne = dataUser.readLine();
            logger.debug("run :: envois du message au serveur : " + ligne);
            while (!ligne.equals(stopClientString)) {
                printWriter.println(ligne);
                ligne = dataUser.readLine();
                logger.debug("run :: envois du message au serveur : " + ligne);
            }
        } catch (IOException e) {
            logger.error("run :: " + e);
            ecrisVersFenetre(e.getMessage());
        }
    }

    /**
     * Fermeture des buffers et socket
     * 
     * @throws IOException
     */
    public void close() {
        try {
            dataUser.close();
            printWriter.close();
            socket.close();
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
        Fenetre.appendToChatBox(msg + "\r\n");
    }
}
