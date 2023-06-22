package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import projet.Article;
import projet.Data;



public class searchArticle  {
	
	public searchArticle() throws Exception   {
		initsearch();
	}

	
	public void initsearch() throws Exception {
		Group g = new Group();
    	StackPane sp =new StackPane();
		Rectangle rec=new Rectangle(700,70,Color.GOLD);
		
		Label lb=new Label("Rechercher un article : ");
		lb.setFont(Font.font("Normal",FontWeight.BOLD,50));
		lb.setTextFill(Color.WHITE);
		sp.getChildren().addAll(rec ,lb) ;
		
		Label err=new Label();
		err.setFont(Font.font("Normal",15));
		err.setTextFill(Color.RED);
		HBox er=new HBox(err);
		er.setAlignment(Pos.CENTER);
		Label lb2=new Label("Nom :");
		TextField tf2 = new TextField();
		
		Image image = new Image(new FileInputStream("download.png"));  
	      ImageView imageView = new ImageView(image); 
	      imageView.setX(0); 
	      imageView.setY(90); 
	      imageView.setFitHeight(530); 
	      imageView.setFitWidth(270); 
	      imageView.setPreserveRatio(true);
		
		Button bt1 = new Button ("Rechercher");
		bt1.setMinWidth(100);
		bt1.setMinHeight(20);
		bt1.setStyle("-fx-background-color: #9dc319; ");
		
		Button bt2 = new Button ("Annuler");
		bt2.setStyle("-fx-background-color: #9dc319; ");
		bt2.setMinWidth(100);
		bt2.setMinHeight(20);
		bt2.setOnMouseClicked((MouseEvent e)->{
			new Menu_admin();
		});
		HBox h = new HBox(bt1 , bt2  );
		h.setAlignment(Pos.CENTER);
		h.setPadding(new Insets(10, 10, 10, 10));
		h.setSpacing(40.0);
		
		VBox v = new VBox();
		v.setPadding(new Insets(30, 25, 25, 25));
		v.setSpacing(13.0);
		v.getChildren().addAll(er,lb2,tf2, h);
		v.setLayoutY(100);
		v.setLayoutX(300);
		
		g.getChildren().addAll(sp,v,imageView) ;
		
		bt1.setOnMouseClicked((MouseEvent even)-> {
			err.setText("");
			String n=""+tf2.getText();
			try {
				Data d=new Data();
				List<Article> a=d.search(n);
				new ShowArticle(a,0);
				}catch(Exception e) {
					err.setText("Produit Non Existant");
				}
		});
		
        Scene scene = new Scene(g, 700, 600);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setTitle("Gestion De Magasin");
        stage.show();
		
	}

}

