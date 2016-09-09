package grayscaleconverter;

import edu.duke.*;
import java.io.*;
import java.awt.*;

public class GrayScaleConverter {
    //I started with the image I wanted(inImage)
    public ImageResource makeGray(ImageResource inImage) {
        //I made another image of the same size(outImage)
        ImageResource outImage = new ImageResource(inImage.getWidth(),inImage.getHeight());
        //for each pixel in outImage:
        for (Pixel pixel: outImage.pixels()){
            //look at the corresponding pixel in inImage(call it inPixel)
            Pixel inPixel = inImage.getPixel(pixel.getX(),pixel.getY());
            //compile inPixels Red+inPixel's green + inPixel's Blue
            //Devide that sum by 3(call it average)
            int average = (inPixel.getRed() + inPixel.getGreen() + inPixel.getBlue())/3;
            //set Pixel's Red by average
            pixel.setRed(average);
            //set pixel's green by average
            pixel.setGreen(average);
            //set pixel's blue by average
            pixel.setBlue(average);
        }
        //outImage is your answer
        return outImage;
    }
    
    public void testGray(){
        ImageResource ir = new ImageResource();
        ImageResource gray = makeGray(ir);
        gray.draw();
    }
    
    public void selectAndConvert(){
        DirectoryResource dr = new DirectoryResource();
        for(File f:dr.selectedFiles()){
            ImageResource inImage = new ImageResource(f);
            String fname = inImage.getFileName();
            System.out.println(fname);
            String newName = "./images/gray-"+fname;
            ImageResource gray = makeGray(inImage);
            gray.setFileName(newName);
            System.out.println(gray.getFileName());
            gray.save();
            gray.draw();
        }
    }
    public static void main(String[] args) {
        GrayScaleConverter gsc = new GrayScaleConverter();
        gsc.selectAndConvert();
    }
        
}
