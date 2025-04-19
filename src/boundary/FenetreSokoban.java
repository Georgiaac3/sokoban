package boundary;

import control.Controleur;
import control.IControleur;
import entity.Direction;
// import boundary.PanneauSokoban;
import entity.Joueur;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class FenetreSokoban extends JFrame implements KeyListener {
    static final int TAILLE_IMAGE = 32;
    private static final int LARGEUR_FENETRE = 20 * TAILLE_IMAGE;
    private static final int HAUTEUR_FENETRE = 12 * TAILLE_IMAGE;
    private static final int HAUTEUR_TITRE_FENETRE = 20;
    private IControleur controleur;
    private PanneauSokoban panneauSokoban;
    private int currentLevel;

    public FenetreSokoban(IControleur controleur, int level) {
        this.controleur = controleur;
        this.currentLevel = level;

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setPreferredSize(new Dimension(LARGEUR_FENETRE, HAUTEUR_FENETRE + HAUTEUR_TITRE_FENETRE));
        this.setTitle("Sokoban de LG");

        panneauSokoban = new PanneauSokoban(controleur);
        this.add(panneauSokoban, BorderLayout.CENTER);

        // bouton pour réinitialiser

        JButton resetButton = new JButton("Réinitialiser");
        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetGrille();
            }
        });
        this.add(resetButton, BorderLayout.SOUTH);

        this.addKeyListener(this);
        this.setFocusable(true);
        this.requestFocusInWindow();

        this.pack();
        this.setVisible(true);
    }

    private void resetGrille() {
        controleur = new Controleur(Controleur.getLevel(currentLevel));
        panneauSokoban.setControleur(controleur);
        // reinitialisation de la variable qui gère les cibles bougées par les mobiles
        Joueur.cibleBougee = 0;
        panneauSokoban.repaint();
        this.requestFocusInWindow();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        // nothing
    }

    @Override
    public void keyPressed(KeyEvent e) {
        Direction direction = null;
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                direction = Direction.HAUT;
                break;
            case KeyEvent.VK_DOWN:
                direction = Direction.BAS;
                break;
            case KeyEvent.VK_LEFT:
                direction = Direction.GAUCHE;
                break;
            case KeyEvent.VK_RIGHT:
                direction = Direction.DROITE;
                break;
        }
        if (direction == null) return;
        controleur.action(direction);
        System.out.println("Key pressed : " + direction);
        panneauSokoban.repaint();
        if (controleur.jeuTerminé()) {
            JOptionPane.showMessageDialog(this, "Vous avez gagné !");
            currentLevel++;
            int[][] nextLevelConfig = Controleur.getLevel(currentLevel);
            if (nextLevelConfig != null) {
                this.dispose();
                new FenetreSokoban(new Controleur(nextLevelConfig), currentLevel);
            } else {
                JOptionPane.showMessageDialog(this, "Félicitations ! Vous avez terminé tous les niveaux.");
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // nothing
    }
}