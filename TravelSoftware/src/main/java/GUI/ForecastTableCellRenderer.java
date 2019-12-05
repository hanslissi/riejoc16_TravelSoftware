/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.ForecastInformation;
import Data.WeatherInformation;
import java.awt.Color;
import java.awt.Component;
import java.time.format.DateTimeFormatter;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author johannesriedmueller
 */
public class ForecastTableCellRenderer implements TableCellRenderer{
    private static DateTimeFormatter dtf;
    
    static
    {
        dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        JLabel label = new JLabel();
        label.setOpaque(true);
        label.setAlignmentY(label.CENTER);
        if (isSelected) {
            label.setBackground(Color.gray);
        }

        if (value != null) {
            ForecastInformation forecastInfo = (ForecastInformation) value;
            if(column == 0) {
                switch(row) {
                    case 0: label.setText(forecastInfo.getDateTime().format(dtf)); break;
                    case 1: label.setText(forecastInfo.getDateTime().format(dtf)); break;
                    case 2: label.setText(forecastInfo.getDateTime().format(dtf)); break;
                    case 3: label.setText(forecastInfo.getDateTime().format(dtf)); break;
                    case 4: label.setText(forecastInfo.getDateTime().format(dtf)); break;
                }
            } else {
                switch(column) {
                    case 1: label.setIcon(forecastInfo.getWeatherBasicInfo().getIcon()); break;
                    case 2: label.setText(forecastInfo.getWeatherInfo().getHumidity()+" %"); break;
                    case 3: label.setText(forecastInfo.getWeatherInfo().getPressure()+" mbar"); break;
                    case 4: label.setText(String.format("%.2f °C",forecastInfo.getWeatherInfo().getTemp())); break;
                    default: label.setText("???");
                }   
            }
        }
        return label;
    }
    
}
