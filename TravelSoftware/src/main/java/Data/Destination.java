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
    private WeatherInformation weatherInfo;
    private WeatherType weatherType;

    public Destination(String cityName, WeatherInformation weatherInfo, WeatherType weatherType) {
        this.cityName = cityName;
        this.weatherInfo = weatherInfo;
        this.weatherType = weatherType;
    }

    public String getCityName() {
        return cityName;
    }
    
    public WeatherType getWeatherType() {
        return weatherType;
    }

    public WeatherInformation getWeatherInfo() {
        return weatherInfo;
    }
    
}
