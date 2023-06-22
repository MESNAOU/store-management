package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.MapValueFactory;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
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

public class ShowArticle  {
	List<Article> a;
	int iteration;
	
	public ShowArticle(List<Article> ar, int i) throws Exception   {
		this.a=ar;
		this.iteration=i;
		initshow();
	}

	public void initshow() throws Exception {
		Group g = new Group();
    	StackPane sp =new StackPane();
		Rectangle rec=new Rectangle(800,70,Color.GOLD);
		
		Label lb=new Label("Article");
		lb.setFont(Font.font("Normal",FontWeight.BOLD,50));
		lb.setTextFill(Color.WHITE);
		sp.getChildren().addAll(rec ,lb) ;
		
		Image image = new Image(new FileInputStream("download.png"));  
	    ImageView imageView = new ImageView(image); 
	    imageView.setX(0); 
	    imageView.setY(90); 
	    imageView.setFitHeight(530); 
	    imageView.setFitWidth(270); 
	    imageView.setPreserveRatio(true);
	    /////////////////////////////////////////Construction de la table
	    Article ar=this.a.get(this.iteration);
	    
	    GridPane grid=new GridPane();
	    grid.setPadding(new Insets(20,10,20,40));
	    //grid.setHgap(10);
        //grid.setVgap(10);
	    
	    Label lb1=createHeadCell("Type");
	    Label lb2=null;
	    if(ar instanceof Article_electromenager) {
	    	lb2=createCell("Article Electromenager");
	    }else {
	    	lb2=createCell("Article Primeur");
		}
	    
	    Label lb3=createHeadCell("Nom");
	    
	    Label lb4=createCell(ar.getNom());
	    
	    Label lb5=createHeadCell("Fournisseur");
	    
	    Label lb6=createCell(ar.getNom_fournisseur());
	    
	    Label lb7=createHeadCell("Prix d'achat");
	    
	    Label lb8=createCell(ar.getPrix_achat()+"");
	    
	    Label lb9=createHeadCell("Prix de vente");
	    
	    Label lb10=createCell(""+ar.getPrix_vente());
	    
	    Label lb11=createHeadCell("Rendement");
	    
	    Label lb12=createCell(""+ar.calculer_taux_rendement()+"%");
	    
	    Label lb13=createHeadCell("Quantité disponible");
	    
	    Label lb14=createCell("");
	    if(ar instanceof Article_electromenager) {
	    	lb14.setText(((Article_electromenager) ar).getStock()+" pièces");
	    }else {
	    	lb14.setText(((Primeur) ar).getStock()+" Kg");
		}
	    
	    grid.add(lb1, 0, 0);
	    
	    grid.add(lb2, 1, 0);
	    grid.add(lb3, 0, 1);
	    grid.add(lb4, 1, 1);
	    grid.add(lb5, 0, 2);
	    grid.add(lb6, 1, 2);
	    grid.add(lb7, 0, 3);
	    grid.add(lb8, 1, 3);
	    grid.add(lb9, 0, 4);
	    grid.add(lb10, 1, 4);
	    grid.add(lb11, 0, 5);
	    grid.add(lb12, 1, 5);
	    
	    grid.add(lb13, 0, 6);
	    
	    grid.add(lb14, 1, 6);
	    
	    	    	      ///////////////////////////////////////////creation de la table
		Button bt1 = new Button ("<");
		bt1.setStyle("-fx-background-color: #eeeeee;-fx-font-size: 30px; ");
		
		if(this.iteration!=0) {
			bt1.setOnMouseClicked((MouseEvent even)-> {
				try {
					new ShowArticle(a, iteration-1);
				}catch(Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			});
		}else bt1.setVisible(false);
		
		Button bt2 = new Button (">");
		bt2.setStyle("-fx-background-color: #eeeeee;-fx-font-size: 30px; ");
		if(this.iteration+1!=(this.a).size()) {
			bt2.setOnMouseClicked((MouseEvent e)->{
				try {
					new ShowArticle(this.a,this.iteration+1);
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
		}else bt2.setVisible(false);
		HBox h = new HBox(bt1 , bt2  );
		h.setAlignment(Pos.CENTER);
		h.setPadding(new Insets(0, 10, 10, 20));
		h.setSpacing(40.0);
		
		VBox v = new VBox();
		v.setPadding(new Insets(10, 25, 25, 15));
		v.setSpacing(13.0);
		v.getChildren().addAll(grid,h);
		v.setLayoutY(100);
		v.setLayoutX(300);
		
		g.getChildren().addAll(sp,v,imageView) ;
	
		
		
        Scene scene = new Scene(g, 800, 550); 
        Stage stage = new Stage();
        
        stage.setScene(scene);
        stage.setTitle("Gestion De Magasin");
        stage.show();
		
	}

	
    private Label createHeadCell(String s) {
    	Label lb=new Label(s);
	    lb.setFont(Font.font("Normal",FontWeight.BOLD,15));
	    lb.setPrefSize(180, 40);
	    lb.setStyle("-fx-border-color: black;-fx-border-width: .5px;-fx-background-color: #FFD811;-fx-effect: dropshadow(one-pass-box, black, 10, 0.5, 0, 0)");
	    lb.setAlignment(Pos.CENTER);
	    return lb;
    }
 
    private Label createCell(String s) {
    	Label lb=new Label(s);
	    lb.setFont(Font.font("Normal",15));
	    lb.setPrefSize(200, 40);
	    lb.setStyle("-fx-border-color: black;-fx-border-width: .5px;");
	    lb.setAlignment(Pos.CENTER);
	    return lb;
    }
}

