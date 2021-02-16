package renderer;

import game.GameBoard;

import javax.swing.*;
import java.awt.*;

public class SwingRenderer extends JFrame
{
    GameBoard gameBoard;

    public SwingRenderer(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;

        this.setTitle("Knights vs Elves vs Dwarfs");
        this.setSize(900, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.gameBoard.playerAKnight();
        this.gameBoard.playerAElf();
        this.gameBoard.playerADwarf();

        this.gameBoard.playerBKnight();
        this.gameBoard.playerBElf();
        this.gameBoard.playerBDwarf();
    }

    @Override
    public void paint(Graphics g)
    {
        for (int row = 0; row < 7; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                this.gameBoard.renderPlayerField(g, row, col);

                this.gameBoard.renderBattleField(g, row, col);

                this.gameBoard.renderPiece(g, row, col);
            }
        }
    }
}