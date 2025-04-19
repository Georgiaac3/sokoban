package entity;

// Responsabilité: représenter les caractéristiques d'une case immobile de la grille

public class Immobile extends Case {
	private boolean estCible;
    private boolean estMur;

    public Immobile(int ligne, int colonne, boolean estCible, boolean estMur) {
        super(ligne, colonne);
        this.estCible = estCible;
        this.estMur = estMur;
    }

    public boolean estCible() {
        return estCible;
    }

    public boolean estMur() {
        return estMur;
    }

    @Override
    public boolean estMobile() {
        return false;
    }


}
