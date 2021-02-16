package piece;

import java.awt.*;

public class Elf extends Piece
{
    public Elf(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor, "E", 5, 1, 10, 3, 3);
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
