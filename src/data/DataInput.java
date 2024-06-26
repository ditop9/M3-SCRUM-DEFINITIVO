package data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
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
            System.out.println("0 => Sortir");
            if (!message.isBlank()) {
                System.out.println(message);
            }
            try {
                int anInt = sc.nextInt();
                handleExit(String.valueOf(anInt));
                return anInt;
            } catch (InputMismatchException e) {
                System.out.println("Error: No es tracta d'un caràcter vàlid");
                sc.next();
            }
        } while (true);
    }

    static double getValidDouble(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0 => Sortir");
            if (!message.isBlank()) {
                System.out.println(message);
            }
            try {
                double aDouble = sc.nextDouble();
                handleExit(String.valueOf(aDouble));
                return aDouble;
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
        return !matcher.matches();
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

    static boolean getValidBoolean(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("0 => Sortir");
            try {
                System.out.println(message);
                int option = sc.nextInt();
                if (option == 1) {
                    return true;
                } else if (option == 2) {
                    return false;
                } else System.out.println("Error: no és una opció vàlida.");
            } catch (InputMismatchException e) {
                System.out.println("Error: No es tracta d'un caràcter vàlid");
                sc.next();
            }
        } while (true);
    }

    static String getActualDate() {
        LocalDateTime actualDate = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return actualDate.format(formatter);
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
            if (Main.admin.getId() != -1) {
                Main.run();
            } else {
                System.out.println("El programa es tanca...");
                System.exit(0);
            }
        }
    }
}