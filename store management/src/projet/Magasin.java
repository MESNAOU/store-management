package projet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Magasin {
	Set <Article_electromenager> produits_electromenagers;
	Set <Primeur> produits_primeurs;
	
	public Magasin(Set<Article_electromenager> p_e,Set<Primeur> p_p) {
		this.produits_electromenagers=p_e;
		this.produits_primeurs=p_p;
	}
	
	public void ajouterProduit(Article e) {
		boolean ajout1=false;
	    boolean ajout2=false;
		if(e instanceof Article_electromenager) {
			ajout1=this.produits_electromenagers.add((Article_electromenager) e);
		}
		else if(e instanceof Primeur) {
			ajout2=this.produits_primeurs.add((Primeur) e);
		}
		try {
			if(!(ajout1 || ajout2)) {
				throw new produitExistant();
			}
		}
		catch(produitExistant e1){
			System.out.println(e1);
		}
	}
	
	public Article recherche_produit(String nom) throws produitInexistant{
		Iterator<Article_electromenager> it_e=produits_electromenagers.iterator();
		Iterator<Primeur> it_p=produits_primeurs.iterator();
		while(it_e.hasNext()) {
			if(it_e.next().getNom().equals(nom)) {
				return (Article) it_e.next();
			}
		}
		while(it_p.hasNext()) {
			if(it_p.next().getNom().equals(nom)) {
				return (Article)it_p.next();
			}
		}
		throw new produitInexistant();
	}
	public <T> List triParStock(List<T> l) {
		c comparateur=new c();
		try {
			l.sort(comparateur);
			throw new NonArticle();
		}
		catch(NonArticle e) {
			System.out.println(e);
		}
		return l;
	}
	
	public void ecrireArticle(Article a,File f) throws IOException {
		f.getParentFile().mkdirs();
		FileOutputStream out=new FileOutputStream(f);
		ObjectOutputStream out_ob=new ObjectOutputStream(out);
		out_ob.writeObject(a);
		out_ob.close();
		System.out.println("cet article est enregistré dans "+f.getAbsolutePath());
	}
}
