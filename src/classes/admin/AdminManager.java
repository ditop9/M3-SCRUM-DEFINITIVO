package classes.admin;

import app.Main;
import data.DataInput;

import java.util.List;

public class AdminManager {
    private static final AdminDAO db = new AdminDAO();

    public static void run() {
        int option;
        do {
            displayMenu();
            option = DataInput.getValidInteger("Escull una opció");
            handleOption(option);
        } while (option != 0);
    }

    public static void displayMenu() {
        System.out.println("""
                _______________________________________
                |  ==== OPCIONS D'ADMINISTRADOR ====  |
                |_____________________________________|
                | 1. AFEGIR ADMINISTRADOR             |
                | 2. ACTUALITZAR ADMINISTRADOR        |
                | 3. ELIMINAR ADMINISTRADOR           |
                | 4. MOSTRAR ADMINISTRADORS           |
                | 0. TORNAR AL MENÚ PRINCIPAL         |
                |_____________________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                addNewAdmin();
                break;
            case 2:
                updateAdmin();
                break;
            case 3:
                deleteAdmin();
                break;
            case 4:
                listAdmins();
                break;
            case 0:
                System.out.println("Tornant al menú principal");
                Main.run();
                break;
            default:
                System.out.println("Error: No és una opció vàlida");
                break;
        }
    }

    private static void listAdmins() {
        List<Admin> admins = db.read();
        for (Admin a : admins) {
            System.out.println(a);
        }
    }

    private static void addNewAdmin() {
        Admin admin = Admin.createNewAdmin();
        if (db.create(admin)) {
            System.out.println("S'ha introduït el nou usuari Admin.");
        } else System.out.println("Error: no s'ha pogut introduïr l'usuari Admin.");
    }

    private static void updateAdmin() {
        listAdmins();
        System.out.println("0 => Sortir.");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'admin a actualitzar.");
        Admin admin = db.searchById(id);
        System.out.println("Introdueix les dades noves de l'usuari Admin.");
        Admin updatedAdmin = Admin.createNewAdmin();
        if (admin != null) {
            if (db.update(updatedAdmin, id)) {
                System.out.println("S'ha actualitzat l'usuari Admin correctament.");
            } else System.out.println("Error: no s'ha pogut actualitzar l'usuari Admin.");
        } else System.out.println("Error: no s'ha trobat l'usuari Admin.");
    }

    private static void deleteAdmin() {
        listAdmins();
        System.out.println("0 => Sortir.");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'admin a eliminar.");
        Admin admin = db.searchById(id);
        if (admin != null) {
            if (db.delete(admin.getId())) {
                System.out.println("S'ha eliminat correctament l'usuari Admin " + admin.getName());
            } else System.out.println("Error: no s'ha pogut completar l'acció.");
        } else System.out.println("Error: no s'ha trobat l'usuari Admin.");
    }
}
