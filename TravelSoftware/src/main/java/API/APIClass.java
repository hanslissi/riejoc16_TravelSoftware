/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import javax.swing.ImageIcon;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author johannesriedmueller
 */
public class APIClass {

    private static APIClass instance;
    private static String URI = "https://api.openweathermap.org/data/2.5/";
    private static String PATH_WEATHER = "weather";
    private static String PATH_FORECAST = "forecast";
    private static String APPID = "bf9ce9d4ceaa0426b362345a33429a80";
    private static Client client;
    private static WebTarget targetWeather;
    private static WebTarget targetForecast;

    private APIClass() {
        this.client = ClientBuilder.newClient();
    }

    public static synchronized APIClass getInstance() {
        if (instance == null) {
            instance = new APIClass();
        }
        return instance;
    }
    
    public Response getTodaysWeatherOf(String cityName) {
        return client.target(URI).path(PATH_WEATHER)
                .queryParam("APPID", APPID)
                .queryParam("q", cityName)
                .queryParam("units", "metric")
                .request(MediaType.APPLICATION_JSON)
                .get();
    }
    
    public Response getForecastOf(String cityName) {
        return client.target(URI).path(PATH_FORECAST)
                .queryParam("APPID", APPID)
                .queryParam("q", cityName)
                .queryParam("units", "metric")
                .request(MediaType.APPLICATION_JSON)
                .get();
    }

    public static boolean httpResponseIsOk(Response response) {
        if(response.getStatus() == 200) {
            return true;
        }
        return false;
    }
    
}
