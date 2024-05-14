package classes.order;

import classes.customer.CustomerManager;
import classes.product.Product;
import classes.customer.Customer;
import classes.supermarket.SupermarketManager;
import data.DataInput;
import data.input_output.Input;

import classes.supermarket.Supermarket;

import java.util.*;

public class Order {
    private int id;
    private String date;
    private Customer customer;
    private Supermarket supermarket;
    private HashMap<Product, Integer> orderProducts = new HashMap<>();

    public int getId() {
        return id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Supermarket getSupermarket() {
        return supermarket;
    }

    public String getDate() {
        return date;
    }

    public Order() {
    }

    public Order(int identifier, Customer customer, Supermarket supermarket, String date) {
        this.id = identifier;
        this.customer = customer;
        this.supermarket = supermarket;
        this.date = date;
    }

    public static Order createNewOrder() {
        String date = DataInput.getValidDate();
        Customer customer = CustomerManager.searchCustomerById();
        if (customer != null) {
            Supermarket supermarket = SupermarketManager.searchSupermarketById();
            if (supermarket != null) {
                HashMap<Product, Double> products = chooseProductsList();
                int id = OrderDAO.getNewIdentifier();
                return new Order(id, customer, supermarket, date);
            }
        }
        return null;
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
                    if (product.isWeighted()) {
                        quantity = DataInput.getValidDouble("Introdueix el pes comprat de " + product.getName());
                    } else {
                        quantity = DataInput.getValidInteger("Introdueix la quantitat comprada de " + product.getName());
                    }
                    if (quantity > 0) {
                        if (product.isWeighted()) {
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

//    void showProducts(StringBuilder sb) {
//        for (Map.Entry<Product, Double> entry : productsOrder.entrySet()) {
//            if (entry.getKey().isWeight()) {
//                sb.append(entry.getKey().toStringTicket()).append(" Quantitat: ").append(entry.getValue()).append("Kg\n");
//            } else {
//                sb.append(entry.getKey().toStringTicket()).append(" Quantitat: ").append(entry.getValue()).append("\n");
//            }
//        }
//    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n================================")
                .append("\nOrder: ")
                .append(id)
                .append("\nData: ").append(date)
                .append("\nClient: ").append(customer.getName())
                .append("\nSupermercat: ").append(supermarket.getName())
                .append("\nProductes:\n");
//        showProducts(sb);
        return sb.toString();
    }
}
