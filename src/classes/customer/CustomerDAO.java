package classes.customer;

import database.DAO;
import database.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO implements DAO<Customer> {
    private static final Connection con = SQLConnection.getConnection();

    public static int getNewIdentifier() {
        try (Statement statement = con.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT MAX(ID) FROM Customers");
            return result.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static Customer createNewCustomerFromQuery(ResultSet rs) {
        try {
            return new Customer(rs.getInt(1), rs.getString(2),
                    rs.getString(3), rs.getString(4),
                    rs.getInt(5), rs.getInt(6), rs.getString(7));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<Customer> read() {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Customers");
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                customers.add(createNewCustomerFromQuery(rs));
            }
            return customers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Customer searchById(int id) {
        String query = "SELECT * FROM Customers WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return createNewCustomerFromQuery(rs);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Customer customer) {
        String query = "INSERT INTO Customers (ID, dni, first_name, last_name, age, phone, email) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, customer.getId());
            statement.setString(2, customer.getDni());
            statement.setString(3, customer.getName());
            statement.setString(4, customer.getLastName());
            statement.setInt(5, customer.getAge());
            statement.setInt(6, customer.getPhone());
            statement.setString(7, customer.getEmail());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Customers WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Customer updatedCustomer, int id) {
        String query = "UPDATE Customers SET dni = ?, first_name = ?, age = ? WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(4, id);
            statement.setString(1, updatedCustomer.getDni());
            statement.setString(2, updatedCustomer.getName());
            statement.setInt(3, updatedCustomer.getAge());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
