package ui;

import game.GameBoard;
import renderer.SwingRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

    public Modal(String message, JFrame parent, String title)
    {
        super(parent, title, true);

        JPanel panel = new JPanel();
        JLabel label = new JLabel(message);
        JButton buttonRestart = new JButton("Нова игра?");

        panel.add(label);
        panel.add(buttonRestart);
        getContentPane().add(panel);

        buttonRestart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                parent.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                parent.setVisible(false);
                GameBoard gameBoard = new GameBoard();
                SwingRenderer renderer = new SwingRenderer(gameBoard);

                gameBoard.CHOSEN_PLAYER = 0;
                gameBoard.isGameOn = false;
                gameBoard.allPiecesPlaced = false;
                gameBoard.ROUND = 0;
            }
        });

        this.setSize(250, 100);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }

    public static void renderMessage(JFrame parent, String title, String message)
    {
        new Modal(parent, title, message);
    }

    public static void renderMessageWithButton(JFrame parent, String title, String message)
    {
        new Modal(message, parent, title);
    }
}