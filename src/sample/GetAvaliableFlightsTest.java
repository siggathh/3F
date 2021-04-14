package sample;

import javafx.collections.ObservableList;
import org.junit.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Observable;

import static org.junit.Assert.*;

public class GetAvaliableFlightsTest {
    private Controller controller;

    private DataFactory dataFactory = new DataFactory();
    //should return all available flight
    @Test
    public void testAvailableFlightsFromReykjavikToAkureyri() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,07, 14), "Reykjavik", "Akureyri",5);

        for (Flight flight : flights) {
            assertEquals(2021, flight.getDateArrivalTime().getYear());
            assertEquals(07,  flight.getDateArrivalTime().getMonthValue());
            assertEquals(14,  flight.getDateArrivalTime().getDayOfMonth());
            assertEquals("Reykjavík", flight.getSource());
            assertEquals("Akureyri", flight.getDestination());
        }

    }

    @Test
    public void testDataFactory(){
        ObservableList<Flight> flights = DataFactory.getFlights();
        assertEquals(null, flights.get(0).toString());
    }

    //Illegal destination should get exception
    @Test(expected = IllegalArgumentException.class)
    public void testFlightsForUnavailableDestination() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,03 ,31), "Reykjavik", "Blabla", 3);

    }

    //Illegal source should get exception
    @Test(expected = IllegalArgumentException.class)
    public void testFlightsForUnavailableSource() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,03 ,31), "blabla", "Akureyri",1);

    }

}
