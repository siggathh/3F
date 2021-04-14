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
import java.time.LocalDate;

import java.net.URL;
import java.util.ArrayList;
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


        ObservableList<Flight> availableFlights = getAvailableFlights(date, source, destination, groupSize);

        flightsListView.setItems(availableFlights);
    }

    public static ObservableList<Flight> getAvailableFlights(LocalDate date, String source, String destination, int groupSize){
        ObservableList<Flight> availableFlights = FXCollections.observableArrayList();
        //LocalDate beforeDate = date.minusDays(1);
        //LocalDate afterDate = date.plusDays(1);

        for(Flight flight : DataFactory.getFlights()){
            if (date.equals(flight.getDateArrivalTime().toLocalDate()) &&
                    source.equals(flight.getSource()) &&
                    destination.equals(flight.getDestination()) &&
                    flight.totalAvailableSeats() >= groupSize) {
                    availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

    public ObservableList<Seat> getAvailableSeats(String flightNumber){
        ObservableList<Seat> availableSeats = FXCollections.observableArrayList();

        // find flight that we want to book
        Flight ourFlight = null;
        for(Flight flight : flights){
            if(flight.getFlightNumber().equals(flightNumber)){
                ourFlight = flight;
                break;
            }
        }

        ArrayList<Seat> ourSeats = ourFlight.getSeats();

        for(Seat seat : ourSeats){
            if(seat.isBooked() == false){
                availableSeats.add(seat);
            }
        }

        return availableSeats;
    }

    public void bookFlight(String flightNumber, ArrayList<Seat> seats, Passenger passenger, int groupSize, int bags, int oddSized, int pillows, int blankets, boolean handicaped){
        //bý til nýtt booking object
        Booking booking = new Booking(seats, passenger, groupSize, bags, oddSized, pillows, blankets, handicaped);

        // find flight that we want to book
        Flight ourFlight = null;
        for(Flight flight : flights){
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
            i = 0;
            String seatNumber = seat.getSeatNumber();
            String seatNumber2 = seats.get(i).getSeatNumber(); //okei veit ekki hvort megi hehö

            if(seatNumber.equals(seatNumber2)){
                seat.setBooked();
                i+=1;
            }
        }
    }

}