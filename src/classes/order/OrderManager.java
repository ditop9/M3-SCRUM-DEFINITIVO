package classes.order;

import app.Main;
import data.DataInput;

import javax.swing.plaf.PanelUI;
import java.util.List;

// En desenvolupament
public class OrderManager {
    private static final OrderDAO db = new OrderDAO();
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
                |   ==== MENÚ DE COMPRES ====   |
                |_______________________________|
                | 1. MOSTRAR TOTES LES COMPRES  |
                | 2. AFEGIR COMPRA              |
                | 3. BUSCAR UNA COMPRA PER ID   |
                | 0. TORNAR AL MENÚ PRINCIPAL   |
                |_______________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                listAll();
                break;
            case 2:
                addNewOrder();
            case 0:
                System.out.println("Tornant al menú principal");
                Main.run();
                break;
            default:
                System.out.println("Error: No és una opció vàlida");
                break;
        }
    }
    public static void listAll() {
        List<Order> orders = db.read();
        for (Order o : orders) {
            o.printRecipe();
        }
    }
    private static void addNewOrder() {
        Order order = Order.createNewOrder();
        if (db.create(order)) {
            order.printRecipe();
            System.out.println("S'ha introduït la compra al sistema.");
        } else System.out.println("Error: no s'ha pogut introduïr la compra.");
    }

}
