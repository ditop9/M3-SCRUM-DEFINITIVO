package classes;

import javax.print.attribute.standard.OrientationRequested;

public class OrderProduct {
    private Product product;
    private double quantity;
    public OrderProduct(Product product, double quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
