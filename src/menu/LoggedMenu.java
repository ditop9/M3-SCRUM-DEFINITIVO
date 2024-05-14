package menu;

import app.Main;
import classes.admin.Admin;
import classes.admin.AdminManager;
import classes.customer.CustomerManager;
import classes.order.OrderManager;
import classes.supermarket.SupermarketManager;

public class LoggedMenu {
    public static void displayMenu() {
        System.out.println(Main.admin.getName());
        System.out.println("""
                ====== BENVINGUT A L'ENTORN D'USUARI ======
                ___________________________________________
                | * 1. GESTIÓ DE CLIENTS                  |
                | * 2. GESTIÓ DE COMPRES                  |
                | * 3. GESTIÓ DE SUPERMERCATS             |
                | * 4. OPCIONS D'ADMINISTRADOR            |
                | * 5. TANCAR SESSIÓ                      |
                | * 0. SORTIR                             |
                |_________________________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                CustomerManager.run();
                break;
            case 2:
                OrderManager.run();
                break;
            case 3:
                SupermarketManager.run();
                break;
            case 4:
                AdminManager.run();
                break;
            case 5:
                Admin.logout();
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
}