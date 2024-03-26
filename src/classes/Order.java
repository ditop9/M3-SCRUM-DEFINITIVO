package classes;

import data.DataInput;

import java.util.ArrayList;

public class Order {
    private final String identifier;
    private final String date;
    private final Customer customer;
    private final Supermarket supermarket;
    private final ArrayList<Product> productsOrder;

    public String getIdentifier() {
        return identifier;
    }

    public Order(Customer customer, String date, Supermarket supermarket, ArrayList<Product> productsOrder) {
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
    public static void createNewOrder() {
        String date = DataInput.getValidDate();
        Customer customer = Customer.chooseExistingCustomer();
        Supermarket supermarket = Supermarket.chooseExistingSupermarket();

    }
}
