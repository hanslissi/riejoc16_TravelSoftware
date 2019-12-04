/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package API;

import Data.WeatherInformation;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;

/**
 *
 * @author johannesriedmueller
 */
public class WeatherInfoDeserializer implements JsonDeserializer<WeatherInformation>{

    @Override
    public WeatherInformation deserialize(JsonElement je, Type type, JsonDeserializationContext jdc) throws JsonParseException {
        JsonElement weatherInfo = je.getAsJsonObject().get("main");
        return new Gson().fromJson(weatherInfo, WeatherInformation.class);
    }
    
}
