package projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Data {
	
	public Data() {
	}

	public Connection connect() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/magasin";
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,"root","Database");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problème de connexion");
		}
		return conn;
	}
	
	public boolean ajout(Article a,String type){
		try {
			Connection conn = this.connect();
			String req="insert into article (type,Nom,fournisseur,prix_achat,prix_vente,stock) values(?,?,?,?,?,?)";
			double s=0;
			PreparedStatement pst = conn.prepareStatement(req);
			if(type.equals("Article Electroménager")) {
				s=((Article_electromenager) a).getStock();
			}else if(type.equals("Primeur")){
				s=((Primeur) a).getStock();
			}
			pst.setString(1, type);
			pst.setString(2, a.getNom());
			pst.setString(3, a.getNom_fournisseur());
			pst.setDouble(4, a.getPrix_achat());
			pst.setDouble(5,a.getPrix_vente());
			pst.setDouble(6, s);
			pst.executeUpdate();
			return true;
		} catch (Exception e) {
			return false;
		}	
	}
	
	public boolean rempl(String type,String n,String f,double pa,double s){
		try {
			Connection conn=this.connect();
			Statement st=conn.createStatement();
			String req="select * from article where type='"+type+"' and Nom='"+n+"' and fournisseur='"+f+"' and prix_achat="+pa;
			ResultSet result_articles=st.executeQuery(req);
			int cpt=0;
			double stock=0;
			while(result_articles.next()) {
				cpt++;
				switch(type) {
				case "Article Electroménager":
					stock=(double) result_articles.getInt("stock");
					break;
				case "Primeur":
					stock=result_articles.getDouble("stock");
					break;
				}
			}
			if(cpt==0) {
				return false;
			}
			stock+=s;
			if(stock>=0) {
				String req_upd="update article set stock="+stock+"where type='"+type+"' and Nom='"+n+"' and fournisseur='"+f+"' and prix_achat="+pa;
				int nb=st.executeUpdate(req_upd);
				if(nb==0) {
					return false;
				}
			}else return false;
			
			return true;
		}catch(Exception e) {
			return false;
		}	
	}
	
	public boolean log(String uid,String psswd, String role) {
		Connection conn=null;
		String psswdBase=null;
		String roleBase=null;
		ResultSet rset=null;
		try {
			conn = this.connect();
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			System.out.println("probleme de connexion");
			return false;
		}
		try {
			Statement st = conn.createStatement();
			String req="select * from user where uid='"+uid+"'";
			rset=st.executeQuery(req);
			
			while(rset.next()) {
				psswdBase=rset.getString(2);
				roleBase=rset.getString(3);
			}
		}catch(Exception e) {
			return false;
		}
		if(psswd.equals(psswdBase) && role.equals(roleBase)) {
			return true;
		}else {
			return false;
		}
	}

	public Magasin creerMagasin() {
		try {
			Connection conn=this.connect();
			Statement st=conn.createStatement();
			String req="select * from article";
			ResultSet result_articles=st.executeQuery(req);
			Set<Article_electromenager> set_a_e=new HashSet<>();
			Set<Primeur> set_p=new HashSet<>();
			Article_electromenager a_e=null;
			Primeur p= null;
			int c=0;
			while(result_articles.next()) {
				c++;
				switch(result_articles.getString("type")) {
				case "Article Electroménager":
					a_e= new Article_electromenager(result_articles.getDouble("prix_achat"), result_articles.getDouble("prix_vente"), result_articles.getString("Nom"), result_articles.getString("fournisseur"));
					a_e.setStock((int)result_articles.getDouble("stock"));
					set_a_e.add(a_e);
					break;
				case "Primeur":
					p= new Primeur(result_articles.getDouble("prix_achat"), result_articles.getDouble("prix_vente"), result_articles.getString("Nom"), result_articles.getString("fournisseur"));
					p.setStock(result_articles.getDouble("stock"));
					set_p.add(p);
					break;
				}
			}
			String sel_c_aff="select distinct chiffre_aff from user where role='Admin'";
			ResultSet RSc=st.executeQuery(sel_c_aff);
			double ch=0;
			while(RSc.next()) {
				ch= RSc.getDouble("chiffre_aff");
			}
			Magasin m=new Magasin(set_a_e,set_p,ch);
			return m;
		}catch(Exception e) {
			return null;
		}
	}
	
	public List<Article> search(String n) throws produitInexistant{
		Magasin m=this.creerMagasin();
		return m.recherche_produit(n);
	}
	public List<Article> tri(String cl) throws produitInexistant{
		Magasin m=this.creerMagasin();
		Set s=new HashSet();
		if(cl.equals("E")) s.addAll(m.produits_electromenagers);
		else s.addAll(m.produits_primeurs);
		List l=new ArrayList(s);
		return m.triParStock(l);
	}
	public boolean solde(String n,String f,double pa,double t,String trs){
		try {
			Connection conn=this.connect();
			Statement st=conn.createStatement();
			String req="select * from article where type='Article Electroménager' and Nom='"+n+"' and fournisseur='"+f+"' and prix_achat="+pa;
			ResultSet result_articles=st.executeQuery(req);
			int cpt=0;
			double pv=0;
			while(result_articles.next()) {
				cpt++;
				pv= result_articles.getDouble("prix_vente");
			}
			if(cpt==0) {
				return false;
			}
			switch(trs) {
			case "start":
				System.out.println(pv);
				System.out.println(t);
				pv=pv*(1-t/100);
				System.out.println(pv);
				break;
			case "end":
				System.out.println(pv);
				pv=pv*(1+t/100);
				System.out.println(pv);
				break;
			}
			String req_upd="update article set prix_vente="+pv+"where type='Article Electroménager' and Nom='"+n+"' and fournisseur='"+f+"' and prix_achat="+pa;
			int nb=st.executeUpdate(req_upd);
			if(nb==0) {
				return false;
			}
			return true;
		}catch(Exception e) {
			return false;
		}	
	}
	public double get_set_pv(String n,String f,double pa,double s){
		try {
			double pv=0;
			double c=this.get_chiffre();
			Connection conn=this.connect();
			Statement st=conn.createStatement();
			String req="select * from article where type='Article Electroménager' and Nom='"+n+"' and fournisseur='"+f+"' and prix_achat="+pa;
			ResultSet result_articles=st.executeQuery(req);
			
			while(result_articles.next()) {
				pv= result_articles.getDouble("prix_vente");
			}
			
			pv=pv*s;
			c+=pv;
			
			String req_upd_c="update user set chiffre_aff="+c+"where role='Admin'";
			int nb=st.executeUpdate(req_upd_c);
			
			return pv;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
	
	public double get_chiffre(){
		try {
			double c=0;
			Connection conn=this.connect();
			Statement st=conn.createStatement();
			
			String req_c="select distinct chiffre_aff from user where role='Admin'";
			ResultSet result_c=st.executeQuery(req_c);
			while(result_c.next()) {
				c= result_c.getDouble("chiffre_aff");
			}
			
			return c;
		}catch(Exception e) {
			e.printStackTrace();
		}
		return 0;
		
	}
}
