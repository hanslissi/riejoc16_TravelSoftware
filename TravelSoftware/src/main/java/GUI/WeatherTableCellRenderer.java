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
        if (isSelected) {
            label.setBackground(Color.gray);
        }

        if (value != null) {
            Destination dest = (Destination) value;
            if (column == 4) {
                label.setIcon(dest.getWeatherType().getIcon());
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
                        break;
                    case 3:
                        label.setText(String.format("%.2f °C",weatherInfo.getTemp()));
                        break;
                    default:
                        label.setText("???");
                        break;
                }
            }
        }

        return label;
    }
}
