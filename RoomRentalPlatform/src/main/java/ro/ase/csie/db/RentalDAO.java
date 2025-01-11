package ro.ase.csie.db;


import ro.ase.csie.models.Rental;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class RentalDAO {

    private Connection connection;

    public RentalDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertRental(int userId, int roomId, String startDate, String endDate) {
        String query = "INSERT INTO Rental (id_user, id_room, start_date, end_date) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, roomId);
            stmt.setString(3, startDate);
            stmt.setString(4, endDate);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Rental> getAllRentals() {
        List<Rental> rentals = new ArrayList<>();
        String query = "SELECT * FROM Rental";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Obținem start_date și end_date ca șiruri de caractere
                String startDateString = rs.getString("start_date");
                String endDateString = rs.getString("end_date");

                // Convertim șirurile într-un obiect LocalDate
                LocalDate startDate = LocalDate.parse(startDateString);  // Convertește șirul în LocalDate
                LocalDate endDate = LocalDate.parse(endDateString);      // Convertește șirul în LocalDate

                Rental rental = new Rental(
                        rs.getInt("id_rental"),
                        rs.getInt("id_user"),
                        rs.getInt("id_room"),
                        startDate,  // Folosim LocalDate
                        endDate     // Folosim LocalDate
                );
                rentals.add(rental);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rentals;
    }

    public void updateRentalDates(int rentalId, String newStartDate, String newEndDate) {
        String query = "UPDATE Rental SET start_date = ?, end_date = ? WHERE id_rental = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newStartDate);  // Setăm noua dată de început
            stmt.setString(2, newEndDate);    // Setăm noua dată de sfârșit
            stmt.setInt(3, rentalId);         // Setăm id-ul închirierii pe care dorim să o actualizăm
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

