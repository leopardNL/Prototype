package application;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import businesslogic.ImageProcessing;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;


public class ControlDashboard {

	@FXML
	private AnchorPane window;

	@FXML
	private ImageView mainImage;

	@FXML
	private Label name;

	@FXML
	private Label size;

	@FXML
	private Label date;

    @FXML
    private TextArea consoleArea;
    
	@FXML
	private MenuItem loadImageButton;
	
	@FXML
    private SplitPane horizontalLeftSplit;
	
    @FXML
    private SplitPane verticalSplit;
   
    @FXML
    private SplitPane horizontalRightSplit;
    
    @FXML
    private MenuItem filtreXButton;

    @FXML
    private BorderPane imagePane;
    	
	private String newline = "\n";
	
	private String workingImage;
	
	
	
	
	


	@FXML
	void loadImage(ActionEvent event) {
		writeConsole(">> Image is loading...");
		JFileChooser image = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & PNG Images", "jpg", "png", "jpeg");
		image.setFileFilter(filter);
		int returnVal = image.showOpenDialog(null);
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			String result = image.getSelectedFile().getAbsolutePath().toString();
			this.workingImage = result;
	        this.displayImage(result);
	        writeConsole("Image is ready.");

		} else { 
			writeConsole("Open command cancelled by user."); 
		} 

	} 

    
    
    @FXML
    void filtreX(ActionEvent event) {
    		ImageProcessing process = new ImageProcessing(this.workingImage);
    		writeConsole(">> Filtre X :");
    		writeConsole(process.filtreX());
    		this.displayImage(workingImage);
    		
    }
    
    @FXML
    void resizeImage(ActionEvent event) {
    		this.resize();
    }
    
    @FXML
    void fullscreenImage(ActionEvent event) {
    	this.verticalSplit.setDividerPositions(1);
		this.horizontalLeftSplit.setDividerPositions(1);
		this.resize();
    }
    
	
	
	void writeConsole(String message){
		consoleArea.appendText(message + newline);
	}
	
	void displayImage(String path) {
	        File file = new File(path);
	        Image i = new Image(file.toURI().toString());
			mainImage.setImage(i);
			this.resize();
			
			
	} 
	
	 
	 public void resize(){
		 
		 double x = this.imagePane.getWidth();
		 double y = this.imagePane.getHeight();
		 double a = this.mainImage.getImage().getWidth();
		 double b = this.mainImage.getImage().getHeight();
		 double ecart1 = x-a-100;
		 double ecart2 = y-b-100;
		 
		 if(ecart1 < 0)
		 	{
		 		mainImage.setFitWidth(this.imagePane.getWidth() - 100);
		 	}
		 if(ecart2 < 0)
		 	{
		 		mainImage.setFitHeight(this.imagePane.getHeight() - 100);
		 	}
	 }


}
