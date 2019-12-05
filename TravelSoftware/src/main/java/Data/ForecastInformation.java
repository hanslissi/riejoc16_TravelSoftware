/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author johannesriedmueller
 */
public class ForecastInformation {
    private String dt_txt;
    private WeatherInformation main;
    private WeatherBasicInformation[] weather;
    private static DateTimeFormatter dtf;

    public ForecastInformation(String dt_txt, WeatherInformation weatherInfo) {
        this.dt_txt = dt_txt;
        this.main = weatherInfo;
    }
    
    static
    {
        dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    }

    public WeatherBasicInformation getWeatherBasicInfo() {
        return weather[0];
    }
    
    public LocalDateTime getDateTime() {
        return LocalDateTime.parse(dt_txt, dtf);
    }

    public WeatherInformation getWeatherInfo() {
        return main;
    }
    
}
