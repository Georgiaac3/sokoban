package entity;

// Responsabilité: représenter les caractéristiques d'une case mobile de la grille

public abstract class Mobile extends Case{
	protected boolean mur = false;
	// public static int cibleBougee = 0;

	public Mobile (int ligne, int colonne) {
		super(ligne, colonne);
	}
	
	@Override
	public boolean estMobile() {
		return true ;
	}
	@Override
	public boolean estMur() {
		return mur;
	}
	@Override
	public boolean estCible() {
		return false;
	}
	
	
	protected abstract boolean deplacer(Direction direction, Grille grille);

}
