package application;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;

import com.drew.imaging.ImageMetadataReader;
import com.drew.imaging.ImageProcessingException;
import com.drew.metadata.*;


public class ControlDashboard {

	@FXML
	private AnchorPane window;

	@FXML
	private ImageView mainImage;

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

	@FXML
	private ImageView tumbnail3;

	@FXML
	private ImageView tumbnail2;

	@FXML
	private ImageView tumbnail1;

	@FXML
	private Label labelTumbnail1;

	@FXML
	private Label labelTumbnail2;

	@FXML
	private Label labelTumbnail3;

    @FXML
    private TextArea metadata;


	private ArrayList<String> images = new ArrayList<String>();

	private String newline = "\n";

	private String workingImage;

	private int tumbnails[];







	@FXML
	void loadImage(ActionEvent event) {
		writeConsole(">> Image is loading...");
		JFileChooser image = new JFileChooser();
		image.setMultiSelectionEnabled(true);
		FileNameExtensionFilter filter = new FileNameExtensionFilter(
				"JPG & PNG Images", "jpg", "png", "jpeg");
		image.setFileFilter(filter);
		int returnVal = image.showOpenDialog(null);
		int[] tab = {0,1,2};
		this.tumbnails = tab;
		if(returnVal == JFileChooser.APPROVE_OPTION) {
			File[] files = image.getSelectedFiles();
			for(int i = 0; i < files.length; i++)
			{
				String result = files[i].getAbsolutePath().toString();
				this.data(files[0]);
				this.images.add(result);
				writeConsole(result);
			}
			this.workingImage = files[0].getAbsolutePath().toString();;
			this.displayImage(this.workingImage);



		} else { 
			writeConsole("Open command cancelled by user."); 
		} 

	} 


	@FXML
	void filtreX(ActionEvent event) {
		ImageProcessing process = new ImageProcessing(this.workingImage);
		File file = new File(this.images.get(tumbnails[0]));
		writeConsole(">> Filtre X <"+ file.getName() +">:");
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

	@FXML
	void nextImage(MouseEvent event) {
		int max = images.size() - 1;
		if(max > tumbnails[2])
		{
			tumbnails[0]++;
			tumbnails[1]++;
			tumbnails[2]++;
			this.displayImage(this.workingImage);    		
		}
	}

	@FXML
	void previousImage(MouseEvent event) {
		if(0 < tumbnails[0])
		{
			tumbnails[0]--;
			tumbnails[1]--;
			tumbnails[2]--;
			this.displayImage(this.workingImage);    		
		}
	}

	@FXML
	void changetumbnail1(MouseEvent event) {
		this.workingImage = images.get(tumbnails[0]);
		this.displayImage(this.workingImage);
	}

	@FXML
	void changetumbnail2(MouseEvent event) {
		this.workingImage = images.get(tumbnails[1]);
		this.displayImage(this.workingImage);
	}

	@FXML
	void changetumbnail3(MouseEvent event) {
		this.workingImage = images.get(tumbnails[2]);
		this.displayImage(this.workingImage);
	}

	void writeConsole(String message){
		consoleArea.appendText(message + newline);
	}

	void displayImage(String path) {
		File file1 = new File(path);
		Image i1 = new Image(file1.toURI().toString());
		mainImage.setImage(i1);
		this.resize();

		if(images.size() > 2)
		{
			File file2 = new File(this.images.get(tumbnails[0]));
			this.labelTumbnail1.setText(file2.getName());
			Image i2 = new Image(file2.toURI().toString());
			tumbnail1.setImage(i2);
			tumbnail1.setFitWidth(100);

			File file3 = new File(this.images.get(tumbnails[1]));
			this.labelTumbnail2.setText(file3.getName());
			Image i3 = new Image(file3.toURI().toString());
			tumbnail2.setImage(i3);
			tumbnail2.setFitWidth(100);

			File file4 = new File(this.images.get(tumbnails[2]));
			this.labelTumbnail3.setText(file4.getName());
			Image i4 = new Image(file4.toURI().toString());
			tumbnail3.setImage(i4);
			tumbnail3.setFitWidth(100);
		}
		else if(images.size() == 2)
		{
			File file2 = new File(this.images.get(tumbnails[0]));
			this.labelTumbnail1.setText(file2.getName());
			Image i2 = new Image(file2.toURI().toString());
			tumbnail1.setImage(i2);
			tumbnail1.setFitWidth(100);

			File file3 = new File(this.images.get(tumbnails[1]));
			this.labelTumbnail2.setText(file3.getName());
			Image i3 = new Image(file3.toURI().toString());
			tumbnail2.setImage(i3);
			tumbnail2.setFitWidth(100);
		}
		else if (images.size() == 1)
		{
			File file2 = new File(this.images.get(tumbnails[0]));
			this.labelTumbnail1.setText(file2.getName());
			Image i2 = new Image(file2.toURI().toString());
			tumbnail1.setImage(i2);
			tumbnail1.setFitWidth(100);
		}


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

	public void data(File file){


		Metadata metadata;
		try {
			metadata = ImageMetadataReader.readMetadata(file);


			for (Directory directory : metadata.getDirectories()) {
				for (Tag tag : directory.getTags()) {
					String mess = tag.getTagName() + " = "+ tag.getDescription()+ "\n";
					this.metadata.appendText(mess);
				}
				if (directory.hasErrors()) {
					for (String error : directory.getErrors()) {
						System.err.format("ERROR: %s", error);
					}
				}
			}
		} catch (ImageProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
