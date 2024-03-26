package classes;

import data.DataInput;
import data.input_output.Input;
import data.input_output.Output;
import main.Main;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

public class Customer {
    private final int identifier;
    private final String dni;
    private final String name;
    private final int age;
    private final ArrayList<Order> orderList;

    public int getIdentifier() {
        return identifier;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public ArrayList<Order> getOrderList() {
        return orderList;
    }

    public Customer(int identifier, String dni, String nombre, int edad) {
        this.identifier = identifier;
        this.dni = dni;
        this.name = nombre;
        this.age = edad;
        orderList = new ArrayList<>();
    }
    static int getNewIdentifier() {
        ArrayList<Customer> customers = Input.readCustomersFile();
        return customers.getLast().getIdentifier() + 1;
    }
    static Customer chooseExistingCustomer() {
        ArrayList<Customer> customers = Input.readCustomersFile();
        Input.showCustomers();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'usuari");
        DataInput.handleExit(String.valueOf(id));
        for (Customer c : customers) {
            if (c.getIdentifier() == id) {
                return c;
            }
        }
        System.out.println("Error: No s'ha trobat el client");
        Main.run();
        return null;
    }
    public static Customer createNewCustomer() {
        int identifier = getNewIdentifier();
        String dni = DataInput.getValidDni();
        String name = DataInput.getValidString("Introdueix el nom del client");
        int age = DataInput.getValidAge();
        return new Customer(identifier, dni, name, age);
    }
    public static void addNewCustomer() {
        Customer customer = createNewCustomer();
        try {
            Output.writeCustomersFile(customer);
        } catch (IOException e) {
            System.out.println("Error: No s'ha trobat l'arxiu de clients");
            Main.run();
        }
    }
    public static void deleteCustomer() {
        ArrayList<Customer> customers = Input.readCustomersFile();
        if (customers.isEmpty()) {
            System.out.println("Error: No es troben clients");
            Main.run();
        }
        Customer customer = chooseExistingCustomer();
        System.out.println(customer);
        if (customer == null) {
            System.out.println("S'ha produït un error");
            Main.run();
        } else {
            if (DataInput.confirmAction()) {
                customers.removeIf(c -> c.getIdentifier() == customer.getIdentifier());
                try {
                    Output.reWriteCustomersFile(customers);
                    System.out.println("S'ha eliminat el client");
                } catch (FileNotFoundException e) {
                    System.out.println("Error: No s'ha trobat l'arxiu dels clients");
                    Main.run();
                }
            } else {
                System.out.println("Error: No s'ha proporcionat el número correcte");
                Main.run();
            }
        }
    }

    @Override
    public String toString() {
        return "Client" +
                " ID " + identifier +
                " DNI " + dni +
                " Nom " + name +
                " Edat " + age;
    }
}


