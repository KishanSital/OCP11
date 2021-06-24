package chapter16.utils;

import java.util.Locale;
import java.util.ResourceBundle;

public class MyResourceBundle {
    public static ResourceBundle rb;

    public static void localChanger(Locale locale) {
        rb = ResourceBundle.getBundle("chapter16", locale);
    }

}
