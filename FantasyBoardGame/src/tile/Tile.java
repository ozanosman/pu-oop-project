package tile;

import java.awt.*;

/**
 * Клас съдържащ конструктор, променливи и методи за елементи "Tile".
 *
 * @author Озан Осман
 */
public class Tile
{
    public static final int TILE_SIZE = 100;

    protected int row;
    protected int col;
    protected Color color;
    protected Color outlineColor;

    /**
     * Конструктор на елемента "Tile".
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     * @param color     цвят на елемента
     * @param outlineColor      контур на елемента
     */
    public Tile(int row, int col, Color color, Color outlineColor)
    {
        this.row = row;
        this.col = col;
        this.color = color;
        this.outlineColor = outlineColor;
    }

    /**
     * Метод, който връща цвят на елемента.
     */
    public Color getColor()
    {
        return color;
    }

    /**
     * Метод съдържащ логика за визуализиране на елементи "Tile".
     *
     * @param g     обект на супер класа
     */
    public void renderTile(Graphics g)
    {
        int tileX = this.col * TILE_SIZE;
        int tileY = this.row * TILE_SIZE;

        g.setColor(this.color);
        g.fillRect(tileX, tileY, TILE_SIZE, TILE_SIZE);

        g.setColor(this.outlineColor);
        g.drawRect(tileX, tileY, TILE_SIZE, TILE_SIZE);
    }
}