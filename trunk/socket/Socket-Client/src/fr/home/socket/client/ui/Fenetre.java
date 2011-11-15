package fr.home.socket.client.ui;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import fr.home.socket.client.model.Client;

@SuppressWarnings("serial")
public class Fenetre extends JFrame {

    private JTextArea field;

    private JLabel label;

    private static Client client = new Client();

    public Fenetre() throws InterruptedException {
        super();
        build();

        try {
            while (!client.isStop()) {
                // if (client.connection())
                // client.run();
                // Thread.sleep(10000);
            }
            client.close();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }

    private void build() {
        setTitle("Serveur"); // On donne un titre à l'application
        setSize(350, 250); // On donne une taille à notre fenêtre
        setLocationRelativeTo(null); // On centre la fenêtre sur l'écran
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // On dit à
        // l'application de se
        // fermer lors du clic
        // sur la croix
        setContentPane(buildContentPane());
    }

    private JPanel buildContentPane() {
        JPanel panel = new JPanel();
        panel.setLayout(new FlowLayout());
        panel.setBackground(Color.white);

        label = new JLabel("test");
        label.setSize(300, 150);

        panel.add(label);

        field = new JTextArea("", 4, 30);
        field.setLineWrap(true);

        panel.add(new JScrollPane(field));

        return panel;
    }

    public JTextArea getField() {
        return field;
    }
}
