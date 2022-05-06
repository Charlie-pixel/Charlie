import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.*;



/**
 * Author: Charles Atisele
 * This class implements a high boost filter.
 * Significant sharpening is achieved by increasing the boost factor.
 * A = 1: Laplacian filter
 * 1<A<2: Limited low frequency sharpening
 * A = 2: Laplacian Sharpening
 * A > 2: Brightened image with edges enhanced
 */
public class High_Boost_Filter {
    public static void main(String args[]){
        BufferedImage img = null;
        File f = null;
        double A = 4; // A is the factor of the boost. Try values between 1 and 4.
        // read image
            try {
                f = new File(
                    "original.png");// The original image
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
                        
                        double red = ((clr1.getRed()*-1)+((8*A*clr2.getRed()))+(clr3.getRed()*-1)
                        +(clr4.getRed()*-1)+(clr5.getRed()*-1)+(clr6.getRed()*-1)
                        +(clr7.getRed()*-1)+(clr8.getRed()*-1)+(clr9.getRed()*-1))/9;
                        red = red>255?255:red<0?0:red;
    
                        double blue = ((clr1.getBlue()*-1)+((8*A*clr2.getBlue()))+(clr3.getBlue()*-1)
                        +(clr4.getBlue()*-1)+(clr5.getBlue()*-1)+(clr6.getBlue()*-1)
                        +(clr7.getBlue()*-1)+(clr8.getBlue()*-1)+(clr9.getBlue()*-1))/9;
                        blue = blue>255?255:blue<0?0:blue;
    
                        double green = ((clr1.getGreen()*-1)+((8*A*clr2.getGreen()))+(clr3.getGreen()*-1)
                        +(clr4.getGreen()*-1)+(clr5.getGreen()*-1)+(clr6.getGreen()*-1)
                        +(clr7.getGreen()*-1)+(clr8.getGreen()*-1)+(clr9.getGreen()*-1))/9;
                        green = green>255?255:green<0?0:green;
                        
                        img.setRGB(x, y, (new Color((int)red, (int)green, (int)blue)).getRGB());
                    }
            }
    
            // write image
            try {
                f = new File(
                    "Brightened_sharpened_result.png");
                ImageIO.write(img, "png", f);
            }
            catch (IOException e) {
                System.out.println(e);
            }
    }
}
