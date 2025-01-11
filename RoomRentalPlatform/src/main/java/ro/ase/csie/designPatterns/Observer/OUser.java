package ro.ase.csie.designPatterns.Observer;

import ro.ase.csie.models.User;

// OUser.java
public class OUser extends User implements IUser {


    public OUser(String nume) {
        super(nume);
    }

    @Override
    public void primesteNotificare(String mesaj) {
        System.out.println(this.getName() + " ai primit următoarea notificare: " + mesaj);
    }
}


