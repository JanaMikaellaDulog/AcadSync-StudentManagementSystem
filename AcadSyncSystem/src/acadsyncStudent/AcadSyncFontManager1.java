package acadsyncStudent;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
 

public class AcadSyncFontManager1 {
    private static final Map<String, Font> fonts = new HashMap<>();
 
    static {
        try {
            loadFont("Poppins-SemiBold", "src/Poppins-SemiBold.ttf", 15f);
            loadFont("Poppins-SemiBold1", "src/Poppins-SemiBold.ttf", 14f);
            loadFont("Poppins-SemiBold2", "src/Poppins-SemiBold.ttf", 18f);
            loadFont("Poppins-Medium", "src/Poppins-Medium.ttf", 18f);
            loadFont("Poppins-Medium1", "src/Poppins-Medium.ttf", 14f);
            loadFont("Poppins-Medium2", "src/Poppins-Medium.ttf", 16f);
            loadFont("Poppins-Medium3", "src/Poppins-Medium.ttf", 12f);
            loadFont("Poppins-Bold", "src/Poppins-Bold.ttf", 23f);
            loadFont("Poppins-Bold2", "src/Poppins-Bold.ttf", 14f);
            loadFont("Poppins-Bold3", "src/Poppins-Bold.ttf", 12f);
            loadFont("Poppins-Bold4", "src/Poppins-Bold.ttf", 33f);
            loadFont("Poppins-Light", "src/Poppins-Light.ttf", 11f);
            loadFont("Arvo-Bold", "src/Arvo-Bold.ttf", 22f);
            loadFont("Arvo-Bold1", "src/Arvo-Bold.ttf", 25f);
        } catch (Exception e) {
            e.printStackTrace(); 
            System.out.println("Failed to load one or more custom fonts.");
        }
    }
 
    private static void loadFont(String name, String path, float size) throws FontFormatException, IOException {
        File fontFile = new File(path);
        Font font = Font.createFont(Font.TRUETYPE_FONT, fontFile).deriveFont(size);
        GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(font);
        fonts.put(name, font);
    }

    public static Font getFont(String name) {
        return fonts.get(name);
    }
}


