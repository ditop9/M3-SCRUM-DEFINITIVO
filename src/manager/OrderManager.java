package manager;

import app.Main;
import classes.Customer;
import classes.Order;
import data.DataInput;

// En desenvolupament
public class OrderManager {
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
                _______________________________
                |  ==== MENÚ DE COMPRES ====  |
                |_____________________________|
                | 1. AFEGIR COMPRA            |
                | 0. TORNAR AL MENÚ PRINCIPAL |
                |_____________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                Order order = Order.createNewOrder();
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
}
