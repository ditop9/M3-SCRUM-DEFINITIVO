package classes.order;

import app.Main;
import classes.customer.Customer;
import classes.customer.CustomerManager;
import classes.register.Register;
import data.DataInput;

import java.util.List;

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
                ___________________________________
                |    ==== MENÚ DE COMPRES ====    |
                |_________________________________|
                | 1. MOSTRAR TOTES LES COMPRES    |
                | 2. AFEGIR COMPRA                |
                | 3. BUSCAR UNA COMPRA PER ID     |
                | 4. BUSCAR UNA COMPRA PER CLIENT |
                | 5. ELIMINAR UNA COMPRA          |
                | 0. TORNAR AL MENÚ PRINCIPAL     |
                |_________________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                listAll();
                break;
            case 2:
                addNewOrder();
                break;
            case 3:
                searchOrderById();
                break;
            case 4:
                List<Order> orders = searchOrderByCustomer();
                for (Order o : orders) {
                    o.printRecipe();
                }
                break;
            case 5:
                deleteOrder();
                break;
            case 6:
                deleteAllOrdersFromCustomer();
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
            Register.createNewRegister("Compra agegida: ID: " + order.getId() + " Client: ID: " + order.getCustomer().getId() +
                    " Nom: " + order.getCustomer().getName());
        } else System.out.println("Error: no s'ha pogut introduïr la compra.");
    }

    private static void searchOrderById() {
        int id = DataInput.getValidInteger("Introdueix l'ID de la compra a buscar.");
        Order order = db.searchById(id);
        order.printRecipe();
    }

    private static List<Order> searchOrderByCustomer() {
        Customer customer = CustomerManager.searchCustomerById();
        return db.searchOrdersByCustomer(customer);
    }

    private static void deleteOrder() {
        int id = DataInput.getValidInteger("Introdueix l'ID de la compra a eliminar.");
        Order order = db.searchById(id);
        if (order != null) {
            db.delete(id);
            System.out.println("S'ha eliminat la compra del sistema.");
            Register.createNewRegister("Compra eliminada: ID: " + order.getId() + " Client: ID: " + order.getCustomer().getId() +
                    " Nom: " + order.getCustomer().getName());
        } else System.out.println("Error: no s'ha pogut eliminar la compra.");
    }

    private static void deleteAllOrdersFromCustomer() {
        List<Order> orders = searchOrderByCustomer();
        for (Order o : orders) {
            db.delete(o.getId());
            Register.createNewRegister("Compra eliminada: ID: " + o.getId() + " Client: ID: " + o.getCustomer().getId() +
                    " Nom: " + o.getCustomer().getName());
        }
        System.out.println("S'han eliminat totes les compres.");
    }
}
