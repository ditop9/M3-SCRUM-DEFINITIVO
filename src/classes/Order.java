package classes;

import data.DataInput;
import data.input_output.Input;
import app.Main;

import java.util.ArrayList;
import java.util.Random;

public class Order {
    private final String identifier;
    private final String date;
    private final Customer customer;
    private final Supermarket supermarket;
    private final ArrayList<OrderProduct> productsOrder;

    public String getIdentifier() {
        return identifier;
    }

    public Order(String identifier, String date, Customer customer, Supermarket supermarket, ArrayList<OrderProduct> productsOrder) {
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
        Customer customer = Customer.chooseExistingCustomer();
        if (customer == null) {
            System.out.println("Error: No s'ha trobat el client");
            Main.run();
        } else System.out.println("Client escollit: " + customer.getName());
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
        String identifier = getNewIdentifier(supermarket);
        return new Order(identifier, date, customer, supermarket, products);
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
                        chosenProducts.add(new OrderProduct(product, quantity));
                    } else System.out.println("No s'ha afegit el producte");
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

    void showProducts(StringBuilder sb) {
        for (OrderProduct p : productsOrder) {
            if (p.getProduct().isWeight()) {
                sb.append(p.getProduct().toStringTicket()).append(" Quantitat: ").append(p.getQuantity()).append("Kg\n");
            } else {
                sb.append(p.getProduct().toStringTicket()).append(" Quantitat: ").append(p.getQuantity()).append("\n");
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
