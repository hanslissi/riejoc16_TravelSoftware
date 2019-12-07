/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Data.Destination;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Enumeration;
import javax.swing.AbstractButton;
import javax.swing.JOptionPane;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherComparisonDialog extends javax.swing.JDialog {

    private LocalDate dayChosen = LocalDate.now();
    private int comparerChosen = 0;
    private boolean ok = false;
    private static DateTimeFormatter dtf;

    static {
        dtf = DateTimeFormatter.ofPattern("dd.MM.yyyy");
    }

    public WeatherComparisonDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        for (int i = 0; i < 5; i++) {
            cbDay.addItem(LocalDate.now().plusDays(i).format(dtf));
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bgComparer = new javax.swing.ButtonGroup();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        cbDay = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        rbTemp = new javax.swing.JRadioButton();
        jLabel4 = new javax.swing.JLabel();
        rbHumidity = new javax.swing.JRadioButton();
        jLabel5 = new javax.swing.JLabel();
        rbPressure = new javax.swing.JRadioButton();
        btCancel = new javax.swing.JButton();
        btOk = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("Weather Comparison");
        getContentPane().add(jLabel1, java.awt.BorderLayout.PAGE_START);

        jPanel1.setLayout(new java.awt.BorderLayout());

        jPanel2.setPreferredSize(new java.awt.Dimension(400, 30));
        jPanel2.setLayout(new java.awt.GridLayout(1, 2));

        jLabel2.setText("Day:");
        jPanel2.add(jLabel2);

        jPanel2.add(cbDay);

        jPanel1.add(jPanel2, java.awt.BorderLayout.PAGE_START);

        jPanel3.setLayout(new java.awt.GridLayout(4, 2));

        jLabel3.setText("Compare by:");
        jPanel3.add(jLabel3);

        bgComparer.add(rbTemp);
        rbTemp.setText("Temperature");
        jPanel3.add(rbTemp);
        jPanel3.add(jLabel4);

        bgComparer.add(rbHumidity);
        rbHumidity.setText("Humidity");
        jPanel3.add(rbHumidity);
        jPanel3.add(jLabel5);

        bgComparer.add(rbPressure);
        rbPressure.setText("Pressure");
        jPanel3.add(rbPressure);

        btCancel.setText("Cancel");
        btCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCancelActionPerformed(evt);
            }
        });
        jPanel3.add(btCancel);

        btOk.setText("OK");
        btOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btOkActionPerformed(evt);
            }
        });
        jPanel3.add(btOk);

        jPanel1.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private int getSelectedComparer() {
        String selected = "";
        for (Enumeration<AbstractButton> buttons = bgComparer.getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                selected = button.getText();
            }
        }
        if (selected.startsWith("Temp")) {
            return 0;
        } else if (selected.startsWith("Humi")) {
            return 1;
        } else if (selected.startsWith("Press")) {
            return 2;
        } else {
            return -1;
        }
    }

    public LocalDate getDayChosen() {
        return dayChosen;
    }

    public int getComparerChosen() {
        return comparerChosen;
    }
    
    public boolean isOk() {
        return ok;
    }
    private void btOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btOkActionPerformed
        ok = true;
        comparerChosen = getSelectedComparer();
        if(comparerChosen != -1) {
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(null, "You have to select an Comparer");
        }
    }//GEN-LAST:event_btOkActionPerformed

    private void btCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCancelActionPerformed
        ok = false;
        this.dispose();
    }//GEN-LAST:event_btCancelActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup bgComparer;
    private javax.swing.JButton btCancel;
    private javax.swing.JButton btOk;
    private javax.swing.JComboBox<String> cbDay;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton rbHumidity;
    private javax.swing.JRadioButton rbPressure;
    private javax.swing.JRadioButton rbTemp;
    // End of variables declaration//GEN-END:variables

}