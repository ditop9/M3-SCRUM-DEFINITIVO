package data.input_output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import main.Main;

public class Input {
    public static ArrayList<User> readUsersFile() {
        File file = new File("database/UsersData.csv");
        Scanner sc = new Scanner(System.in);
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No es troba l'arxiu d'usuaris");
            Main.run();
        }
        ArrayList<User> users = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            users.add(new User(Integer.parseInt(line[0]), line[1], line[2]));
        }
        return users;
    }
    public static void showUsers() {
        ArrayList<User> users = readUsersFile();
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }

    public static ArrayList<Product> readProductsFile() throws FileNotFoundException {
        File file = new File("database/ProductsData.csv");
        Scanner sc = new Scanner(file);
        ArrayList<Product> products = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            products.add(new Product(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2]), Boolean.parseBoolean(line[3])));
        }
        return products;
    }
}
