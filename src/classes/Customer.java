package classes;

import app.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Customer {
    private static final Connection con = SQLConnection.getConnection();
    private final int id;
    private final String dni;
    private final String name;
    private final int age;
    private final ArrayList<Order> orderList;

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public Customer(int identifier, String dni, String name, int edad) {
        this.id = identifier;
        this.dni = dni;
        this.name = name;
        this.age = edad;
        orderList = new ArrayList<>();
    }

    public static List<Customer> read() {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Customers");
            List<Customer> customers = new ArrayList<>();
            while (rs.next()) {
                customers.add(new Customer(rs.getInt(1), rs.))
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public boolean create() {
        String query = "INSERT INTO Customers () VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean delete() {
        String query = "DELETE FROM Customers WHERE customer_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public String toString() {
        return "Client" +
                " ID " + id +
                " DNI " + dni +
                " Nom " + name +
                " Edat " + age;
    }
}



