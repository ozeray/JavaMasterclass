package co.ayo.jmc.i18n;

import javax.swing.*;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public class Main {

    public static void main(String[] args) {
        for (Locale l : List.of(Locale.getDefault(), Locale.CANADA, Locale.of("tr", "TR"))) {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("BaseName", l);
//            System.out.println(resourceBundle.getClass().getName());
//            System.out.println(resourceBundle.getBaseBundleName());
//            System.out.println(resourceBundle.keySet());

            String msg = String.format("%s %s!%n", resourceBundle.getString("yes"), resourceBundle.getString("hello"));
            String title = ResourceBundle.getBundle("ui", l).getString("title");
            JOptionPane.showOptionDialog(null, msg, title, JOptionPane.DEFAULT_OPTION,
                    JOptionPane.INFORMATION_MESSAGE, null, new Object[]{resourceBundle.getString("yes")}, null);
        }

    }
}
