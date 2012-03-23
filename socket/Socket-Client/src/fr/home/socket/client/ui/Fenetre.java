package fr.home.socket.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import fr.home.socket.client.model.Client;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Runnable {

    static Logger logger = Logger.getLogger(Fenetre.class);

    private static String ip, login;

    private static int port;

    private static boolean modeConsole;

    private Thread thread;

    private static Client client = new Client();

    /**
     * Lié à l'affichage
     */
    private static StringBuffer toAppend = new StringBuffer("");

    private static JTextArea chatText;

    private static JTextField chatLine, ipField, portField, loginField;

    private static JButton connexionButton, deconnexionButton;

    private static JComboBox listfriends;

    public Fenetre(String ip, int port, String login, boolean modeConsole) throws InterruptedException {
        super();
        Fenetre.ip = ip;
        Fenetre.port = port;
        Fenetre.login = login;
        build();
    }

    private void build() {
        setTitle("Client");
        setSize(650, 100);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildFenetreConnexion());
    }

    private JPanel buildFenetreContact() {

        JPanel friendPane = new JPanel(new BorderLayout());
        friendPane.setPreferredSize(new Dimension(200, 200));

        JButton connexionButton = new JButton("Connexion");
        connexionButton.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                ouvertureFenetreDiscution();
            }
        });

        Object[] friends = new Object[]{"test0", "test1", "test2", "test3" };
        listfriends = new JComboBox(friends);
        listfriends.setPreferredSize(new Dimension(100, 25));
        friendPane.add(listfriends);
        friendPane.add(connexionButton);

        return friendPane;
    }

    private JPanel buildFenetreDiscution() {
        JPanel globalPane = new JPanel(new BorderLayout());
        JPanel chatPane = new JPanel(new BorderLayout());
        chatPane.setPreferredSize(new Dimension(200, 200));
        globalPane.add(chatPane, BorderLayout.SOUTH);

        chatText = new JTextArea(10, 20);
        chatText.setLineWrap(true);
        chatText.setEditable(false);
        chatText.setForeground(Color.blue);
        chatText.append(toAppend.toString());
        JScrollPane chatTextPane = new JScrollPane(chatText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        chatPane.add(chatTextPane, BorderLayout.CENTER);

        chatLine = new JTextField();
        chatLine.setEnabled(false);
        chatLine.setBackground(Color.gray);
        chatPane.add(chatLine, BorderLayout.SOUTH);
        chatLine.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                client.sendToServer(chatLine.getText());
                chatLine.setText("");
            }
        });

        return globalPane;
    }

    private JPanel buildFenetreConnexion() {

        JPanel globalPane = new JPanel(new BorderLayout());
        JPanel configPane = new JPanel();
        JPanel chatPane = new JPanel(new BorderLayout());
        chatPane.setPreferredSize(new Dimension(50, 50));
        globalPane.add(configPane, BorderLayout.NORTH);
        // globalPane.add(chatPane, BorderLayout.SOUTH);

        // Create the text field and set it up.
        ipField = new JTextField();
        ipField.setText(Fenetre.ip);
        ipField.setColumns(15);

        portField = new JTextField();
        portField.setText(String.valueOf(Fenetre.port));
        portField.setColumns(4);

        loginField = new JTextField();
        loginField.setText(Fenetre.login);
        loginField.setColumns(20);

        configPane.add(ipField);
        // configPane.add(portField);
        configPane.add(loginField);

        connexionButton = new JButton("Connexion");
        configPane.add(connexionButton);
        connexionButton.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                runClient();
                setContentPane(buildFenetreDiscution());
            }
        });

        deconnexionButton = new JButton("Deconnexion");
        deconnexionButton.setVisible(false);
        configPane.add(deconnexionButton);
        deconnexionButton.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                stopClient();
                setContentPane(buildFenetreConnexion());
            }
        });

        return globalPane;
    }

    public void ouvertureFenetreDiscution() {
        // TODO
        // ouverture fentre discution
    }

    public static void appendToChatBox(String s) {
        synchronized (toAppend) {
            toAppend.append(s);
            chatText.setText(toAppend.toString());
        }
    }

    public void runClient() {
        thread = new Thread(this);
        thread.start();
    }

    public void stopClient() {
        client.stopClient();
        chatLine.setEnabled(false);
        chatLine.setBackground(Color.gray);
        ipField.setEnabled(true);
        portField.setEnabled(true);
        loginField.setEnabled(true);
        connexionButton.setVisible(true);
        deconnexionButton.setVisible(false);
    }

    @Override
    public void run() {
        String ip = ipField.getText();
        int port = Integer.parseInt(portField.getText());
        String login = loginField.getText();
        if (client.connection(ip, port, login, modeConsole)) {
            chatLine.setEnabled(true);
            chatLine.setBackground(Color.white);
            ipField.setEnabled(false);
            portField.setEnabled(false);
            loginField.setEnabled(false);
            connexionButton.setVisible(false);
            deconnexionButton.setVisible(true);
        }
    }
}
