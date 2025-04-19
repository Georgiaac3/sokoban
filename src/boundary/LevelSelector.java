package boundary;

import control.Controleur;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// Responsabilité : Afficher l'interface utilisateur pour le choix du niveau

public class LevelSelector extends JFrame {
    private JComboBox<String> levelComboBox;
    private JButton startButton;

    public LevelSelector() {
        setTitle("Choix du niveau");
        setSize(300, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        levelComboBox = new JComboBox<>(new String[]{"Niveau 1", "Niveau 2", "Niveau 3", "Niveau 4"});
        startButton = new JButton("Démarrer");

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Récupérer le niveau sélectionné
                int selectedLevel = levelComboBox.getSelectedIndex() + 1;
                int[][] configuration = Controleur.getLevel(selectedLevel);
                new FenetreSokoban(new Controleur(configuration), selectedLevel);
                dispose();
            }
        });

        JPanel panel = new JPanel();
        panel.add(levelComboBox);
        panel.add(startButton);

        add(panel);
    }
}