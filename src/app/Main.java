package app;

import classes.admin.Admin;
import data.DataInput;
import menu.*;

public class Main {
    public static Admin admin = new Admin();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        int option;
        do {
            if (admin.getId() != -1) {
                LoggedMenu.displayMenu();
                option = DataInput.getValidInteger("Escull una opció");
                LoggedMenu.handleOption(option);
            } else {
                MainMenu.displayMenu();
                option = DataInput.getValidInteger("Escull una opció");
                MainMenu.handleOption(option);
            }
        } while (option != 0);
    }
}