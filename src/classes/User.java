package classes;

import data.DataIntroduction;
import data.input_output.Input;
import main.Main;

import java.util.ArrayList;

public class User {
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

    public User() {
    }

    public User(int identifier, String username, String password) {
        this.identifier = identifier;
        this.username = username;
        this.password = password;
    }

    public static int getNewIdentifier() {
        ArrayList<User> users = Input.readUsersFile();
        return users.getLast().getIdentifier() + 1;
    }

    public static User createNewUser() {
        int identifier = User.getNewIdentifier();
        String username = DataIntroduction.introduceString("Introdueix el nom d'usuari");
        String password = DataIntroduction.introduceString("Introdueix la contrasenya");
        return new User(identifier, username, password);
    }

    public static User chooseExistingUser() {
        ArrayList<User> users = Input.readUsersFile();
        Input.showUsers();
        System.out.println("0 => Sortir");
        int id = DataIntroduction.introduceInteger("Introdueix l'ID de l'usuari");
        if (id == 0) {
            Main.run();
        }
        for (User u : users) {
            if (u.getIdentifier() == id) {
                return u;
            }
        }
        System.out.println("Error: No s'ha trobat l'usuari");
        Main.run();
        return null;
    }

    @Override
    public String toString() {
        return "User " +
                "ID " + identifier +
                " Username " + username;
    }
}
