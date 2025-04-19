package entity;


// Responsabilité : Définir les directions possibles pour le déplacement des éléments mobiles

public enum Direction {
    HAUT (-1, 0),
    BAS (1, 0),
    DROITE (0, 1),
    GAUCHE (0, -1);

	private final int dLigne;
	private final int dColonne;
	
	Direction(int dLigne, int dColonne) {
		this.dLigne = dLigne;
		this.dColonne = dColonne;
	}
	
	public int getDLigne() {
		return this.dLigne;
	}
	
	public int getDColonne() {
		return this.dColonne;
	}
}
