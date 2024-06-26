package classes.product;

import database.DAO;
import database.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO implements DAO<Product> {

    private static final Connection con = SQLConnection.getConnection();

    public static int getNewIdentifier() {
        try (Statement statement = con.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT MAX(ID) FROM Products");
            return result.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    private static Product createProductFromQuery(ResultSet rs) {
        try {
            return new Product(rs.getInt(1), rs.getString(2),
                    rs.getDouble(3), rs.getBoolean(4));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Product> read() {
        try (Statement statement = con.createStatement()) {
            List<Product> products = new ArrayList<>();
            ResultSet rs = statement.executeQuery("SELECT * FROM Products");
            while (rs.next()) {
                products.add(createProductFromQuery(rs));
            }
            return products;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Product searchById(int id) {
        String query = "SELECT * FROM Products WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return createProductFromQuery(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Product product) {
        String query = "INSERT INTO Products (ID, name, price, weighted_product) VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setBoolean(4, product.isWeighted());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Products WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Product product, int id) {
        String query = "UPDATE Products Name = ?, Price = ?, Weighted = ? WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(4, id);
            statement.setString(2, product.getName());
            statement.setDouble(3, product.getPrice());
            statement.setBoolean(4, product.isWeighted());
            int rowsAffected = statement.executeUpdate();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
