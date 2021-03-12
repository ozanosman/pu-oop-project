package game;

import piece.Piece;
import tile.ObstacleTile;
import tile.Tile;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Клас съдържащ методи за изпълнението на логиката в приложението.
 *
 * @author Озан Осман
 */
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
    public boolean allPiecesPlaced = false;

    private int PLAYER1_KILLED_PIECES = 0;
    private int PLAYER2_KILLED_PIECES = 0;

    public int ROUND = 0;

    /**
     * Метод, който визуализира полето на Player A.
     *
     * @param g     обект на супер класа
     */
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

    /**
     * Метод, който визуализира полето на Player B.
     *
     * @param g     обект на супер класа
     */
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

    /**
     * Метод, който визуализира бойното поле.
     *
     * @param g     обект на супер класа
     */
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

    /**
     * Метод задаващ координати за визуализиране на елементи "Obstacle Tile".
     */
    public void obstacleCoordinates()
    {
        int OBSTACLE_COUNT = ThreadLocalRandom.current().nextInt(1, 6);

        do
        {
            int row = ThreadLocalRandom.current().nextInt(2, 5);
            int col = ThreadLocalRandom.current().nextInt(0, 9);

            if (this.hasBoardTile(row, col))
            {
                continue;
            }

            this.tileCollection[row][col] = new ObstacleTile(row, col, Color.BLACK , Color.BLACK);

            OBSTACLE_COUNT--;
        }
        while(OBSTACLE_COUNT != 0);
    }

    /**
     * Метод съдържащ инстанция на клас за визуализиране на елементи "Obstacle Tile".
     *
     * @param g     обект на супер класа
     * @param row   ред на елемента
     * @param col   колона на елемента
     */
    private void renderObstacle(Graphics g, int row, int col)
    {
        if (this.hasBoardTile(row, col))
        {
            Tile tile = this.tileCollection[row][col];
            tile.renderTile(g);
        }
    }

    /**
     * Метод, който проверява и връща елемент от обекта за елементи "Obstacle Tile", ако то е от определения цвят.
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     */
    public boolean isObstacleBlocking(int row,int col)
    {
        Tile tile = getBoardTile(row,col);

        if (this.hasBoardTile(row, col))
        {
            return tile.getColor().equals(Color.BLACK);
        }

        return false;
    }

    /**
     * Метод, който визуализира низ базиран на стойност на избрания играч.
     *
     * @param g     обект на супер класа
     */
    public void renderPlayerTurn(Graphics g)
    {
        g.setFont(Font.decode("Courier, Font.BOLD, 25"));

        if (CHOSEN_PLAYER % 2 == PLAYER1)
        {
            g.setColor(Color.RED);
            g.drawString("Ред е на Player A", 925, 350);
        }

        if (CHOSEN_PLAYER % 2 == PLAYER2)
        {
            g.setColor(Color.BLUE);
            g.drawString("Ред е на Player B", 925, 350);
        }
    }

    /**
     * Метод, който визуализира низове и полета за избиране на игрални фигури.
     *
     * @param g     обект на супер класа
     */
    public void renderPlayerPiecePickerFields(Graphics g)
    {
        g.setFont(Font.decode("Courier, Font.BOLD, 25"));
        g.setColor(Color.BLACK);

        g.drawString("Фигури на Player A", 1000, 95);
        renderPlayerAPiecePickerField(g);
        renderPlayerAPlacementBlocker(g);

        g.drawString("Фигури на Player B", 1000, 395);
        renderPlayerBPiecePickerField(g);
        renderPlayerBPlacementBlocker(g);
    }

    /**
     * Метод, който визуализира рунда.
     *
     * @param g     обект на супер класа
     */
    public void renderRound(Graphics g)
    {
        g.setFont(Font.decode("Courier, Font.BOLD, 25"));
        g.setColor(Color.GRAY);

        g.drawString("Рунд: " + ++ROUND, 1000, 650);
    }

    /**
     * Метод, който убитите фигури на Player A и Player B.
     *
     * @param g     обект на супер класа
     */
    public void renderPlayersKillCount(Graphics g)
    {
        g.setFont(Font.decode("Courier, Font.BOLD, 15"));

        g.setColor(Color.RED);
        g.drawString("Убити фигури: " + PLAYER1_KILLED_PIECES, 1250, 95);

        g.setColor(Color.BLUE);
        g.drawString("Убити фигури: " + PLAYER2_KILLED_PIECES, 1250, 395);
    }

    /**
     * Метод съдържащ цикъл и инстанция на клас за визуализиране на елементи "Knight", "Elf" и "Dwarf".
     *
     * @param g     обект на супер класа за всички графични контексти
     */
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

    /**
     * Метод, който контролира елемента "Piece" въз основа на това кой го контролира.
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     * @param piece     инстанция на клас
     */
    public void movePiece(int row, int col, Piece piece)
    {
        if (CHOSEN_PLAYER % 2 == PLAYER1 && piece.getColor().equals(Color.RED))
        {
            int initialRow = piece.getRow();
            int initialCol = piece.getCol();

            piece.movePiece(row, col);

            this.pieceCollection[piece.getRow()][piece.getCol()] = this.selectedPiece;
            this.pieceCollection[initialRow][initialCol] = null;

            this.selectedPiece = null;

            CHOSEN_PLAYER++;
        }

        if (CHOSEN_PLAYER % 2 == PLAYER2 && piece.getColor().equals(Color.BLUE))
        {
            int initialRow = piece.getRow();
            int initialCol = piece.getCol();

            piece.movePiece(row, col);

            this.pieceCollection[piece.getRow()][piece.getCol()] = this.selectedPiece;
            this.pieceCollection[initialRow][initialCol] = null;

            this.selectedPiece = null;

            CHOSEN_PLAYER++;
        }
    }

    /**
     * Метод, който връща елемент от обекта за елементи "Tile".
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     */
    public Tile getBoardTile(int row, int col)
    {
        return this.tileCollection[row][col];
    }

    /**
     * Метод, който проверява и връща елемент от обекта за елементи "Tile", ако те съществуват.
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     */
    public boolean hasBoardTile(int row, int col)
    {
        return this.getBoardTile(row, col) != null;
    }

    /**
     * Метод, който връща елемент от обекта за елементи "Piece".
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     */
    public Piece getBoardPiece(int row, int col)
    {
        return this.pieceCollection[row][col];
    }

    /**
     * Метод, който проверява и връща елемент от обекта за елементи "Piece", ако те съществуват.
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     */
    public boolean hasBoardPiece(int row, int col)
    {
        return this.getBoardPiece(row, col) != null;
    }

    /**
     * Метод, който връща координати на игралната дъска в единични числа.
     *
     * @param coordinates   координати
     */
    public int getBoardCoordinates(int coordinates)
    {
        return coordinates / Tile.TILE_SIZE;
    }

    /**
     * Метод, който задава цвят на елемент според неговия ред и колона.
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     */
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

    /**
     * Метод, който визуализира полето за избиране на игрални фигури за Player A.
     *
     * @param g     обект на супер класа
     */
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

    /**
     * Метод, който визуализира полето за избиране на игрални фигури за Player B.
     *
     * @param g     обект на супер класа
     */
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

    /**
     * Метод, който визуализира блокиращи полето против слагане на фигури за Player A.
     *
     * @param g     обект на супер класа
     */
    private void renderPlayerAPlacementBlocker(Graphics g)
    {
        if (CHOSEN_PLAYER % 2 == PLAYER1 && CHOSEN_PLAYER < 12)
        {
            for (int row = 2; row < 7; row++)
            {
                for (int col = 0; col < 9; col++)
                {
                    Tile blocker = new Tile(row, col, Color.BLUE, Color.BLACK);
                    blocker.renderTile(g);
                }
            }
        }
    }

    /**
     * Метод, който визуализира блокиращи полето против слагане на фигури за Player B.
     *
     * @param g     обект на супер класа
     */
    private void renderPlayerBPlacementBlocker(Graphics g)
    {
        if (CHOSEN_PLAYER % 2 == PLAYER2 && CHOSEN_PLAYER < 12)
        {
            for (int row = 0; row < 5; row++)
            {
                for (int col = 0; col < 9; col++)
                {
                    Tile blocker = new Tile(row, col, Color.RED, Color.BLACK);
                    blocker.renderTile(g);
                }
            }
        }
    }
}