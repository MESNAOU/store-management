package projet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Data {
	
	public Data() {
	}

	public Connection connect() throws ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String url="jdbc:mysql://localhost:3306/magasin";
		Connection conn=null;
		try {
			conn=DriverManager.getConnection(url,"root","jajadsq15987$$%MySQL");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("Problème de connexion");
		}
		return conn;
	}
	
	public boolean ajout(Article a,String type){
		try {
			Connection conn = this.connect();
			String req="insert into article values(?,?,?,?,?,?)";
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
	
	public boolean supprim(Article a,String type){
		try {
			Connection conn=this.connect();
			String req="delete from article where type=? and Nom=?and fournisseur=? and prix_achat=? and prix_vente=? and stock=?";
			double s=0;
			PreparedStatement pst=conn.prepareStatement(req);
			if(type.equals("Article Electroménager")) {
				s=((Article_electromenager) a).getStock();
			}else if(type.equals("Primeur")){
				s=((Primeur) a).getStock();
			}
			pst.setString(1, type);
			pst.setString(2, a.getNom());
			pst.setString(3, a.getNom_fournisseur());
			pst.setDouble(4, a.getPrix_achat());
			pst.setDouble(5, a.getPrix_vente());
			pst.setDouble(6, s);
			pst.executeUpdate();
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
			String req="select * from user where uid="+uid;
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
}
