/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparison;

import Data.Destination;
import java.util.ArrayList;

/**
 *
 * @author johannesriedmueller
 */
public class DestinationComparer {
    
    public static String getBestDestination(ArrayList<Destination> destinationComps, int comp) {
        String bestCity = "";
        switch(comp) {
            case 0: bestCity = getBestByTemperature(destinationComps); break;
            case 1: bestCity = getBestByHumidity(destinationComps); break;
            case 2: bestCity = getBestByPressure(destinationComps); break;
        }
        return bestCity;
    }
    
    private static String getBestByTemperature(ArrayList<Destination> destinationComps) {
        int highestTempIdx = 0;
        float highestTemp = destinationComps.get(highestTempIdx).getWeatherInfo().getTemp();
        for (int i = 1; i < destinationComps.size(); i++) {
            float tempToCompare = destinationComps.get(i).getWeatherInfo().getTemp();
            if(highestTemp < tempToCompare) {
                highestTemp = tempToCompare;
                highestTempIdx = i;
            }
        }
        return destinationComps.get(highestTempIdx).getCityName();
    }
    
    private static String getBestByHumidity(ArrayList<Destination> destinationComps) {
        int lowestHumIdx = 0;
        int lowestHum = destinationComps.get(lowestHumIdx).getWeatherInfo().getHumidity();
        for (int i = 1; i < destinationComps.size(); i++) {
            int humidityToCompare = destinationComps.get(i).getWeatherInfo().getHumidity();
            if(lowestHum > humidityToCompare) {
                lowestHum = humidityToCompare;
                lowestHumIdx = i;
            }
        }
        return destinationComps.get(lowestHumIdx).getCityName();
    }
    
    private static String getBestByPressure(ArrayList<Destination> destinationComps) {
        int highestPressIdx = 0;
        int highestPressure = destinationComps.get(highestPressIdx).getWeatherInfo().getPressure();
        for (int i = 1; i < destinationComps.size(); i++) {
            int pressToCompare = destinationComps.get(i).getWeatherInfo().getPressure();
            if(highestPressure < pressToCompare) {
                highestPressure = pressToCompare;
                highestPressIdx = i;
            }
        }
        return destinationComps.get(highestPressIdx).getCityName();
    }
}
