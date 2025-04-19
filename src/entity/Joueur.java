package entity;

// Responsabilité: déplacer le joueur et replacer les cibles bougées par ce dernier

public class Joueur extends Mobile {
	// si jamais le joueur se place sur la cible
	public static int cibleBougee = 0;
    
        public Joueur (int ligne, int colonne) {
            super(ligne, colonne);
        }

        public boolean deplacer(Direction direction, Grille grille) {
            int dLigne = direction.getDLigne();
            int dColonne = direction.getDColonne();
            
            int nouvelleLigne = getLigne() + dLigne;
            int nouvelleColonne = getColonne() + dColonne;
    
            Case destination = grille.getCase(nouvelleLigne, nouvelleColonne);

            // destination : mur
            if (destination.estMur()) {
                return false;
            }


            // destination : caisse
            else if (destination instanceof Caisse) {
                // il y a une caisse, on essaie de la déplacer
                boolean bouge = ((Caisse) destination).deplacer(direction, grille);
                if (bouge) {
                    // si le mobile a bougé, on se déplace
                    grille.setCase(getLigne(), getColonne(), new Immobile(getLigne(), getColonne(), cibleBougee >=2, false));
                    if (cibleBougee >=2) cibleBougee =0;
                    if (cibleBougee == 1) cibleBougee ++;
                    grille.setCase(nouvelleLigne, nouvelleColonne, this);
                    setPosition(nouvelleLigne, nouvelleColonne);
                }  
                return bouge;
            }
            
            
            // destination : cible ou sol
            else if (destination instanceof Immobile && ((Immobile) destination).estCible()) {
                // si la case est une cible, on avance et on met a jour le nombre de cibles bougees pour la repositionner apres
                cibleBougee ++;
                System.out.println("cibleBougee = " + cibleBougee);
            }


            if (cibleBougee >=2) { 
                // on pose la cible a sa place ou elle etait couverte
                grille.setCase(getLigne(), getColonne(), new Immobile(getLigne(), getColonne(), true, false));
                cibleBougee --;
    
        	    if (cibleBougee == 1) cibleBougee --;
                System.out.println("cibleBougee = " + cibleBougee);
            }

            else { // on pose un sol
                if(cibleBougee == 1) cibleBougee ++;
                System.out.println("cibleBougee = " + cibleBougee);
                grille.setCase(getLigne(), getColonne(), new Immobile(getLigne(), getColonne(), false, false));
            }
        grille.setCase(nouvelleLigne, nouvelleColonne, this);
    
        // Met à jour la position du joueur
        setPosition(nouvelleLigne, nouvelleColonne);
        return true;

     }
}