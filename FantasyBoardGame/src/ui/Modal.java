package ui;

import javax.swing.*;

public class Modal extends JDialog
{
    public Modal(JFrame parent, String title, String message)
    {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);

        panel.add(label);
        getContentPane().add(panel);

        this.pack();
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void renderMessage(JFrame parent, String title, String message)
    {
        new Modal(parent, title, message);
    }
}