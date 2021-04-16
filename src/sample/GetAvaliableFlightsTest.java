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
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,07, 14), CNST.RVK, CNST.AK,5);

        for (Flight flight : flights) {
            assertEquals(2021, flight.getDateArrivalTime().getYear());
            assertEquals(07,  flight.getDateArrivalTime().getMonthValue());
            assertEquals(14,  flight.getDateArrivalTime().getDayOfMonth());
            assertEquals("Reykjavík", flight.getSource());
            assertEquals("Akureyri", flight.getDestination());
        }

    }

    @Test
    public void testAvailableSeats(){
        ObservableList<Passenger> passengers = DataFactory.getPassengers();
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,07, 14), CNST.RVK, CNST.AK,5);
//        Flight flight = flights.get(0);
//        Passenger passenger = new Passenger("6", "Inga", "Hall");
//        ArrayList seatsToBook = new ArrayList<>();
//        seatsToBook.add(flight.getSeats().get(0));
//        seatsToBook.add(flight.getSeats().get(1));
//        seatsToBook.add(flight.getSeats().get(2));


//        controller.bookFlight(flight.getFlightNumber(), seatsToBook, passenger, 1, 3, 6, 8,false);
        ObservableList<Seat> seats = controller.getAvailableSeats(flights.get(0).getFlightNumber());
        Assert.assertEquals(17, seats.size());

    }

    @Test
    public void testDataFactory(){
        ObservableList<Flight> flights = DataFactory.getFlights();
//        assertEquals(null, flights.get(0).toString());
        Flight flight = flights.get(0);
        ArrayList<Seat> seats = DataFactory.getSeats(flight.getFlightNumber(), flight.getTotal_seats());
        assertEquals(17, seats.get(0).getSeatNumber());
    }

    //Illegal destination should get exception
    @Test(expected = IllegalArgumentException.class)
    public void testFlightsForUnavailableDestination() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,03 ,31), CNST.RVK, 6, 3);

    }

    //Illegal source should get exception
    @Test(expected = IllegalArgumentException.class)
    public void testFlightsForUnavailableSource() {
        ObservableList<Flight> flights = controller.getAvailableFlights(LocalDate.of(2021,03 ,31), 6, CNST.RVK,1);

    }

}
