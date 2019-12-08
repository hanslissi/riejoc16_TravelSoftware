/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Comparison;

import Data.Destination;
import Data.WeatherBasicInformation;
import Data.WeatherInformation;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

/**
 *
 * @author johannesriedmueller
 */
@RunWith(value = Parameterized.class)
public class DestinationComparerTest {

    @Parameter(value = 0)
    public int comparer;

    @Parameter(value = 1)
    public ArrayList<Destination> destinations;

    @Parameter(value = 2)
    public String cityNameExp;

    @Parameters(name = "{index}: testGetBestDestination({0},{1})={2}")
    public static Iterable<Object[]> data1() {
        ArrayList<Destination> list = new ArrayList<Destination>() {
            {
                add(new Destination("Graz", new WeatherInformation(3.0f, 1020, 80), new WeatherBasicInformation()));
                add(new Destination("Vienna", new WeatherInformation(4.0f, 1023, 85), new WeatherBasicInformation()));
                add(new Destination("Nice", new WeatherInformation(24.0f, 998, 86), new WeatherBasicInformation()));
            }
        };
        return Arrays.asList(new Object[][]{
            {0, list, "Nice"},
            {1, list, "Graz"},
            {2, list, "Vienna"}
        });
    }

    public DestinationComparerTest() {
    }

    /**
     * Test of getBestDestination method, of class DestinationComparer.
     */
    @org.junit.Test
    public void testGetBestDestination() {
        String result = DestinationComparer.getBestDestination(destinations, comparer);
        String expected = cityNameExp;
        Assertions.assertEquals(expected, result);
    }

}
