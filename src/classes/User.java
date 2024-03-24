package classes;

import data.input_output.Input;
import main.Main;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        ArrayList<User> users = new ArrayList<>();
        try {
            users = Input.readUsersFile();
        } catch (FileNotFoundException e) {
            System.out.println(e + "\nError: No s'ha trobat l'arxiu d'usuaris");
            Main.run();
        }
        return users.getLast().getIdentifier() + 1;
    }
    public static User createNewUser() {
        Scanner sc = new Scanner(System.in);
        int identifier = User.getNewIdentifier();
        System.out.println("Introdueix el nom d'usuari");
        String username = sc.nextLine();
        System.out.println("Introdueix la contrasenya");
        String password = sc.nextLine();
        return new User(identifier, username, password);
    }
}
