package menu;

import static classes.Admin.login;

public class MainMenu {
    public static void displayMenu() {
        System.out.println("""
                ====== BENVINGUT A L'ENTORN D'USUARI ======
                ___________________________________________
                | * 1. INICIAR SESSIÓ                     |
                | * 0. SORTIR                             |
                |_________________________________________|""");
    }

    public static void handleOption(int option) {
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
}