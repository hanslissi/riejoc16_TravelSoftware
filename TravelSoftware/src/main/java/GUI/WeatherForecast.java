/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import API.APIClass;
import Data.Destination;
import Data.ForecastInformation;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDate;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherForecast extends javax.swing.JFrame {

    ForecastTableModel forecastModel = new ForecastTableModel();
    private Destination destination;
    private ArrayList<ForecastInformation> forecastInfosDays = new ArrayList<>();

    public WeatherForecast(Destination destination) {
        this.destination = destination;
        initComponents();
        taForecast.setModel(forecastModel);
        taForecast.setRowHeight(50);
        taForecast.getTableHeader().setReorderingAllowed(false);
        taForecast.setDefaultRenderer(Object.class, new ForecastTableCellRenderer());
        laCityname.setText("5-day forecast of: " + destination.getCityName());
        loadForecast();
    }

    /**
     * Loads forecast and adds the 5 days to the table.
     */
    private void loadForecast() {
        Response response = APIClass.getInstance().getForecastOf(destination.getCityName());
        if (APIClass.getInstance().httpResponseIsOk(response)) {
            String responseString = response.readEntity(String.class);

            Gson gson = new Gson();
            JsonObject forecastJsonObject = gson.fromJson(responseString, JsonObject.class);
            java.lang.reflect.Type listType = new TypeToken<ArrayList<ForecastInformation>>() {
            }.getType();
            List<ForecastInformation> forecastInfosHours = new Gson().fromJson(forecastJsonObject.get("list"), listType);
            LocalDate date = LocalDate.now();
            while(date.isBefore(LocalDate.now().plusDays(5))) {
                forecastInfosDays.add(getForecastOfDay(date, forecastInfosHours));
                date = date.plusDays(1);
            }
            forecastModel.addAll(forecastInfosDays);
        }
    }
    
    /**
     * Gets the forecast of a specific day
     * @param date day of which the weather should be found.
     * @param forecastInfosHours All forecast information in which the day should be searched
     * @return forecast at 12:00 if it finds 12:00. If not then it returns the first one it finds at the given day. If it doesn't find the day at all it returns null.
     */
    private ForecastInformation getForecastOfDay(LocalDate date, List<ForecastInformation> forecastInfosHours) {
        int idxOfFirst = -1;
        for (int i = 0; i < forecastInfosHours.size(); i++) {
            if(forecastInfosHours.get(i).getDateTime().getDayOfMonth() == date.getDayOfMonth()) {
                idxOfFirst = idxOfFirst == -1 ? i : idxOfFirst;
                if(forecastInfosHours.get(i).getDateTime().getHour() == 12) {
                    return forecastInfosHours.get(i);
                }
            }
        }
        if(idxOfFirst != -1) {
            return forecastInfosHours.get(idxOfFirst);
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        taForecast = new javax.swing.JTable();
        laCityname = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        taForecast.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(taForecast);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        laCityname.setText("Cityname");
        getContentPane().add(laCityname, java.awt.BorderLayout.PAGE_START);

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel laCityname;
    private javax.swing.JTable taForecast;
    // End of variables declaration//GEN-END:variables
}
