package sample;

public class Booking {
    private Seat seat;
    private Passenger passenger;
    private int bags;
    private int oddSized;
    private int blankets;
    private int pillows;

    public Booking(Seat seat, Passenger passenger, int bags, int oddSized, int blankets, int pillows) {
        this.seat = seat;
        this.passenger = passenger;
        this.bags = bags;
        this.oddSized = oddSized;
        this.blankets = blankets;
        this.pillows = pillows;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
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
