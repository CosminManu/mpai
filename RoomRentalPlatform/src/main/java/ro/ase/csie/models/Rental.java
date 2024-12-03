package ro.ase.csie.models;

import java.time.LocalDate;

public class Rental {

    private int id;
    private User user;
    private Room room;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental(int id, User user, Room room, LocalDate startDate, LocalDate endDate) {
        this.id = id;
        this.user = user;
        this.room = room;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getDurationInDays(){
        return java.time.temporal.ChronoUnit.DAYS.between(startDate, endDate);
    }

    public double calculateTotalCost() {
        return getDurationInDays() * room.getPricePerDay();
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", user=" + user +
                ", room=" + room +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
