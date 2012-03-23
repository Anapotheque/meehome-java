package fr.home.socket.server;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import fr.home.socket.server.model.Server;
import fr.home.socket.server.ui.Fenetre;
import fr.home.socket.server.util.PropertiesEnum;
import fr.home.socket.server.util.UtilProperties;

public class LauncherServer {

    public static final int port = Integer.parseInt(UtilProperties.getData(PropertiesEnum.PORT));

    static Logger logger = Logger.getLogger(LauncherServer.class);

    private static final boolean MODE_CONSOLE = Boolean.parseBoolean(UtilProperties.getData(PropertiesEnum.MODE_CONSOLE));

    public static void main(String[] args) throws InterruptedException {

        if (MODE_CONSOLE) {
            Server server = new Server(true);
            server.runServeur(port);
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    Fenetre fenetre = new Fenetre(port);
                    fenetre.setVisible(true);
                }
            });
        }
    }
}
