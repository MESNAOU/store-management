package projet;

import java.io.Serializable;
import java.util.Objects;

public class Article implements Serializable{
	private double prix_achat;
	private double prix_vente;
	private String nom;
	private String nom_fournisseur;
	
	public Article(){
	}
	public Article(double p_a,double p_v,String n,String n_f) {
		this.prix_achat=p_a;
		this.prix_vente=p_v;
		this.nom=n;
		this.nom_fournisseur=n_f;
	}
	
	public double getPrix_achat() {
		return prix_achat;
	}
	public void setPrix_achat(double prix_achat) {
		this.prix_achat = prix_achat;
	}
	public double getPrix_vente() {
		return prix_vente;
	}
	public void setPrix_vente(double prix_vente) {
		this.prix_vente = prix_vente;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String n) {
		this.nom = n;
	}
	public String getNom_fournisseur() {
		return nom_fournisseur;
	}
	public void setNom_fournisseur(String nom_fournisseur) {
		this.nom_fournisseur = nom_fournisseur;
	}
	public double calculer_taux_rendement() {
		return ((this.prix_vente-this.prix_achat)/this.prix_achat)*100;
	}
	public String toString() {
		return "le prix d'achat:"+this.prix_achat+"'le prix de vente:"+this.prix_vente+",nom:"+this.nom+", nom du fournisseur:"+this.nom_fournisseur+",le rendement:"+this.calculer_taux_rendement();
	}
	@Override
	public int hashCode() {
		return Objects.hash(nom, nom_fournisseur, prix_achat, prix_vente);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Article other = (Article) obj;
		return Objects.equals(nom, other.nom) && Objects.equals(nom_fournisseur, other.nom_fournisseur)
				&& Double.doubleToLongBits(prix_achat) == Double.doubleToLongBits(other.prix_achat)
				&& Double.doubleToLongBits(prix_vente) == Double.doubleToLongBits(other.prix_vente);
	}
	
	
	

}
