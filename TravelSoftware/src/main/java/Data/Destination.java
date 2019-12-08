/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;


/**
 * Class to store weather information
 * @author johannesriedmueller
 */
public class Destination {
    private String cityName;
    private WeatherInformation main;
    private WeatherBasicInformation[] weather;

    public Destination(String cityName, WeatherInformation main, WeatherBasicInformation weather) {
        this.cityName = cityName;
        this.main = main;
        WeatherBasicInformation[] weatherArr = {weather};
        this.weather = weatherArr;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }
    
    public WeatherBasicInformation getWeatherBasicInfo() {
        return weather[0];
    }
    
    public String getCityName() {
        return cityName;
    }

    public WeatherInformation getWeatherInfo() {
        return main;
    }
    
}
