package piece;

import tile.Tile;

import java.awt.*;

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

    public int getRow()
    {
        return row;
    }

    public int getCol()
    {
        return col;
    }

    public Color getColor()
    {
        return color;
    }

    public void movePiece(int newRow, int newCol)
    {
        this.row = newRow;
        this.col = newCol;
    }

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

    public abstract boolean isPlacementValid(int placementRow, int placementCol);

    public abstract boolean isMoveValid(int moveRow, int moveCol);

    public abstract boolean isAttackValid(int attackRow, int attackCol);
}