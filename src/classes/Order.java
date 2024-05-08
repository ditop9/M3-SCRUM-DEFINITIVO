package classes;

import data.DataInput;
import data.input_output.Input;
import app.Main;
import manager.CustomerManager;
import manager.SupermarketManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Order {
    private final String identifier;
    private final String date;
    private final Customer customer;
    private final Supermarket supermarket;
    private final HashMap<Product, Double> productsOrder;

    public String getIdentifier() {
        return identifier;
    }

    public Order(String identifier, String date, Customer customer, Supermarket supermarket, HashMap<Product, Double> productsOrder) {
        this.identifier = identifier;
        this.date = date;
        this.customer = customer;
        this.supermarket = supermarket;
        this.productsOrder = productsOrder;
        this.customer.getOrderList().add(this);
    }

    static String getNewIdentifier(Supermarket supermarket) {
        Random random = new Random();
        int randomNum = 10000000 + random.nextInt(90000000);
        return randomNum + supermarket.getName().toUpperCase().substring(0, 3);
    }

    public static Order createNewOrder() {
        String date = DataInput.getValidDate();
        Customer customer = CustomerManager.chooseExistingCustomer();
        if (customer == null) {
            System.out.println("Error: No s'ha trobat el client");
            Main.run();
        } else System.out.println("Client escollit: " + customer.getName());
        Supermarket supermarket = SupermarketManager.chooseExistingSupermarket();
        if (supermarket == null) {
            System.out.println("Error: No s'ha trobat el supermercat");
            Main.run();
        } else System.out.println("Supermercat escollit: " + supermarket.getName());
        HashMap<Product, Double> products = chooseProductsList();
        if (products.isEmpty()) {
            System.out.println("Error: No s'han trobat productes comprats");
            Main.run();
        }
        String identifier = getNewIdentifier(supermarket);
        return new Order(identifier, date, customer, supermarket, products);
    }

    public static HashMap<Product, Double> chooseProductsList() {
        HashMap<Product, Double> chosenProducts = new HashMap<>();
        int identifier;
        do {
            System.out.println("0 => Finalitzar");
            Input.showProducts();
            identifier = DataInput.getValidInteger("Introdueix el número identificador del producte");
            if (identifier != 0) {
                Product product = chooseProduct(identifier);
                if (product != null) {
                    System.out.println("0 => Cancel·lar producte");
                    double quantity;
                    if (product.isWeight()) {
                        quantity = DataInput.getValidDouble("Introdueix el pes comprat de " + product.getName());
                    } else {
                        quantity = DataInput.getValidInteger("Introdueix la quantitat comprada de " + product.getName());
                    }
                    if (quantity > 0) {
                        if (product.isWeight()) {
                            System.out.println("S'ha afegit " + quantity + "Kg de " + product.getName());
                        } else System.out.println("S'ha afegit " + quantity + " unitats de " + product.getName());
                        chosenProducts.put(product, quantity);
                    } else System.out.println("No s'ha afegit el producte");
                } else System.out.println("Error: Producte no trobat");
            }
        } while (identifier != 0);
        return chosenProducts;
    }

    static Product chooseProduct(int identifier) {
        ArrayList<Product> productsList = Input.readProductsFile();
        for (Product p : productsList) {
            if (identifier == p.getId()) {
                return p;
            }
        }
        return null;
    }

    void showProducts(StringBuilder sb) {
        for (Map.Entry<Product, Double> entry : productsOrder.entrySet()) {
            if (entry.getKey().isWeight()) {
                sb.append(entry.getKey().toStringTicket()).append(" Quantitat: ").append(entry.getValue()).append("Kg\n");
            } else {
                sb.append(entry.getKey().toStringTicket()).append(" Quantitat: ").append(entry.getValue()).append("\n");
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n================================")
                .append("\nOrder: ")
                .append(identifier)
                .append("\nData: ").append(date)
                .append("\nClient: ").append(customer.getName())
                .append("\nSupermercat: ").append(supermarket.getName())
                .append("\nProductes:\n");
        showProducts(sb);
        return sb.toString();
    }
}
