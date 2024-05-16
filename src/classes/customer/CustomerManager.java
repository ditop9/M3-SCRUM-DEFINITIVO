package classes.customer;

import app.Main;
import classes.register.Register;
import data.DataInput;

import java.util.List;

public class CustomerManager {
    private static final CustomerDAO db = new CustomerDAO();

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
                _______________________________
                |  ==== MENÚ DE CLIENTS ====  |
                |_____________________________|
                | 1. AFEGIR CLIENT            |
                | 2. MOSTRAR TOTS ELS CLIENTS |
                | 3. CERCAR CLIENT PER ID     |
                | 4. CERCAR CLIENTS PER NOM   |
                | 5. ELIMINAR CLIENT          |
                | 6. ACTUALITZAR CLIENT       |
                | 0. TORNAR AL MENÚ PRINCIPAL |
                |_____________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                addNewCustomer();
                break;
            case 2:
                listCustomers();
                break;
            case 3:
                searchCustomerById();
                break;
            case 4:
                searchCustomerByName();
                break;
            case 5:
                deleteCustomer();
                break;
            case 6:
                updateCustomer();
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

    private static void listCustomers() {
        List<Customer> customers = db.read();
        for (Customer c : customers) {
            System.out.println(c);
        }
    }

    public static void addNewCustomer() {
        Customer customer = Customer.createNewCustomer();
        if (db.create(customer)) {
            System.out.println("S'ha introduït el nou Client.");
            Register.createNewRegister("Nou client afegit: ID: " + customer.getId() + " Nom: " + customer.getName());
        } else System.out.println("Error: no s'ha pogut introduïr el nou Client.");
    }

    public static Customer searchCustomerById() {
        listCustomers();
        int id = DataInput.getValidInteger("Introdueix l'ID del client");
        DataInput.handleExit(String.valueOf(id));
        Customer customer = db.searchById(id);
        if (customer != null) {
            System.out.println("Client trobat: " + customer);
            return customer;
        } else {
            System.out.println("Error: no s'ha trobat el client.");
            return null;
        }
    }

    private static void searchCustomerByName() {
        List<Customer> customers = db.read();
        String name = DataInput.getValidString("Introdueix el nom del client a buscar");
        for (Customer c : customers) {
            if (c.getName().equals(name)) {
                System.out.println(c);
            }
        }
    }

    public static void deleteCustomer() {
        listCustomers();
        int id = DataInput.getValidInteger("Introdueix l'ID del Client a eliminar.");
        Customer customer = db.searchById(id);
        if (customer != null) {
            if (db.delete(id)) {
                System.out.println("S'ha eliminat el Client correctament.");
                Register.createNewRegister("Client eliminat: ID: " + customer.getId() + " Nom: " + customer.getName());
            } else System.out.println("No s'ha pogut eliminar el Client.");
        } else System.out.println("Error: no s'ha trobat el Client.");
    }

    public static void updateCustomer() {
        Customer customer = searchCustomerById();
        if (customer != null) {
            Customer updatedCustomer = Customer.createNewCustomer();
            if (db.update(updatedCustomer, customer.getId())) {
                System.out.println("S'ha actualitzat correctament el client.");
                Register.createNewRegister("Client actualitzat: ID: " + customer.getId() + " Nom: " + customer.getName());
            } else System.out.println("Error: no s'ha pogut actualitzar el client.");
        } else System.out.println("Error: no s'ha trobat el client.");
    }
}
