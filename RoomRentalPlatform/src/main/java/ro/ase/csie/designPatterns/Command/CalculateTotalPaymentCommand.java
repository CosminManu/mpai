package ro.ase.csie.designPatterns.Command;

import ro.ase.csie.dao.PaymentDAO;

/***
 *  logic for calculating the total amount paid by a user
 */
public class CalculateTotalPaymentCommand implements Command{
    private PaymentDAO paymentDAO;
    private int userId;

    public CalculateTotalPaymentCommand(PaymentDAO paymentDAO, int userId) {
        this.paymentDAO = paymentDAO;
        this.userId = userId;
    }

    @Override
    public void execute() {
        double total = paymentDAO.getTotalPaymentsByUserId(userId);
        System.out.println("Total amount paid by User ID " + userId + ": " + total);
    }
}
