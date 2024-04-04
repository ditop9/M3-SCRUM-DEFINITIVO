package data.input_output;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import classes.*;
import app.Main;
import manager.CustomerManager;

public class Input {
    static ArrayList<String[]> readFileData(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        ArrayList<String[]> fileData = new ArrayList<>();
        sc.nextLine();
        while (sc.hasNextLine()) {
            String[] line = sc.nextLine().split(",");
            fileData.add(line);
        }
        return fileData;
    }

    public static ArrayList<Admin> readUsersFile() {
        File file = new File("database/AdminData.csv");
        ArrayList<Admin> users = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No es troba l'arxiu d'usuaris");
            Main.run();
        }
        for (String[] line : fileData) {
            users.add(new Admin(Integer.parseInt(line[0]), line[1], line[2]));
        }
        return users;
    }

    public static void showUsers() {
        ArrayList<Admin> users = readUsersFile();
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + ". " + users.get(i));
        }
    }

    public static void showCustomers() {
        ArrayList<Customer> customers = readCustomersFile();
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }
    }

    public static void showSupermarkets() {
        ArrayList<Supermarket> supermarkets = readSupermarketsFile();
        for (int i = 0; i < supermarkets.size(); i++) {
            System.out.println((i + 1) + ". " + supermarkets.get(i));
        }
    }

    public static void showProducts() {
        ArrayList<Product> products = readProductsFile();
        for (Product p : products) {
            System.out.println("- " + p);
        }
    }

    public static ArrayList<Product> readProductsFile() {
        File file = new File("database/ProductsData.csv");
        ArrayList<Product> products = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de productes");
            Main.run();
        }
        for (String[] line : fileData) {
            products.add(new Product(Integer.parseInt(line[0]), line[1], Double.parseDouble(line[2]), Boolean.parseBoolean(line[3])));
        }
        return products;
    }

    public static ArrayList<Customer> readCustomersFile() {
        File file = new File("database/CustomersData.csv");
        ArrayList<Customer> customers = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de clients");
            Main.run();
        }
        for (String[] line : fileData) {
            customers.add(new Customer(Integer.parseInt(line[0]), line[1], line[2], Integer.parseInt(line[3])));
        }
        return customers;
    }

    public static ArrayList<Supermarket> readSupermarketsFile() {
        File file = new File("database/SupermarketsData.csv");
        ArrayList<Supermarket> supermarkets = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de supermercats");
            Main.run();
        }
        for (String[] line : fileData) {
            supermarkets.add(new Supermarket(Integer.parseInt(line[0]), line[1]));
        }
        return supermarkets;
    }

    public static ArrayList<Order> readOrdersFile() {
        File file = new File("database/Orders.csv");
        ArrayList<Order> orders = new ArrayList<>();
        ArrayList<String[]> fileData = new ArrayList<>();
        try {
            fileData = readFileData(file);
        } catch (FileNotFoundException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de compres");
            Main.run();
        }
        for (String[] line : fileData) {
            String identifier = line[0];
            String date = line[1];
            Customer customer = CustomerManager.selectCustomerById(Integer.parseInt(line[2]));
            Supermarket supermarket = Supermarket.selectSupermarketById(Integer.parseInt(line[3]));
            ArrayList<OrderProduct> orderProducts = Product.refactorProductIdInProducts(line[4]);
            orders.add(new Order(identifier, date, customer, supermarket, orderProducts));
        }
        return orders;
    }
}
