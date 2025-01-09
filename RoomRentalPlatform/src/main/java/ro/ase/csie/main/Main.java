package ro.ase.csie.main;

import ro.ase.csie.db.DatabaseManager;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");

        DatabaseManager db = DatabaseManager.getInstance();
        db.createSchema();
        db.closeConnection();
    }
}