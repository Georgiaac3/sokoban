package control;

import entity.Case;
import entity.Direction;

/**
 * Interface du controleur pour le jeu Sokoban 
 * 
 * @author Dominique Marcadet
 * @version 1.2
 *
 */


// Responsabilité : Intreface pour gérer les interactions entre la vue et le modèle
public interface IControleur {

	/**
	 * Méthode appellée par l'IHM quand je joueur appuie sur l'une des
	 * flèches du clavier.
	 * 
	 * @param direction direction de la progression demandée par le joueur
	 */
    void action( Direction direction );

	/**
	 * Méthode appellée par l'IHM pour savoir si le jeu est terminé.
	 * 
	 * @return true si le jeu est terminé, false sinon
	 */
    boolean jeuTerminé();

	/**
	 * Méthode appellée par l'IHM pour connaître le nombre de
	 * lignes de l'entrepot courant.
	 * 
	 * @return le nombre de lignes de l'entrepot
	 */
    int getNbLignes();

	/**
	 * Méthode appellée par l'IHM pour connaître le nombre de
	 * colonnes de l'entrepot courant.
	 * 
	 * @return le nombre de colonnes de l'entrepot
	 */
    int getNbColonnes();

	/**
	 * Méthode appellée par l'IHM pour connaître le contenu
	 * d'une case de l'entrepot courant.
	 * 
	 * @param ligne numéro de la ligne de la case (de 0 à getNbLines() - 1)
	 * @param colonne numéro de la colonne de la case (de 0 à getNbColumns() - 1)
	 * @return le type de la case
	 */
    Case getContenu( int ligne, int colonne );

}
