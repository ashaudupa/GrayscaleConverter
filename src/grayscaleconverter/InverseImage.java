package grayscaleconverter;

import java.io.File;

import edu.duke.*;

public class InverseImage {
    /**
     * create new image which is inverse of the given image
     * In inverting an image, a pixel’s red, blue, and green components are modified to be
     *  the exact opposite within the 0 to 255 range. That is, if a pixel’s red, blue, and green values are (34, 198, 240), 
     *  then that same pixel in the inverted image would have the red, blue and green values of (221, 57, 15). 
     * Note that 255 - 34 is 221, 255 - 198 is 57, and 255 - 240 is 15.
     */
    public ImageResource makeInverse(ImageResource inImage) {
        //create another image of same size
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        //for each pixel of outImage
        for ( Pixel pixel : outImage.pixels()) {
            Pixel inpixel = inImage.getPixel(pixel.getX(), pixel.getY());
            int inverseRed = 255 - inpixel.getRed();
            int inverseBlue = 255 - inpixel.getBlue();
            int inverseGreen = 255 - inpixel.getGreen();
            pixel.setRed(inverseRed);
            pixel.setBlue(inverseBlue);
            pixel.setGreen(inverseGreen);
           
        }
        
        return outImage;
    }
    
    public void selectAndInverse(){
        DirectoryResource dr = new DirectoryResource();
        
        for ( File file : dr.selectedFiles()) {
            ImageResource inImage = new ImageResource(file);
            ImageResource inverseImage = makeInverse(inImage);
            String fname = inImage.getFileName();
            String newname = "./images/inverse-" + fname;
            inverseImage.setFileName(newname);
            inverseImage.save();
            inverseImage.draw();
        }
    }
    
    public static void main(String[] agrs) {
        InverseImage invimg = new InverseImage();
        invimg.selectAndInverse();
    }
    

}
