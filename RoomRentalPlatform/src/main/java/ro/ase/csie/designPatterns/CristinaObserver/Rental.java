package ro.ase.csie.designPatterns.CristinaObserver;

// Rental.java
import java.util.ArrayList;
import java.util.List;

public class Rental implements IRental {
    private int rentalId;
    private int roomId;
    private int projectId;
    private String status;
    private List<IManager> managers;

    public Rental(int rentalId, int roomId, int projectId, String status) {
        this.rentalId = rentalId;
        this.roomId = roomId;
        this.projectId = projectId;
        this.status = status;
        this.managers = new ArrayList<>();
    }

    public void setStatus(String status) {
        this.status = status;
        // Verificăm dacă sala a fost eliberată
        if ("Eliberată".equalsIgnoreCase(status)) {
            // Afișăm doar numărul sălii care s-a eliberat
            System.out.println("Sala cu numărul " + roomId + " a fost eliberată.");
        }
        // Nu vom mai notifica despre schimbarea statutului închirierii în acest caz
    }

    public String getStatus() {
        return status;
    }

    @Override
    public void abonareManager(IManager manager) {
        managers.add(manager);
    }

    @Override
    public void dezabonareManager(IManager manager) {
        managers.remove(manager);
    }

    @Override
    public void notificare(String mesaj) {
        for (IManager manager : managers) {
            manager.primesteNotificare(mesaj);
        }
    }

    public int getRentalId() {
        return rentalId;
    }

    public int getRoomId() {
        return roomId;
    }

    public int getProjectId() {
        return projectId;
    }
}
