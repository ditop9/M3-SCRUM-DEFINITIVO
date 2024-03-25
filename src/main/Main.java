package main;

import classes.User;
import data.DataIntroduction;
import menu.*;

public class Main {
    public static User masterUser = null;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        int option;
        do {
            if (masterUser != null) {
                LoggedMenu.ui();
                option = DataIntroduction.introduceInteger("Escull una opció");
                LoggedMenu.chooseOption(option);
            } else {
                MainMenu.ui();
                option = DataIntroduction.introduceInteger("Escull una opció");
                MainMenu.chooseOption(option);
            }
        } while (option != 0);
    }
}