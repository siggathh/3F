package sample;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Observable;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class DataFactory {

    public ArrayList<Seat> getSeats(String flightNumber, int total_seats) {
        ArrayList<Seat> seats = new ArrayList<Seat>();
        for (int k = 0; k < (total_seats / 4); k++) {
            String flightClass;
            if (k < (total_seats / 4)) {
                flightClass = "firstClass";
            } else {
                flightClass = "economyClass";
            }

            seats.add(new Seat(Integer.toString(k) + "A", flightNumber, false, flightClass));
            seats.add(new Seat(Integer.toString(k) + "B", flightNumber, false, flightClass));
            seats.add(new Seat(Integer.toString(k) + "C", flightNumber, false, flightClass));
            seats.add(new Seat(Integer.toString(k) + "D", flightNumber, false, flightClass));
        }

        return seats;
    }

    public ObservableList<Flight> getFlights() {
        ObservableList<Flight> flightslist = FXCollections.observableArrayList();

        int[] myNum = {19, 32, 37, 72};
        //LocalDate date = LocalDate.of(2021, 07, 14);

        LocalDateTime departure = ?;
        LocalDateTime arrival = ?;
        //creates mock flights
        Flight flight1 = new Flight(
                "FI741",
                myNum[(int) Math.random() * 4],
                "Reykjavík",
                "Akureyri",
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight1.setSeats(getSeats(flight1.getFlightNumber(), flight1.getTotal_seats()));
        flightslist.add(flight1);

        Flight flight2 = new Flight(
                "FI852",
                myNum[(int) Math.random() * 4],
                "Reykjavík",
                "Egilsstaðir",
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight2.setSeats(getSeats(flight2.getFlightNumber(), flight2.getTotal_seats()));
        flightslist.add(flight2);

        Flight flight3 = new Flight(
                "FI574",
                myNum[(int) Math.random() * 4],
                "Reykjavík",
                "Ísafjörður",
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight3.setSeats(getSeats(flight3.getFlightNumber(), flight3.getTotal_seats()));
        flightslist.add(flight3);

        Flight flight4 = new Flight(
                "FEI943",
                myNum[(int) Math.random() * 4],
                "Reykjavík",
                "Vestmannaeyjar",
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight4.setSeats(getSeats(flight4.getFlightNumber(), flight4.getTotal_seats()));
        flightslist.add(flight4);

        Flight flight5 = new Flight(
                "FEI638",
                myNum[(int) Math.random() * 4],
                "Húsavík",
                "Reykjavík",
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight5.setSeats(getSeats(flight5.getFlightNumber(), flight5.getTotal_seats()));
        flightslist.add(flight5);

        Flight flight6 = new Flight(
                "FEI422",
                myNum[(int) Math.random() * 4],
                "Höfn í Hornafirði",
                "Reykjavík",
                departure,
                arrival,
                null,
                30000 + (int) Math.random() * (100000 - 30000 + 1)
        );
        flight6.setSeats(getSeats(flight6.getFlightNumber(), flight6.getTotal_seats()));
        flightslist.add(flight6);
        return flightslist;
    }

    public ObservableList<Passenger> getPassengers(){

        ObservableList<Passenger> passengers = FXCollections.observableArrayList();
        Passenger passenger1 = new Passenger("1", "Sigga", "Hall");
        Passenger passenger2 = new Passenger("2", "Signý", "Hall");
        Passenger passenger3 = new Passenger("3", "Inga", "Hall");

        ObservableList<Flight> flights = getFlights();
        Flight flight = flights.get(0);

        ArrayList<Booking> bookings1 = new ArrayList<>();
        bookings1.add(new Booking(flight.getSeats().get(0), passenger1, 1, 1,1,1));
        flight.getSeats().get(0).setBooked(true);
        passenger1.setBookings(bookings1);

        ArrayList<Booking> bookings2 = new ArrayList<>();
        bookings2.add(new Booking(flight.getSeats().get(1), passenger2, 1, 1,1,5));
        flight.getSeats().get(1).setBooked(true);
        passenger2.setBookings(bookings2);

        ArrayList<Booking> bookings3 = new ArrayList<>();
        bookings3.add(new Booking(flight.getSeats().get(2), passenger3, 0, 10,1,1));
        flight.getSeats().get(2).setBooked(true);
        passenger3.setBookings(bookings3);

        passengers.add(passenger1);
        passengers.add(passenger2);
        passengers.add(passenger3);

        return passengers;
    }


}
