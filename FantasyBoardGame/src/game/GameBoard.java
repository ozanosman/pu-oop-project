package game;

import piece.Dwarf;
import piece.Elf;
import piece.Knight;
import piece.Piece;
import tile.Tile;

import java.awt.*;

public class GameBoard
{
    private final int TILE_SIDE_HEIGHT = 7;
    private final int TILE_SIDE_WIDTH = 9;

    private Piece[][] pieceCollection = new Piece[TILE_SIDE_HEIGHT][TILE_SIDE_WIDTH];

    public void renderPlayerField(Graphics g, int row, int col)
    {
        Color fieldColor = this.getFieldColor(row, col);

        Tile field = new Tile(row, col, fieldColor, Color.BLACK);
        field.renderTile(g);
    }

    public void renderBattleField(Graphics g, int row, int col)
    {
        if (row > 1 && row < 5)
        {
            Tile battleField = new Tile(row, col, Color.LIGHT_GRAY, Color.BLACK);
            battleField.renderTile(g);
        }
    }

    public void playerAKnight()
    {
        int KNIGHT_COUNT = 2;

        do
        {
            int row = getRowCoordinates();
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Knight(row, col, Color.WHITE, Color.BLACK);

            KNIGHT_COUNT--;
        }
        while(KNIGHT_COUNT != 0);
    }

    public void playerAElf()
    {
        int ELF_COUNT = 2;

        do
        {
            int row = getRowCoordinates();
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Elf(row, col, Color.WHITE, Color.BLACK);

            ELF_COUNT--;
        }
        while(ELF_COUNT != 0);
    }

    public void playerADwarf()
    {
        int DWARF_COUNT = 2;

        do
        {
            int row = getRowCoordinates();
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Dwarf(row, col, Color.WHITE, Color.BLACK);

            DWARF_COUNT--;
        }
        while(DWARF_COUNT != 0);
    }

    public void playerBKnight()
    {
        int KNIGHT_COUNT = 2;

        do
        {
            int row = getRowCoordinates() + 5;
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Knight(row, col, Color.WHITE, Color.BLACK);

            KNIGHT_COUNT--;
        }
        while(KNIGHT_COUNT != 0);
    }

    public void playerBElf()
    {
        int ELF_COUNT = 2;

        do
        {
            int row = getRowCoordinates() + 5;
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Elf(row, col, Color.WHITE, Color.BLACK);

            ELF_COUNT--;
        }
        while(ELF_COUNT != 0);
    }

    public void playerBDwarf()
    {
        int DWARF_COUNT = 2;

        do
        {
            int row = getRowCoordinates() + 5;
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Dwarf(row, col, Color.WHITE, Color.BLACK);

            DWARF_COUNT--;
        }
        while(DWARF_COUNT != 0);
    }

    public void renderPiece(Graphics g, int row, int col)
    {
        if (this.hasBoardPiece(row, col))
        {
            Piece piece = this.getBoardPiece(row, col);
            piece.renderPiece(g);
        }
    }

    private Color getFieldColor(int row, int col)
    {
        boolean isRowEven = (row % 2 == 0);
        boolean isRowOdd = !isRowEven;

        boolean isColEven = (col % 2 == 0);
        boolean isColOdd = !isColEven;

        if (isRowEven && isColEven)
        {
            return Color.GRAY;
        }

        if (isRowEven && isColOdd)
        {
            return Color.BLACK;
        }

        if (isRowOdd && isColEven)
        {
            return Color.BLACK;
        }

        return Color.GRAY;
    }

    private Piece getBoardPiece(int row, int col)
    {
        return this.pieceCollection[row][col];
    }

    private boolean hasBoardPiece(int row, int col)
    {
        return this.getBoardPiece(row, col) != null;
    }

    private int getRowCoordinates()
    {
        return (int) (Math.random() * 2);
    }

    private int getColCoordinates()
    {
        return (int) (Math.random() * 9);
    }
}