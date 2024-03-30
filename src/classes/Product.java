package classes;

import data.input_output.Input;

import java.util.ArrayList;
import java.util.HashMap;

public class Product {
    private final int identifier;
    private final String name;
    private final double price;
    private final boolean weight;

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public boolean isWeight() {
        return weight;
    }

    public Product(int identifier, String name, double price, boolean weight) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    public static ArrayList<OrderProduct> refactorProductIdInProducts(String productsIdLine) {
        String[] productsIdAndQuantity = productsIdLine.split("'");
        return refactorOrderProducts(productsIdAndQuantity);
    }
    public static Product selectProductById(int id) {
        ArrayList<Product> products = Input.readProductsFile();
        for (Product p : products) {
            if (id == p.getIdentifier()) {
                return p;
            }
        }
        return null;
    }
    public static ArrayList<OrderProduct> refactorOrderProducts(String[] products) {
        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
        for (String p : products) {
            String[] productsSplit = p.split(":");
            Product product = selectProductById(Integer.parseInt(productsSplit[0]));
            orderProducts.add(new OrderProduct(product, Double.parseDouble(productsSplit[1])));
        }
        return orderProducts;
    }

    @Override
    public String toString() {
        return "ID " + identifier +
                " Nom " + name +
                " Preu " + price;
    }
}
