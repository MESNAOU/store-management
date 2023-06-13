package projet;

public class produitExistant extends Exception {
	public produitExistant() {
		super("le produit exist déjà,il faut plutôt remplir son stock");
	}

}
