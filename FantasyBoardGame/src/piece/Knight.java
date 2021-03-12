package piece;

import game.GameBoard;

import java.awt.*;

public class Knight extends Piece
{
    public Knight(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor, "K", 8, 3, 15, 1, 1);
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
        return false;
    }

    @Override
    public boolean isAttackValid(int attackRow, int attackCol)
    {
        return false;
    }
}