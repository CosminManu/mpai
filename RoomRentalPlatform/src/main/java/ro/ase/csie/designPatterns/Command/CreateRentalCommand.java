package ro.ase.csie.designPatterns.Command;

import ro.ase.csie.dao.RentalDAO;
import ro.ase.csie.models.Rental;

/**
 * concrete classes for each operation
 */
public class CreateRentalCommand implements Command{
    private RentalDAO rentalDao;
    private Rental rental;

    public CreateRentalCommand(RentalDAO rentalDao, Rental rental) {
        this.rentalDao = rentalDao;
        this.rental = rental;
    }


    @Override
    public void execute() {
        rentalDao.insertRental(rental.getIdUser(), rental.getIdRoom(), rental.getStartDate().toString(), rental.getEndDate().toString());
        System.out.println("Rental created: " + rental);
    }
}
