package ro.ase.csie.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Logger;

public class DatabaseManager {
    private static DatabaseManager instance;
    private static final String DB_URL = "jdbc:sqlite:room_rental.db";
    private Connection connection;

    private DatabaseManager() {
        try {
            connection = DriverManager.getConnection(DB_URL);
            System.out.println("Database connected successfully.");
        } catch (SQLException e) {
            Logger.getLogger(DatabaseManager.class.getName()).severe(e.getMessage());
            throw new RuntimeException("Failed to connect to the database.");
        }
    }

    public static synchronized DatabaseManager getInstance() {
        if (instance == null) {
            instance = new DatabaseManager();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }

    public void createSchema() {
        try (Statement stmt = connection.createStatement()) {
            // Create User table
            stmt.execute("CREATE TABLE IF NOT EXISTS User (" +
                    "id_user INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL UNIQUE); ");

            // Create Room table
            stmt.execute("CREATE TABLE IF NOT EXISTS Room (" +
                    "id_room INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "floor INTEGER NOT NULL, " +
                    "location TEXT, " +
                    "capacity REAL NOT NULL, " +
                    "type TEXT NOT NULL, " +
                    "is_available BOOLEAN NOT NULL, " +
                    "has_projector BOOLEAN NOT NULL, " +
                    "has_smartBoard BOOLEAN NOT NULL, " +
                    "price_per_day REAL NOT NULL); ");

            // Create Rental table
            stmt.execute("CREATE TABLE IF NOT EXISTS Rental (" +
                    "id_rental INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "id_user INTEGER NOT NULL, " +
                    "id_room INTEGER NOT NULL, " +
                    "start_date TEXT NOT NULL, " +
                    "end_date TEXT NOT NULL, " +
                    "FOREIGN KEY(id_user) REFERENCES User(id_user) ON DELETE CASCADE, " +
                    "FOREIGN KEY(id_room) REFERENCES Room(id_room) ON DELETE CASCADE, " +
                    "CONSTRAINT no_overlap UNIQUE (id_room, start_date, end_date));");

            // Create Payment table
            stmt.execute("CREATE TABLE IF NOT EXISTS Payment (" +
                    "id_payment INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "id_user INTEGER NOT NULL, " +
                    "amount REAL NOT NULL, " +
                    "payment_date TEXT NOT NULL, " +
                    "status TEXT NOT NULL, " +
                    "FOREIGN KEY(id_user) REFERENCES User(id_user));");

            System.out.println("Schema created successfully.");
        } catch (SQLException e) {
            Logger.getLogger(DatabaseManager.class.getName()).severe(e.getMessage());
            throw new RuntimeException("Failed to create schema.");
        }
    }

    public void insertTestData() {
        try (Statement stmt = connection.createStatement()) {
            // Insert data into User table
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (1, 'Alice', 'alice@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (2, 'Bob', 'bob@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (3, 'Charlie', 'charlie@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (4, 'Diana', 'diana@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (5, 'Eve', 'eve@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (6, 'Frank', 'frank@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (7, 'Grace', 'grace@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (8, 'Hank', 'hank@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (9, 'Ivy', 'ivy@example.com');");
            stmt.executeUpdate("INSERT INTO User (id_user, name, email) VALUES (10, 'Jack', 'jack@example.com');");

            // Insert data into Room table
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (1, 'Room A', 1, 'Building 1', 20, 'Conference', 1, 1, 1, 100.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (2, 'Room B', 2, 'Building 1', 30, 'Lecture', 1, 1, 0, 150.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (3, 'Room C', 3, 'Building 2', 10, 'Office', 1, 0, 0, 75.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (4, 'Room D', 4, 'Building 2', 25, 'Workshop', 1, 1, 1, 200.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (5, 'Room E', 5, 'Building 3', 15, 'Lab', 1, 1, 0, 120.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (6, 'Room F', 1, 'Building 3', 20, 'Conference', 1, 1, 1, 90.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (7, 'Room G', 2, 'Building 4', 35, 'Lecture', 1, 1, 0, 170.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (8, 'Room H', 3, 'Building 4', 50, 'Hall', 1, 1, 1, 300.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (9, 'Room I', 4, 'Building 5', 40, 'Auditorium', 1, 1, 1, 250.0);");
            stmt.executeUpdate("INSERT INTO Room (id_room, name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                    "VALUES (10, 'Room J', 5, 'Building 5', 5, 'Private Office', 1, 0, 0, 50.0);");

            // Insert data into Rental table
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (1, 1, 1, '2023-01-01', '2023-01-05');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (2, 2, 2, '2023-02-10', '2023-02-15');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (3, 3, 3, '2023-03-20', '2023-03-25');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (4, 4, 4, '2023-04-05', '2023-04-10');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (5, 5, 5, '2023-05-15', '2023-05-20');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (6, 6, 6, '2023-06-10', '2023-06-15');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (7, 7, 7, '2023-07-01', '2023-07-05');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (8, 8, 8, '2023-08-20', '2023-08-25');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (9, 9, 9, '2023-09-10', '2023-09-15');");
            stmt.executeUpdate("INSERT INTO Rental (id_rental, id_user, id_room, start_date, end_date) VALUES (10, 10, 10, '2023-10-05', '2023-10-10');");

            // Insert data into Payment table
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (1, 1, 500.0, '2023-01-06', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (2, 2, 750.0, '2023-02-16', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (3, 3, 375.0, '2023-03-26', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (4, 4, 1000.0, '2023-04-11', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (5, 5, 600.0, '2023-05-21', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (6, 6, 450.0, '2023-06-16', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (7, 7, 850.0, '2023-07-06', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (8, 8, 1500.0, '2023-08-26', 'Paid');");
            stmt.executeUpdate("INSERT INTO Payment (id_payment, id_user, amount, payment_date, status) VALUES (9, 9, 1250.0, '2023-09-16', 'Paid');");
            System.out.println("Test data inserted successfully.");
        } catch (SQLException e) {
            Logger.getLogger(DatabaseManager.class.getName()).severe(e.getMessage());
            throw new RuntimeException("Failed to insert test data.");
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            Logger.getLogger(DatabaseManager.class.getName()).severe(e.getMessage());
            throw new RuntimeException("Failed to close the database connection.");
        }
    }
}