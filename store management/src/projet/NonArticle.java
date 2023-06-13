package projet;

public class NonArticle extends Exception {
	public NonArticle() {
		super("ce n'est pas un article de notre magasin");
	}

}
