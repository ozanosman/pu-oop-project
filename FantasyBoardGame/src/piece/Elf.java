package piece;

import game.GameBoard;

import java.awt.*;

public class Elf extends Piece
{
    public Elf(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor, "E", 5, 1, 10, 3, 3);
    }

    @Override
    public boolean isPlacementValid(int placementRow, int placementCol)
    {
        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER1 && placementRow < 2 && placementCol < 9)
        {
            return true;
        }
        return GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER2 && placementRow > 4 && placementCol < 9;
    }

    @Override
    public boolean isMoveValid(int moveRow, int moveCol)
    {
        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER1 && moveRow < 5 && moveCol < 9)
        {
            int rowCoefficient = Math.abs(moveRow - this.row);
            int colCoefficient = Math.abs(moveCol - this.col);

            return rowCoefficient == 0 && colCoefficient <= 3 || rowCoefficient <= 3 && colCoefficient == 0;
        }

        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER2 && moveRow > 1 && moveCol < 9)
        {
            int rowCoefficient = Math.abs(moveRow - this.row);
            int colCoefficient = Math.abs(moveCol - this.col);

            return rowCoefficient == 0 && colCoefficient <= 3 || rowCoefficient <= 3 && colCoefficient == 0;
        }

        return false;
    }

    @Override
    public boolean isAttackValid(int attackRow, int attackCol)
    {
        return false;
    }
}