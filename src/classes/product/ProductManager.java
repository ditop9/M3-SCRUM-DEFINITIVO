package classes.product;

import app.Main;
import data.DataInput;

public class ProductManager {
    private static final ProductDAO db = new ProductDAO();
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
                _________________________________
                |  ==== MENÚ DE PRODUCTES ====  |
                |_______________________________|
                | 1. AFEGIR PRODUCTE            |
                | 2. CERCAR PRODUCTE PER ID     |
                | 3. MOSTRAR TOTS ELS PRODUCTES |
                | 4. ELIMINAR PRODUCTE          |
                | 4. ACTUALITZAR PRODUCTE       |
                | 0. TORNAR AL MENÚ PRINCIPAL   |
                |_______________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:

                break;
            case 2:

                break;
            case 3:

                break;
            case 4:

            case 5:

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
