package ui;

import game.GameBoard;
import renderer.SwingRenderer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Клас наследяващ JDialog, съдържащ конструктори и методи за визуализиране на прозореца "Modal".
 *
 * @author Озан Осман
 */
public class Modal extends JDialog
{
    /**
     * Конструктор съдържащ характеристиките за създаване на прозореца "Modal".
     *
     * @param parent    родителят, който JFrame ще го използва
     * @param title     заглавие на прозореца
     * @param message   съобщение в прозореца
     */
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

    /**
     * Конструктор съдържащ характеристиките за създаване на прозореца "Modal" с бутон.
     *
     * @param message   съобщение в прозореца
     * @param parent    родителят, който JFrame ще го използва
     * @param title     заглавие на прозореца
     */
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

    /**
     * Метод за инстанция на прозореца "Modal".
     *
     * @param parent    родителят, който JFrame ще го използва
     * @param title     заглавие на прозореца
     * @param message   съобщение в прозореца
     */
    public static void renderMessage(JFrame parent, String title, String message)
    {
        new Modal(parent, title, message);
    }

    /**
     * Метод за инстанция на прозореца "Modal" с бутон.
     *
     * @param parent    родителят, който JFrame ще го използва
     * @param title     заглавие на прозореца
     * @param message   съобщение в прозореца
     */
    public static void renderMessageWithButton(JFrame parent, String title, String message)
    {
        new Modal(message, parent, title);
    }
}