/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.ForecastInformation;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author johannesriedmueller
 */
public class ForecastTableModel extends AbstractTableModel {
    private ArrayList<ForecastInformation> forecastInfo = new ArrayList<>();
    private String[] colnames = {"Date","", "Humidity", "Pressure", "Temperature"};

    /**
     * Adds all forecast information to existing ones.
     * @param forecastInfos information to be added.
     */
    public void addAll(ArrayList<ForecastInformation> forecastInfos) {
        forecastInfo.addAll(forecastInfos);
        fireTableDataChanged();
    }

    @Override
    public String getColumnName(int column) {
        return colnames[column];
    }
    
    @Override
    public int getRowCount() {
        return forecastInfo.size();
    }

    @Override
    public int getColumnCount() {
        return colnames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return forecastInfo.get(rowIndex);
    }
    
}
