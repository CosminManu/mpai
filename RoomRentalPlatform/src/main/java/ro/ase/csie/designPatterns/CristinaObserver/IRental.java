package ro.ase.csie.designPatterns.CristinaObserver;

// IRental.java
public interface IRental {
    void abonareManager(IManager manager);
    void dezabonareManager(IManager manager);
    void notificare(String mesaj);
}

