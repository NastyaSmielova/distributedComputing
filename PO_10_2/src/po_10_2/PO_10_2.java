/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package po_10_2;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PO_10_2 {

    public static void main(String[] args) {
        try {
            Map2 m = new Map2("map","localhost",3306);
          /*  m.showCountries();
            m.addCountry(7, "RUSSIA");
            m.addCountry(14, "JAPAN");
            m.addCountry(6, "UKRAINE");
            m.addCity(90, "Kharkiv", 6, 8880, 0);
            m.addCity(90, "NeyYork", 2, 88080, 0);
            m.deleteCity(4);
            m.deleteCity(90);
            m.deleteCountry(16);
            m.deleteCountry(7);
            m.changeCountryName(6, "POLSKA");
            m.changeCountryName(16, "UK");
            m.changeCityInfo(3, "WASHINGTON", 2, 99999, 1);
            m.changeCityInfo(3, "WASHINGTON", 2777, 99999, 1);
            m.changeCityInfo(30, "WASHINGTON", 2777, 99999, 1);


            m.showCountries();
            m.showCities();
            m.showCitiesFromCountry(1);
            
            m.showCapitals();
            */
        } catch (Exception ex) {
            Logger.getLogger(PO_10_2.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
