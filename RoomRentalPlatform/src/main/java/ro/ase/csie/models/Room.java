package ro.ase.csie.models;

public class Room {
    private int id;
    private String name;
    private double size;    //square meters
    private double pricePerDay;   //per day
    private boolean isAvailable;

    public Room(int id, String name, double size, double pricePerDay, boolean isAvailable) {
        this.id = id;
        this.name = name;
        this.size = size;
        this.pricePerDay = pricePerDay;
        this.isAvailable = isAvailable;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public String toString() {
        return "Room{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", size=" + size +
                ", pricePerDay=" + pricePerDay +
                ", isAvailable=" + isAvailable +
                '}';
    }
}