package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import javax.xml.crypto.Data;
import java.lang.reflect.Array;
import java.time.LocalDate;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button searchButton;
    @FXML
    private ListView flightsListView;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField sourceTextField;
    @FXML
    private TextField destinationTextField;
    @FXML
    private TextField groupSizeTextField;

    private DataFactory dataFactory = new DataFactory();
    private static ObservableList<Flight> flights = FXCollections.observableArrayList();
    private ObservableList<Passenger> passengers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passengers = dataFactory.getPassengers();
        flights = dataFactory.getFlights();
    }

    public void searchButtonOnActivity(ActionEvent event) {

        LocalDate date = datePicker.getValue();

        String source = sourceTextField.getText();
        String destination = destinationTextField.getText();
        int groupSize = Integer.parseInt(groupSizeTextField.getText());

//        ObservableList<Flight> availableFlights = getAvailableFlights(date, source, destination, groupSize);

//       flightsListView.setItems(availableFlights);
    }

    public static ObservableList<Flight> getAvailableFlights(LocalDate date, int source, int destination, int groupSize) {
        ObservableList<Flight> availableFlights = FXCollections.observableArrayList();

        for (Flight flight : DataFactory.getFlights()) {
            if (date.equals(flight.getDateArrivalTime().toLocalDate()) &&
                    source == flight.getSource() &&
                    destination == flight.getDestination() &&
                    flight.totalAvailableSeats() >= groupSize) {
                availableFlights.add(flight);
            }
        }

        return availableFlights;
    }
//
//    public static ObservableList<Seat> getAvailableSeats(String flightNumber){
//        ArrayList<Seat> notAvailableSeats = new ArrayList<>();
//
//        ArrayList<Booking> bookings = DataFactory.getAllBookings();
//
//        for(Booking booking : bookings) {
//            ArrayList<Seat> seats = booking.getSeats();
//            for (Seat seat : seats) {
//                if (seat.getFlightNumber().equals(flightNumber)) {
//                    notAvailableSeats.add(seat);
//                }
//                System.out.println(notAvailableSeats);
//            }
//        }
//
//        Flight ourFlight = null;
//        for(Flight flight : DataFactory.getFlights()){
//            if(flight.getFlightNumber().equals(flightNumber)){
//                ourFlight = flight;
//                break;
//            }
//        }
//
//       ArrayList<Seat> availableSeats = diffSeat(notAvailableSeats, ourFlight.getSeats());
//        System.out.println(availableSeats.size());
//
//       availableSeats.removeAll(notAvailableSeats);
//        System.out.println(availableSeats);
//
//       return FXCollections.observableArrayList(availableSeats);
//    }

    public static ObservableList<Seat> getAvailableSeats(String flightNumber) {
        ObservableList<Seat> availableSeats = FXCollections.observableArrayList();

        Flight ourFlight = null;
        for (Flight flight : DataFactory.getFlights()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                ourFlight = flight;
                break;
            }
        }

        ArrayList<Seat> ourSeats = ourFlight.getSeats();

        for (Seat seat : ourSeats) {
            if (!seat.isBooked()) {
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }

//    private static ArrayList<Seat> diffSeat(ArrayList<Seat> notAvailable, ArrayList<Seat> all){
//        ArrayList<Seat> availableSeats = new ArrayList<>();
//
//        ArrayList<String> notAvailableSeatNumber = new ArrayList<>();
//        for(Seat notSeat : notAvailable) {
//            notAvailableSeatNumber.add(notSeat.getSeatNumber());
//        }
//
//        ArrayList<String> seatNumbers = new ArrayList<>();
//        for(Seat seat : all){
//            seatNumbers.add(seat.getSeatNumber());
//        }
//
//       seatNumbers.removeAll(notAvailableSeatNumber);
//
//        for(Seat seat :all){
//            if(seatNumbers.contains(seat.getSeatNumber())){
//                availableSeats.add(seat);
//            }
//        }
//
//
//        return availableSeats;
//    }


    public static boolean bookFlight(String flightNumber, ArrayList<Seat> seats, Passenger passenger, int bags, int oddSized, int pillows, int blankets, boolean disability) {
        //b?? til n??tt booking object
        boolean b = false;

        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking(seats, passenger, bags, oddSized, pillows, blankets, disability);
        bookings.add(booking);
        passenger.setBookings(bookings);

        // find flight that we want to book
        Flight ourFlight = null;
        for (Flight flight : DataFactory.getFlights()) {
            if (flight.getFlightNumber().equals(flightNumber)) {
                ourFlight = flight;
                break;
            }
        }

        ArrayList<Seat> flightSeats = ourFlight.getSeats();

        //h??r ??arf svo a?? stilla sko isBooked fyrir vi??eigandi s??ti
        int i = 0;
        for (Seat seat : flightSeats) {
            String seatNumber = seat.getSeatNumber();
            if (i >= seats.size()) {
                break;
            }
            String seatNumber2 = seats.get(i).getSeatNumber();
            if (seatNumber.equals(seatNumber2)) {
                seat.setBooked(true);
                i += 1;
            }
        }
        if(bookings.size() != 0){
            b = true;
        }
        return b;

    }

}