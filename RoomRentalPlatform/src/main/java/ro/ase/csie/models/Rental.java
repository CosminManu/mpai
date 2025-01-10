package ro.ase.csie.models;

import java.time.LocalDate;

import java.time.LocalDate;

public class Rental {
    private int idRental;
    private int idUser;
    private int idRoom;
    private LocalDate startDate;
    private LocalDate endDate;

    public Rental(int idRental, int idUser, int idRoom, LocalDate startDate, LocalDate endDate) {
        this.idRental = idRental;
        this.idUser = idUser;
        this.idRoom = idRoom;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getIdRental() {
        return idRental;
    }

    public void setIdRental(int idRental) {
        this.idRental = idRental;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRoom() {
        return idRoom;
    }

    public void setIdRoom(int idRoom) {
        this.idRoom = idRoom;
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

    @Override
    public String toString() {
        return "Rental{idRental=" + idRental + ", idUser=" + idUser + ", idRoom=" + idRoom +
                ", startDate=" + startDate + ", endDate=" + endDate + "}";
    }
}
