package application;

import java.io.IOException;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {
	private Stage primaryStage;
	private BorderPane barMenu;

	@Override
	public void start(Stage primaryStage) {
		 this.primaryStage = primaryStage;
	        this.primaryStage.setTitle("SystemO Simulator 1.0");
	        
	        this.primaryStage.getIcons().add(new Image("file:ressources/images/thread.png"));
	        
	        initBarMenu();
	        showProcess();
	}
	
	private void showProcess() {
		// TODO Auto-generated method stub
		 try {
			 FXMLLoader loader= new FXMLLoader();
			 loader.setLocation(Main.class.getResource("view/Interface3.fxml"));
			 BorderPane centre= (BorderPane)loader.load();
			 barMenu.setCenter(centre);
		 }catch(IOException e) {
			 e.printStackTrace();
		 }
	}

	public void initBarMenu() {
		 try {
	            // Load root layout from fxml file.
	            FXMLLoader loader = new FXMLLoader();
	            loader.setLocation(Main.class
	                    .getResource("view/Interface1.fxml"));
	            barMenu = (BorderPane) loader.load();
	            
	            Scene scene = new Scene(barMenu);
	            primaryStage.setScene(scene);
	            
	            //BarMenuController controller = loader.getController();
	            //controller.setMain(this);

	            primaryStage.show();
		 } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
	public Stage getPrimaryStage() {
		return primaryStage;
	}

	public static void main(String[] args) {
		launch(args);
	}
}
