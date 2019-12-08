/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

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
    private static final String URI = "https://api.openweathermap.org/data/2.5/";
    private static final String PATH_WEATHER = "weather";
    private static final String PATH_FORECAST = "forecast";
    private static final String APPID = "bf9ce9d4ceaa0426b362345a33429a80";
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
    
    /**
     * API call to OpenWeatherMap to get the current weather of a specific city.
     * @param cityName The name of the city of which the weather should be loaded.
     * @return The http response of the API
     */
    public Response getTodaysWeatherOf(String cityName) {
        return client.target(URI).path(PATH_WEATHER)
                .queryParam("APPID", APPID)
                .queryParam("q", cityName)
                .queryParam("units", "metric")
                .request(MediaType.APPLICATION_JSON)
                .get();
    }
    
    /**
     * API call to OpenWeatherMap to get a 5 day, 3 hourly weather forecast of a specific city.
     * @param cityName The name of the city of which the weather should be loaded.
     * @return The http response of the API
     */
    public Response getForecastOf(String cityName) {
        return client.target(URI).path(PATH_FORECAST)
                .queryParam("APPID", APPID)
                .queryParam("q", cityName)
                .queryParam("units", "metric")
                .request(MediaType.APPLICATION_JSON)
                .get();
    }

    /**
     * Checks if a response has status code 200
     * @param response The http response to check
     * @return true if status is 200, false if it's something else.
     */
    public static boolean httpResponseIsOk(Response response) {
        if(response.getStatus() == 200) {
            return true;
        }
        return false;
    }
    
}
