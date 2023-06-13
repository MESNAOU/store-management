package projet;

public class produitInexistant extends Exception {
	public produitInexistant() {
		super("cet article n'existe pas dans le stock");
	}

}
