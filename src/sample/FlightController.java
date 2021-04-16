package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FlightController implements Initializable{

    private DataFactory dataFactory = new DataFactory();
    private ObservableList<Flight> flights = FXCollections.observableArrayList();
    private ObservableList<Passenger> passengers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passengers = dataFactory.getPassengers();
        flights = dataFactory.getFlights();
    }

    public static ObservableList<Flight> getAvailableFlights(Parameters p, boolean isTo){
        ObservableList<Flight> availableFlights = FXCollections.observableArrayList();

        int groupSize = p.getgroupSize();
        int source = 0;
        int destination = 0;
        LocalDate date = null;
        if(isTo){
            date = p.getcheckIn();

            source = p.getdepartLocation();
            destination = p.getDestination();
        }
        else{
            date = p.getcheckOut();

            source = p.getDestination();
            destination = p.getdepartLocation();
        }

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

    public void bookFlight(String flightNumber, ArrayList<Seat> seats, Passenger passenger, int bags, int oddSized, int pillows, int blankets){
        //bý til nýtt booking object
        Booking booking = new Booking(seats, passenger, bags, oddSized, pillows, blankets, false);

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
                seat.setBooked(true);
                i+=1;
            }
        }
    }

}