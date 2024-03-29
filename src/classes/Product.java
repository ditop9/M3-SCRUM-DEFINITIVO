package classes;

public class Product {
    private int identifier;
    private String name;
    private double price;
    private boolean weight;

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

    @Override
    public String toString() {
        return "Producte " +
                "ID " + identifier +
                " Nom " + name +
                " Preu " + price;
    }
}
