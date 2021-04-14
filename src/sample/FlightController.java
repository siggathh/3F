package sample;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class FlightController implements Initializable {

    public static ObservableList<Flight> getAvailableFlights(Parameters p, boolean isTo){
        ObservableList<Flight> availableFlights = FXCollections.observableArrayList();

        int groupSize = p.getgroupSize();

        if(isTo){
            LocalDate date = p.getcheckIn();

            int source = p.getdepartLocation();
            int destination = p.getdestination();
        }
        else{
            LocalDate date = p.getcheckOut();

            int source = p.getdestination();
            int destination = p.getdepartLocation();
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

    public void booking(String flightNumber, ArrayList<Seat> seats, Passenger passenger, int groupSize, int bags, int oddSized, int pillows, int blankets){
        //bý til nýtt booking object
        Booking booking = new Booking(seats, passenger, groupSize, bags, oddSized, pillows, blankets);

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