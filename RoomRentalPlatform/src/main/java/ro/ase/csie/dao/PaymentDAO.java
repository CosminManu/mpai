package ro.ase.csie.dao;

import ro.ase.csie.models.Payment;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAO {

    private Connection connection;

    public PaymentDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertPayment(int userId, double amount, String paymentDate, String status) {
        String query = "INSERT INTO Payment (id_user, amount, payment_date, status) VALUES (?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setDouble(2, amount);
            stmt.setString(3, paymentDate);
            stmt.setString(4, status);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Payment> getAllPayments() {
        List<Payment> payments = new ArrayList<>();
        String query = "SELECT * FROM Payment";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                // Obținem payment_date ca șir de caractere
                String paymentDateString = rs.getString("payment_date");

                // Convertim șirul într-un obiect LocalDate
                LocalDate paymentDate = LocalDate.parse(paymentDateString);  // Convertește șirul în LocalDate

                Payment payment = new Payment(
                        rs.getInt("id_payment"),
                        rs.getInt("id_user"),
                        rs.getDouble("amount"),
                        paymentDate,  // Folosim LocalDate
                        rs.getString("status")
                );
                payments.add(payment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return payments;
    }


    public void updatePaymentStatus(int paymentId, String newStatus) {
        String query = "UPDATE Payment SET status = ? WHERE id_payment = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, newStatus); // Setăm noul status
            stmt.setInt(2, paymentId);    // Setăm id-ul plății care trebuie actualizat
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
