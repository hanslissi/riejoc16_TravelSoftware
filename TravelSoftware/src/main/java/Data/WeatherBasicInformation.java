/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.awt.image.BufferedImage;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherBasicInformation {
    private int id;
    private String main;
    private String description;
    private String icon;
    private ImageIcon imageIcon;
    private static final String IMAGE_PATH = "http://openweathermap.org/img/wn/";
    
    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }
    
    public ImageIcon getIcon() {
        if(imageIcon == null) {
            try{
                BufferedImage img = ImageIO.read(new URL(IMAGE_PATH+icon+".png"));  
                imageIcon = new ImageIcon(img);
            } catch (Exception ex) {
                imageIcon = null;
            }
        }
        return imageIcon;
    }
    
    
}
