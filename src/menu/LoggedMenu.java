package menu;

import classes.User;
import data.input_output.Output;
import main.Main;

import java.io.IOException;

public class LoggedMenu {
    public static void ui() {
        System.out.println(Main.masterUser.getUsername());
        System.out.println("""
                ====== BENVINGUT A L'ENTORN D'USUARI ======
                ___________________________________________
                | * 1. INICIAR SESSIÓ                     |
                | * 2. AFEGIR NOVA COMPRA                 |
                | * 3. FILTRAR COMPRES                    |
                | * 4. AFEGIR NOU USUARI MÀSTER           |
                | * 5. TANCAR SESSIÓ                      |
                | * 0. SORTIR                             |
                |_________________________________________|""");
    }
    public static void chooseOption(int option) {
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 4:
                logout();
                break;
            case 5:
                createNewMasterUser();
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
    static void logout() {
        Main.masterUser = null;
        Main.run();
    }
    static void createNewMasterUser() {
        User newMasterUser = User.createNewUser();
        try {
            Output.writeUsersFile(newMasterUser);
        } catch (IOException e) {
            System.out.println(e + "Error: No s'ha trobat l'arxiu d'usuaris");
            Main.run();
        }
    }
}