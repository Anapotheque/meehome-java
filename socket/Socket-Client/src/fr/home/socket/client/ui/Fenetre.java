package fr.home.socket.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
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

    public static String ip;

    public static String login;

    public static int port;

    public static boolean modeConsole;

    public static StringBuffer toAppend = new StringBuffer("");

    public static StringBuffer toSend = new StringBuffer("");

    public static JTextArea chatText;

    public static JTextField chatLine, ipField, portField, loginField;

    public Thread thread;

    public static Client client = new Client();

    public Fenetre(String ip, int port, String login, boolean modeConsole) throws InterruptedException {
        super();

        Fenetre.ip = ip;
        Fenetre.port = port;
        Fenetre.login = login;

        build();
    }

    private void build() {
        setTitle("Client");
        setSize(650, 300);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildNewContentPane());
    }

    private JPanel buildNewContentPane() {

        JPanel globalPane = new JPanel(new BorderLayout());
        JPanel configPane = new JPanel();
        JPanel chatPane = new JPanel(new BorderLayout());

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

        JButton connexionButton = new JButton("Connexion");
        connexionButton.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                runClient();
            }
        });

        JButton deconnexionButton = new JButton("Deconnexion");
        deconnexionButton.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                stopClient();
            }
        });

        configPane.add(ipField);
        configPane.add(portField);
        configPane.add(loginField);
        configPane.add(connexionButton);
        configPane.add(deconnexionButton);

        chatText = new JTextArea(10, 20);
        chatText.setLineWrap(true);
        chatText.setEditable(false);
        chatText.setForeground(Color.blue);
        chatText.append(toAppend.toString());

        JScrollPane chatTextPane = new JScrollPane(chatText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        chatLine = new JTextField();
        chatLine.setEnabled(false);
        chatLine.setBackground(Color.gray);

        chatLine.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                String s = chatLine.getText();
                if (!s.equals("")) {
                    appendToChatBox(login + " : " + s + "\n");
                    chatLine.setText("");
                    sendString(s);
                }
            }
        });

        chatPane.add(chatLine, BorderLayout.SOUTH);
        chatPane.add(chatTextPane, BorderLayout.CENTER);
        chatPane.setPreferredSize(new Dimension(200, 200));

        globalPane.add(configPane, BorderLayout.NORTH);
        globalPane.add(chatPane, BorderLayout.SOUTH);

        return globalPane;
    }

    public static void appendToChatBox(String s) {
        synchronized (toAppend) {
            toAppend.append(s);
            chatText.setText(toAppend.toString());
        }
    }

    private static void sendString(String s) {
        synchronized (toSend) {
            client.sendToServer(s);
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
        }
    }
}
