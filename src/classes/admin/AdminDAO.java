package classes.admin;

import database.DAO;
import database.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AdminDAO implements DAO<Admin> {
    private static final Connection con = SQLConnection.getConnection();

    public static int verifyName(String name) {
        String query = "SELECT admin_id FROM Admins WHERE name = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            ResultSet result = statement.executeQuery();
            return result.getInt(1);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }

    public static boolean verifyPassword(int id, String password) {
        String query = "SELECT password FROM Admins WHERE admin_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet result = statement.executeQuery();
            return result.getString(1).equals(password);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    static int getNewIdentifier() {
        try (Statement statement = con.createStatement()) {
            ResultSet result = statement.executeQuery("SELECT MAX(admin_id) FROM Admins");
            return result.getInt(1) + 1;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return -1;
        }
    }
    @Override
    public List<Admin> read() {
        try (Statement statement = con.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM Admins");
            List<Admin> admins = new ArrayList<>();
            while (rs.next()) {
                admins.add(new Admin(rs.getInt(1), rs.getString(2), rs.getString(3)));
            }
            return admins;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Admin searchById(int id) {
        String query = "SELECT * FROM Admins WHERE admin_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            ResultSet rs = statement.executeQuery();
            return new Admin(rs.getInt(1), rs.getString(2), rs.getString(3));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public boolean create(Admin admin) {
        String query = "INSERT INTO Admins (admin_id, name, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, admin.getId());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getPassword());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        String query = "DELETE FROM Admins WHERE admin_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean update(Admin updatedAdmin, int id) {
        String query = "UPDATE Admins SET name = ?, password = ? WHERE admin_id = ?";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(3, id);
            statement.setString(1, updatedAdmin.getName());
            statement.setString(2, updatedAdmin.getPassword());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
