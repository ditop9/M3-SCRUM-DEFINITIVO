package classes.supermarket;

import database.DAO;
import database.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupermarketDAO implements DAO<Supermarket> {
    private final static Connection con = SQLConnection.getConnection();

    static int getNewIdentifier() {
        try (Statement statement = con.createStatement();) {
            ResultSet rs = statement.executeQuery("SELECT MAX(ID) FROM Supermarkets");
            return rs.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }


    @Override
    public List<Supermarket> read() {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Supermarkets");
            List<Supermarket> supermarkets = new ArrayList<>();
            while (rs.next()) {
                supermarkets.add(new Supermarket(rs.getInt(1), rs.getString(2)));
            }
            return supermarkets;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Supermarket searchById(int id) {
        String query = "SELECT * FROM Supermarkets WHERE supermarket_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return new Supermarket(rs.getInt(1), rs.getString(2));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Supermarket supermarket) {
        String query = "INSERT INTO Supermarkets (ID, name) VALUES ?, ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, supermarket.getId());
            statement.setString(2, supermarket.getName());
            int rowsAffected = statement.executeUpdate();
            con.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Supermarkets WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            int rowsAffected = statement.executeUpdate();
            con.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Supermarket supermarket, int id) {
        String query = "UPDATE Supermarkets SET Name = ? WHERE ID = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, supermarket.getName());
            statement.setInt(2, id);
            int rowsAffected = statement.executeUpdate();
            con.close();
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
