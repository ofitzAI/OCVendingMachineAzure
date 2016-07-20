package com.opitzconsulting.com.Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;


/**
 * Created by OFI on 24.06.2016.
 */
public class CreateWindow{

    private BufferedImage[] bufferedImages;
    //File homeDir = new File(System.getProperty("user.home"));
    //File fileToRead = new File(homeDir, "java/ex.txt");

    public CreateWindow() throws IOException {
        bufferedImages=loadPictures();
    }

    private BufferedImage[] loadPictures() throws IOException {
        File inputFile = new File("C:\\Users\\ofi\\OneDrive\\Showcase_VendingMachineAzure\\workspace\\OCVendingMachineAzure\\themes\\oc");
        //File inputFile = new File("/home/pi/Java/OCVendingMachineAzure/themes/oc");

        File[] dateienFileArray = inputFile.listFiles();
        BufferedImage[] bufferedImageArray = new BufferedImage[dateienFileArray.length];
        for (int i = 0; i < bufferedImageArray.length; i++) {
            if (dateienFileArray[i].isFile()) {
                bufferedImageArray[i] = ImageIO.read(dateienFileArray[i]);
            }
        }
        return bufferedImageArray;
    }

    public BufferedImage[] getBufferedImages(){
        return bufferedImages;
    }

    public Dimension getScreensize(){
        return ((Toolkit.getDefaultToolkit().getScreenSize()));
    }
}
