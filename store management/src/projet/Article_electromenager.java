package projet;

import java.io.Serializable;

public class Article_electromenager extends Article implements Vendable_par_piece,vendu_en_solde,Serializable{
	private int stock;
	
	public Article_electromenager(double p_a,double p_v,String n,String n_f) {
		super(p_a,p_v,n,n_f);
		this.stock=0;
	}

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public void remplir_stock(int s) {
		this.setStock(this.getStock()+s);
	}
	@Override
	public String toString() {
		return super.toString()+", stock:"+this.getStock()+"pieces.";
	}
	
	public double vendre(int q) throws ExceptionStock {
		if(q>this.stock) {
			throw new ExceptionStock();
		}
		this.stock-=q;
		return q*this.getPrix_vente();
	}

	@Override
	public void lancer_solde(double pourcentage) {
		// TODO Auto-generated method stub
		this.setPrix_vente(this.getPrix_vente()*(1-pourcentage/100));
		
	}

	@Override
	public void terminer_solde(double pourcentage) {
		// TODO Auto-generated method stub
		this.setPrix_vente(this.getPrix_vente()*(1+pourcentage/100));
		
	}
}
