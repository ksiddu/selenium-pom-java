package utils;

import org.testng.ITestResult;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ImageUtil {

    private static final DateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd SSS");

    public static void writeImageFromBytes(byte[] imageBytes, String filePath) {
        try {
            Path path = Paths.get(filePath);
            Files.write(path, imageBytes);
            System.out.println("Image saved to: " + filePath);
        } catch (IOException e) {
            System.err.println("Error writing image: " + e.getMessage());
        }
    }

    public synchronized static String generateFileName(ITestResult result){
        Date date = new Date();
        String filePath = "./screenshots/" + result.getName()+ "_" + dateFormat.format(date) +".png";
        return filePath;
    }

    public static void main(String[] args) {
        //byte[] imageBytes = ... // your byte[] here
        //writeImageFromBytes(imageBytes, "output/screenshot.png");
    }
}

