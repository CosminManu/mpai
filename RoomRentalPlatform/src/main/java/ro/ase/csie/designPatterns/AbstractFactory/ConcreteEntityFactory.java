package ro.ase.csie.designPatterns.AbstractFactory;

import ro.ase.csie.models.Payment;
import ro.ase.csie.models.Rental;
import ro.ase.csie.models.Room;
import ro.ase.csie.models.User;

import java.time.LocalDate;

public class ConcreteEntityFactory implements EntityFactory{

    @Override
    public User createUser(int id, String name, String email) {
        return new User(id, name, email);
    }

    @Override
    public Room createRoom(int id, String name, int floor, String location, double capacity, String type, boolean isAvailable, boolean hasProjector, boolean hasSmartBoard, double pricePerDay) {
        return new Room(id, name, floor, location, capacity, type, isAvailable, hasProjector, hasSmartBoard, pricePerDay);
    }

    @Override
    public Rental createRental(int id, int idUser, int idRoom, LocalDate startDate, LocalDate endDate) {
        return new Rental(id, idUser, idRoom, startDate, endDate);
    }

    @Override
    public Payment createPayment(int id, int idUser, double amount, LocalDate paymentDate, String status) {
        return new Payment(id, idUser, amount, paymentDate, status);
    }

}
