package ro.ase.csie.designPatterns.CristinaObserver;

import java.util.ArrayList;
import java.util.List;

public class ORoom implements IRoomNotifier {
    private String name; // Numele sălii
    private boolean isAvailable; // Disponibilitatea sălii
    private List<IUser> users; // Lista utilizatorilor abonați

    public ORoom(String name) {
        this.name = name;
        this.isAvailable = true; // Inițial sala este disponibilă
        this.users = new ArrayList<>();
    }

    @Override
    public void abonareUtilizator(IUser user) {
        users.add(user);
    }

    @Override
    public void dezabonareUtilizator(IUser user) {
        users.remove(user);
    }

    @Override
    public void notificare(String mesaj) {
        for (IUser user : users) {
            user.primesteNotificare(mesaj);
        }
    }

    // Metodă pentru a elibera sala și a trimite notificări
    public void elibereazaSala() {
        this.isAvailable = true;
        String mesaj = "Sala " + this.name + " este acum disponibilă.";
        notificare(mesaj);
    }

    // Metodă pentru a ocupa sala
    public void ocupaSala() {
        this.isAvailable = false;
        String mesaj = "Sala " + this.name + " este acum ocupata.";
        notificare(mesaj);
    }

    // Getter pentru disponibilitate
    public boolean esteDisponibila() {
        return this.isAvailable;
    }
}
