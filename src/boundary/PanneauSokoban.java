package boundary;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

// import control.Controleur;
import control.IControleur;
import entity.Caisse;
import entity.Case;
import entity.Immobile;
import entity.Joueur;

// Responsabilité : Afficher les images du jeu Sokoban

public class PanneauSokoban extends JPanel {

    private static final int IMAGE_SIZE = FenetreSokoban.TAILLE_IMAGE;

    private static Image goalImage;
    private static Image boxOnGoalImage;
    private static Image emptyFloorImage;
    private static Image wallImage;
    private static Image manImage;
    private static Image boxImage;

    private IControleur controleur;

    public PanneauSokoban(IControleur controleur) {
        this.controleur = controleur;
        try {
            // goalImage = ImageIO.read(new File("Sokoban/img/Goal.jpg"));
            // boxOnGoalImage = ImageIO.read(new File("Sokoban/img/BoxOnGoal.jpg"));
            // emptyFloorImage = ImageIO.read(new File("Sokoban/img/EmptyFloor.jpg"));
            // wallImage = ImageIO.read(new File("Sokoban/img/Wall.jpg"));
            // manImage = ImageIO.read(new File("Sokoban/img/Man.jpg"));
            // boxImage = ImageIO.read(new File("Sokoban/img/Box.jpg")); 
            goalImage = ImageIO.read(new File("./img/Goal.jpg"));
            boxOnGoalImage = ImageIO.read(new File("./img/BoxOnGoal.jpg"));
            emptyFloorImage = ImageIO.read(new File("./img/EmptyFloor.jpg"));
            wallImage = ImageIO.read(new File("./img/Wall.jpg"));
            manImage = ImageIO.read(new File("./img/Man.jpg"));
            boxImage = ImageIO.read(new File("./img/Box.jpg")); 
        } catch (IOException e) {
            System.out.println("Erreur lors de la lecture des images");
            e.printStackTrace();
        }
    }
    public void setControleur(IControleur controleur2) {
        this.controleur = controleur2;
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        for (int l = 0; l < controleur.getNbLignes(); ++l) {
            for (int c = 0; c < controleur.getNbColonnes(); ++c) {
                Case currentCase = controleur.getContenu(l, c);
              
                // Affichage des images

                // Sol
                if (currentCase instanceof Immobile && !((Immobile) currentCase).estMur()) {
                    g.drawImage(emptyFloorImage, c * IMAGE_SIZE, l * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null);
                }
                // Mur
                if (currentCase instanceof Immobile && ((Immobile) currentCase).estMur()) {
                    g.drawImage(wallImage, c * IMAGE_SIZE, l * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null);
                }
                // Cible
                if (currentCase instanceof Immobile && ((Immobile) currentCase).estCible()) {
                    g.drawImage(goalImage, c * IMAGE_SIZE, l * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null);
                }
                // Caisse sur cible
                if (currentCase instanceof Caisse && ((Caisse)currentCase).surCible()) {
                    g.drawImage(boxOnGoalImage, c * IMAGE_SIZE, l * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null);
                }
                // Joueur
                if (currentCase instanceof Joueur) {
                    g.drawImage(manImage, c * IMAGE_SIZE, l * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null);
                }
                // Caisse
                if (currentCase instanceof Caisse && ! ((Caisse)currentCase).surCible()) {
                    g.drawImage(boxImage, c * IMAGE_SIZE, l * IMAGE_SIZE, IMAGE_SIZE, IMAGE_SIZE, null);
                }
    
            }
        }
    }
}
