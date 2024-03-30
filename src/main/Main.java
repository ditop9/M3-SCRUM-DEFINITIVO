package main;

import classes.*;
import data.DataInput;
import data.input_output.Input;
import menu.*;

public class Main {
    public static User masterUser = null;

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        int option;
        System.out.println(Input.readOrdersFile());
        do {
            if (masterUser != null) {
                LoggedMenu.ui();
                option = DataInput.getValidInteger("Escull una opció");
                LoggedMenu.chooseOption(option);
            } else {
                MainMenu.ui();
                option = DataInput.getValidInteger("Escull una opció");
                MainMenu.chooseOption(option);
            }
        } while (option != 0);
    }
}