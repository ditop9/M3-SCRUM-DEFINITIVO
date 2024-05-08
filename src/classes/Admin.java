package classes;

import app.SQLConnection;
import data.DataInput;
import data.input_output.Input;
import data.input_output.Output;
import app.Main;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Admin {
    private static final Connection con = SQLConnection.getConnection();
    private int id;
    private String name;
    private String password;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin() {
        id = -1;
    }

    public Admin(int identifier, String username, String password) {
        this.id = identifier;
        this.name = username;
        this.password = password;
    }

    public static void login() {
        String name = DataInput.getValidString("Introdueix el nom d'usuari");
        int id = verifyName(name);
        if (id != -1) {
            String password = DataInput.getValidString("Introdueix la contrassenya");
            if (verifyPassword(id, password)) {
                Main.admin.setId(id);
                Main.admin.setName(name);
                Main.admin.setPassword(password);
            } else System.out.println("Error: contrassenya no vàlida.");
        } else System.out.println("Error: no es troba l'usuari.");
    }

    public static void logout() {
        Main.admin = new Admin();
    }

    private static int verifyName(String name) {
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

    private static boolean verifyPassword(int id, String password) {
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
        ArrayList<Admin> users = Input.readUsersFile();
        return users.getLast().getId() + 1;
    }

    public static Admin createNewAdmin() {
        int identifier = getNewIdentifier();
        String username = DataInput.getValidString("Introdueix el nom d'usuari");
        String password = DataInput.getValidString("Introdueix la contrasenya");
        return new Admin(identifier, username, password);
    }

    private static Admin chooseExistingAdmin() {
        ArrayList<Admin> users = Input.readUsersFile();
        Input.showUsers();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'usuari");
        if (id == 0) {
            Main.run();
        }
        for (Admin u : users) {
            if (u.getId() == id) {
                return u;
            }
        }
        System.out.println("Error: No s'ha trobat l'usuari");
        Main.run();
        return null;
    }

    public boolean create() {
        String query = "INSERT INTO Admins (admin_id, name, password) VALUES (?, ?, ?)";
        try (PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setString(2, name);
            statement.setString(3, password);
            return statement.executeUpdate() > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public static void deleteAdmin() {
        ArrayList<Admin> users = Input.readUsersFile();
        if (users.isEmpty()) {
            System.out.println("Error: No es troben usuaris Màster");
            Main.run();
        }
        Admin user = Admin.chooseExistingAdmin();
        System.out.println(user);
        System.out.println("Introdueix la contrasenya de l'usuari màster a eliminar");
        if (user == null) {
            System.out.println("S'ha produït un error");
            Main.run();
        } else {
            DataInput.introducePasswordForLogin(user);
            if (DataInput.confirmAction()) {
                users.removeIf(u -> u.getId() == user.getId());
                try {
                    Output.reWriteUsersFile(users);
                    System.out.println("S'ha eliminat l'usuari");
                } catch (FileNotFoundException e) {
                    System.out.println("Error: No s'ha trobat l'arxiu dels usuaris");
                    Main.run();
                }
            } else {
                System.out.println("Error: No s'ha proporcionat el número correcte");
                Main.run();
            }
        }
    }
    @Override
    public String toString() {
        return "User " +
                "ID " + id +
                " Username " + name;
    }
}
