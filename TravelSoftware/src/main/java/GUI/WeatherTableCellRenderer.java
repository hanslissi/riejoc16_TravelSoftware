/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.Destination;
import Enums.WeatherType;
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
                switch (column) {
                    case 0:
                        label.setText(dest.getCityName());
                        break;
                    case 1:
                        label.setText("" + dest.getPressure());
                        break;
                    case 2:
                        label.setText("" + dest.getHumidity());
                        break;
                    case 3:
                        label.setText("" + dest.getTemperature());
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
