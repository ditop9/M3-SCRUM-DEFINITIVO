package main;

import classes.User;
import data.DataIntroduction;
import data.input_output.Input;
import menu.*;

public class Main {
    public static User masterUser = null;
    public static void main(String[] args) {
        run();
    }
    public static void run() {
        int option;
        if (masterUser != null) {
            do {
                LoggedMenu.ui();
                option = DataIntroduction.introduceInteger("Escull una opció");
                LoggedMenu.chooseOption(option);
            } while (option != 0);
        } else {
            do {
                MainMenu.ui();
                option = DataIntroduction.introduceInteger("Escull una opció");
                MainMenu.chooseOption(option);
            } while (option != 0);
        }
    }
}