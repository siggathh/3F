package sample;

import javafx.collections.ObservableList;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

import static org.junit.Assert.*;

public class GetAvaliableFlightsTest {
    private Controller controller;
    //should return all available flight
    @Test
    public void testAvailableFlightsFromReykjavikToAkureyri() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,07, 14), "Reykjavik", "Akureyri");

        for (Flight flight : flights) {
            assertEquals(2021 - 03 - 31, flight.getDate());
            assertEquals("Reykjavík", flight.getSource());
            assertEquals("Akureyri", flight.getDestination());
        }

    }

    //Illegal destination should get exception
    @Test(expected = IllegalArgumentException.class)
    public void testFlightsForUnavailableDestination() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,03 ,31), "Reykjavik", "Blabla");

    }

    //Illegal source should get exception
    @Test(expected = IllegalArgumentException.class)
    public void testFlightsForUnavailableSource() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,03 ,31), "blabla", "Akureyri");

    }

}
