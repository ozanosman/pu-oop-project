import game.GameBoard;
import renderer.SwingRenderer;

/**
 * Клас съдържащ главния метод за изпълнение на приложението.
 *
 * @author Озан Осман
 */
public class Application
{
    public static void main(String[] args)
    {
        GameBoard gameBoard = new GameBoard();
        SwingRenderer renderer = new SwingRenderer(gameBoard);
    }
}