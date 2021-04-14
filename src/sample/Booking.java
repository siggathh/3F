package sample;

import java.util.ArrayList;

public class Booking {
    private ArrayList<Seat> seats;
    private Passenger passenger;
    private int groupSize;
    private int bags;
    private int oddSized;
    private int blankets;
    private int pillows;

    public Booking(ArrayList<Seat> seats, Passenger passenger, int groupSize, int bags, int oddSized, int blankets, int pillows) {
        this.seats = seats;
        this.passenger = passenger;
        this.groupSize = groupSize;
        this.bags = bags;
        this.oddSized = oddSized;
        this.blankets = blankets;
        this.pillows = pillows;
    }

    public ArrayList<Seat> getSeats() {
        return seats;
    }

    public void setSeat(ArrayList<Seat> seats) {
        this.seats = seats;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public int getgroupSize() {
        return groupSize;
    }

    public void setgroupSize(int groupSize) {
        this.groupSize = groupSize;
    }

    public int getBags() {
        return bags;
    }

    public void setBags(int bags) {
        this.bags = bags;
    }

    public int getOddSized() {
        return oddSized;
    }

    public void setOddSized(int oddSized) {
        this.oddSized = oddSized;
    }

    public int getBlankets() {
        return blankets;
    }

    public void setBlankets(int blankets) {
        this.blankets = blankets;
    }

    public int getPillows() {
        return pillows;
    }

    public void setPillows(int pillows) {
        this.pillows = pillows;
    }
}
