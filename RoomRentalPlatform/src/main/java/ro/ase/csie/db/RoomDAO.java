package ro.ase.csie.db;


import ro.ase.csie.models.Room;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RoomDAO {

    private Connection connection;

    public RoomDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertRoom(String name, int floor, String location, double capacity, String type, boolean isAvailable,
                           boolean hasProjector, boolean hasSmartBoard, double pricePerDay) {
        String query = "INSERT INTO Room (name, floor, location, capacity, type, is_available, has_projector, has_smartBoard, price_per_day) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, name);
            stmt.setInt(2, floor);
            stmt.setString(3, location);
            stmt.setDouble(4, capacity);
            stmt.setString(5, type);
            stmt.setBoolean(6, isAvailable);
            stmt.setBoolean(7, hasProjector);
            stmt.setBoolean(8, hasSmartBoard);
            stmt.setDouble(9, pricePerDay);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Room> getAllRooms() {
        List<Room> rooms = new ArrayList<>();
        String query = "SELECT * FROM Room";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Room room = new Room(rs.getInt("id_room"), rs.getString("name"), rs.getInt("floor"), rs.getString("location"),
                        rs.getDouble("capacity"), rs.getString("type"), rs.getBoolean("is_available"),
                        rs.getBoolean("has_projector"), rs.getBoolean("has_smartBoard"), rs.getDouble("price_per_day"));
                rooms.add(room);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rooms;
    }

    public void updateRoomAvailability(int roomId, boolean isAvailable) {
        String query = "UPDATE Room SET is_available = ? WHERE id_room = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setBoolean(1, isAvailable);
            stmt.setInt(2, roomId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}

