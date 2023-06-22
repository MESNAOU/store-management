package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;

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
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import projet.Article;
import projet.Article_electromenager;
import projet.Data;
import projet.Primeur;

public class sellArticle  {
	
	public sellArticle() throws Exception   {
		initfull();
	}

	
	public void initfull() throws Exception {
		Group g = new Group();
    	StackPane sp =new StackPane();
		Rectangle rec=new Rectangle(700,70,Color.GOLD);
		
		Label lb=new Label("Remplir au stock ");
		lb.setFont(Font.font("Normal",FontWeight.BOLD,50));
		lb.setTextFill(Color.WHITE);
		sp.getChildren().addAll(rec ,lb) ;
		
		Label lb1=new Label("Type :");
		ChoiceBox tf1 = new ChoiceBox();
		tf1.getItems().add("Article Electroménager");
		tf1.getItems().add("Primeur");
		
		Label lb2=new Label("Nom :");
		TextField tf2 = new TextField();
		Label lb3=new Label("Fournisseur : ");
		TextField tf3 = new TextField();
		Label lb4=new Label("Prix d'achat : ");
		TextField tf4 = new TextField();
		Label lb6=new Label("Quantité vendue : ");
		TextField tf6 = new TextField();
		Label re=new Label("Addition :");
		re.setVisible(false);
		Label tre = lbl("");
		HBox htre=new HBox(tre);
		htre.setAlignment(Pos.CENTER);
		htre.setVisible(false);
		
		Image image = new Image(new FileInputStream("download.png"));  
	      ImageView imageView = new ImageView(image); 
	      imageView.setX(0); 
	      imageView.setY(90); 
	      imageView.setFitHeight(530); 
	      imageView.setFitWidth(270); 
	      imageView.setPreserveRatio(true);
		
		Button bt1 = new Button ("Vendre");
		bt1.setMinWidth(100);
		bt1.setMinHeight(20);
		bt1.setStyle("-fx-background-color: #9dc319; ");
		Label err=new Label();
		err.setFont(Font.font("Normal",12));
		err.setTextFill(Color.GREEN);
		HBox er=new HBox(err);
		er.setAlignment(Pos.CENTER);
		Label err_up=new Label();
		err_up.setFont(Font.font("Normal",12));
		err_up.setTextFill(Color.RED);
		HBox er_up=new HBox(err_up);
		er_up.setAlignment(Pos.CENTER);
		bt1.setOnMouseClicked((MouseEvent even)-> {
			err.setText("");
			err_up.setText("");
			String t=(String) tf1.getSelectionModel().getSelectedItem();
			String type=""+t;
			String n=""+tf2.getText();
			String f=""+tf3.getText();
			double pa=0;
			double s=0;
			try {
				pa=Double.parseDouble(tf4.getText());
				s=-(Double.parseDouble(tf6.getText()));
				Data d=new Data();
				if(d.rempl(type,n,f,pa,s)) {
					re.setVisible(true);
					Data dt=new Data();
					double pv=dt.get_set_pv(n, f, pa,-s);
					tre.setText(pv+" dhs");
					htre.setVisible(true);
				}else {
					err_up.setText("article Non Existant");
				}
			}catch(Exception e) {
				err_up.setText("Veuillez saisir des données valides");
				err.setText("");
			}
			
		});
		Button bt2 = new Button ("Annuler");
		bt2.setStyle("-fx-background-color: #9dc319; ");
		bt2.setMinWidth(100);
		bt2.setMinHeight(20);
		bt2.setOnMouseClicked((MouseEvent e)->{
			new Menu_admin();
		});
		HBox h = new HBox(bt1 , bt2  );
		h.setAlignment(Pos.CENTER);
		h.setPadding(new Insets(2, 10, 10, 10));
		h.setSpacing(40.0);
		
		VBox v = new VBox();
		v.setPadding(new Insets(1, 25, 25, 25));
		v.setSpacing(13.0);
		v.getChildren().addAll(er_up,er,lb1,tf1, lb2,tf2, lb3,tf3, lb4,tf4, lb6,tf6,re,htre, h);
		v.setLayoutY(100);
		v.setLayoutX(300);
		
		g.getChildren().addAll(sp,v,imageView) ;
	
		
		
        Scene scene = new Scene(g, 700, 650);
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setTitle("Gestion De Magasin");
        stage.show();
		
	}


	private Label lbl(String s) {
		// TODO Auto-generated method stub
		Label lb=new Label(s);
		lb.setFont(Font.font("Normal",15));
	    lb.setPrefSize(80, 30);
	    lb.setStyle("-fx-border-color: black;-fx-border-width: 1px;");
	    lb.setAlignment(Pos.CENTER);
	    return lb;
	}

}

