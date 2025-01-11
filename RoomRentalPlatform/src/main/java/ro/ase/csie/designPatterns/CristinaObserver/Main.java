package ro.ase.csie.designPatterns.CristinaObserver;

import ro.ase.csie.designPatterns.CristinaObserver.OUser;
import ro.ase.csie.designPatterns.CristinaObserver.ORoom;

public class Main {
    public static void main(String[] args) {
        // Creare utilizatori
        IUser utilizator1 = new OUser("Ana");
        IUser utilizator2 = new OUser("Mihai");
        IUser utilizator3 = new OUser("Ioana");

        // Creare săli
        ORoom sala1 = new ORoom("101");
        ORoom sala2 = new ORoom("202");

        // Abonare utilizatori
        sala1.abonareUtilizator(utilizator1);
        sala1.abonareUtilizator(utilizator2);

        sala2.abonareUtilizator(utilizator3);

        // Test notificări
        sala1.elibereazaSala(); // Notifică Ana și Mihai
        sala2.elibereazaSala(); // Notifică Ioana

        // Dezabonare utilizator și notificare ulterioară
        sala1.dezabonareUtilizator(utilizator1);
        sala1.elibereazaSala(); // Notifică doar Mihai


        sala2.ocupaSala();
    }
}

