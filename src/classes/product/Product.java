package classes.product;

import data.DataInput;

/**
 * Classe que representa un producte en el sistema.
 */
public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final boolean weighted; // Indica si el producte es ven per pes o per unitat

    // Constructor
    public Product(int identifier, String name, double price, boolean weight) {
        this.id = identifier;
        this.name = name;
        this.price = price;
        this.weighted = weight;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isWeighted() {
        return weighted;
    }

    public static Product createNewProduct() {
        int identifier = ProductDAO.getNewIdentifier();
        String name = DataInput.getValidString("Introdueix el nom del nou producte.");
        double price = DataInput.getValidDouble("Introdueix el preu del nou producte");
        boolean weighted = DataInput.getValidBoolean("1 si el producte és de pes | 2 si el producte no és de pes");
        return new Product(identifier, name, price, weighted);
    }

    @Override
    public String toString() {
        return "ID " + id +
                " Nom " + name +
                " Preu " + price;
    }
}

