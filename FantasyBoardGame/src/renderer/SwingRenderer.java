package renderer;

import game.GameBoard;
import ui.Modal;

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

        this.gameBoard.obstacleCoordinates();

        this.gameBoard.playerAKnightCoordinates();
        this.gameBoard.playerAElfCoordinates();
        this.gameBoard.playerADwarfCoordinates();

        this.gameBoard.playerBKnightCoordinates();
        this.gameBoard.playerBElfCoordinates();
        this.gameBoard.playerBDwarfCoordinates();

        if (this.gameBoard.IS_GAME_OFF)
        {
            Modal.renderMessage(this, "Внимание!", "Играта започва!");
            Modal.renderMessage(this, "Внимание!", this.gameBoard.getStartingPlayer());
            this.gameBoard.IS_GAME_ON = true;
        }
    }

    @Override
    public void paint(Graphics g)
    {
      this.gameBoard.renderPlayerAField(g);
      this.gameBoard.renderPlayerBField(g);

      this.gameBoard.renderBattleField(g);

      this.gameBoard.renderPiece(g);
    }
}