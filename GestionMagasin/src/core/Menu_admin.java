package core;


import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import projet.Data;

public class Menu_admin {
	

	public Menu_admin()  {
		initMenu();}
	
	
	public void initMenu()  {
		
    	StackPane sp =new StackPane();
		Rectangle rec=new Rectangle(800,600,Color.GOLD);
		
		VBox v = new VBox();
		v.setSpacing(10);
		Label lb1=new Label("Bienvenue ! ");
		lb1.setFont(Font.font("Normal",FontWeight.BOLD,70));
		lb1.setTextFill(Color.WHITE);
		
		Data dt=new Data();
		Label lb_c=new Label("Votre chiffre d'affaires a atteint : "+dt.get_chiffre()+" dhs ");
		lb_c.setFont(Font.font("Normal",FontWeight.MEDIUM,FontPosture.ITALIC,17));
		lb_c.setTextFill(Color.BLUE);
		lb_c.setStyle("-fx-effect: dropshadow(one-pass-box, black, 0.5, 0.5, 0, 0)");
	    
        
		HBox h_c=new HBox(lb_c);
		h_c.setAlignment(Pos.TOP_RIGHT);
		lb_c.setTranslateX(-100); 
		
		Button bt1 = new Button ("Ajouter un article");
		bt1.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt1.setTextFill(Color.GOLD);
		bt1.setPrefWidth(400);
		bt1.setMinHeight(50);
		bt1.setStyle("-fx-background-color: #ffffff; ");
		
		Button bt2 = new Button ("Remplir le stock d'un article");
		bt2.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt2.setTextFill(Color.GOLD);
		bt2.setPrefWidth(400);
		bt2.setMinHeight(50);
		bt2.setStyle("-fx-background-color: #ffffff; ");
		
		Button bt3 = new Button ("Rechercher un article");
		bt3.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt3.setTextFill(Color.GOLD);
		bt3.setMinWidth(400);
		bt3.setMinHeight(50);
		bt3.setStyle("-fx-background-color: #ffffff; ");
		
		Button bt4 = new Button ("Trier les articles electromenagers par stocks");
		bt4.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt4.setTextFill(Color.GOLD);
		bt4.setMinWidth(400);
		bt4.setMinHeight(50);
		bt4.setStyle("-fx-background-color: #ffffff; ");
		
		Button bt5 = new Button ("Trier les articles primeurs par stocks");
		bt5.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt5.setTextFill(Color.GOLD);
		bt5.setMinWidth(400);
		bt5.setMinHeight(50);
		bt5.setStyle("-fx-background-color: #ffffff; ");
		
		Button bt6 = new Button ("Lancer un solde");
		bt6.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt6.setTextFill(Color.GOLD);
		bt6.setMinWidth(400);
		bt6.setMinHeight(50);
		bt6.setStyle("-fx-background-color: #ffffff; ");
		
		Button bt7 = new Button ("Terminer un solde");
		bt7.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt7.setTextFill(Color.GOLD);
		bt7.setMinWidth(400);
		bt7.setMinHeight(50);
		bt7.setStyle("-fx-background-color: #ffffff; ");
		
		EventHandler<MouseEvent> hd1 =new ajouterButton();
		bt1.setOnMouseClicked(hd1);
		
		EventHandler<MouseEvent> hd2 =new remplirStockButton();
		bt2.setOnMouseClicked(hd2);
		
		EventHandler<MouseEvent> hd3 =new searchButton();
		bt3.setOnMouseClicked(hd3);
		
		EventHandler<MouseEvent> hd4 =new affButton("E");
		bt4.setOnMouseClicked(hd4);
		
		EventHandler<MouseEvent> hd5 =new affButton("P");
		bt5.setOnMouseClicked(hd5);
		
		EventHandler<MouseEvent> hd6 =new soldeButton("start");
		bt6.setOnMouseClicked(hd6);
		
		EventHandler<MouseEvent> hd7 =new soldeButton("end");
		bt7.setOnMouseClicked(hd7);
		
		v.getChildren().addAll(lb1 ,h_c, bt1, bt2, bt3, bt4, bt5, bt6, bt7) ;
		v.setAlignment(Pos.CENTER);
		sp.getChildren().addAll(rec,v);
		
		
		
        Scene scene = new Scene(sp, 900, 680);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gestion De Magasin");
        stage.show();
		
	}
	class ajouterButton implements EventHandler<MouseEvent>{

    	@Override
    	public void handle(MouseEvent even) {
    	      try {
				new AddArticle();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		
    	}
	}
	
    class remplirStockButton implements EventHandler<MouseEvent>{
    	@Override
        public void handle(MouseEvent even) {
    		try {
    			new Remplir_stock();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }	
    }

    class searchButton implements EventHandler<MouseEvent>{
    	@Override
        public void handle(MouseEvent even) {
    		try {
    			new searchArticle();
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }	
    }
    class affButton implements EventHandler<MouseEvent>{
    	String s;
    	
    	public affButton(String sg) {
    		this.s=sg;
    	}
    	@Override
        public void handle(MouseEvent even) {
    		try {
    			Data d=new Data();
    			new ShowArticle(d.tri(this.s),0);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }	
    }
    class soldeButton implements EventHandler<MouseEvent>{
    	String s;
    	
    	public soldeButton(String sg) {
    		this.s=sg;
    	}
    	
    	@Override
        public void handle(MouseEvent even) {
    		try {
    			new lancerSolde(this.s);
    		} catch (Exception e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
        }	
    }
}
