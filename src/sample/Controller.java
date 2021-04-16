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
    private ObservableList<Flight> flights = FXCollections.observableArrayList();
    private ObservableList<Passenger> passengers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passengers = dataFactory.getPassengers();
        flights = dataFactory.getFlights();
    }

    public void searchButtonOnActivity(ActionEvent event){

        LocalDate date = datePicker.getValue();

        String source = sourceTextField.getText();
        String destination = destinationTextField.getText();
        int groupSize = Integer.parseInt(groupSizeTextField.getText());


//        ObservableList<Flight> availableFlights = getAvailableFlights(date, source, destination, groupSize);

//        flightsListView.setItems(availableFlights);
    }

    public static ObservableList<Flight> getAvailableFlights(LocalDate date, int source, int destination, int groupSize){
        ObservableList<Flight> availableFlights = FXCollections.observableArrayList();
        //LocalDate beforeDate = date.minusDays(1);
        //LocalDate afterDate = date.plusDays(1);

        for(Flight flight : DataFactory.getFlights()){
            if (date.equals(flight.getDateArrivalTime().toLocalDate()) &&
                    source == flight.getSource() &&
                    destination == flight.getDestination() &&
                    flight.totalAvailableSeats() >= groupSize) {
                    availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

    public static ArrayList<Seat> getAvailableSeats(String flightNumber){
        ArrayList<Seat> notAvailableSeats = new ArrayList<>();

        ArrayList<Booking> bookings = getAllBookngs();

        for(Booking booking : bookings){
            ArrayList<Seat> seats = booking.getSeats();
            for(Seat seat : seats){
                if(seat.getFlightNumber().equals(flightNumber));
                    notAvailableSeats.add(seat);
            }


        }


        // find flight that we want to book
        Flight ourFlight = null;
        for(Flight flight : DataFactory.getFlights()){
            if(flight.getFlightNumber().equals(flightNumber)){
                ourFlight = flight;
                break;
            }
        }

       ArrayList<Seat> availableSeats = ourFlight.getSeats();

       availableSeats.removeAll(notAvailableSeats);
//
//        for(Seat seat : ourSeats){
//            if(!seat.isBooked()){
//                availableSeats.add(seat);
//            }
//        }
//
       return availableSeats;
    }

    public static ArrayList<Booking> getAllBookngs() {
        ArrayList<Booking> bookings = new ArrayList<>();
        ObservableList<Passenger> passengers = DataFactory.getPassengers();

        for(Passenger passenger : passengers)
            bookings.addAll(passenger.getBookings());

        return bookings;

    }
    public static void bookFlight(String flightNumber, ArrayList<Seat> seats, Passenger passenger, int bags, int oddSized, int pillows, int blankets, boolean handicaped){
        //bý til nýtt booking object
        ArrayList<Booking> bookings = new ArrayList<>();
        Booking booking = new Booking(seats, passenger, bags, oddSized, pillows, blankets, handicaped);
        bookings.add(booking);
        passenger.setBookings(bookings);

        // find flight that we want to book
        Flight ourFlight = null;
        for(Flight flight : DataFactory.getFlights()){
            if(flight.getFlightNumber().equals(flightNumber)){
                ourFlight = flight;
                break;
            }
        }

        ArrayList<Seat> flightSeats = ourFlight.getSeats();

        //hér þarf svo að stilla sko isBooked fyrir viðeigandi sæti
        //þetta gæti verið glöötuð lykkja endilega skulum skoða betur
        int i = 0;
        for(Seat seat : flightSeats){
            String seatNumber = seat.getSeatNumber();
            if(i>= seats.size()) {
                break;
            }
            String seatNumber2 = seats.get(i).getSeatNumber(); //okei veit ekki hvort megi hehö
            if(seatNumber.equals(seatNumber2)){
                seat.setBooked(true);
                i+=1;
            }
        }
    }

}