package tile;

import java.awt.*;

public class ObstacleTile extends Tile
{
    public ObstacleTile(int row, int col, Color color, Color outlineColor)
    {
        super(row, col, color, outlineColor);
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