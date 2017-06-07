package API;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FakeAPI {
	
	public static String filtreX_API(String path)
	{
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(path));
		    /*File saveCopy = new File(path + "v2");
		    ImageIO.write(img, "jpg", saveCopy);*/
		    img = FakeAPI.BlackCouleur(img);
		    File outputfile = new File(path);
		    ImageIO.write(img, "jpg", outputfile);
		    
		} catch (IOException e) {
			return "Error";
		}
		String APIanswer = "Success";
		return APIanswer;
		
	}
	
	public static BufferedImage BlackCouleur(BufferedImage img ) {
		BufferedImage image=img; 
        int w = image.getWidth();
        int h = image.getHeight();
        int  colB=new Color(255,255,255).getRGB();
        int  colN=new Color(1,1,1).getRGB();
        int  colMoyen=(colB + colN)/2;
        for (int x=0; x<w; x++) {
            for (int y=0;y<h; y++) {
                int k =image.getRGB(x, y);
 
               if (k <=colMoyen )
                 image.setRGB(x,y ,colN);
               if (k >colMoyen)
                 image.setRGB(x,y,colB);          
 
            }
        }
        return  image;
}
}
