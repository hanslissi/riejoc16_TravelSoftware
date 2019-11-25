/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import java.util.Map;
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
    private static String PATH = "weather";
    private static String APPID = "bf9ce9d4ceaa0426b362345a33429a80";
    private static Client client;
    private static WebTarget target;

    private APIClass() {
        this.client = ClientBuilder.newClient();
        this.target = client.target(URI).path(PATH).queryParam("APPID", APPID);
    }

    public APIClass getInstance() {
        if (instance == null) {
            instance = new APIClass();
        }
        return instance;
    }
    
    public Response getTodaysWeatherOf(String cityName) {
        return client.target(URI).path(PATH)
                .queryParam("APPID", APPID)
                .queryParam("q", cityName)
                .request(MediaType.APPLICATION_JSON)
                .get();
    }

    
}
