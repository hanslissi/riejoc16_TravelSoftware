/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

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
public class WeatherTableModelTest {
    
    @Parameter(value = 0)
    public int index;

    @Parameter(value = 1)
    public ArrayList<Destination> destinations;
    
    @Parameter(value = 2)
    public Destination destination;
    
    @Parameter(value = 3)
    public Destination destinationExpAtIdx;
    
    @Parameters(name = "{index}: testGetBestDestination({0},{1})={2}")
    public static Iterable<Object[]> data1() {
        ArrayList<Destination> list = new ArrayList<Destination>() {
            {
                add(new Destination("Graz", new WeatherInformation(3.0f, 1020, 80), new WeatherBasicInformation()));
                add(new Destination("Vienna", new WeatherInformation(4.0f, 1023, 85), new WeatherBasicInformation()));
                add(new Destination("Nice", new WeatherInformation(24.0f, 998, 86), new WeatherBasicInformation()));
            }
        };
        Destination dest1 = new Destination("Oslo", new WeatherInformation(-10.0f, 990, 86), new WeatherBasicInformation()); 
        return Arrays.asList(new Object[][]{
            {1, list, dest1, dest1},
            {2, list, dest1, dest1},
            {-4, list, dest1, null},
            {42, list, dest1, null}
        });
    }
    
    public WeatherTableModelTest() {
    }

    /**
     * Test of edit method, of class WeatherTableModel.
     */
    @org.junit.Test
    public void testEdit() {
        WeatherTableModel model = new WeatherTableModel();
        model.addAllAndClearOld(destinations);
        try{
            model.edit(destination, index);
            Assertions.assertEquals(destinationExpAtIdx, model.getValueAt(index, 0));
        } catch (Exception ex) {
            if(destinationExpAtIdx == null) {
                Assertions.assertTrue(ex instanceof IllegalArgumentException);
            }
        }
    }

}
