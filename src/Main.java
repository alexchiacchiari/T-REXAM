import com.it.unimol.exam.platform.gui.GameWindow;
import javax.swing.SwingUtilities;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            GameWindow gameWindow = new GameWindow();
            gameWindow.startGame();
        });
    }
}
