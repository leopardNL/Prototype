package businesslogic;
import API.FakeAPI;

public class ImageProcessing {
	
	String workingImage;
	
	public ImageProcessing(String workingImage2) {
		this.workingImage = workingImage2;
	}

	public String filtreX()
	{
		
		String APIanswer = FakeAPI.filtreX_API(this.workingImage);
		return APIanswer;
		
	}
		
}
