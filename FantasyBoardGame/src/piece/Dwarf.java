package piece;

import game.GameBoard;

import java.awt.*;

public class Dwarf extends Piece
{
    public Dwarf(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor, "D", 6, 2, 12, 2, 2);
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

            return rowCoefficient == 0 && colCoefficient <= 2 || rowCoefficient <= 2 && colCoefficient == 0;
        }

        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER2 && moveRow > 1 && moveCol < 9)
        {
            int rowCoefficient = Math.abs(moveRow - this.row);
            int colCoefficient = Math.abs(moveCol - this.col);

            return rowCoefficient == 0 && colCoefficient <= 2 || rowCoefficient <= 2 && colCoefficient == 0;
        }

        return false;
    }

    @Override
    public boolean isAttackValid(int attackRow, int attackCol)
    {
        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER1 && attackRow < 5 && attackCol < 9 && getColor().equals(Color.BLUE))
        {
            int rowCoefficient = Math.abs(attackRow - this.row);
            int colCoefficient = Math.abs(attackRow - this.col);

            return rowCoefficient == 0 && colCoefficient <= 2 || rowCoefficient <= 2 && colCoefficient == 0;
        }

        if (GameBoard.CHOSEN_PLAYER % 2 == GameBoard.PLAYER2 && attackRow > 1 && attackCol < 9 && getColor().equals(Color.RED))
        {
            int rowCoefficient = Math.abs(attackRow - this.row);
            int colCoefficient = Math.abs(attackRow - this.col);

            return rowCoefficient == 0 && colCoefficient <= 2 || rowCoefficient <= 2 && colCoefficient == 0;
        }

        return false;
    }
}