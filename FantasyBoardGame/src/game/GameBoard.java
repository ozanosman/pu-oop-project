package game;

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
    public Piece[][] pieceCollection = new Piece[7][14];
    public Piece selectedPiece;

    public static int CHOSEN_PLAYER = 0;
    public static int PLAYER1 = 0;
    public static int PLAYER2 = 1;

    public boolean isGameOn = false;

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

    private void renderObstacle(Graphics g, int row, int col)
    {
        if (this.tileCollection[row][col] != null)
        {
            Tile tile = this.tileCollection[row][col];
            tile.renderTile(g);
        }
    }

    public void renderPlayerPiecePickerFields(Graphics g)
    {
        g.setColor(Color.BLACK);
        g.setFont(Font.decode("Courier, Font.BOLD, 25"));

        g.drawString("Фигури на Player A", 1000, 95);
        renderPlayerAPiecePickerField(g);

        g.drawString("Фигури на Player B", 1000, 395);
        renderPlayerBPiecePickerField(g);
    }

    public void renderPiece(Graphics g)
    {
        for (int row = 0; row < 7; row++)
        {
            for (int col = 0; col < 14; col++)
            {
                if (this.hasBoardPiece(row, col))
                {
                    Piece piece = this.getBoardPiece(row, col);
                    piece.renderPiece(g);
                }
            }
        }
    }

    public void movePieceFromPickerField(int row, int col, Piece piece)
    {
        int initialRow = piece.getRow();
        int initialCol = piece.getCol();

        piece.movePiece(row, col);

        this.pieceCollection[piece.getRow()][piece.getCol()] = this.selectedPiece;
        this.pieceCollection[initialRow][initialCol] = null;

        this.selectedPiece = null;
        CHOSEN_PLAYER++;
    }

    public Piece getBoardPiece(int row, int col)
    {
        return this.pieceCollection[row][col];
    }

    public boolean hasBoardPiece(int row, int col)
    {
        return this.getBoardPiece(row, col) != null;
    }

    public int getBoardCoordinates(int coordinates)
    {
        return coordinates / Tile.TILE_SIZE;
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

    private void renderPlayerAPiecePickerField(Graphics g)
    {
        for (int row = 1; row < 3; row++)
        {
            for (int col = 10; col < 13; col++)
            {
                Tile pickerField = new Tile(row, col, Color.LIGHT_GRAY, Color.BLACK);
                pickerField.renderTile(g);
            }
        }
    }

    private void renderPlayerBPiecePickerField(Graphics g)
    {
        for (int row = 4; row < 6; row++)
        {
            for (int col = 10; col < 13; col++)
            {
                Tile pickerField = new Tile(row, col, Color.LIGHT_GRAY, Color.BLACK);
                pickerField.renderTile(g);
            }
        }
    }
}