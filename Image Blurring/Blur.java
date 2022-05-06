import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;


/**
 * This class is used to create a blur effect on an image.
 * Significant blurring is observered after several iterations.
 * Author: Charles O. Atisele
 */

public class Blur {
    public static void main(String args[]){
	BufferedImage img = null;
	File f = null;

    // read image
		try {
			f = new File(
				"235460_20220425020808_1.png"); //original image: 235460_20220425020808_1.png
			img = ImageIO.read(f);
		}
		catch (IOException e) {
			System.out.println(e);
		}

		// Get image width and height
		int width = img.getWidth();
		int height = img.getHeight();

        

        for ( int y=1; y<height-1 ; y++){
				for ( int x=1 ; x<width-1; x++) {

                    Color clr1 = new Color(img.getRGB(x-1,y));
				    Color clr2 = new Color(img.getRGB(x,y));
                    Color clr3 = new Color(img.getRGB(x+1,y));
                    Color clr4 = new Color(img.getRGB(x-1,y-1));
                    Color clr5 = new Color(img.getRGB(x,y-1));
                    Color clr6 = new Color(img.getRGB(x+1,y-1));
                    Color clr7 = new Color(img.getRGB(x-1,y+1));
                    Color clr8 = new Color(img.getRGB(x,y+1));
                    Color clr9 = new Color(img.getRGB(x+1,y+1));
                    
                    int red = ((clr1.getRed()*4)+(clr2.getRed()*6)+(clr3.getRed()*4)
                    +(clr4.getRed()*2)+(clr5.getRed()*4)+(clr6.getRed()*2)
                    +(clr7.getRed()*2)+(clr8.getRed()*4)+(clr9.getRed()*2))/30;

                    int blue = ((clr1.getBlue()*4)+(clr2.getBlue()*6)+(clr3.getBlue()*4)
                    +(clr4.getBlue()*2)+(clr5.getBlue()*4)+(clr6.getBlue()*2)
                    +(clr7.getBlue()*2)+(clr8.getBlue()*4)+(clr9.getBlue()*2))/30;

                    int green = ((clr1.getGreen()*4)+(clr2.getGreen()*6)+(clr3.getGreen()*4)
                    +(clr4.getGreen()*2)+(clr5.getGreen()*4)+(clr6.getGreen()*2)
                    +(clr7.getGreen()*2)+(clr8.getGreen()*4)+(clr9.getGreen()*2))/30;
                    
					img.setRGB(x, y, (new Color(red, green, blue)).getRGB());
				}
        }

        // write image
		try {
			f = new File(
				"result.png");
			ImageIO.write(img, "png", f);
		}
		catch (IOException e) {
			System.out.println(e);
		}
}
}