package fr.home.socket.server.model;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Server {

    static Logger logger = Logger.getLogger(Server.class);

    private boolean stopServer = false;

    // private Fenetre ui;

    private ServerSocket serverSocket;

    private List<Client> listClient;

    public Server(int port) {
        listClient = new ArrayList<Client>();
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            logger.error("Server :: " + e);
        }

        // SwingUtilities.invokeLater(new Runnable() {
        // public void run() {
        // Fenetre fenetre = new Fenetre();
        // fenetre.setVisible(true);
        // ui.afficher("En attente de connexion client...");
        // }
        // });
    }

    public void runServeur() {
        logger.debug("runServeur :: En attente de connexion client");
        while (!stopServer) {
            try {
                listClient.add(new Client(serverSocket.accept()/* , ui */));
                logger.debug("runServeur :: Nouveau client ajouté");
            } catch (IOException e) {
                logger.error("runServeur :: " + e);
            }
        }
    }

    public void close() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            logger.error("close :: " + e);
        }
    }
}
