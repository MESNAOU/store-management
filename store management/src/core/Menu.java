package core;


import java.io.FileNotFoundException;

import core.Main.login;
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

public class Menu {
	

	public Menu()  {
		initMenu();}
	
	
	public void initMenu()  {
		
    	StackPane sp =new StackPane();
		Rectangle rec=new Rectangle(600,500,Color.GOLD);
		
		VBox v = new VBox();
		Label lb1=new Label("Bienvenue ! ");
		lb1.setFont(Font.font("Normal",FontWeight.BOLD,70));
		lb1.setTextFill(Color.WHITE);
		Button bt1 = new Button ("Ajouter un article");
		bt1.setMinWidth(80);
		bt1.setMinHeight(50);
		bt1.setStyle("-fx-background-color: #ffffff; ");
		
		EventHandler<MouseEvent> hd1 =new ajouterButton();
		bt1.setOnMouseClicked(hd1);
		
		Button bt2 = new Button ("Supprimer un article");
		bt2.setStyle("-fx-background-color: #ffffff; ");
		bt2.setMinWidth(80);
		bt2.setMinHeight(50);
		
		EventHandler<MouseEvent> hd2 =new supprimerButton();
		bt2.setOnMouseClicked(hd2);
		
		HBox h = new HBox(bt1 , bt2  );
		h.setAlignment(Pos.CENTER);
		h.setPadding(new Insets(10, 10, 10, 10));
		h.setSpacing(60.0);
		h.setLayoutX(100);
		
		v.getChildren().addAll(lb1 , h) ;
		v.setAlignment(Pos.CENTER);
		sp.getChildren().addAll(rec,v);
		
		
		
        Scene scene = new Scene(sp, 700, 600);
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
    	class supprimerButton implements EventHandler<MouseEvent>{

        	@Override
        	public void handle(MouseEvent even) {
        	      try {
					new DeleteArticle();
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		
        	}	
    	}

}
