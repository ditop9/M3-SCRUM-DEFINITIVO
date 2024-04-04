package manager;

import app.Main;
import classes.Admin;
import classes.Order;
import data.DataInput;

public class AdminManager {
    public static void run() {
        int option;
        do {
            displayMenu();
            option = DataInput.getValidInteger("Escull una opció");
            handleOption(option);
        } while (option != 0);
    }

    public static void displayMenu() {
        System.out.println("""
                _______________________________________
                |  ==== OPCIONS D'ADMINISTRADOR ====  |
                |_____________________________________|
                | 1. AFEGIR ADMINISTRADOR             |
                | 2. ELIMINAR ADMINISTRADOR           |
                | 0. TORNAR AL MENÚ PRINCIPAL         |
                |_____________________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                Admin.addNewAdmin();
                break;
            case 2:
                Admin.deleteAdmin();
                break;
            case 0:
                System.out.println("Tornant al menú principal");
                Main.run();
                break;
            default:
                System.out.println("Error: No és una opció vàlida");
                break;
        }
    }

    public static void logout() {
        Main.admin = null;
    }
}
