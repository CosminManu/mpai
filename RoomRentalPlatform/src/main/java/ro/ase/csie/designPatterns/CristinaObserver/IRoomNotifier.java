package ro.ase.csie.designPatterns.CristinaObserver;

// IRoomNotifier.java
import java.util.List;

public interface IRoomNotifier {
    void abonareUtilizator(IUser user);
    void dezabonareUtilizator(IUser user);
    void notificare(String mesaj);
}

