package ro.ase.csie.designPatterns.AbstractFactory;

import ro.ase.csie.models.Payment;
import ro.ase.csie.models.Rental;
import ro.ase.csie.models.Room;
import ro.ase.csie.models.User;

import java.time.LocalDate;

public interface EntityFactory {
    User createUser(int id, String name, String email);

    Room createRoom(int id, String name, int floor, String location, int capacity, String type, boolean isAvailable, boolean hasProjector, boolean hasSmartBoard, double pricePerDay);

    Rental createRental(int id, int idUser, int idRoom, LocalDate startDate, LocalDate endDate);

    Payment createPayment(int id, int idUser, double amount, LocalDate paymentDate, String status);

}