package control;


import entity.Direction;
import entity.Grille;
import entity.Joueur;
import entity.Caisse;
import entity.Case;

// Responsabilité: Gérer les interactions entre la vue et le modèle

public class Controleur implements IControleur {
    
    public Grille grille;
    private Joueur joueur;
	private int lignes;
	private int colonnes;
    private static final int[][] LEVEL_1 = {
			{1, 1, 1, 1, 1, 1},
			{1, 0, 0, 0, 0, 1},
			{1, 0, 2, 3, 4, 1},
			{1, 0, 0, 0, 0, 1},
			{1, 1, 1, 1, 1, 1}
		};

    private static final int[][] LEVEL_2 = {
		{1, 1, 1, 1, 1, 1, 1},
		{1, 0, 0, 0, 4, 0, 1},
		{1, 0, 3, 1, 0, 0, 1},
		{1, 0, 2, 0, 3, 4, 1},
		{1, 1, 1, 1, 1, 1, 1}
	};
	private static final int[][] LEVEL_3 = {
		{1, 1, 1, 1, 1, 1, 1, 1},
		{1, 0, 0, 0, 0, 0, 0, 1},
		{1, 0, 3, 1, 3, 0, 0, 1},
		{1, 0, 2, 0, 0, 1, 4, 1},
		{1, 4, 1, 3, 0, 0, 4, 1},
		{1, 0, 0, 0, 0, 0, 0, 1},
		{1, 1, 1, 1, 1, 1, 1, 1}
	};
	private static final int[][] LEVEL_4 = {
		{1, 1, 1, 1, 1, 1, 1},
		{1, 0, 1, 1, 1, 0, 1},
		{1, 0, 4, 3, 0, 0, 1},
		{1, 2, 3, 4, 0, 0, 1},
		{1, 0, 4, 3, 0, 0, 1},
		{1, 0, 1, 1, 1, 0, 1},
		{1, 1, 1, 1, 1, 1, 1}
	};

    public Controleur(int[][] configuration) {
		int nbCibles = 0;
        for (int[] row : configuration) {
            for (int cell : row) {
                if (cell == 4) {
                    nbCibles++;
                }
            }
        }
		lignes = configuration.length;
		colonnes = configuration[0].length;
        grille = new Grille(configuration.length, configuration[0].length, nbCibles, configuration);

        // identifier le joueur dans la grille
        for (int l = 0; l < grille.getNbLignes(); l++) {
            for (int c = 0; c < grille.getNbColonnes(); c++) {
                Case currentCase = grille.getCase(l, c);
                if (currentCase instanceof Joueur) {
                    joueur = (Joueur) currentCase;
                    return;
                }
            }
        }
        throw new IllegalStateException("Aucun joueur trouvé dans la grille");
    }

    public void action(Direction direction) {
        if (joueur == null) {
            throw new IllegalStateException("Joueur non initialisé");
        }

        joueur.deplacer(direction, grille);
    }

	public static int[][] getLevel(int level) {
        switch (level) {
			case 1:
				System.out.println("Niveau 1");
                return LEVEL_1;
            case 2:
				System.out.println("Niveau 2");
                return LEVEL_2;
			case 3:
				System.out.println("Niveau 3");
				return LEVEL_3;
			case 4:
				System.out.println("Niveau 4");
				return LEVEL_4;
            default:
                return null;
        }
    }

	@Override
	public boolean jeuTerminé() {
		int caissesSurCibles = 0;
		for (int l = 0; l<getNbLignes(); ++l) {
			for (int c = 0; c<getNbColonnes(); ++c) {
				Case courante = grille.getCase(l,c);
				if (courante instanceof Caisse) {
					if (!((Caisse)courante).surCible()) return false;
					caissesSurCibles ++;
				}
			}
		}
		// le jeu est terminé lorsque toutes les caisses sont posées sur les cibles
		return caissesSurCibles == grille.nbCibles;
	}

	@Override
	public int getNbLignes() {
		return lignes;
	}

	@Override
	public int getNbColonnes() {
		return colonnes;
	}

	@Override
	public Case getContenu(int l, int c) {
		return grille.getCase(l, c);
	}



}



