package classes;

import data.DataInput;
import data.input_output.Input;
import main.Main;

import java.util.ArrayList;
import java.util.HashMap;

public class Order {
    private final String identifier;
    private final String date;
    private final Customer customer;
    private final Supermarket supermarket;
    private final ArrayList<OrderProduct> productsOrder;

    public String getIdentifier() {
        return identifier;
    }

    public Order(String date, Customer customer, Supermarket supermarket, ArrayList<OrderProduct> productsOrder) {
        identifier = getNewIdentifier(customer, supermarket);
        this.date = date;
        this.customer = customer;
        this.supermarket = supermarket;
        this.productsOrder = productsOrder;
        this.customer.getOrderList().add(this);
    }
    static String getNewIdentifier(Customer customer, Supermarket supermarket) {
        StringBuilder sb = new StringBuilder();
        sb.append(customer.getIdentifier());
        if (customer.getOrderList().isEmpty()) {
            sb.append("0");
        } else sb.append(customer.getOrderList().size());
        sb.append(supermarket.getName().toUpperCase(), 0, 3);
        return sb.toString();
    }
    public static Order createNewOrder() {
        String date = DataInput.getValidDate();
        Customer customer = Customer.chooseExistingCustomer();
        if (customer == null) {
            System.out.println("Error: No s'ha trobat el client");
            Main.run();
        } else System.out.println("Client escollit: " +  customer.getName());
        Supermarket supermarket = Supermarket.chooseExistingSupermarket();
        if (supermarket == null) {
            System.out.println("Error: No s'ha trobat el supermercat");
            Main.run();
        } else System.out.println("Supermercat escollit: " + supermarket.getName());
        ArrayList<OrderProduct> products = chooseProductsList();
        if (products.isEmpty()) {
            System.out.println("Error: No s'han trobat productes comprats");
            Main.run();
        }
        return new Order(date, customer, supermarket, products);
    }
    public static ArrayList<OrderProduct> chooseProductsList() {
        ArrayList<OrderProduct> chosenProducts = new ArrayList<>();
        int identifier;
        do {
            System.out.println("0 => Finalitzar");
            Input.showProducts();
            identifier = DataInput.getValidInteger("Introdueix el número identificador del producte");
            if (identifier != 0) {
                Product product = chooseProduct(identifier);
                if (product != null) {
                    System.out.println("0 => Cancel·lar producte");
                    if (product.isWeight()) {
                        double quantity = DataInput.getValidDouble("Introdueix el pes comprat de " + product.getName());
                        if (quantity != 0) {
                            chosenProducts.add(new OrderProduct(product, quantity));
                        } else System.out.println("No s'ha afegit el producte");
                    } else {
                        double quantity = DataInput.getValidInteger("Introdueix la quantitat comprada de " + product.getName());
                        if (quantity != 0) {
                            chosenProducts.add(new OrderProduct(product, quantity));
                        } else System.out.println("No s'ha afegit el producte");
                    }
                } else System.out.println("Error: Producte no trobat");
            }
        } while (identifier != 0);
        return chosenProducts;
    }
    static Product chooseProduct(int identifier) {
        ArrayList<Product> productsList = Input.readProductsFile();
        for (Product p : productsList) {
            if (identifier == p.getIdentifier()) {
                return p;
            }
        }
        return null;
    }
}
