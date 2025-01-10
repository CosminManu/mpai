package ro.ase.csie.designPatterns.CristinaObserver;

// Manager.java
public class Manager implements IManager {
    private String name;

    public Manager(String name) {
        this.name = name;
    }

    @Override
    public void primesteNotificare(String mesaj) {
        System.out.println("Managerul de proiect " + name + " a fost notificat: " + mesaj);
    }
}

