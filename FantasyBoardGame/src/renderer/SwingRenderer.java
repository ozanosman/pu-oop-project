package renderer;

import game.GameBoard;
import piece.Dwarf;
import piece.Elf;
import piece.Knight;
import piece.Piece;
import ui.Modal;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SwingRenderer extends JFrame implements MouseListener
{
    GameBoard gameBoard;

    public SwingRenderer(GameBoard gameBoard)
    {
        this.gameBoard = gameBoard;

        this.gameBoard.pieceCollection[1][10] = new Knight(1, 10, Color.RED, Color.BLACK);
        this.gameBoard.pieceCollection[2][10] = new Knight(2, 10, Color.RED, Color.BLACK);
        this.gameBoard.pieceCollection[1][11] = new Elf(1, 11, Color.RED, Color.BLACK);
        this.gameBoard.pieceCollection[2][11] = new Elf(2, 11, Color.RED, Color.BLACK);
        this.gameBoard.pieceCollection[1][12] = new Dwarf(1, 12, Color.RED, Color.BLACK);
        this.gameBoard.pieceCollection[2][12] = new Dwarf(2, 12, Color.RED, Color.BLACK);

        this.gameBoard.pieceCollection[4][10] = new Knight(4, 10, Color.BLUE, Color.BLACK);
        this.gameBoard.pieceCollection[5][10] = new Knight(5, 10, Color.BLUE, Color.BLACK);
        this.gameBoard.pieceCollection[4][11] = new Elf(4, 11, Color.BLUE, Color.BLACK);
        this.gameBoard.pieceCollection[5][11] = new Elf(5, 11, Color.BLUE, Color.BLACK);
        this.gameBoard.pieceCollection[4][12] = new Dwarf(4, 12, Color.BLUE, Color.BLACK);
        this.gameBoard.pieceCollection[5][12] = new Dwarf(5, 12, Color.BLUE, Color.BLACK);

        this.setTitle("Knights vs Elves vs Dwarfs");
        this.setSize(1400, 700);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addMouseListener(this);

        this.gameBoard.obstacleCoordinates();

        if (!this.gameBoard.isGameOn)
        {
            Modal.renderMessage(this, "Внимание!", "Играта започва!");
            Modal.renderMessage(this, "Внимание!", "Player A започва първи!");

            this.gameBoard.isGameOn = true;
        }
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        int row = this.gameBoard.getBoardCoordinates(e.getY());
        int col = this.gameBoard.getBoardCoordinates(e.getX());

        if (this.gameBoard.ROUND >= 25 && this.gameBoard.isGameOn)
        {
            Modal.renderMessageWithButton(this,"Внимание!","Играта завършва без победители!");
        }

        Piece piece = this.gameBoard.selectedPiece;

        if (this.gameBoard.selectedPiece != null && !this.gameBoard.allPiecesPlaced)
        {
            if (piece.isPlacementValid(row, col))
            {
                this.gameBoard.movePiece(row, col, piece);

                this.repaint();

                return;
            }
            else
            {
                Modal.renderMessage(this, "Внимание!", "Тука не можете да поставите фигурата си!");
            }
        }

        if (this.gameBoard.CHOSEN_PLAYER == 12)
        {
            this.gameBoard.allPiecesPlaced = true;
        }

        if (this.gameBoard.selectedPiece != null && this.gameBoard.allPiecesPlaced)
        {
            if (this.gameBoard.isObstacleBlocking(row, col))
            {
                Modal.renderMessage(this, "Внимание!", "Тука има препятствие!");
            }
            else
            {
                if (piece.isMoveValid(row, col) || piece.isAttackValid(row, col))
                {
                    this.gameBoard.movePiece(row, col, piece);

                    this.repaint();

                    return;
                }
                else
                {
                    Modal.renderMessage(this, "Внимание!", "Тука не можете да подвижите фигурата си!");
                }
            }
        }

        if (this.gameBoard.hasBoardPiece(row, col))
        {
            this.gameBoard.selectedPiece = this.gameBoard.getBoardPiece(row, col);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }

    @Override
    public void paint(Graphics g)
    {
        super.paint(g);

        this.gameBoard.renderPlayerAField(g);
        this.gameBoard.renderPlayerBField(g);

        this.gameBoard.renderBattleField(g);

        this.gameBoard.renderPlayerTurn(g);
        this.gameBoard.renderPlayerPiecePickerFields(g);
        this.gameBoard.renderPiece(g);

        if (this.gameBoard.CHOSEN_PLAYER >= 12)
        {
            this.gameBoard.renderRound(g);
            this.gameBoard.renderPlayersKillCount(g);
        }
    }
}