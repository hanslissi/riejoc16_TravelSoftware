/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.Destination;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherTableModel extends AbstractTableModel{
    private ArrayList<Destination> destinations = new ArrayList<>();
    private static final String[] colnames = {"City","Pressure", "Humidity", "Temp", ""};

    public void add(Destination destination) {
        destinations.add(destination);
        fireTableRowsInserted(destinations.size()-1, destinations.size()-1);
    }
    
    @Override
    public int getRowCount() {
        return destinations.size();
    }

    @Override
    public int getColumnCount() {
        return colnames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return destinations.get(rowIndex);
    }

    @Override
    public String getColumnName(int column) {
        return colnames[column];
    }
    
    
    
}
