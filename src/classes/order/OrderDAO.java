package classes.order;

import classes.customer.CustomerDAO;
import classes.product.Product;
import classes.product.ProductDAO;
import classes.supermarket.SupermarketDAO;
import database.DAO;
import database.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderDAO implements DAO<Order> {
    private final static Connection con = SQLConnection.getConnection();

    private Order createNewOrderFromQuery(ResultSet rs) {
        try {
        return new Order(rs.getInt(1), new CustomerDAO().searchById(rs.getInt(2)),
                new SupermarketDAO().searchById(rs.getInt(3)), rs.getString(4), rs.getDouble(5));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static int getNewIdentifier() {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT MAX(ID) FROM Orders");
            return rs.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    @Override
    public List<Order> read() {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Orders");
            List<Order> orders = new ArrayList<>();
            while (rs.next()) {
                orders.add(createNewOrderFromQuery(rs));
            }
            return orders;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Order searchById(int id) {
        String query = "SELECT * FROM Orders WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return createNewOrderFromQuery(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Order order) {
        String query = "INSERT INTO Orders (ID, customer_id, supermarket_id, date, price) VALUES (?, ?, ?, ?, ?)";
        String query2 = "INSERT INTO OrderProducts (order_id, product_id, quantity) VALUES (?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, order.getId());
            statement.setInt(2, order.getCustomer().getId());
            statement.setInt(3, order.getSupermarket().getId());
            statement.setString(4, order.getDate());
            statement.setDouble(5, order.getPrice());
            int rowsInserted = statement.executeUpdate();
            if (rowsInserted > 0) {
                try (PreparedStatement statement2 = con.prepareStatement(query2)) {
                    for (Map.Entry<Product, Double> entry : order.getOrderProducts().entrySet()) {
                        statement2.setInt(1, order.getId());
                        statement2.setInt(2, entry.getKey().getId());
                        statement2.setDouble(3, entry.getValue());
                        statement2.executeUpdate();
                    }
                    return true;
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    return false;
                }
            } else return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Orders WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Order updatedOrder, int id) {
        String query = "UPDATE Orders SET customer_id = ?, supermarket_id = ?, date = ? WHERE order_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(4, id);
            statement.setInt(1, updatedOrder.getCustomer().getId());
            statement.setInt(2, updatedOrder.getSupermarket().getId());
            statement.setString(3, updatedOrder.getDate());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public HashMap<Product, Double> searchProductsFromOrder(int id) {
        String query = "SELECT product_id, quantity FROM OrderProducts WHERE order_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            HashMap<Product, Double> orderProducts = new HashMap<>();
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                Product product = new ProductDAO().searchById(rs.getInt(1));
                orderProducts.put(product, rs.getDouble(2));
            }
            return orderProducts;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
