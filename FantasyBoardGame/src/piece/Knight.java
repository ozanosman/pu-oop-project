package piece;

import game.GameBoard;

import java.awt.*;

/**
 * Клас наследяващ Piece, съдържащ конструктор и метод за елементи "Knight".
 *
 * @author Озан Осман
 */
public class Knight extends Piece
{
    /**
     * Конструктор на супер класа за елемента "Knight".
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     * @param color     цвят на елемента
     * @param outlineColor      контур на елемента
     */
    public Knight(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor, "K", 8, 3, 15, 1, 1);
    }

    /**
     * Метод, който проверява и връща дали елемента "Knight" може да се постави.
     *
     * @param placementRow   ред на елемента, който може да се постави
     * @param placementCol   колона на елемента, който може да се постави
     */
    @Override
    public boolean isPlacementValid(int placementRow, int placementCol)
    {
        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER1 && placementRow < 2 && placementCol < 9)
        {
            return true;
        }
        return GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER2 && placementRow > 4 && placementCol < 9;
    }

    /**
     * Метод, който проверява и връща дали елемента "Knight" може да се движи.
     *
     * @param moveRow   ред на елемента, който може да се движи
     * @param moveCol   колона на елемента, който може да се движи
     */
    @Override
    public boolean isMoveValid(int moveRow, int moveCol)
    {
        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER1 && moveRow < 5 && moveCol < 9)
        {
            int rowCoefficient = Math.abs(moveRow - this.row);
            int colCoefficient = Math.abs(moveCol - this.col);

            return rowCoefficient == 0 && colCoefficient == 1 || rowCoefficient == 1 && colCoefficient == 0;
        }

        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER2 && moveRow > 1 && moveCol < 9)
        {
            int rowCoefficient = Math.abs(moveRow - this.row);
            int colCoefficient = Math.abs(moveCol - this.col);

            return rowCoefficient == 0 && colCoefficient == 1 || rowCoefficient == 1 && colCoefficient == 0;
        }

        return false;
    }

    /**
     * Метод, който проверява и връща дали елемента "Knight" може да атакува.
     *
     * @param attackRow   ред на елемента, който може да атакува
     * @param attackCol   колона на елемента, който може да атакува
     */
    @Override
    public boolean isAttackValid(int attackRow, int attackCol)
    {
        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER1 && attackRow < 5 && attackCol < 9 && getColor().equals(Color.BLUE))
        {
            int rowCoefficient = Math.abs(attackRow - this.row);
            int colCoefficient = Math.abs(attackRow - this.col);

            return rowCoefficient == 0 && colCoefficient == 1 || rowCoefficient == 1 && colCoefficient == 0;
        }

        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER2 && attackRow > 1 && attackCol < 9 && getColor().equals(Color.RED))
        {
            int rowCoefficient = Math.abs(attackRow - this.row);
            int colCoefficient = Math.abs(attackRow - this.col);

            return rowCoefficient == 0 && colCoefficient == 1 || rowCoefficient == 1 && colCoefficient == 0;
        }

        return false;
    }
}