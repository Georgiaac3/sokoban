package entity;

import java.util.ArrayList;
import java.util.List;

// Responsabilité: Crée une grille et gère les cases

public class Grille{
	private List<Case> cases = new ArrayList<>();
    private int nbLignes;
    private int nbColonnes;
	public int nbCibles;

    public Grille(int nbLignes, int nbColonnes, int nbCibles, int[][] configuration) {
        this.nbLignes = nbLignes;
        this.nbColonnes = nbColonnes;
        this.nbCibles = nbCibles;
        // Création de la grille
        for (int l = 0; l < nbLignes; l++) {
            for (int c = 0; c < nbColonnes; c++) {
                switch (configuration[l][c]) {
                    case 0: // Sol
                        cases.add(new Immobile(l, c, false, false));
                        break;
                    case 1: // Mur
                        cases.add(new Immobile(l, c, false, true));
                        break;
                    case 2: // Joueur
                        cases.add(new Joueur(l, c));
                        break;
                    case 3: // Caisse
                        cases.add(new Caisse(l, c, false));
                        break;
                    case 4: // Cible
                        cases.add(new Immobile(l, c, true, false));
                        break;
                    case 5: // Caisse sur cible
                        cases.add(new Caisse(l, c, true));
                        break;
                    default:
                        throw new IllegalArgumentException("Valeur invalide dans la configuration");
                }
            }
        }
    }

    public Case getCase(int ligne, int colonne) {
        int index = ligne * nbColonnes + colonne;
        return cases.get(index);
    }

    public void setCase(int ligne, int colonne, Case nouvelleCase) {
        int index = ligne * nbColonnes + colonne;
        cases.set(index, nouvelleCase);
    }

    public int getNbLignes() {
        return nbLignes;
    }

    public int getNbColonnes() {
        return nbColonnes;
    }
}