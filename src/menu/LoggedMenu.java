package menu;

import classes.User;
import data.DataIntroduction;
import data.input_output.Input;
import data.input_output.Output;
import main.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

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
                | * 5. ELIMINAR USUARI MÀSTER             |
                | * 6. TANCAR SESSIÓ                      |
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
                createNewMasterUser();
                break;
            case 5:
                deleteMasterUser();
                break;
            case 6:
                logout();
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
    static void deleteMasterUser() {
        ArrayList<User> users = Input.readUsersFile();
        if (users.isEmpty()) {
            System.out.println("Error: No es troben usuaris Màster");
            Main.run();
        }
        User user = User.chooseExistingUser();
        System.out.println(user);
        System.out.println("Introdueix la contrasenya de l'usuari màster a eliminar");
        if (user == null) {
            System.out.println("S'ha produït un error");
            Main.run();
        } else {
            DataIntroduction.introducePasswordForLogin(user);
            users.remove(user);
            users.removeIf(u -> u.getIdentifier() == user.getIdentifier());
            try {
                Output.reWriteUsersFile(users);
                System.out.println("S'ha eliminat l'usuari");
            } catch (FileNotFoundException e) {
                System.out.println("Error: No s'ha trobat l'arxiu dels usuaris");
                Main.run();
            }
        }
    }
}