package app;

import classes.*;
import data.DataInput;
import menu.*;

public class Main {
    // Usuari admin comença null al principi per tal que no tingui permisos d'administrador
    public static User admin = null;

    public static void main(String[] args) {
        run();
    }

    // Funció que executa l'app amb lógica per tancar-la. Es verifica si l'usuari és administrador o no per tal d'entrar a l'aplicació.
    // Els usuaris administradors es troben al document UsersData.
    public static void run() {
        int option;
        do {
            if (admin != null) {
                LoggedMenu.displayMenu();
                option = DataInput.getValidInteger("Escull una opció");
                LoggedMenu.handleOption(option);
            } else {
                MainMenu.displayMenu();
                option = DataInput.getValidInteger("Escull una opció");
                MainMenu.handleOption(option);
            }
        } while (option != 0);
    }
}