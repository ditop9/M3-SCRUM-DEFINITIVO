package menu;

import classes.Customer;
import classes.Order;
import classes.User;
import main.Main;

public class LoggedMenu {
    public static void ui() {
        System.out.println(Main.masterUser.getUsername());
        System.out.println("""
                ====== BENVINGUT A L'ENTORN D'USUARI ======
                ___________________________________________
                | * 1. AFEGIR NOU CLIENT                  |
                | * 2. ELIMINAR CLIENT                    |
                | * 3. AFEGIR NOVA COMPRA                 |
                | * 4. ELIMINAR COMPRA                    |
                | * 5. FILTRAR COMPRA                     |
                | * 6. AFEGIR NOU USUARI MÀSTER           |
                | * 7. ELIMINAR USUARI MÀSTER             |
                | * 8. TANCAR SESSIÓ                      |
                | * 0. SORTIR                             |
                |_________________________________________|""");
    }

    public static void chooseOption(int option) {
        switch (option) {
            case 1:
                Customer.addNewCustomer();
                break;
            case 2:
                Customer.deleteCustomer();
                break;
            case 3:
                System.out.println(Order.createNewOrder());
                break;
            case 6:
                User.addNewMasterUser();
                break;
            case 7:
                User.deleteMasterUser();
                break;
            case 8:
                User.logout();
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