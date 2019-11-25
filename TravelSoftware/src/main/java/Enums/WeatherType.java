/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Enums;

import javax.swing.ImageIcon;

/**
 *
 * @author johannesriedmueller
 */
public enum WeatherType {
    SUNNY("./icons/sunny.png"),
    RAINY("./icons/rainy.png"),
    CLOUDY("./icons/cloudy.png"),
    SUNNYCLOUDY("./icons/sunnycloudy.png");
    
    private ImageIcon icon;
    
    private WeatherType(String iconPath) {
        this.icon = new ImageIcon(iconPath);
    }

    public ImageIcon getIcon() {
        return icon;
    }
    
    
    
}
