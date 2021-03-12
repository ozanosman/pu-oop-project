package tile;

import java.awt.*;

/**
 * Клас наследяващ Tile, съдържащ конструктор и метод за елементи "Obstacle Tile".
 *
 * @author Озан Осман
 */
public class ObstacleTile extends Tile
{
    /**
     * Конструктор на супер класа за елемента "Obstacle Tile".
     *
     * @param row   ред на елемента
     * @param col   колона на елемента
     * @param color     цвят на елемента
     * @param outlineColor      контур на елемента
     */
    public ObstacleTile(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor);
    }

    /**
     * Метод съдържащ логика за визуализиране на елементи "Obstacle Tile".
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