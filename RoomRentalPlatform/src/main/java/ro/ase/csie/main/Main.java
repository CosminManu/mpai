package ro.ase.csie.main;

import ro.ase.csie.db.*;
import ro.ase.csie.designPatterns.CristinaObserver.IUser;
import ro.ase.csie.designPatterns.CristinaObserver.ORoom;
import ro.ase.csie.designPatterns.CristinaObserver.OUser;
import ro.ase.csie.models.User;
import ro.ase.csie.models.Room;
import ro.ase.csie.models.Rental;
import ro.ase.csie.models.Payment;

import java.util.List;
import java.util.Scanner;

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
//        User user15=new User( "Alice", "alice1@example.com");
//        userDAO.insertUser(user15);
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








        // Meniu pentru selectarea design pattern-ului
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\nSelectati design patternul pe care doriti sa il exemplificati:");
        System.out.println("1. Mihnea");
        System.out.println("2. Cristina Observer");
        System.out.println("3. Cosmin");
        System.out.println("4. Danusia");

        int selection = scanner.nextInt();
        scanner.nextLine(); // Consumă newline după introducerea numărului

// Implementăm cazul pentru "Observer"
        switch (selection) {
            case 2: {
                System.out.println("Selecteaza nume utilizator: ");
                for (User user : users) {
                    System.out.println(user.getName()); // Afișăm toți utilizatorii
                }
                String selectedUserName = scanner.nextLine();

                // Căutăm utilizatorul selectat
                IUser selectedUser = null;
                for (User user : users) {
                    if (user.getName().equals(selectedUserName)) {
                        selectedUser = new OUser(user.getName()); // Creăm un obiect OUser
                        break;
                    }
                }

                if (selectedUser != null) {
                    System.out.println("Vrei sa rezervi o sala? REZERVA");
                    String response = scanner.nextLine();

                    if ("REZERVA".equalsIgnoreCase(response)) {
                        // Creăm și ocupăm două săli exemplu
                        ORoom salaJ = new ORoom("Room J");
                        ORoom salaD = new ORoom("Room D");

                        // Sălile sunt ocupate și notificările sunt trimise
                        salaJ.ocupaSala();
                        salaD.ocupaSala();

                        // Abonăm utilizatorul la toate sălile disponibile
                        for (Room room : rooms) {
                            if (room.isAvailable()) {
                                ORoom oroom = new ORoom(room.getName());
                                oroom.abonareUtilizator(selectedUser);
                                oroom.elibereazaSala(); // Notificăm utilizatorul
                            }
                        }
                    } else {
                        System.out.println("Programul se încheie.");
                    }
                } else {
                    System.out.println("Utilizatorul selectat nu a fost găsit.");
                }
                break;
            }
            // Alte cazuri pentru selecțiile 1, 3, 4
            case 1:
                System.out.println("Opțiunea Mihnea selectată");
                break;
            case 3:
                System.out.println("Opțiunea Cosmin selectată");
                break;
            case 4:
                System.out.println("Opțiunea Danusia selectată");
                break;
            default:
                System.out.println("Selecție invalidă!");
        }









        // Închide conexiunea la baza de date
        dbManager.closeConnection();
    }
}
