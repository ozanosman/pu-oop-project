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

    private int PLAYER_A_FIGURE_COUNT = 6;
    private int PLAYER_B_FIGURE_COUNT = 6;

    private Piece[][] pieceCollection = new Piece[TILE_SIDE_HEIGHT][TILE_SIDE_WIDTH];

    public void renderBattleField(Graphics g, int row, int col)
    {
        Color fieldColor = this.getFieldColor(row, col);

        Tile field = new Tile(row, col, fieldColor, Color.BLACK);
        field.renderTile(g);
    }

    public void playerAFigures()
    {
        do
        {
            int row = getRowCoordinates();
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Knight(row, col, Color.WHITE, Color.BLACK);
            this.pieceCollection[row][col] = new Knight(row, col, Color.WHITE, Color.BLACK);

            this.pieceCollection[row][col] = new Elf(row, col, Color.WHITE, Color.BLACK);
            this.pieceCollection[row][col] = new Elf(row, col, Color.WHITE, Color.BLACK);

            this.pieceCollection[row][col] = new Dwarf(row, col, Color.WHITE, Color.BLACK);
            this.pieceCollection[row][col] = new Dwarf(row, col, Color.WHITE, Color.BLACK);

            PLAYER_A_FIGURE_COUNT--;
        }
        while(PLAYER_A_FIGURE_COUNT != 0);
    }

    public void playerBFigures()
    {
        do
        {
            int row = getRowCoordinates() + 5;
            int col = getColCoordinates();

            if (this.hasBoardPiece(row, col))
            {
                continue;
            }

            this.pieceCollection[row][col] = new Knight(row, col, Color.WHITE, Color.BLACK);
            this.pieceCollection[row][col] = new Knight(row, col, Color.WHITE, Color.BLACK);

            this.pieceCollection[row][col] = new Elf(row, col, Color.WHITE, Color.BLACK);
            this.pieceCollection[row][col] = new Elf(row, col, Color.WHITE, Color.BLACK);

            this.pieceCollection[row][col] = new Dwarf(row, col, Color.WHITE, Color.BLACK);
            this.pieceCollection[row][col] = new Dwarf(row, col, Color.WHITE, Color.BLACK);

            PLAYER_B_FIGURE_COUNT--;
        }
        while(PLAYER_B_FIGURE_COUNT != 0);
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
            return Color.BLACK;
        }

        if (isRowEven && isColOdd)
        {
            return Color.GRAY;
        }

        if (isRowOdd && isColEven)
        {
            return Color.GRAY;
        }

        return Color.BLACK;
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
