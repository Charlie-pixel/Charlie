/**
 * Author: Charles Atisele
 */
  
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.awt.*;

/**
 * The HighBoostFilter class is used to apply a high boost filter to an image.
 * It brightens the image and emphasizes the edges.
 * A = 1.0: Laplacian filter
 * 1.0 < A < 2.0: Limited low frequency filter
 * A = 2.0: Laplacian Sharpening
 * A > 2: Brightened image with edge enhanced
 *  */

public class HighBoostFilter {
    public static void main(String args[])
        throws IOException
    {
        BufferedImage img = null;
        File f = null;
        double A = 1; //try a range of 1 to 4
  
        // read image
        try {
            f = new File(
                "unknown.png");
            img = ImageIO.read(f);
        }
        catch (IOException e) {
            System.out.println(e);
        }
  
        // Get image width and height
        int width = img.getWidth();
        int height = img.getHeight();
  
        for (int y = 1; y < height-1; y++) {
            for (int x = 1; x < width-1; x++) {
                Color clr1 =  new Color (img.getRGB(x-1, y-1));
                Color clr2 = new Color(img.getRGB(x, y-1));
                Color clr3 = new Color(img.getRGB(x+1, y-1));
                Color clr4 = new Color(img.getRGB(x-1, y));
                Color clr5 = new Color(img.getRGB(x, y));
                Color clr6 = new Color(img.getRGB(x+1, y));
                Color clr7 = new Color(img.getRGB(x-1, y+1));
                Color clr8 = new Color(img.getRGB(x, y+1));
                Color clr9 = new Color(img.getRGB(x+1, y+1));

                double red = ((-1*clr1.getRed()) - clr2.getRed() - clr3.getRed() - clr4.getRed() + (clr5.getRed()*8*A) 
                - clr6.getRed() - clr7.getRed() - clr8.getRed() - clr9.getRed())/9;
                red = red>255?255:red<0?0:red;

                double green = ((-1*clr1.getGreen()) - clr2.getGreen() - clr3.getGreen() - clr4.getGreen() + (clr5.getGreen()*8*A)
                 - clr6.getGreen() - clr7.getGreen() - clr8.getGreen() - clr9.getGreen())/9;
                green = green>255?255:green<0?0:green;

                double blue = ((-1*clr1.getBlue()) - clr2.getBlue() - clr3.getBlue() - clr4.getBlue() + (clr5.getBlue()*8*A)
                 - clr6.getBlue() - clr7.getBlue() - clr8.getBlue() - clr9.getBlue())/9;
                blue = blue>255?255:blue<0?0:blue;

                img.setRGB(x, y, new Color((int)red, (int)green, (int)blue).getRGB());
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