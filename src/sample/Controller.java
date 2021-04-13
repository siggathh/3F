package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import java.time.LocalDate;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {
    @FXML
    private Button searchButton;
    @FXML
    private ListView flightsListView;
    @FXML
    private TextField yearTextField;
    @FXML
    private TextField dayTextField;
    @FXML
    private TextField monthTextField;
    @FXML
    private TextField sourceTextField;
    @FXML
    private TextField destinationTextField;
    private DataFactory dataFactory = new DataFactory();
    private ObservableList<Flight> flights = FXCollections.observableArrayList();
    private ObservableList<Passenger> passengers = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        passengers = dataFactory.getPassengers();
        flights = dataFactory.getFlights();
    }

    public void searchButtonOnActivity(ActionEvent event){

        int year = Integer.parseInt(yearTextField.getText()); // hér þarf að breyta í int úr string sigga reddar
        int day = Integer.parseInt(dayTextField.getText());
        int month = Integer.parseInt(monthTextField.getText());

        LocalDate date = LocalDate.of(year, month, day);

        String source = sourceTextField.getText();
        String destination = destinationTextField.getText();

        ObservableList<Flight> availableFlights = getAvailableFlights(date, source, destination);

        flightsListView.setItems(availableFlights);
    }

    public ObservableList<Flight> getAvailableFlights(LocalDate date, String source, String destination){
        ObservableList<Flight> availableFlights = FXCollections.observableArrayList();
        LocalDate beforeDate = date.minusDays(1);
        LocalDate afterDate = date.plusDays(1);

        for(Flight flight : flights){
            if (((date.equals(flight.getDate()) || (beforeDate.equals(flight.getDate())) || (afterDate.equals(flight.getDate()))) &&
                    source.equals(flight.getSource()) &&
                    destination.equals(flight.getDestination()))) {
                     availableFlights.add(flight);
            }
        }

        return availableFlights;
    }

}