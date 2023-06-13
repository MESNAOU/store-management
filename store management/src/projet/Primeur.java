package projet;

import java.io.Serializable;

public class Primeur extends Article implements Vendable_par_kilogramme,Serializable{
	private double stock;
	
	public Primeur(double p_a,double p_v,String n,String n_f) {
		super(p_a,p_v,n,n_f);
		this.stock=0;
	}

	public double getStock() {
		return stock;
	}

	public void setStock(double stock) {
		this.stock = stock;
	}
	
	public void remplir_stock(double s) {
		this.setStock(this.getStock()+s);
	}
	
	@Override
	public String toString() {
		return super.toString()+", stock:"+this.getStock()+"Kg.";
	}
	public double vendre(double q) throws ExceptionStock {
		if(q>this.stock) {
			throw new ExceptionStock();
		}
		this.stock-=q;
		return q*this.getPrix_vente();
	}
	
}
