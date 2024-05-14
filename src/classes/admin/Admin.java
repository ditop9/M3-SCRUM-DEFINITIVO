package classes.admin;


import data.DataInput;
import app.Main;

public class Admin {
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
        int id = AdminDAO.verifyName(name);
        if (id != -1) {
            String password = DataInput.getValidString("Introdueix la contrassenya");
            if (AdminDAO.verifyPassword(id, password)) {
                Main.admin.setId(id);
                Main.admin.setName(name);
                Main.admin.setPassword(password);
            } else System.out.println("Error: contrassenya no vàlida.");
        } else System.out.println("Error: no es troba l'usuari.");
    }

    public static void logout() {
        Main.admin = new Admin();
    }

    public static Admin createNewAdmin() {
        int identifier = AdminDAO.getNewIdentifier();
        String username = DataInput.getValidString("Introdueix el nom d'usuari");
        String password = DataInput.getValidString("Introdueix la contrasenya");
        return new Admin(identifier, username, password);
    }


    @Override
    public String toString() {
        return "User " +
                "ID " + id +
                " Username " + name;
    }
}
