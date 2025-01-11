package ro.ase.csie.designPatterns.Observer;

import ro.ase.csie.models.Room;

import java.util.ArrayList;
import java.util.List;

public class ORoom extends Room implements IRoomNotifier {

    private List<IUser> users; // Lista utilizatorilor abonați

    public ORoom(String name) {
        super(name);
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
        this.setAvailable(true);
        String mesaj = "Sala " + this.getName() + " este acum disponibilă.";
        notificare(mesaj);
    }

    // Metodă pentru a ocupa sala
    public void ocupaSala() {
        this.setAvailable(false);
        String mesaj = "Sala " + this.getName() + " este acum ocupata.";
        notificare(mesaj);
    }

    // Getter pentru disponibilitate
    public boolean esteDisponibila() {
        return this.isAvailable();
    }

    public List<IUser> getUsers() {
        return users;
    }

    public void setUsers(List<IUser> users) {
        this.users = users;
    }


}
