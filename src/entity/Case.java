package entity;

// Responsabilité: représenter les caractéristiques d'une case de la grille

public abstract class Case {
    public int ligne;
    public int colonne;
    public Grille saGrille;
    
    public Case (int ligne, int colonne) {
    	this.ligne = ligne;
    	this.colonne = colonne;
    }
    
    public int getLigne() {
    	return this.ligne;
    }
    public int getColonne() {
    	return this.colonne;
    }
    public void setPosition (int ligne, int colonne) {
    	this.ligne = ligne;
    	this.colonne = colonne;
    }
    public abstract boolean estMobile();
    public abstract boolean estMur();
    public abstract boolean estCible();

}
