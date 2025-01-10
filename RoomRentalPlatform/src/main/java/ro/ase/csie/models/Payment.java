package ro.ase.csie.models;

import java.time.LocalDate;

public class Payment {
    private int idPayment;
    private int idUser;
    private double amount;
    private LocalDate paymentDate;
    private String status;

    public Payment(int idPayment, int idUser, double amount, LocalDate paymentDate, String status) {
        this.idPayment = idPayment;
        this.idUser = idUser;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.status = status;
    }

    public int getIdPayment() {
        return idPayment;
    }

    public void setIdPayment(int idPayment) {
        this.idPayment = idPayment;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Payment{idPayment=" + idPayment + ", idUser=" + idUser + ", amount=" + amount +
                ", paymentDate=" + paymentDate + ", status='" + status + "'}";
    }
}
