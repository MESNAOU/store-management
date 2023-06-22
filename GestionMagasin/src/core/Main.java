package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import projet.Data;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws FileNotFoundException {
    	Group g =new Group();
    	StackPane sp =new StackPane();
		Rectangle rec=new Rectangle(300,600,Color.GOLD);
		
		VBox v = new VBox();
		Label lb1=new Label("SHOP ");
		lb1.setFont(Font.font("Normal",FontWeight.BOLD,35));
		Label lb2=new Label("MANAGEMENT ");
		lb2.setFont(Font.font("Normal",FontWeight.BOLD,35));
		lb1.setTextFill(Color.WHITE);
		lb2.setTextFill(Color.WHITE);
		
	      Image image = new Image(new FileInputStream("images.jfif"));  
	      ImageView imageView = new ImageView(image); 
	      imageView.setX(50); 
	      imageView.setY(25); 
	      imageView.setFitHeight(70); 
	      imageView.setFitWidth(70); 
	      imageView.setPreserveRatio(true);   
		  v.getChildren().addAll(lb1 , lb2 ,imageView) ;
		  v.setAlignment(Pos.CENTER);
		  sp.getChildren().addAll(rec,v);
		
		
		GridPane gp =new GridPane();
		gp.setPadding(new Insets(40,40,40,40));
		gp.setHgap(25);                 //esapce entre les cellules
		gp.setVgap(20);
		gp.setAlignment(Pos.CENTER);
		Text tef=new Text("LOGIN");
		tef.setFont(Font.font("Normal",FontWeight.BOLD,50));
		tef.setFill(Color.GOLD);
		
		Label tef11=new Label("Select role : ");
		tef11.setFont(Font.font("Normal",FontWeight.BOLD,20));
		ChoiceBox tef12 = new ChoiceBox();
		tef12.getItems().add("Admin");
		tef12.getItems().add("Worker");
		Label tef21=new Label("UID : ");
		tef21.setFont(Font.font("Normal",FontWeight.BOLD,20));
		TextField tef22=new TextField();
		Label tef31=new Label("password : ");
		tef31.setFont(Font.font("Normal",FontWeight.BOLD,20));
		PasswordField tef32=new PasswordField();
		
		Button bt=new Button("login");
		bt.setStyle("-fx-background-color: #ffd700; ");
		Label err=new Label();
		err.setFont(Font.font("Normal",12));
		err.setTextFill(Color.RED);
		HBox er=new HBox(err);
		er.setAlignment(Pos.CENTER);
		/////////////////////////////////////////////////////////event////////////////////////////
		bt.setOnMouseClicked((MouseEvent even)-> {
			String uid=tef22.getText();
			String psswd = tef32.getText();
			String r=(String) tef12.getSelectionModel().getSelectedItem();
			String role = ""+r;
			Data d=new Data();
			boolean b=d.log(uid, psswd, role);
			if(b) {
				switch(role) {
				case "Admin": 
					new Menu_admin();
					break;
				case "Worker":
					new Menu_worker();
					break;
				}
			}
			else {
				err.setText("uid ou password ou role est incorrect");
			}
  		});
        ////////////////////////////////////////////////////////event//////////////////////////////
		HBox hb =new HBox(bt);
		hb.setAlignment(Pos.BOTTOM_RIGHT);
		
		
		gp.add(tef,0,0,2,1);
		gp.add(tef11,0,1);
		gp.add(tef12,1,1);
		gp.add(tef21,0,2);
		gp.add(tef22,1,2);
		gp.add(tef31,0,3);
		gp.add(tef32,1,3);
		gp.add(hb,1,4);
		gp.add(er, 0, 5,2,1);
		gp.setLayoutX(300);
		gp.setLayoutY(120);
		
		g.getChildren().addAll(sp,gp);
        Scene scene = new Scene(g, 700, 600);
        stage.setScene(scene);
        stage.setTitle("Gestion De Magasin");
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
    
}

