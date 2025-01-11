package ro.ase.csie.designPatterns.Command;

import ro.ase.csie.dao.PaymentDAO;
import ro.ase.csie.models.Payment;

/**
 * concrete classes for each operation
 */
public class MakePaymentCommand implements Command{
    private PaymentDAO paymentDao;
    private Payment payment;

    public MakePaymentCommand(PaymentDAO paymentDao, Payment payment) {
        this.paymentDao = paymentDao;
        this.payment = payment;
    }

    @Override
    public void execute() {
        paymentDao.insertPayment(payment.getIdUser(), payment.getAmount(), payment.getPaymentDate().toString(), payment.getStatus());
        System.out.println("Payment made: " + payment);
    }
}
