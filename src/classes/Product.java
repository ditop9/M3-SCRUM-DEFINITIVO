package classes;

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

    @Override
    public String toString() {
        return "ID " + identifier +
                " Nom " + name +
                " Preu " + price;
    }
}
