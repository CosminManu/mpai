package ro.ase.csie.designPatterns.CristinaObserver;

// OUser.java
public class OUser implements IUser {
    private String nume;

    public OUser(String nume) {
        this.nume = nume;
    }

    @Override
    public void primesteNotificare(String mesaj) {
        System.out.println(this.nume + " ai primit următoarea notificare: " + mesaj);
    }
}


