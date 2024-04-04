package classes;

import data.DataInput;
import data.input_output.Input;
import data.input_output.Output;
import app.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Admin {
    private int identifier;
    private String username;
    private String password;

    public int getIdentifier() {
        return identifier;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Admin(int identifier, String username, String password) {
        this.identifier = identifier;
        this.username = username;
        this.password = password;
    }

    static int getNewIdentifier() {
        ArrayList<Admin> users = Input.readUsersFile();
        return users.getLast().getIdentifier() + 1;
    }

    static Admin createNewAdmin() {
        int identifier = getNewIdentifier();
        String username = DataInput.getValidString("Introdueix el nom d'usuari");
        String password = DataInput.getValidString("Introdueix la contrasenya");
        return new Admin(identifier, username, password);
    }

    static Admin chooseExistingAdmin() {
        ArrayList<Admin> users = Input.readUsersFile();
        Input.showUsers();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'usuari");
        if (id == 0) {
            Main.run();
        }
        for (Admin u : users) {
            if (u.getIdentifier() == id) {
                return u;
            }
        }
        System.out.println("Error: No s'ha trobat l'usuari");
        Main.run();
        return null;
    }

    public static void addNewAdmin() {
        Admin newMasterUser = createNewAdmin();
        try {
            Output.writeUsersFile(newMasterUser);
        } catch (IOException e) {
            System.out.println(e + "Error: No s'ha trobat l'arxiu d'usuaris");
            Main.run();
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
                users.removeIf(u -> u.getIdentifier() == user.getIdentifier());
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
                "ID " + identifier +
                " Username " + username;
    }
}
