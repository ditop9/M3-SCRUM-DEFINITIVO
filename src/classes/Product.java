package classes;

import data.input_output.Input;

import java.util.ArrayList;

/**
 * Classe que representa un producte en el sistema.
 */
public class Product {
    private final int id;
    private final String name;
    private final double price;
    private final boolean weight; // Indica si el producte es ven per pes o per unitat

    // Constructor
    public Product(int identifier, String name, double price, boolean weight) {
        this.id = identifier;
        this.name = name;
        this.price = price;
        this.weight = weight;
    }

    // Getters
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public boolean isWeight() {
        return weight;
    }

    /**
     * Mètode que refactoritza una línia de productes amb els seus identificadors i quantitats.
     *
     * @param productsIdLine La línia de productes en format "id:quantitat'id:quantitat'..."
     * @return Una llista de productes de la compra amb les seves quantitats corresponents.
     */
//    public static ArrayList<OrderProduct> refactorProductIdInProducts(String productsIdLine) {
//        String[] productsIdAndQuantity = productsIdLine.split("'");
//        return refactorOrderProducts(productsIdAndQuantity);
//    }

    /**
     * Mètode que selecciona un producte pel seu identificador.
     *
     * @param id L'identificador del producte a seleccionar.
     * @return El producte corresponent a l'identificador, o null si no es troba.
     */
    public static Product selectProductById(int id) {
        ArrayList<Product> products = Input.readProductsFile();
        for (Product p : products) {
            if (id == p.getId()) {
                return p;
            }
        }
        return null;
    }
//    public static ArrayList<OrderProduct> refactorOrderProducts(String[] products) {
//        ArrayList<OrderProduct> orderProducts = new ArrayList<>();
//        for (String p : products) {
//            String[] productsSplit = p.split(":");
//            Product product = selectProductById(Integer.parseInt(productsSplit[0]));
//            orderProducts.add(new OrderProduct(product, Double.parseDouble(productsSplit[1])));
//        }
//        return orderProducts;
//    }

    @Override
    public String toString() {
        return "ID " + id +
                " Nom " + name +
                " Preu " + price;
    }
    public String toStringTicket() {
        return name + ". Preu: " + price;
    }
}

