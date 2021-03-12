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
        return false;
    }

    @Override
    public boolean isAttackValid(int attackRow, int attackCol)
    {
        return false;
    }
}