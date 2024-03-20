import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface DataIntroduction {
    static int introduceInteger(String message) {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println(message);
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
}