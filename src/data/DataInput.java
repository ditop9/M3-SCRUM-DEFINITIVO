package data;

import classes.admin.Admin;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import app.Main;

public interface DataInput {
    static String getValidString(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0 => Sortir");
            System.out.println(message);
            String str = sc.nextLine();
            handleExit(str);
            if (str.isBlank()) {
                System.out.println("Error: No es un caràcter vàlid");
            } else return str;
        } while (true);
    }

    static int getValidInteger(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            if (!message.isBlank()) {
                System.out.println(message);
            }
            try {
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: No es tracta d'un caràcter vàlid");
                sc.next();
            }
        } while (true);
    }

    static double getValidDouble(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            if (!message.isBlank()) {
                System.out.println(message);
            }
            try {
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Error: No es tracta d'un caràcter vàlid");
                sc.next();
            }
        } while (true);
    }

    static String getValidDni() {
        Scanner sc = new Scanner(System.in);
        String dni;
        do {
            System.out.println("0 => Sortir");
            System.out.println("Introdueix eL DNI");
            dni = sc.nextLine();
            handleExit(dni);
            if (!validateDni(dni)) {
                System.out.println("Error: No és un format correcte de DNI. Exemple '12345678A'");
            }
        } while (!validateDni(dni));
        return dni;
    }

    static boolean validateDni(String dni) {
        String patron = "\\d{8}[A-HJ-NP-TV-Z]";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(dni);
        return matcher.matches();
    }

    static int getValidAge() {
        int age;
        do {
            System.out.println("0 => Sortir");
            age = getValidInteger("Introdueix l'edat");
            handleExit(String.valueOf(age));
            if (age < 18) {
                System.out.println("Error: El client ha de ser major de 18 anys");
            }
        } while (age < 18);
        return age;
    }

    static Admin introduceUsernameForLogin(ArrayList<Admin> users) {
        Scanner sc = new Scanner(System.in);
        System.out.println("0 => Sortir");
        System.out.println("Introdueix el nom d'usuari");
        String username = sc.nextLine();
        handleExit(username);
        Admin user = validateUsername(users, username);
        if (user == null) {
            System.out.println("Error: No és un usuari correcte");
            Main.run();
        }
        return user;
    }

    static Admin validateUsername(ArrayList<Admin> users, String username) {
        for (Admin u : users) {
            if (username.equals(u.getName())) {
                return u;
            }
        }
        return null;
    }

    static void introducePasswordForLogin(Admin user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("0 => Sortir");
        System.out.println("Introdueix la contrasenya");
        String password = sc.nextLine();
        handleExit(password);
        if (!password.equals(user.getPassword())) {
            System.out.println("Error: No és una contrasenya correcte");
            Main.run();
        }
    }

    static boolean confirmAction() {
        Random random = new Random();
        int randomInt = 1000 + random.nextInt(9000);
        System.out.println("Introdueix el següent número per confirmar l'acció");
        System.out.println(randomInt);
        int entry = getValidInteger("");
        return entry == randomInt;
    }

    static String getValidDate() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0 => Sortir");
            System.out.println("Introdueix la data (dd/mm/aaaa)");
            String date = sc.nextLine();
            handleExit(date);
            if (validateDate(date)) {
                return date;
            }
        } while (true);
    }

    static boolean validateDate(String date) {
        String regex = "^(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[0-2])/\\d{4}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(date);
        return matcher.matches();
    }

    static void handleExit(String val) {
        if (val.equals("0")) {
            System.out.println("Tornant al menú...");
            Main.run();
        }
    }
}