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
            //e.printStackTrace();
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
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "email TEXT NOT NULL UNIQUE, " +
                    "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TEXT DEFAULT CURRENT_TIMESTAMP);");

            // Create RoomType table
            stmt.execute("CREATE TABLE IF NOT EXISTS RoomType (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "description TEXT, " +
                    "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TEXT DEFAULT CURRENT_TIMESTAMP);");
            System.out.println("RoomType table created successfully.");


            // Create Room table
            stmt.execute("CREATE TABLE IF NOT EXISTS Room (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "name TEXT NOT NULL, " +
                    "size REAL NOT NULL, " +
                    "price_per_day REAL NOT NULL, " +
                    "is_available BOOLEAN NOT NULL, " +
                    "description TEXT, " +
                    "location TEXT, " +
                    "type_id INTEGER NOT NULL, " +
                    "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(type_id) REFERENCES RoomType(id));");
            System.out.println("Room table created successfully.");

            // Create Rental table
            stmt.execute("CREATE TABLE IF NOT EXISTS Rental (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "room_id INTEGER NOT NULL, " +
                    "start_date TEXT NOT NULL, " +
                    "end_date TEXT NOT NULL, " +
                    "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(user_id) REFERENCES User(id) ON DELETE CASCADE, " +
                    "FOREIGN KEY(room_id) REFERENCES Room(id) ON DELETE CASCADE, " +
                    "CONSTRAINT no_overlap UNIQUE (room_id, start_date, end_date));");

            // Create Payment table
            stmt.execute("CREATE TABLE IF NOT EXISTS Payment (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "user_id INTEGER NOT NULL, " +
                    "amount REAL NOT NULL, " +
                    "payment_date TEXT NOT NULL, " +
                    "status TEXT NOT NULL, " +
                    "created_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "updated_at TEXT DEFAULT CURRENT_TIMESTAMP, " +
                    "FOREIGN KEY(user_id) REFERENCES User(id));");

            System.out.println("Schema created successfully.");
        } catch (SQLException e) {
            //e.printStackTrace();
            Logger.getLogger(DatabaseManager.class.getName()).severe(e.getMessage());
            throw new RuntimeException("Failed to create schema.");
        }
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            //e.printStackTrace();
            Logger.getLogger(DatabaseManager.class.getName()).severe(e.getMessage());
            throw new RuntimeException("Failed to close the database connection.");
        }
    }
}
