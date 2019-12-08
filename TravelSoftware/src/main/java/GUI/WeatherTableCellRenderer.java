/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.Destination;
import Data.WeatherInformation;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherTableCellRenderer implements TableCellRenderer {

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setAlignmentY(label.CENTER);
        label.setBackground(Color.LIGHT_GRAY);
        if (isSelected) {
            label.setBackground(Color.gray);
        }

        if (value != null) {
            Destination dest = (Destination) value;
            if (column == 4) {
                label.setIcon(dest.getWeatherBasicInfo().getIcon());
            } else {
                WeatherInformation weatherInfo = dest.getWeatherInfo();
                switch (column) {
                    case 0:
                        label.setText(dest.getCityName());
                        break;
                    case 1:
                        label.setText(weatherInfo.getPressure() + " mBar");
                        break;
                    case 2:
                        label.setText(weatherInfo.getHumidity() + " %");
                        int humidity = dest.getWeatherInfo().getHumidity();
                        setHumidityBackground(label, humidity);
                        break;
                    case 3:
                        label.setText(String.format("%.2f °C",weatherInfo.getTemp()));
                        float temp = dest.getWeatherInfo().getTemp();
                        setTempBackground(label, temp);
                        break;
                    case 5:
                        label.setText(dest.getWeatherBasicInfo().getDescription());
                        break;
                    default:
                        label.setText("???");
                        break;
                }
            }
            
        }

        return label;
    }
    
    /**
     * Sets background color of label depending on the temperature.
     * @param label JLabel of which the background color should be set
     * @param temp the temperature in celsius
     */
    private void setTempBackground(JLabel label, float temp) {
        if(temp <= 0.0f) {
                label.setBackground(new Color(153, 204, 255)); //light-Blue
            } else if(temp <= 10.0f) {
                label.setBackground(new Color(255, 230, 128)); //light-orange
            } else if(temp <= 20.0f) {
                label.setBackground(new Color(255, 209, 26)); //orange
            } else if(temp > 20) {
                label.setBackground(new Color(255, 133, 51)); //deep orange
            }
    }
    
    /**
     * Sets background color of label depending on the humidity.
     * @param label JLabel of which the background color should be set
     * @param humidity the humidity in percent
     */
    private void setHumidityBackground(JLabel label, int humidity) {
        if(humidity <= 25) {
                label.setBackground(new Color(204, 230, 255)); //light-light-Blue
            } else if(humidity <= 50) {
                label.setBackground(new Color(153, 206, 255)); //light-blue
            } else if(humidity <= 70) {
                label.setBackground(new Color(128, 193, 255)); //blue
            } else if(humidity <= 80) {
                label.setBackground(new Color(77, 169, 255)); //dark-blue
            } else if(humidity <= 90) {
                label.setBackground(new Color(26, 144, 255)); //dark-dark-blue
            } else if(humidity > 90) {
                label.setBackground(new Color(0, 119, 230)); //dark-dark-dark-blue
            }
    }
}
