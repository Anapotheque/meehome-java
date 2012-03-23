package fr.home.socket.server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import fr.home.socket.server.ui.Fenetre;

public class Server {

    static Logger logger = Logger.getLogger(Server.class);

    private boolean stopServer = false;

    private ServerSocket serverSocket;

    private boolean modeConsole;

    public List<Client> listClient;

    public Server(boolean modeConsole) {
        this.modeConsole = modeConsole;
        listClient = new ArrayList<Client>();
    }

    public void runServeur(int port) {
        try {
            serverSocket = new ServerSocket(port);

            String msg = "runServeur :: En attente de connexion client";
            if (!modeConsole) {
                Fenetre.appendToChatBox(msg);
            }
            logger.info(msg);

            while (!stopServer) {
                listClient.add(new Client(this, serverSocket.accept(), modeConsole));
            }

            serverSocket.close();
        } catch (IOException e) {
            String msg = "runServeur :: " + e;
            if (!modeConsole) {
                Fenetre.appendToChatBox(msg);
            }
            logger.info(msg);
        }
    }

    public void write(String msg) {
        Fenetre.appendToChatBox(msg);
        logger.info(msg);
    }
}
