package sample;

import java.time.LocalDate;
import java.util.ArrayList;

//flights class
public class Flight{
    private String flightNumber;
    private int total_seats;
    private String source;
    private String destination;
    private String time;
    private LocalDate date;
    private ArrayList<Seat> seats;
    private String duration;
    private int basePrice;


    public Flight(String flightNumber, int total_seats, String source, String destination, String time, LocalDate date, ArrayList<Seat> seats, String duration, int basePrice) {
        this.flightNumber = flightNumber;
        this.total_seats = total_seats;
        this.source = source;
        this.destination = destination;
        this.time = time;
        this.date = date;
        this.seats = seats;
        this.duration = duration;
        this.basePrice = basePrice;
    }

    //Always returns false for now, but should check if the flight is full or not
    public boolean isFull(){
        return false;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "flightNumber='" + flightNumber + '\'' +
                ", time='" + time + '\'' +
                ", duration='" + duration + '\'' +
                ", basePrice=" + basePrice +
                '}';
    }

    public LocalDate getDate(){
        return this.date;
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

    public String getTime() {
        return time;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public String getDuration() {
        return duration;
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

    public void setTime(String time) {
        this.time = time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setSeats(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }
}
