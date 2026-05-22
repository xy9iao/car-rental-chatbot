package sg.edu.nus.car_rental_chatbot.model;

public class Car {
    private int id;
    private String name;
    private String type;
    private int seats;
    private double pricePerDay;
    private boolean available;

    public Car() {
    }

    public Car(int id, String name, String type, int seats, double pricePerDay, boolean available) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.seats = seats;
        this.pricePerDay = pricePerDay;
        this.available = available;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getSeats() {
        return seats;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public boolean isAvailable() {
        return available;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }
}