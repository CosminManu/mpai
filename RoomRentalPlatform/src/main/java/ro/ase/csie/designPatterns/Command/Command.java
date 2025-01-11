package ro.ase.csie.designPatterns.Command;

/**
 * The Command Pattern is perfect for encapsulating requests or operations as objects.
 * This allows us to parameterize objects with operations, delay execution, or even queue operations.
 * We’ll implement the Command Pattern for actions such as creating a rental or making a payment.
 *
 * Here the interface declares a single execute method.
 */
public interface Command {
    void execute();
}
