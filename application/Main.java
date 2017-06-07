package application;


import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

//import businesslogic.Service;
//import facade.Facade;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
		@Override
		public void start(Stage primaryStage) throws IOException {
			
			primaryStage.setTitle("PhleboMorph");
			Parent root = FXMLLoader.load(getClass().getResource("UIDashboard.fxml"));
			
			Scene scene = new Scene(root);
			
			primaryStage.setScene(scene);
			primaryStage.show();
			
			
		}
		
		public static void main(String[] args) {
			launch(args);
		}
}
