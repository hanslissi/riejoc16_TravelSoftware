/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import Enums.WeatherType;

/**
 *
 * @author johannesriedmueller
 */
public class Destination {
    private String cityName;
    private float humidity;
    private float pressure;
    private float temperature;
    private WeatherType weatherType;

    public Destination(String cityName, float humidity, float preassure, float temperature, WeatherType weatherType) {
        this.cityName = cityName;
        this.humidity = humidity;
        this.pressure = preassure;
        this.temperature = temperature;
        this.weatherType = weatherType;
    }

    public String getCityName() {
        return cityName;
    }

    public float getHumidity() {
        return humidity;
    }

    public float getPressure() {
        return pressure;
    }

    public float getTemperature() {
        return temperature;
    }

    public WeatherType getWeatherType() {
        return weatherType;
    }
    
    
}
