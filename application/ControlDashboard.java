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
    private Button fullscreenButton;
    
    @FXML
    private MenuItem filtreXButton;

    @FXML
    private BorderPane imagePane;
	
	private String newline = "\n";
	
	private boolean full;

	private String workingImage;
	
	
	


	@FXML
	void loadImage(ActionEvent event) {
		full = false;
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
    void fullScreenImage(MouseEvent event) {
    	
    	if(!full)
    	{
    		this.verticalSplit.setDividerPositions(1);
    		this.horizontalLeftSplit.setDividerPositions(1);
    		full = true;
    	}
    	else
    	{
    		this.verticalSplit.setDividerPositions(0.8);
    		this.horizontalLeftSplit.setDividerPositions(0.7);
    		full = false;
    	}
    	
    }
    

    @FXML
    void filtreX(ActionEvent event) {
    		ImageProcessing process = new ImageProcessing(this.workingImage);
    		writeConsole(">> Filtre X :");
    		writeConsole(process.filtreX());
    		this.displayImage(workingImage);
    		
    }
	
	
	void writeConsole(String message){
		consoleArea.appendText(message + newline);
	}
	
	void displayImage(String path) {
	        File file = new File(path);
	        Image i = new Image(file.toURI().toString());
			mainImage.setImage(i);
			//mainImage.fitWidthProperty().bind(imagePane.widthProperty());
			
	} 
	

	





}
