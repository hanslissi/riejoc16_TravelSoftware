/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.time.LocalDate;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherInformation {
    private float temp;
    private int pressure;
    private int humidity;
    private float temp_min;
    private float temp_max;
    private LocalDate date;

    public WeatherInformation(float temp, int pressure, int humidity) {
        this.temp = temp;
        this.pressure = pressure;
        this.humidity = humidity;
    }
    
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public float getTemp() {
        return temp;
    }

    public int getPressure() {
        return pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public float getTemp_min() {
        return temp_min;
    }

    public float getTemp_max() {
        return temp_max;
    }
    
}
