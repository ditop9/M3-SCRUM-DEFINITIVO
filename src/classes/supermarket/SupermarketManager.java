package classes.supermarket;

import app.Main;
import data.DataInput;

import java.util.List;

public class SupermarketManager {
    private final static SupermarketDAO db = new SupermarketDAO();

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
            case 2:
                break;
            case 3:
                listSupermarkets();
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

    private static void listSupermarkets() {
        List<Supermarket> supermarkets = db.read();
        for (Supermarket s : supermarkets) {
            System.out.println(s);
        }
    }

    public static void addNewSupermarket() {
        Supermarket supermarket = Supermarket.createNewSupermarket();
        if (db.create(supermarket)) {
            System.out.println("S'ha introduït el nou supermercat.");
        } else System.out.println("Error: no s'ha pogut introduïr el nou supermercat.");
    }

    public static Supermarket searchSupermarketById() {
        listSupermarkets();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID del supermercat.");
        DataInput.handleExit(String.valueOf(id));
        Supermarket supermarket = db.searchById(id);
        if (supermarket != null) {
            System.out.println("Supermercat trobat: " + supermarket);
            return supermarket;
        } else {
            System.out.println("Error: no s'ha trobat el supermercat.");
            return null;
        }
    }
}
