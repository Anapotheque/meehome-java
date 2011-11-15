package fr.home.socket.client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import org.apache.log4j.Logger;

import fr.home.socket.client.model.Client;
import fr.home.socket.client.util.PropertiesEnum;
import fr.home.socket.client.util.Util;

@SuppressWarnings("serial")
public class Fenetre extends JFrame implements Runnable {

    static Logger logger = Logger.getLogger(Fenetre.class);

    public static final String IP = Util.getData(PropertiesEnum.IP);

    public static final String LOGIN = Util.getData(PropertiesEnum.LOGIN);

    public static final int PORT = Integer.parseInt(Util.getData(PropertiesEnum.PORT));

    public static StringBuffer toAppend = new StringBuffer("");

    public static StringBuffer toSend = new StringBuffer("");

    public static JTextArea chatText = null;

    public static JTextField chatLine = null;

    public Thread thread;

    public Fenetre() throws InterruptedException {
        super();
        build();
        thread = new Thread(this);
        thread.start();
    }

    private void build() {
        setTitle("Client");
        setSize(350, 250);
        setLocationRelativeTo(null);
        setResizable(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setContentPane(buildNewContentPane());
    }

    private JPanel buildNewContentPane() {
        // Set up the chat pane
        JPanel chatPane = new JPanel(new BorderLayout());
        chatText = new JTextArea(10, 20);
        chatText.setLineWrap(true);
        chatText.setEditable(false);
        chatText.setForeground(Color.blue);
        chatText.append(toAppend.toString());

        JScrollPane chatTextPane = new JScrollPane(chatText, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        chatLine = new JTextField();
        chatLine.setEnabled(true);

        chatLine.addActionListener(new ActionAdapter() {
            public void actionPerformed(ActionEvent e) {
                String s = chatLine.getText();
                if (!s.equals("")) {
                    appendToChatBox("OUTGOING: " + s + "\n");
                    chatLine.selectAll();
                    sendString(s);
                }
            }
        });

        chatPane.add(chatLine, BorderLayout.SOUTH);
        chatPane.add(chatTextPane, BorderLayout.CENTER);
        chatPane.setPreferredSize(new Dimension(200, 200));

        return chatPane;
    }

    // Action adapter for easy event-listener coding
    class ActionAdapter implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        }
    }

    // Thread-safe way to append to the chat box
    public static void appendToChatBox(String s) {
        synchronized (toAppend) {
            toAppend.append(s);
            chatText.setText(toAppend.toString());
        }
    }

    // Add text to send-buffer
    private static void sendString(String s) {
        synchronized (toSend) {
            toSend.append(s + "\n");
        }
    }

    @Override
    public void run() {
        Client client = new Client();
        while (!client.isStop()) {
            if (client.connection(IP, PORT, LOGIN))
                client.run();
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                logger.error("run :: " + e);
            }
        }
        client.close();
    }
}
