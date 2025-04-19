package entity;

// Responsabilité: Bouger les caisses et les placer sur les cibles

public class Caisse extends Mobile {
	public boolean onGoal;
	public Caisse(int ligne, int colonne, boolean onGoal) {
		super(ligne, colonne);
		this.onGoal = onGoal;
	}
	public boolean surCible() {
		return this.onGoal;
	}

		
    public boolean deplacer(Direction direction, Grille grille) {
        int dLigne = direction.getDLigne();
        int dColonne = direction.getDColonne();
        
        int nouvelleLigne = getLigne() + dLigne;
        int nouvelleColonne = getColonne() + dColonne;

        Case destination = grille.getCase(nouvelleLigne, nouvelleColonne);
        // on distingue 3 cas pour le déplacement : 
        // destination est un mur, une caisse (récursif), un sol (ou une cible)

        // destination : mur
        if (destination instanceof Immobile && ((Immobile) destination).estMur()) {
            return false;
        }

        // destination : caisse
        if (destination instanceof Caisse) {
            // On demande a la caisse de se déplacer
            if(peutDeplacer(nouvelleLigne, nouvelleColonne, direction, grille)){
                System.out.println("on peut deplacer");
                if (((Caisse) destination).surCible()) {
                    // La caisse 2 etait sur une cible, la caisse 1 prend sa place
                    this.onGoal = true;
                    Joueur.cibleBougee = 0;
                    System.out.println("hop on remet a 0");
                }
                else if (this.onGoal) {
                    // La caisse n'est plus sur une cible, donc le joueur est sur une cible
                    this.onGoal = false;
                    Joueur.cibleBougee ++;
                    System.out.println("cible bougee cote caisse = " + Joueur.cibleBougee);
                }
                ((Caisse) destination).deplacer(direction, grille);
                // Si la caisse destination a pu se déplacer, on peut déplacer la caisse actuelle
                grille.setCase(getLigne(), getColonne(), new Immobile(getLigne(), getColonne(), false, false));
                grille.setCase(nouvelleLigne, nouvelleColonne, this);
                setPosition(nouvelleLigne, nouvelleColonne);
                return true;
            }
            System.out.println("on peut pas se deplacer");
            return false;
        }
        
        // destination : cible ou sol -> on peut se déplacer
        if (destination.estCible()) {
            // La caisse est sur une cible
            if (this.onGoal){
                Joueur.cibleBougee ++;
                System.out.println("cible bougee cote caisse " + Joueur.cibleBougee);     
            }
            this.onGoal = true;
             
        } else if (this.onGoal) {
            // La caisse n'est plus sur une cible, donc le joueur est dessus (ou une autre caisse)
            this.onGoal = false;
            Joueur.cibleBougee ++;
            System.out.println("cible bougee cote caisse " + Joueur.cibleBougee);
        }

        grille.setCase(getLigne(), getColonne(), new Immobile(getLigne(), getColonne(), false, false));
        grille.setCase(nouvelleLigne, nouvelleColonne, this);

        setPosition(nouvelleLigne, nouvelleColonne);
        return true;
    }

    // pour les déplacements multi caisses
    public boolean peutDeplacer(int nouvelleLigne, int nouvelleColonne, Direction direction, Grille grille) {
        int dLigne = direction.getDLigne();
        int dColonne = direction.getDColonne();
        
        int nouvelleLigne2 = nouvelleLigne + dLigne;
        int nouvelleColonne2 = nouvelleColonne + dColonne;

        Case destination2 = grille.getCase(nouvelleLigne2, nouvelleColonne2);
        System.out.println("caracteristiques" + destination2.getClass());
        return !(destination2.estMur());
    }
}