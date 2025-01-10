package ro.ase.csie.designPatterns.CristinaObserver;

import ro.ase.csie.designPatterns.CristinaObserver.IManager;
import ro.ase.csie.designPatterns.CristinaObserver.Manager;
import ro.ase.csie.designPatterns.CristinaObserver.Rental;

// Main.java
public class Main {
    public static void main(String[] args) {
        // Crearea unor manageri de proiect
        IManager manager1 = new Manager("John Doe");
        IManager manager2 = new Manager("Alice Smith");

        // Crearea unei închirieri
        Rental rental = new Rental(1, 101, 1001, "În așteptare");

        // Abonarea managerilor la notificări
        rental.abonareManager(manager1);
        rental.abonareManager(manager2);

        // Modificarea statutului închirierii la "Confirmată"
        System.out.println("Schimbarea statutului închirierii...");
        rental.setStatus("Confirmată");  // Nu vor fi afișate mesaje suplimentare

        // Modificarea statutului închirierii la "Eliberată"
        System.out.println("Schimbarea statutului închirierii...");
        rental.setStatus("Eliberată");  // Afisează doar numărul sălii eliberate
    }
}
