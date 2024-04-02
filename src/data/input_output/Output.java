package data.input_output;

import classes.Customer;
import classes.User;

import java.io.*;
import java.util.ArrayList;

public class Output {
    public static void reWriteUsersFile(ArrayList<User> users) throws FileNotFoundException {
        File file = new File("database/UsersData.csv");
        PrintWriter pw = new PrintWriter(file);
        pw.println("ID,Nom,Contrasenya");
        for (int i = 0; i < users.size(); i++) {
            User u = users.get(i);
            pw.print(u.getIdentifier() + "," + u.getUsername() + "," + u.getPassword());
            if (i < users.size() - 1) {
                pw.println();
            }
        }
        pw.close();
    }

    public static void writeUsersFile(User user) throws IOException {
        File file = new File("database/UsersData.csv");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println();
        pw.print(user.getIdentifier() + "," + user.getUsername() + "," + user.getPassword());
        pw.close();
        fw.close();
    }

    public static void reWriteCustomersFile(ArrayList<Customer> customers) throws FileNotFoundException {
        File file = new File("database/CustomersData.csv");
        PrintWriter pw = new PrintWriter(file);
        pw.println("ID,DNI,Nom,Edat");
        for (int i = 0; i < customers.size(); i++) {
            Customer c = customers.get(i);
            pw.print(c.getIdentifier() + "," + c.getDni() + "," + c.getName() + "," + c.getAge());
            if (i < customers.size() - 1) {
                pw.println();
            }
        }
        pw.close();
    }

    public static void writeCustomersFile(Customer customer) throws IOException {
        File file = new File("database/UsersData.csv");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println();
        pw.print(customer.getIdentifier() + "," + customer.getDni() + "," + customer.getName() + "," + customer.getAge());
        pw.close();
        fw.close();
    }
}
