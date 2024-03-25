package classes;

import java.util.ArrayList;
import java.util.Random;

public class Order {
    private String identifier;
    private Customer customer;
    private Supermarket supermarket;
    private ArrayList<Product> productsOrder;

    public String getIdentifier() {
        return identifier;
    }

    public Order(Customer customer, Supermarket supermarket, ArrayList<Product> productsOrder) {
        identifier = getNewIdentifier(customer, supermarket);
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
    public static void createNewOrder(Customer customer) {

    }
}
