package classes.register;

import classes.admin.AdminDAO;
import database.DAO;
import database.SQLConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class RegisterDAO implements DAO<Register> {
    private static final Connection con = SQLConnection.getConnection();

    @Override
    public List<Register> read() {
        String query = "SELECT * FROM Registers";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            List<Register> registers = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                registers.add(new Register(new AdminDAO().searchById(rs.getInt(1)), rs.getString(2), rs.getString(3)));
            }
            return registers;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public Register searchById(int id) {
        return null;
    }

    @Override
    public boolean create(Register register) {
        String query = "INSERT INTO Registers (admin_id, date, description) VALUES (?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, register.getAdmin().getId());
            statement.setString(2, register.getDate());
            statement.setString(3, register.getDescription());
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(int id) {
        return false;
    }

    @Override
    public boolean update(Register register, int id) {
        return false;
    }
}
