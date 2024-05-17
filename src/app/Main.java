package app;

import classes.admin.Admin;
import data.DataInput;
import menu.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static Admin admin = new Admin();

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        Scanner sc = new Scanner(System.in);
        int option;
        do {
            try {
                if (admin.getId() != -1) {
                    LoggedMenu.displayMenu();
                    System.out.println("Escull una opció.");
                    option = sc.nextInt();
                    LoggedMenu.handleOption(option);
                } else {
                    MainMenu.displayMenu();
                    option = DataInput.getValidInteger("Escull una opció");
                    MainMenu.handleOption(option);
                }
            } catch (InputMismatchException e) {
                System.out.println(e.getMessage());
                sc.nextLine();
                option = -1;
            }
        } while (option != 0);
    }
}