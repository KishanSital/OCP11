package chapter19.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public class MyResourceBundle {
    public static ResourceBundle rb;

    public static void localChanger(File file) {

        System.out.println(file.getAbsolutePath());

        try {
            rb = new PropertyResourceBundle(Files.newInputStream(Paths.get(file.getAbsolutePath())));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
