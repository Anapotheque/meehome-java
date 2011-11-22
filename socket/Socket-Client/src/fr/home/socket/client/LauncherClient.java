package fr.home.socket.client;

import javax.swing.SwingUtilities;

import org.apache.log4j.Logger;

import fr.home.socket.client.model.Client;
import fr.home.socket.client.ui.Fenetre;
import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.UtilProperties;

public class LauncherClient {

    static Logger logger = Logger.getLogger(LauncherClient.class);

    public static final String IP = UtilProperties.getData(PropertiesEnum.IP);

    public static final String LOGIN = UtilProperties.getData(PropertiesEnum.LOGIN);

    public static final int PORT = Integer.parseInt(UtilProperties.getData(PropertiesEnum.PORT));

    private static final boolean MODE_CONSOLE = Boolean.parseBoolean(UtilProperties.getData(PropertiesEnum.MODE_CONSOLE));

    public static void main(String[] args) throws InterruptedException {

        if (MODE_CONSOLE) {
            Client client = new Client();
            while (true) {
                if (client.connection(IP, PORT, LOGIN, MODE_CONSOLE)) {
                    client.runConsole();
                }
                Thread.sleep(10000);
            }
        } else {
            SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    try {
                        Fenetre fenetre = new Fenetre(IP, PORT, LOGIN, MODE_CONSOLE);
                        fenetre.setVisible(true);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }
}
