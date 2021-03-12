package piece;

import tile.Tile;

import java.awt.*;

/**
 * Абстрактен клас съдържащ конструктор, променливи и методи за елементи "Knight", "Elf" и "Dwarf".
 *
 * @author Озан Осман
 */
public abstract class Piece
{
    public final int PIECE_SIZE = 100;

    protected int row;
    protected int col;
    protected Color color;
    protected Color outlineColor;
    protected String id;
    protected int attack;
    protected int armor;
    protected int health;
    protected int attackRange;
    protected int speed;

    /**
     * Конструктор на елемента "Piece".
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     * @param color     цвят на елемента
     * @param outlineColor      контур на елемента
     * @param id    символ на елемента
     * @param attack    атака на елемента
     * @param armor     броня на елемента
     * @param health    живот на елемента
     * @param attackRange   обхват на атака  на елемента
     * @param speed     скорост на движение на елемента
     */
    public Piece(int row, int col, Color color, Color outlineColor, String id, int attack, int armor, int health, int attackRange, int speed)
    {
        this.row = row;
        this.col = col;
        this.color = color;
        this.outlineColor = outlineColor;
        this.id = id;
        this.attack = attack;
        this.armor = armor;
        this.health = health;
        this.attackRange = attackRange;
        this.speed = speed;
    }

    /**
     * Метод, който връща ред на елемента.
     */
    public int getRow()
    {
        return row;
    }

    /**
     * Метод, който връща колона на елемента.
     */
    public int getCol()
    {
        return col;
    }

    /**
     * Метод, който връща цвят на елемента.
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Метод, който дава нови координати на елементи.
     *
     * @param newRow    нов ред на елемента
     * @param newCol    нова колона на елемента
     */
    public void movePiece(int newRow, int newCol)
    {
        this.row = newRow;
        this.col = newCol;
    }

    /**
     * Метод съдържащ логика за визуализиране на елементи "Piece".
     *
     * @param g     обект на супер класа
     */
    public void renderPiece(Graphics g)
    {
        int pieceX = this.col * Tile.TILE_SIZE;
        int pieceY = this.row * Tile.TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(pieceX, pieceY, PIECE_SIZE, PIECE_SIZE);

        g.setColor(this.outlineColor);
        g.drawRect(pieceX, pieceY, PIECE_SIZE, PIECE_SIZE);

        g.setColor(Color.ORANGE);
        g.setFont(Font.decode("Courier, Font.BOLD, 50"));
        g.drawString(this.id, pieceX + 35, pieceY + 65);
    }

    /**
     * Абстрактен метод, който проверява и връща дали елемента може да се постави.
     *
     * @param placementRow   ред на елемента, който може да се постави
     * @param placementCol   колона на елемента, който може да се постави
     */
    public abstract boolean isPlacementValid(int placementRow, int placementCol);

    /**
     * Абстрактен метод, който проверява и връща дали елемента може да се движи.
     *
     * @param moveRow   ред на елемента, който може да се движи
     * @param moveCol   колона на елемента, който може да се движи
     */
    public abstract boolean isMoveValid(int moveRow, int moveCol);

    /**
     * Абстрактен метод, който проверява и връща дали елемента може да атакува.
     *
     * @param attackRow   ред на елемента, който може да атакува
     * @param attackCol   колона на елемента, който може да атакува
     */
    public abstract boolean isAttackValid(int attackRow, int attackCol);
}