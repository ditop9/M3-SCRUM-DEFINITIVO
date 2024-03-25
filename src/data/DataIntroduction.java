package data;

import classes.User;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.Main;

public interface DataIntroduction {
    static String introduceString(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(message);
            String str = sc.nextLine();
            if (str.isBlank()) {
                System.out.println("Error: No es un caràcter vàlid");
            } else return str;
        } while (true);
    }
    static int introduceInteger(String message) {
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

    static String introduceDni() {
        Scanner sc = new Scanner(System.in);
        String dni;
        do {
            System.out.println("Introdueix eL DNI");
            dni = sc.nextLine();
            if (validateDni(dni)) {
                System.out.println("Error: No és un format correcte de DNI. Exemple '12345678A'");
            }
        } while (validateDni(dni));
        return dni;
    }

    static boolean validateDni(String dni) {
        String patron = "\\d{8}[A-HJ-NP-TV-Z]";
        Pattern pattern = Pattern.compile(patron);
        Matcher matcher = pattern.matcher(dni);
        return matcher.matches();
    }

    static int introduceAge() {
        int age;
        do {
            age = introduceInteger("""
                    Introdueix l'edat
                    0 => sortir""");
            if (age == 0) {
                return 0;
            } else if (age < 18) {
                System.out.println("Error: El client ha de ser major de 18 anys");
            }
        } while (age < 18);
        return age;
    }

    static User introduceUsernameForLogin(ArrayList<User> users) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix el nom d'usuari");
        String username = sc.nextLine();
        User user = validateUsername(users, username);
        if (user == null) {
            System.out.println("Error: No és un usuari correcte");
            Main.run();
        }
        return user;
    }

    static User validateUsername(ArrayList<User> users, String username) {
        for (User u : users) {
            if (username.equals(u.getUsername())) {
                return u;
            }
        }
        return null;
    }

    static void introducePasswordForLogin(User user) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Introdueix la contrasenya");
        String password = sc.nextLine();
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
        int entry = introduceInteger("");
        return entry == randomInt;
    }
}
