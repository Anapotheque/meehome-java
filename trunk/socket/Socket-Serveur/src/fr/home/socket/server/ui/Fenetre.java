package fr.home.socket.server.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.home.socket.server.model.Server;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Runnable {

    private JLabel label;

    private Thread thread;

    private static int port;

    private Server server;

    private static StringBuffer toAppend = new StringBuffer("");

    private static JTextArea chatText;

    public Fenetre(int port) {
        super();
        Fenetre.port = port;
        build();
        server = new Server(false);
        thread = new Thread(this);
        thread.start();
    }

    private void build() {
        setTitle("Serveur"); // On donne un titre à l'application
        setSize(400, 200); // On donne une taille à notre fenêtre
        setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        chatText = new JTextArea(10, 30);
        chatText.setLineWrap(true);
        chatText.setEditable(false);
        chatText.setForeground(Color.blue);
        chatText.append(toAppend.toString());
        JScrollPane chatTextPane = new JScrollPane(chatText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        panel.add(chatTextPane);
        return panel;
    }

    public static void appendToChatBox(String s) {
        synchronized (toAppend) {
            toAppend.append(s);
            chatText.setText(toAppend.toString());
        }
    }

    @Override
    public void run() {
        server.runServeur(port);
    }
}
