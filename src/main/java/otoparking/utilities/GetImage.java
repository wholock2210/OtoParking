package otoparking.utilities;

import javax.swing.ImageIcon;
import java.util.Objects;

public class GetImage {

    private GetImage() {} 
    public static ImageIcon getIcon(String name) {
        return new ImageIcon(
            Objects.requireNonNull(
                GetImage.class.getResource("/icons/" + name),
                "Missing icon: /icons/" + name
            )
        );
    }
}
