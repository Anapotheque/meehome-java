package fr.home.socket.client;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import fr.home.socket.client.model.Client;
import fr.home.socket.client.model.ServeurDistant;
import fr.home.socket.client.ui.Fenetre;
import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.UtilProperties;

public class LauncherClient {

    static Logger logger = Logger.getLogger(LauncherClient.class);

    private static final boolean MODE_CONSOLE = Boolean.parseBoolean(UtilProperties.getData(PropertiesEnum.MODE_CONSOLE));

    public static void main(String[] args) throws InterruptedException {

        if (MODE_CONSOLE) {
            Client client = new Client();
            while (true) {
                if (client.connection(ServeurDistant.IP, ServeurDistant.PORT, ServeurDistant.LOGIN, MODE_CONSOLE)) {
                    client.runConsole();
                }
                Thread.sleep(10000);
            }
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Fenetre fenetre = new Fenetre(ServeurDistant.IP, ServeurDistant.PORT, ServeurDistant.LOGIN, MODE_CONSOLE);
                        fenetre.setVisible(true);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
