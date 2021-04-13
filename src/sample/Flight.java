package sample;

import java.time.LocalDate;
import java.util.ArrayList;

//flights class
public class Flight{
    private String flightNumber;
    private int total_seats;
    private String source;
    private String destination;
    private LocalDateTime dateDepartTime;
    private LocalDateTime dateArriveTime;
    private ArrayList<Seat> seats;
    private int basePrice;


    public Flight(String flightNumber, int total_seats, String source, String destination, String time, LocalDateTime dateDepartTime, LocalDateTime dateArriveTime, ArrayList<Seat> seats, int basePrice) {
        this.flightNumber = flightNumber;
        this.total_seats = total_seats;
        this.source = source;
        this.destination = destination;
        this.dateDepartTime = dateDepartTime;
        this.dateArriveTime = dateArrivTime;
        this.seats = seats;
        this.basePrice = basePrice;
    }

    //check if the flight is full or not
    public boolean isFull(){
        if (flight.totalAvailableSeats() <= 0){
            return true;
        }
        else{
            return false;
        }
    }

    //gets number available seats
    public int totalAvailableSeats(){
        int count = 0;

        for(Seats seat : seats)
            if(seat.isBooked() == false){
                count += 1;
            }

        return count;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", time='" + time + '\'' +
                ", Time Departure='" + dateDepartTime + '\'' +
                ", Time Arrival='" + dateArriveTime + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }

    public LocalDateTime getdateDepartTime(){
        return this.dateDepartTime;
    }

    public LocalDateTime getdateArriveTime(){
        return this.dateArriveTime;
    }

    public String getSource(){
        return this.source;
    }

    public String getDestination(){
        return this.destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public int getTotal_seats() {
        return total_seats;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setTotal_seats(int total_seats) {
        this.total_seats = total_seats;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public LocalDateTime setdateDepartTime(LocalDateTime dateDepartTime){
        this.dateDepartTime = dateDepartTime;
    }

    public LocalDateTime setdateArriveTime(LocalDateTime dateArriveTime){
        this.dateArriveTime = dateArriveTime;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
