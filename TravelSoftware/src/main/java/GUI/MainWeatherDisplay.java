/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import API.APIClass;
import Comparison.DestinationComparer;
import Data.Destination;
import Data.ForecastInformation;
import XML.XMLAccess;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.ws.rs.core.Response;
import org.jdom2.JDOMException;

/**
 *
 * @author johannesriedmueller
 */
public class MainWeatherDisplay extends javax.swing.JFrame {

    private WeatherTableModel weatherModel = new WeatherTableModel();
    private ArrayList<Destination> destinationBuffer = new ArrayList<>();
    private LocalDate travelDay = LocalDate.now();

    private static DateTimeFormatter dtf;

    static {
        dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public MainWeatherDisplay() {
        initComponents();
        taWeather.setModel(weatherModel);
        taWeather.setRowHeight(50);
        taWeather.getTableHeader().setReorderingAllowed(false);
        taWeather.setAutoCreateRowSorter(true);
        taWeather.setDefaultRenderer(Object.class, new WeatherTableCellRenderer());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pmDestination = new javax.swing.JPopupMenu();
        miDelete = new javax.swing.JMenuItem();
        miEdit = new javax.swing.JMenuItem();
        miCompare = new javax.swing.JMenuItem();
        btChangeToToday = new javax.swing.JToggleButton();
        jPanel1 = new javax.swing.JPanel();
        btAdd = new javax.swing.JButton();
        btPlan = new javax.swing.JButton();
        panelWeather = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        laDate = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        taWeather = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        meFile = new javax.swing.JMenu();
        miSave = new javax.swing.JMenuItem();

        miDelete.setText("Delete");
        miDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miDeleteActionPerformed(evt);
            }
        });
        pmDestination.add(miDelete);

        miEdit.setText("Edit");
        miEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miEditActionPerformed(evt);
            }
        });
        pmDestination.add(miEdit);

        miCompare.setText("Compare");
        miCompare.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miCompareActionPerformed(evt);
            }
        });
        pmDestination.add(miCompare);

        btChangeToToday.setText("Change to Today");
        btChangeToToday.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btChangeToTodayActionPerformed(evt);
            }
        });

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setPreferredSize(new java.awt.Dimension(400, 30));
        jPanel1.setLayout(new java.awt.GridLayout(1, 2));

        btAdd.setText("Add Destination");
        btAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddActionPerformed(evt);
            }
        });
        jPanel1.add(btAdd);

        btPlan.setText("Plan travel day");
        btPlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btPlanActionPerformed(evt);
            }
        });
        jPanel1.add(btPlan);

        getContentPane().add(jPanel1, java.awt.BorderLayout.PAGE_END);

        panelWeather.setPreferredSize(new java.awt.Dimension(400, 50));
        panelWeather.setLayout(new java.awt.GridLayout(1, 3));

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel1.setText("Weather of: ");
        panelWeather.add(jLabel1);

        laDate.setText("Today");
        panelWeather.add(laDate);

        getContentPane().add(panelWeather, java.awt.BorderLayout.PAGE_START);

        taWeather.setModel(new javax.swing.table.DefaultTableModel(
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
        taWeather.setComponentPopupMenu(pmDestination);
        taWeather.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                taWeatherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(taWeather);

        getContentPane().add(jScrollPane1, java.awt.BorderLayout.CENTER);

        meFile.setText("File");

        miSave.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        miSave.setText("Save");
        miSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miSaveActionPerformed(evt);
            }
        });
        meFile.add(miSave);

        jMenuBar1.add(meFile);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
     * Makes API call to get weather from it and adds it to the table. If it
     * doesn't find the city it will display a message.
     *
     * @param cityName The name of the city of which the weather should be
     * loaded.
     */
    private void addCity(String cityName) {
        Response response = APIClass.getInstance().getTodaysWeatherOf(cityName);
        if (APIClass.getInstance().httpResponseIsOk(response)) {
            String responseString = response.readEntity(String.class);
            Gson gson = new Gson();
            Destination destination = gson.fromJson(responseString, Destination.class);
            destination.setCityName(cityName);
            weatherModel.add(destination);
        } else {
            JOptionPane.showMessageDialog(null, "City not found...");
        }
    }

    /**
     * Saves the destination which are currently in the list. It saves it in the
     * /xml/destination.xml.
     */
    private void saveDestinations() {
        try {
            XMLAccess access = XMLAccess.getInstance();
            access.persistDestinations(weatherModel.getDestinations());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Not able to save...");
        }
    }

    /**
     * Loads Weather for specific date.
     *
     * @param destination The Destination of which the weather should be loaded
     * @param travelDay The day of which the weather should be loaded
     * @return a Destination with all necessary weather data.
     */
    private Destination loadWeatherForSpecificDate(Destination destination, LocalDate travelDay) {
        Response response = APIClass.getInstance().getForecastOf(destination.getCityName());
        if (APIClass.getInstance().httpResponseIsOk(response)) {
            String responseString = response.readEntity(String.class);

            Gson gson = new Gson();
            JsonObject forecastJsonObject = gson.fromJson(responseString, JsonObject.class);
            java.lang.reflect.Type listType = new TypeToken<ArrayList<ForecastInformation>>() {
            }.getType();
            List<ForecastInformation> forecastInfosHours = new Gson().fromJson(forecastJsonObject.get("list"), listType);
            return getWeatherOfDate(forecastInfosHours, travelDay, destination.getCityName());
        }
        return null;
    }

    /**
     * Gets the weather from a list of hourly forecasts and picks the one at
     * 12:00. If there is no entry for 12:00 on the given day it picks the firs
     * one it finds.
     *
     * @param forecastInfos list of hourly forecast infos
     * @param travelDate The day of which the weather is needed.
     * @param cityName The city name
     * @return The destination with the necessary Weather information. returns
     * null if it doesn't find the day given.
     */
    private Destination getWeatherOfDate(List<ForecastInformation> forecastInfos, LocalDate travelDate, String cityName) {
        int idxOfFirst = -1;
        for (int i = 0; i < forecastInfos.size(); i++) {
            if (forecastInfos.get(i).getDateTime().getDayOfMonth() == travelDate.getDayOfMonth()) {
                idxOfFirst = idxOfFirst == -1 ? i : idxOfFirst;
                if (forecastInfos.get(i).getDateTime().getHour() == 12) {
                    return new Destination(cityName, forecastInfos.get(i).getWeatherInfo(), forecastInfos.get(i).getWeatherBasicInfo());
                }
            }
        }
        if (idxOfFirst != -1) {
            return new Destination(cityName, forecastInfos.get(idxOfFirst).getWeatherInfo(), forecastInfos.get(idxOfFirst).getWeatherBasicInfo());
        }
        return null;
    }

    private void btAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddActionPerformed
        String cityName = JOptionPane.showInputDialog("Name of city:");
        if (cityName != null) {
            addCity(cityName);
        }
    }//GEN-LAST:event_btAddActionPerformed

    private void btPlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btPlanActionPerformed
        PlandayDialog dialog = new PlandayDialog(this, true);
        dialog.setAlwaysOnTop(true);
        dialog.setVisible(true);
        if (dialog.isOk()) {
            travelDay = dialog.getDayChosen();
            laDate.setText(travelDay.format(dtf));
            if (destinationBuffer.size() > 0) {
                destinationBuffer = weatherModel.getAllDestinations();
            }
            weatherModel.clearAll();
            panelWeather.add(btChangeToToday);
            for (Destination destination : destinationBuffer) {
                weatherModel.add(loadWeatherForSpecificDate(destination, travelDay));
            }
        }
    }//GEN-LAST:event_btPlanActionPerformed

    private void miEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miEditActionPerformed
        int row = taWeather.getRowSorter().convertRowIndexToModel(taWeather.getSelectedRow());
        String cityName = JOptionPane.showInputDialog("Name of city:");
        if (cityName != null) {
            Response response = APIClass.getInstance().getTodaysWeatherOf(cityName);
            if (APIClass.getInstance().httpResponseIsOk(response)) {
                String responseString = response.readEntity(String.class);
                Gson gson = new Gson();

                Destination destination = gson.fromJson(responseString, Destination.class);
                destination.setCityName(cityName);
                try {
                    weatherModel.edit(destination, row);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Index wrong!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "City not found...");
            }
        }
    }//GEN-LAST:event_miEditActionPerformed

    private void miDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miDeleteActionPerformed
        int row = taWeather.getRowSorter().convertRowIndexToModel(taWeather.getSelectedRow());
        weatherModel.remove(row);
    }//GEN-LAST:event_miDeleteActionPerformed

    private void miSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miSaveActionPerformed
        saveDestinations();
    }//GEN-LAST:event_miSaveActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        try {
            XMLAccess access = XMLAccess.getInstance();
            ArrayList<String> destinations = access.getDestinations();
            if (destinations != null) {
                for (String destination : destinations) {
                    addCity(destination);
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(MainWeatherDisplay.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JDOMException ex) {
            Logger.getLogger(MainWeatherDisplay.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_formWindowOpened

    private void taWeatherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_taWeatherMouseClicked
        if (evt.getClickCount() == 2) {
            int row = taWeather.getRowSorter().convertRowIndexToModel(taWeather.getSelectedRow());
            Destination selectedDest = (Destination) weatherModel.getDestinationAt(row);
            WeatherForecast forecast = new WeatherForecast(selectedDest);
            forecast.setVisible(true);
        }
    }//GEN-LAST:event_taWeatherMouseClicked

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveDestinations();
    }//GEN-LAST:event_formWindowClosing

    private void btChangeToTodayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btChangeToTodayActionPerformed
        this.travelDay = LocalDate.now();
        weatherModel.addAllAndClearOld(destinationBuffer);
        destinationBuffer.clear();
        laDate.setText("Today");
        panelWeather.remove(btChangeToToday);
    }//GEN-LAST:event_btChangeToTodayActionPerformed

    private void miCompareActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miCompareActionPerformed
        int[] selectedIndices = taWeather.getSelectedRows();
        if (selectedIndices.length > 1) {
            int[] realInices = new int[selectedIndices.length];
            for (int i = 0; i < selectedIndices.length; i++) {
                int row = taWeather.getRowSorter().convertRowIndexToModel(selectedIndices[i]);
                realInices[i] = row;
            }
            WeatherComparisonDialog dialog = new WeatherComparisonDialog(this, true);
            dialog.setVisible(true);
            if (dialog.isOk()) {
                LocalDate dateChosen = dialog.getDayChosen();
                int chosenComparer = dialog.getComparerChosen();
                ArrayList<Destination> destinationComps = new ArrayList<>();
                if (!dateChosen.isEqual(LocalDate.now())) {
                    for (Destination destination : weatherModel.getDestinations()) {
                        destinationComps.add(loadWeatherForSpecificDate(destination, dateChosen));
                    }
                } else {
                    destinationComps = weatherModel.getDestinations(realInices);
                }
                JOptionPane.showMessageDialog(null, "The best destination is " + DestinationComparer.getBestDestination(destinationComps, chosenComparer));
            }
        } else {
            JOptionPane.showMessageDialog(null, "You have to select more than one entry");
        }
    }//GEN-LAST:event_miCompareActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainWeatherDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainWeatherDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainWeatherDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainWeatherDisplay.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainWeatherDisplay().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAdd;
    private javax.swing.JToggleButton btChangeToToday;
    private javax.swing.JButton btPlan;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel laDate;
    private javax.swing.JMenu meFile;
    private javax.swing.JMenuItem miCompare;
    private javax.swing.JMenuItem miDelete;
    private javax.swing.JMenuItem miEdit;
    private javax.swing.JMenuItem miSave;
    private javax.swing.JPanel panelWeather;
    private javax.swing.JPopupMenu pmDestination;
    private javax.swing.JTable taWeather;
    // End of variables declaration//GEN-END:variables

}
