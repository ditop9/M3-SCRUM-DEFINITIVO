package classes.order;

import classes.customer.CustomerManager;
import classes.product.Product;
import classes.customer.Customer;
import classes.product.ProductManager;
import classes.supermarket.SupermarketManager;
import data.DataInput;

import classes.supermarket.Supermarket;

import java.util.*;

public class Order {
    private final int id;
    private final String date;
    private final Customer customer;
    private final Supermarket supermarket;
    private double price;
    private HashMap<Product, Double> orderProducts = new HashMap<>();

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

    public double getPrice() {
        return price;
    }

    public HashMap<Product, Double> getOrderProducts() {
        return orderProducts;
    }

    public Order(int identifier, Customer customer, Supermarket supermarket, String date, double price) {
        this.id = identifier;
        this.customer = customer;
        this.supermarket = supermarket;
        this.date = date;
        this.price = price;
        this.orderProducts = new OrderDAO().searchProductsFromOrder(id);
    }

    public Order(int identifier, Customer customer, Supermarket supermarket, String date, double price, HashMap<Product, Double> orderProducts) {
        this.id = identifier;
        this.customer = customer;
        this.supermarket = supermarket;
        this.date = date;
        this.price = price;
        this.orderProducts = orderProducts;
    }

    public static Order createNewOrder() {
        int identifier = OrderDAO.getNewIdentifier();
        String date = DataInput.getValidDate();
        Customer customer = CustomerManager.searchCustomerById();
        Supermarket supermarket = SupermarketManager.searchSupermarketById();
        HashMap<Product, Double> orderProducts = new HashMap<>();
        int id;
        double price = 0;
        do {
            ProductManager.listAll();
            System.out.println("-1 => Finalitzar compra");
            id = DataInput.getValidInteger("Introdueix l'ID del producte a introduïr.");
            if (id != -1) {
                Product product = ProductManager.searchById(id);
                if (product != null) {
                    double quantity;
                    if (product.isWeighted()) {
                        quantity = DataInput.getValidDouble("Introdueix el pes comprat de " + product.getName());
                    } else {
                        quantity = DataInput.getValidInteger("Introdueix la quantitat comprda de " + product.getName());
                    }
                    if (quantity > 0) {
                        price += product.getPrice() * quantity;
                        orderProducts.put(product, quantity);
                    } else {
                        System.out.println("Error: no es pot afegir quantitat 0 o negativa.");
                    }
                } else {
                    System.out.println("Error: No s'ha trobat cap producte amb l'ID proporcionat.");
                }
            }
        } while (id != -1);
        return new Order(identifier, customer, supermarket, date, price, orderProducts);
    }

    public void printRecipe() {
        System.out.println("_________________________________");
        System.out.println("TICKET DE COMPRA:");
        System.out.println("ID de la compra " + id);
        System.out.println("Client " + customer.getName());
        System.out.println("Supermercat " + supermarket.getName());
        System.out.println("Data " + date);
        System.out.println("Preu total " + price);
        System.out.println("Productes comprats:");
        for (Map.Entry<Product, Double> entry : orderProducts.entrySet()) {
            Product product = entry.getKey();
            double quantity = entry.getValue();
            System.out.println("- " + product.getName() + "  " + quantity + "  " + product.getPrice() * quantity + "€");
        }
        System.out.println("_________________________________");
    }
}
