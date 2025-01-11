package ro.ase.csie.models;

public class Room {
    private int idRoom;
    private String name;
    private int floor;
    private String location;
    private int capacity;
    private String type;
    private boolean isAvailable;
    private boolean hasProjector;
    private boolean hasSmartBoard;
    private double pricePerDay;
//    private List<IUser> users; //Mihnea

    public Room(int idRoom, String name, int floor, String location, int capacity, String type, boolean isAvailable,
                boolean hasProjector, boolean hasSmartBoard, double pricePerDay) {
        this.idRoom = idRoom;
        this.name = name;
        this.floor = floor;
        this.location = location;
        this.capacity = capacity;
        this.type = type;
        this.isAvailable = isAvailable;
        this.hasProjector = hasProjector;
        this.hasSmartBoard = hasSmartBoard;
        this.pricePerDay = pricePerDay;
        //this.users = null;
    }

    public Room(String name) {
        this.name = name;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean hasProjector() {
        return hasProjector;
    }

    public void setHasProjector(boolean hasProjector) {
        this.hasProjector = hasProjector;
    }

    public boolean hasSmartBoard() {
        return hasSmartBoard;
    }

    public void setHasSmartBoard(boolean hasSmartBoard) {
        this.hasSmartBoard = hasSmartBoard;
    }

    public double getPricePerDay() {
        return pricePerDay;
    }

    public void setPricePerDay(double pricePerDay) {
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String toString() {
        return "Room{ name='" + name + "', floor=" + floor + ", location='" + location + "', capacity=" + capacity +
                ", type='" + type + "', isAvailable=" + isAvailable + ", hasProjector=" + hasProjector + ", hasSmartBoard=" + hasSmartBoard +
                ", pricePerDay=" + pricePerDay + "}";
    }
}
