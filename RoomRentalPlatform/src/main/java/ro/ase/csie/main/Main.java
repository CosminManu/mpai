package ro.ase.csie.main;

import ro.ase.csie.dao.PaymentDAO;
import ro.ase.csie.dao.RentalDAO;
import ro.ase.csie.dao.RoomDAO;
import ro.ase.csie.dao.UserDAO;
import ro.ase.csie.db.*;
import ro.ase.csie.designPatterns.AbstractFactory.ConcreteEntityFactory;
import ro.ase.csie.designPatterns.AbstractFactory.EntityFactory;
import ro.ase.csie.designPatterns.Observer.IUser;
import ro.ase.csie.designPatterns.Observer.ORoom;
import ro.ase.csie.designPatterns.Observer.OUser;
import ro.ase.csie.designPatterns.Builder.RoomBuilder;
import ro.ase.csie.designPatterns.Command.CommandInvoker;
import ro.ase.csie.designPatterns.Command.CreateRentalCommand;
import ro.ase.csie.designPatterns.Command.MakePaymentCommand;
import ro.ase.csie.models.User;
import ro.ase.csie.models.Room;
import ro.ase.csie.models.Rental;
import ro.ase.csie.models.Payment;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int readInteger(Scanner scanner) {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
            }
        }
    }

    private static boolean readBoolean(Scanner scanner) {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("true")) {
                return true;
            } else if (input.equals("false")) {
                return false;
            } else {
                System.out.print("Invalid input. Please enter 'true' or 'false': ");
            }
        }
    }

    private static boolean isIdUnique(List<Room> listRoom, int idRoom) {
        for (Room room : listRoom) {
            if (room.getIdRoom() == idRoom) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Hello world!");

        // Obține instanța DatabaseManager și DAO-ul pentru User
        DatabaseManager dbManager = DatabaseManager.getInstance();
        EntityFactory factory = new ConcreteEntityFactory();

        //DAO
        UserDAO userDAO = new UserDAO(dbManager.getConnection());
        RoomDAO roomDAO = new RoomDAO(dbManager.getConnection());
        RentalDAO rentalDAO = new RentalDAO(dbManager.getConnection());
        PaymentDAO paymentDAO = new PaymentDAO(dbManager.getConnection());

        // Creează schema (tabelele) în baza de date
        dbManager.createSchema();

        // Inserează date de test
        dbManager.insertTestData();


        // Citește USERS din DB si afiseaza
        List<User> users = userDAO.getAllUsers();
        System.out.println("Users from DB:");
        for (User user : users) {
            System.out.println(user);
        }
        System.out.println("-------------------------------------------------------------\n");

        // Afisăm ROOMS din DB
        List<Room> rooms = roomDAO.getAllRooms();
        System.out.println("Rooms from DB:");
        for (Room room : rooms) {
            System.out.println(room);
        }
        System.out.println("-------------------------------------------------------------\n");

        // Afisăm RENTALS din DB
        List<Rental> rentals = rentalDAO.getAllRentals();
        System.out.println("Rentals from DB:");
        for (Rental rental : rentals) {
            System.out.println(rental);
        }
        System.out.println("-------------------------------------------------------------\n");

        // Afisăm PAYMENTS din DB
        List<Payment> payments = paymentDAO.getAllPayments();
        System.out.println("Payments from DB:");
        for (Payment payment : payments) {
            System.out.println(payment);
        }
        System.out.println("-------------------------------------------------------------\n");



        // Meniu pentru selectarea design pattern-ului
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n\n\n\nSelectati design patternul pe care doriti sa il exemplificati:");
        System.out.println("1. Builder");
        System.out.println("2. Observer");
        System.out.println("3. Abstract Factory");
        System.out.println("4. Danusia Command");

        int selection = scanner.nextInt();
        scanner.nextLine(); // Consumă newline după introducerea numărului

// Implementăm cazul pentru "Observer"
        switch (selection) {

            case 1: {

                String userResponse;

                do {
                    System.out.println("Do you want to add a new room? (yes/no)");
                    userResponse = scanner.nextLine().trim().toLowerCase();

                    if (userResponse.equals("yes")) {
                        try {
                            RoomBuilder builder = new RoomBuilder();

                            int idRoom;
                            while (true) {
                                System.out.print("Enter the room ID: ");
                                idRoom = readInteger(scanner);
                                if (isIdUnique(rooms, idRoom)) {
                                    break;
                                } else {
                                    System.out.println("A room with this ID already exists. Please enter a unique ID.");
                                }
                            }

                            System.out.print("Enter the room name: ");
                            String name = scanner.nextLine();

                            System.out.print("Enter the floor: ");
                            int floor = readInteger(scanner);

                            System.out.print("Enter the location: ");
                            String location = scanner.nextLine();

                            System.out.print("Enter the capacity: ");
                            int capacity = readInteger(scanner);

                            System.out.print("Enter the room type: ");
                            String type = scanner.nextLine();

                            System.out.print("Does it have a projector? (true/false): ");
                            boolean hasProjector = readBoolean(scanner);

                            System.out.print("Does it have a smart board? (true/false): ");
                            boolean hasSmartBoard = readBoolean(scanner);

                            System.out.print("Enter the price per day: ");
                            double pricePerDay = readInteger(scanner);

                            Room room = builder
                                    .setName(name)
                                    .setFloor(floor)
                                    .setLocation(location)
                                    .setCapacity(capacity)
                                    .setType(type)
                                    .setHasProjector(hasProjector)
                                    .setHasSmartBoard(hasSmartBoard)
                                    .setPricePerDay(pricePerDay)
                                    .build();

                            rooms.add(room);
                            roomDAO.insertRoom(room);

                            System.out.println("Room has been added: " + room);
                        } catch (Exception e) {
                            System.out.println("Error while entering data: " + e.getMessage());
                        }
                    } else if (!userResponse.equals("no")) {
                        System.out.println("Invalid response. Please enter 'yes' or 'no'.");
                    }

                } while (!userResponse.equals("no"));

                System.out.println("Final list of rooms:");
                for (Room room : rooms) {
                    System.out.println(room);
                }
                break;
            }

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
                    System.out.println("Ce doresti sa faci?\n-> caz1: REZERVA\n-> caz2: ELIBEREAZA\n-> caz3: STARE SALI");
                    String response = scanner.nextLine().toUpperCase();

                    switch (response) {
                        case "REZERVA":
                            System.out.println("Camere disponibile:");
                            for (Room room : rooms) {
                                if (room.isAvailable()) {
                                    System.out.println("ID: " + room.getIdRoom() + ", " + room.getName() + ", " + room.getType() + ", Etaj: " + room.getFloor() + ", Locatie: " + room.getLocation() + ", Capacitate: " + room.getCapacity() + ", Are proiector: " + room.hasProjector() + ", Are SmartBoard: " + room.hasSmartBoard() + ", Pretul/zi: " + room.getPricePerDay());

                                }
                            }

                            System.out.println("Selecteaza Camera (introdu ID-ul camerei):");
                            int selectedRoomId = Integer.parseInt(scanner.nextLine());
                            ORoom selectedRoom = null;

                            for (Room room : rooms) {
                                if (room.getIdRoom() == selectedRoomId && room.isAvailable()) {
                                    room.setAvailable(false);
                                    roomDAO.updateRoomAvailability(selectedRoomId, false);
                                    selectedRoom = new ORoom(room.getName());
                                    break;
                                }
                            }

                            if (selectedRoom != null) {
                                selectedRoom.ocupaSala();
                                System.out.println("Sala " + selectedRoom.getName() + " a fost rezervata cu succes.");
                            } else {
                                System.out.println("ID-ul introdus nu corespunde unei camere disponibile.");
                            }
                            break;

                        case "ELIBEREAZA":
                            System.out.println("Camere ocupate:");
                            for (Room room : rooms) {
                                if (!room.isAvailable()) {
                                    System.out.println("ID: " + room.getIdRoom() + ", Nume: " + room.getName());
                                }
                            }

                            System.out.println("Selecteaza Camera (introdu ID-ul camerei):");
                            int roomIdToRelease = Integer.parseInt(scanner.nextLine());
                            ORoom roomToRelease = null;

                            for (Room room : rooms) {
                                if (room.getIdRoom() == roomIdToRelease && !room.isAvailable()) {
                                    room.setAvailable(true);
                                    roomDAO.updateRoomAvailability(roomIdToRelease, true);
                                    roomToRelease = new ORoom(room.getName());
                                    break;
                                }
                            }

                            if (roomToRelease != null) {
                                roomToRelease.elibereazaSala();
                                System.out.println("Sala " + roomToRelease.getName() + " a fost eliberata cu succes.");
                            } else {
                                System.out.println("ID-ul introdus nu corespunde unei camere ocupate.");
                            }
                            break;

                        case "STARE SALI":
                            System.out.println("Lista sali si disponibilitatea lor:");
                            for (Room room : rooms) {
                                String status = room.isAvailable() ? "Disponibila" : "Ocupata";
                                System.out.println("ID: " + room.getIdRoom() + ", " + room.getName() + ", " + room.getType() + ", Locatie: " + room.getLocation() + ", Capacitate: " + room.getCapacity() + ", Status: " + status);

                            }
                            break;

                        default:
                            System.out.println("Optiune invalida.");
                            break;
                    }
                } else {
                    System.out.println("Utilizatorul selectat nu a fost găsit.");
                }
                break;
            }

            case 3:
                System.out.println("Opțiunea Cosmin Abstract Factory selectată");

                User user1 = factory.createUser(102, "Elon", "elon.musk@twitter.com");
                userDAO.insertUser(user1);


                Room room1 = factory.createRoom(11, "Executive Room", 2, "Main Building", 15, "Meeting", true, true, true, 250.0);
                roomDAO.insertRoom(room1);

                Rental newRental = factory.createRental(11, user1.getIdUser(), room1.getIdRoom(), LocalDate.now(), LocalDate.now().plusDays(2));
                rentalDAO.insertRental(newRental.getIdUser(), newRental.getIdRoom(), newRental.getStartDate().toString(), newRental.getEndDate().toString());

                Payment newPayment = factory.createPayment(101, user1.getIdUser(), 500.0, LocalDate.now(), "Paid");
                paymentDAO.insertPayment(101, newPayment.getAmount(), newPayment.getPaymentDate().toString(), newPayment.getStatus());

                System.out.println("Abstract Factory Example:\n");
                System.out.println("Created User: " + user1);
                System.out.println("Created Room: " + room1);
                System.out.println("Created Rental: " + newRental);
                System.out.println("Payment for user " + user1.getName() + " is: " + newPayment);
                break;
            case 4:
                System.out.println("Opțiunea Danusia Command pattern selectată");

                CommandInvoker invoker = new CommandInvoker();

                //create a rental
                Rental rentalId12 = factory.createRental(12, 1, 1, LocalDate.now(), LocalDate.now().plusDays(3));
                CreateRentalCommand rentalCommand = new CreateRentalCommand(rentalDAO, rentalId12);
                invoker.executeCommand(rentalCommand);

                //make a payment
                Payment paymentId12 = factory.createPayment(12, 1, 300.0, LocalDate.now(), "Paid");
                MakePaymentCommand paymentCommand = new MakePaymentCommand(paymentDAO, paymentId12);
                invoker.executeCommand(paymentCommand);
                break;
            default:
                System.out.println("Selecție  invalidă!");
        }


        // Închide conexiunea la baza de date
        dbManager.closeConnection();
    }




}

