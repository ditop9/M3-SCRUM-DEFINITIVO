package classes;

public class Product {
    private int identifier;
    private String name;
    private double price;
    private boolean weight;
    public Product(int identifier, String name, double price, boolean weight) {
        this.identifier = identifier;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "classes.Product{" +
                "identifier=" + identifier +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", weight=" + weight +
                '}';
    }
}
