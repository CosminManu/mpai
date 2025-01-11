package ro.ase.csie.main;

import ro.ase.csie.db.*;
import ro.ase.csie.models.User;
import ro.ase.csie.models.Room;
import ro.ase.csie.models.Rental;
import ro.ase.csie.models.Payment;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Obține instanța DatabaseManager și DAO-ul pentru User
        DatabaseManager dbManager = DatabaseManager.getInstance();

        //DAO
        UserDAO userDAO = new UserDAO(dbManager.getConnection());
        RoomDAO roomDAO = new RoomDAO(dbManager.getConnection());
        RentalDAO rentalDAO = new RentalDAO(dbManager.getConnection());
        PaymentDAO paymentDAO = new PaymentDAO(dbManager.getConnection());

        // Creează schema (tabelele) în baza de date
        dbManager.createSchema();

        // Inserează date de test
        dbManager.insertTestData();
        // Exemple de utilizare DAO pentru a insera date
        //userDAO.insertUser("Alice", "alice1@example.com");
        //roomDAO.insertRoom("Room A", 1, "Building 1", 20, "Conference", true, true, true, 100.0);
        //rentalDAO.insertRental(1, 1, "2025-01-01", "2025-01-05");
        //paymentDAO.insertPayment(1, 500.0, "2025-01-06", "Paid");

        // Citește utilizatorii din DB
        List<User> users = userDAO.getAllUsers();
        System.out.println("Users from DB:");
        for (User user : users) {
            System.out.println(user);
        }

        //Tot citire
        System.out.println("-------------------------------------------------------------\n");

// Afisăm camerele din baza de date
        List<Room> rooms = roomDAO.getAllRooms();
        System.out.println("Rooms from DB:");
        for (Room room : rooms) {
            System.out.println(room);
        }
        System.out.println("-------------------------------------------------------------\n");

// Afisăm închirierile (Rentals)
        List<Rental> rentals = rentalDAO.getAllRentals();
        System.out.println("Rentals from DB:");
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
        System.out.println("-------------------------------------------------------------\n");

// Afisăm plățile (Payments)
        List<Payment> payments = paymentDAO.getAllPayments();
        System.out.println("Payments from DB:");
        for (Payment payment : payments) {
            System.out.println(payment);
        }
        System.out.println("-------------------------------------------------------------\n");


        // Modifică primul utilizator
        if (!users.isEmpty()) {
            User firstUser = users.get(0);
            firstUser.setName("Updated Name");
            firstUser.setEmail("updated_email@example.com");

            // Actualizează utilizatorul în DB
            userDAO.updateUser(firstUser);

            System.out.println("Updated user: " + firstUser);
        }

        // Închide conexiunea la baza de date
        dbManager.closeConnection();
    }
}
