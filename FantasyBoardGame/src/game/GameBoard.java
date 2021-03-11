package game;

import piece.Dwarf;
import piece.Elf;
import piece.Knight;
import piece.Piece;
import tile.ObstacleTile;
import tile.Tile;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

public class GameBoard
{
    private final int TILE_SIDE_HEIGHT = 7;
    private final int TILE_SIDE_WIDTH = 9;

    private Tile[][] tileCollection = new Tile[TILE_SIDE_HEIGHT][TILE_SIDE_WIDTH];
    private Piece[][] pieceCollection = new Piece[TILE_SIDE_HEIGHT][TILE_SIDE_WIDTH];

    public boolean IS_GAME_ON = false;
    public boolean IS_GAME_OFF = true;

    public void renderPlayerAField(Graphics g)
    {
        for (int row = 0; row < 2; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                Color fieldColor = this.getFieldColor(row, col);

                Tile field = new Tile(row, col, fieldColor, Color.BLACK);
                field.renderTile(g);
            }
        }
    }

    public void renderPlayerBField(Graphics g)
    {
        for (int row = 5; row < 7; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                Color fieldColor = this.getFieldColor(row, col);

                Tile field = new Tile(row, col, fieldColor, Color.BLACK);
                field.renderTile(g);
            }
        }
    }

    public void renderBattleField(Graphics g)
    {
        for (int row = 2; row < 5; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                Tile battleField = new Tile(row, col, Color.LIGHT_GRAY, Color.BLACK);
                battleField.renderTile(g);

                this.renderObstacle(g, row, col);
            }
        }
    }

    public String getStartingPlayer()
    {
        return String.format("%s е на ред!", pickStartingPlayer() ? "Player A" : "Player B");
    }

    public void playerAKnightCoordinates()
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

    public void playerAElfCoordinates()
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

    public void playerADwarfCoordinates()
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

    public void playerBKnightCoordinates()
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

    public void playerBElfCoordinates()
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

    public void playerBDwarfCoordinates()
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

    public void obstacleCoordinates()
    {
        int OBSTACLE_COUNT = ThreadLocalRandom.current().nextInt(1, 6);

        do
        {
            int row = ThreadLocalRandom.current().nextInt(2, 5);
            int col = ThreadLocalRandom.current().nextInt(0, 9);

            if (this.tileCollection[row][col] != null)
            {
                continue;
            }

            this.tileCollection[row][col] = new ObstacleTile(row, col, Color.BLACK , Color.BLACK);

            OBSTACLE_COUNT--;
        }
        while(OBSTACLE_COUNT != 0);
    }

    public void renderPiece(Graphics g)
    {
        for (int row = 0; row < 7; row++)
        {
            for (int col = 0; col < 9; col++)
            {
                if (this.hasBoardPiece(row, col))
                {
                    Piece piece = this.getBoardPiece(row, col);
                    piece.renderPiece(g);
                }
            }
        }
    }

    private void renderObstacle(Graphics g, int row, int col)
    {
        if (this.tileCollection[row][col] != null)
        {
            Tile tile = this.tileCollection[row][col];
            tile.renderTile(g);
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
            return new Color(170, 170 ,170);
        }

        if (isRowEven && isColOdd)
        {
            return new Color(68, 68 ,68);
        }

        if (isRowOdd && isColEven)
        {
            return new Color(68, 68 ,68);
        }

        return new Color(170, 170 ,170);
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

    private boolean pickStartingPlayer()
    {
        return Math.random() > 0.5;
    }
}