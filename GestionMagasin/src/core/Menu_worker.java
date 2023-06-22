package core;


import java.io.FileNotFoundException;

import core.Menu_admin.affButton;
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
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import projet.Data;

public class Menu_worker {
	

	public Menu_worker()  {
		initMenu();}
	
	
	public void initMenu()  {
		
    	StackPane sp =new StackPane();
		Rectangle rec=new Rectangle(600,500,Color.GOLD);
		
		VBox v = new VBox();
		v.setSpacing(10);
		Label lb1=new Label("Bienvenue ! ");
		lb1.setFont(Font.font("Normal",FontWeight.BOLD,70));
		lb1.setTextFill(Color.WHITE);
		
		Button bt1 = but("Vendre un article");
		
		Button bt2 = but("Rechercher un article");
		
		Button bt3 = but("Trier les articles electromenagers par stocks");
		
		Button bt4 = but("Trier les articles primeurs par stocks");
		
		EventHandler<MouseEvent> hd1 =new sellButton();
		bt1.setOnMouseClicked(hd1);
		
		EventHandler<MouseEvent> hd2 =new searchButton();
		bt2.setOnMouseClicked(hd2);
		
		EventHandler<MouseEvent> hd3 =new affButton("E");
		bt3.setOnMouseClicked(hd3);
		
		EventHandler<MouseEvent> hd4 =new affButton("P");
		bt4.setOnMouseClicked(hd4);
		
		v.getChildren().addAll(lb1 , bt1, bt2, bt3,bt4) ;
		v.setAlignment(Pos.CENTER);
		sp.getChildren().addAll(rec,v);
		
		
		
        Scene scene = new Scene(sp, 700, 550);
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.setTitle("Gestion De Magasin");
        stage.show();
		
	}
	
	public Button but(String s) {
		Button bt = new Button (s);
		bt.setFont(Font.font("Normal",FontWeight.MEDIUM,20));
		bt.setTextFill(Color.GOLD);
		bt.setPrefWidth(450);
		bt.setMinHeight(50);
		bt.setStyle("-fx-background-color: #ffffff; ");
		return bt;
	}
	
	class sellButton implements EventHandler<MouseEvent>{

    	@Override
    	public void handle(MouseEvent even) {
    	      try {
				new sellArticle();
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
}

