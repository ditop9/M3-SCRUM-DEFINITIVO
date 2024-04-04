package manager;

import app.Main;
import classes.Supermarket;
import data.DataInput;
import data.input_output.Input;
import data.input_output.Output;

import java.io.IOException;
import java.util.ArrayList;

public class SupermarketManager {
    private final static ArrayList<Supermarket> supermarkets = Input.readSupermarketsFile();

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
                ______________________________________
                |   ==== MENÚ DE SUPERMERCATS ====   |
                |____________________________________|
                | * 1. AFEGIR SUPERMERCAT            |
                | * 2. ELIMINAR SUPERMERCAT          |
                | * 3. MOSTRAR TOTS ELS SUPERMERCATS |
                | * 0. TORNAR AL MENÚ PRINCIPAL      |
                |____________________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                addNewSupermarket();
                break;
            // En desenvolupament
            case 2:
                break;
            case 3:
                Input.showSupermarkets();
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

    static int getNewIdentifier() {
        return supermarkets.getLast().getIdentifier() + 1;
    }

    public static Supermarket createNewSupermarket() {
        int identifier = getNewIdentifier();
        String name = DataInput.getValidString("Introdueix el nom del supermercat");
        return new Supermarket(identifier, name);
    }

    public static void addNewSupermarket() {
        Supermarket supermarket = createNewSupermarket();
        try {
            Output.writeSupermarketsFile(supermarket);
        } catch (IOException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de supermercats");
            Main.run();
        }
    }

    public static Supermarket chooseExistingSupermarket() {
        Input.showSupermarkets();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID del supermercat");
        DataInput.handleExit(String.valueOf(id));
        for (Supermarket s : supermarkets) {
            if (s.getIdentifier() == id) {
                return s;
            }
        }
        System.out.println("Error: No s'ha trobat el supermercat");
        Main.run();
        return null;
    }

    public static Supermarket selectSupermarketById(int id) {
        for (Supermarket s : supermarkets) {
            if (id == s.getIdentifier()) {
                return s;
            }
        }
        return null;
    }
}
