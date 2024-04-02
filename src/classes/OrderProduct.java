package classes;

public class OrderProduct {
    private Product product;
    private double quantity;

    public Product getProduct() {
        return product;
    }

    public double getQuantity() {
        return quantity;
    }

    public OrderProduct(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
