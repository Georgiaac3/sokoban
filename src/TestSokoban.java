import javax.swing.SwingUtilities;
import boundary.LevelSelector;

// Responsabilité : Lancer l'interface utilisateur pour le choix du niveau

public class TestSokoban implements Runnable {

    public TestSokoban() {
        // Lancer l'interface utilisateur pour le choix du niveau
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new LevelSelector().setVisible(true);
            }
        });
    }

    @Override
    public void run() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new TestSokoban());
    }
}