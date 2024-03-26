package menu;

import data.input_output.Input;

import java.util.ArrayList;

import classes.User;
import data.DataInput;
import main.Main;

public class MainMenu {
    public static void ui() {
        System.out.println("""
                ====== BENVINGUT A L'ENTORN D'USUARI ======
                ___________________________________________
                | * 1. INICIAR SESSIÓ                     |
                | * 0. SORTIR                             |
                |_________________________________________|""");
    }

    public static void chooseOption(int option) {
        switch (option) {
            case 1:
                login();
                break;
            case 0:
                System.out.println("El programa es tanca...");
                System.exit(0);
                break;
            default:
                System.out.println("Error: No és una opció vàlida.");
                break;
        }
    }

    static void login() {
        ArrayList<User> users = Input.readUsersFile();
        User user = DataInput.introduceUsernameForLogin(users);
        DataInput.introducePasswordForLogin(user);
        Main.masterUser = user;
    }
}