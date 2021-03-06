/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.Destination;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import java.lang.IllegalArgumentException;
import javax.swing.ImageIcon;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherTableModel extends AbstractTableModel {

    private ArrayList<Destination> destinations = new ArrayList<>();
    private static final String[] colnames = {"City", "Pressure", "Humidity", "Temp", "", "Description"};

    public void add(Destination destination) {
        destinations.add(destination);
        fireTableRowsInserted(destinations.size() - 1, destinations.size() - 1);
    }
    
    /**
     * Adds all destinations given, but clears all current ones before.
     * @param destinations list of destinations which should be added.
     */
    public void addAllAndClearOld(ArrayList<Destination> destinations) {
        this.destinations.clear();
        this.destinations.addAll(destinations);
        fireTableDataChanged();
    }

    /**
     * Edits destination at index
     * @param destination Destination to 'replace' with current one at index
     * @param index Index which should be 'replaces'
     */
    public void edit(Destination destination, int index) {
        try{
            destinations.remove(index);
            destinations.add(index, destination);
            fireTableDataChanged();
        } catch (Exception ex) {
            throw new IllegalArgumentException();
        }
    }
    
    /**
     * Get all current destinations (New list)
     * @return list with all destinations
     */
    public ArrayList<Destination> getAllDestinations() {
        ArrayList<Destination> destinations = new ArrayList<>();
        destinations.addAll(this.destinations);
        return destinations;
    }

    /**
     * Get all current destinations
     * @return list with all destinations
     */
    public ArrayList<Destination> getDestinations() {
        return destinations;
    }
    
    public Destination getDestinationAt(int index) {
        return destinations.get(index);
    }
    
    public String getCityNameAt(int idx) {
        return destinations.get(idx).getCityName();
    }
    
    /**
     * Gets destinations on indices.
     * @param indices indices to get destinations
     * @return list of destinations of all indices of Table.
     */
    public ArrayList<Destination> getDestinations(int[] indices) {
        ArrayList<Destination> destiToReturn = new ArrayList<>();
        for (int idx : indices) {
            destiToReturn.add(destinations.get(idx));
        }
        return destiToReturn;
    }

    /**
     * clears all entries
     */
    public void clearAll() {
        destinations.clear();
        fireTableDataChanged();
    }
    
    /**
     * Removes an element at index.
     * @param index index to remove
     */
    public void remove(int index) {
        destinations.remove(index);
        fireTableDataChanged();
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
        switch(columnIndex) {
            case 0: return destinations.get(rowIndex).getCityName(); 
            case 1: return destinations.get(rowIndex).getWeatherInfo().getPressure(); 
            case 2: return destinations.get(rowIndex).getWeatherInfo().getHumidity(); 
            case 3: return destinations.get(rowIndex).getWeatherInfo().getTemp(); 
            case 4: return destinations.get(rowIndex).getWeatherBasicInfo().getIcon(); 
            case 5: return destinations.get(rowIndex).getWeatherBasicInfo().getDescription();
            default: return null;
        }
    }

    @Override
    public String getColumnName(int column) {
        return colnames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0: return String.class;
            case 1: return Integer.class;
            case 2: return Integer.class;
            case 3: return Float.class;
            case 4: return ImageIcon.class;
            case 5: return String.class;
            default: return null;
        }
    }
}
