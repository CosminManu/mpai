package ro.ase.csie.designPatterns.Observer;

// IRoomNotifier.java

public interface IRoomNotifier {
    void abonareUtilizator(IUser user);

    void dezabonareUtilizator(IUser user);

    void notificare(String mesaj);
}

