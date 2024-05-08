package classes;

import app.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

public class Customer {
    private static final Connection con = SQLConnection.getConnection();
    private final int identifier;
    private final String dni;
    private final String name;
    private final int age;
    private final ArrayList<Order> orderList;

    public int getIdentifier() {
        return identifier;
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
        this.identifier = identifier;
        this.dni = dni;
        this.name = name;
        this.age = edad;
        orderList = new ArrayList<>();
    }

    public boolean create() {
        String query = "INSERT INTO Customers () VALUES (?, ?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, identifier);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Client" +
                " ID " + identifier +
                " DNI " + dni +
                " Nom " + name +
                " Edat " + age;
    }
}



