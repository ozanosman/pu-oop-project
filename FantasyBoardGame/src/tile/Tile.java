package tile;

import java.awt.*;

public class Tile
{
    public static final int TILE_SIZE = 100;

    protected int row;
    protected int col;
    protected Color color;
    protected Color outlineColor;

    public Tile(int row, int col, Color color, Color outlineColor)
    {
        this.row = row;
        this.col = col;
        this.color = color;
        this.outlineColor = outlineColor;
    }

    public Color getColor()
    {
        return color;
    }

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