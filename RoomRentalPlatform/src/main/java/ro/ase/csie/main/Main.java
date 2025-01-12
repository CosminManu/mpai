package ro.ase.csie.main;

import ro.ase.csie.dao.PaymentDAO;
import ro.ase.csie.dao.RentalDAO;
import ro.ase.csie.dao.RoomDAO;
import ro.ase.csie.dao.UserDAO;
import ro.ase.csie.db.*;
import ro.ase.csie.designPatterns.AbstractFactory.ConcreteEntityFactory;
import ro.ase.csie.designPatterns.AbstractFactory.EntityFactory;
import ro.ase.csie.designPatterns.Command.CalculateTotalPaymentCommand;
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
        System.out.println("4. Command");

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

            case 3: {
                System.out.println("\n=== Abstract Factory Demonstration: Grouped Object Creation ===\n");

                System.out.print("Enter category (Standard, Premium, or Deluxe): ");
                String category = scanner.nextLine().trim().toLowerCase();

                try {
                    boolean continueCreating = true;
                    while (continueCreating) {
                        System.out.println("What do you want to create in the " + category + " category?");
                        System.out.println("1. User");
                        System.out.println("2. Room");
                        System.out.println("3. Exit category creation");
                        System.out.print("Enter your choice: ");
                        int creationChoice = readInteger(scanner);

                        switch (creationChoice) {
                            case 1: {
                                System.out.print("Enter user name for " + category + " category: ");
                                String userName = scanner.nextLine();
                                System.out.print("Enter user email for " + category + " category: ");
                                String userEmail = scanner.nextLine();
                                int userId = category.equals("standard") ? 101 : category.equals("premium") ? 102 : 103;
                                User user = factory.createUser(userId, userName, userEmail);
                                userDAO.insertUser(user);

                                System.out.println("\nUser Created:\n  ID: " + user.getIdUser() + "\n  Name: " + user.getName() + "\n  Email: " + user.getEmail());
                                break;
                            }
                            case 2: {
                                System.out.print("Enter room name for " + category + " category: ");
                                String roomName = scanner.nextLine();
                                System.out.print("Enter room floor for " + category + " category: ");
                                int roomFloor = readInteger(scanner);
                                System.out.print("Enter room location for " + category + " category: ");
                                String roomLocation = scanner.nextLine();
                                System.out.print("Enter room capacity for " + category + " category: ");
                                int roomCapacity = readInteger(scanner);
                                System.out.print("Does the room have a projector? (true/false): ");
                                boolean roomProjector = Boolean.parseBoolean(scanner.nextLine());
                                System.out.print("Does the room have a smart board? (true/false): ");
                                boolean roomSmartBoard = Boolean.parseBoolean(scanner.nextLine());
                                System.out.print("Enter room price per day for " + category + " category: ");
                                double roomPrice = Double.parseDouble(scanner.nextLine());
                                int roomId = category.equals("standard") ? 201 : category.equals("premium") ? 202 : 203;
                                Room room = factory.createRoom(roomId, roomName, roomFloor, roomLocation, roomCapacity, category.substring(0, 1).toUpperCase() + category.substring(1), true, roomProjector, roomSmartBoard, roomPrice);
                                roomDAO.insertRoom(room);

                                System.out.println("\nRoom Created:\n  ID: " + room.getIdRoom() + "\n  Name: " + room.getName() + "\n  Type: " + room.getType() + "\n  Location: " + room.getLocation() + "\n  Capacity: " + room.getCapacity() + "\n  Price per Day: " + room.getPricePerDay());
                                break;
                            }
                            case 3: {
                                continueCreating = false;
                                break;
                            }
                            default: {
                                System.out.println("Invalid choice. Please select 1, 2, or 3.");
                            }
                        }
                    }
                } catch (Exception e) {
                    System.out.println("Error while creating category-specific entities: " + e.getMessage());
                }
                break;
            }
            case 4: {
                String userResponse;

                do {
                    System.out.println("Available Commands:\n1. Create a rental\n2. Make a payment\n3. Calculate total payments for a user");
                    System.out.print("Enter the command you want to execute (1, 2, or 3): ");
                    int commandChoice = readInteger(scanner);

                    switch (commandChoice) {
                        case 1: {
                            System.out.print("Enter rental start date (YYYY-MM-DD): ");
                            String startDate = scanner.nextLine();
                            System.out.print("Enter rental end date (YYYY-MM-DD): ");
                            String endDate = scanner.nextLine();
                            System.out.println("Available User IDs:");
                            for (User user : userDAO.getAllUsers()) {
                                System.out.println("  ID: " + user.getIdUser() + ", Name: " + user.getName());
                            }
                            System.out.print("Enter user ID for the rental: ");
                            int rentalUserId = readInteger(scanner);

                            System.out.println("Available Room IDs:");
                            for (Room room : roomDAO.getAllRooms()) {
                                System.out.println("  ID: " + room.getIdRoom() + ", Name: " + room.getName());
                            }
                            System.out.print("Enter room ID for the rental: ");
                            int rentalRoomId = readInteger(scanner);

                            Rental rental = factory.createRental(302, rentalUserId, rentalRoomId, LocalDate.parse(startDate), LocalDate.parse(endDate));
                            CreateRentalCommand rentalCommand = new CreateRentalCommand(rentalDAO, rental);
                            CommandInvoker invoker = new CommandInvoker();
                            invoker.executeCommand(rentalCommand);
                            System.out.println("Rental Command Executed:\n Rental ID: " + rental.getIdRental() + "\n User ID: " + rental.getIdUser() + "\n Room ID: " + rental.getIdRoom() + "\n Start Date: " + rental.getStartDate() + "\n End Date: " + rental.getEndDate());
                            break;
                        }
                        case 2: {
                            System.out.print("Enter payment amount: ");
                            double amount = readInteger(scanner);
                            System.out.println("Available User IDs:");
                            for (User user : userDAO.getAllUsers()) {
                                System.out.println("  ID: " + user.getIdUser() + ", Name: " + user.getName());
                            }
                            System.out.print("Enter user ID for the payment: ");
                            int userId = readInteger(scanner);
                            Payment payment = factory.createPayment(402, userId, amount, LocalDate.now(), "Paid");
                            MakePaymentCommand paymentCommand = new MakePaymentCommand(paymentDAO, payment);
                            CommandInvoker invoker = new CommandInvoker();
                            invoker.executeCommand(paymentCommand);
                            System.out.println("Payment Command Executed:\n Payment ID: " + payment.getIdPayment() + "\n User ID: " + payment.getIdUser() + "\n Amount: " + payment.getAmount() + "\n Date: " + payment.getPaymentDate() + "\n Status: " + payment.getStatus());
                            break;
                        }
                        case 3: {
                            System.out.println("Available User IDs:");
                            for (User user : userDAO.getAllUsers()) {
                                System.out.println("  ID: " + user.getIdUser() + ", Name: " + user.getName());
                            }
                            System.out.print("Enter user ID to calculate total payments: ");
                            int userId = readInteger(scanner);

                            CalculateTotalPaymentCommand totalPaymentCommand = new CalculateTotalPaymentCommand(paymentDAO, userId);
                            CommandInvoker invoker = new CommandInvoker();
                            invoker.executeCommand(totalPaymentCommand);
                            break;
                        }

                        default:
                            System.out.println("Invalid command choice. Please select 1, 2 or 3.");
                    }

                    System.out.print("Do you want to execute another command? (yes/no): ");
                    userResponse = scanner.nextLine().trim().toLowerCase();

                } while (!userResponse.equals("no"));

                break;
            }

            default:
                System.out.println("Invalid choice. Please select a valid design pattern.");
        }


        // Închide conexiunea la baza de date
        dbManager.closeConnection();
    }




}

