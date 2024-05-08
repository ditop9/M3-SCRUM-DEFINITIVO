package data.input_output;

import classes.Customer;
import classes.Admin;
import classes.Supermarket;

import java.io.*;
import java.util.ArrayList;

public class Output {
    public static void reWriteAdminFile(ArrayList<Admin> users) throws FileNotFoundException {
        File file = new File("database/AdminData.csv");
        PrintWriter pw = new PrintWriter(file);
        pw.println("ID,Nom,Contrasenya");
        for (int i = 0; i < users.size(); i++) {
            Admin u = users.get(i);
            pw.print(u.getId() + "," + u.getName() + "," + u.getPassword());
            if (i < users.size() - 1) {
                pw.println();
            }
        }
        pw.close();
    }

    public static void writeAdminFIle(Admin user) throws IOException {
        File file = new File("database/AdminData.csv");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println();
        pw.print(user.getId() + "," + user.getName() + "," + user.getPassword());
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
        File file = new File("database/CustomersData.csv");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println();
        pw.print(customer.getIdentifier() + "," + customer.getDni() + "," + customer.getName() + "," + customer.getAge());
        pw.close();
        fw.close();
    }

    public static void writeSupermarketsFile(Supermarket supermarket) throws IOException {
        File file = new File("database/SupermarketsData.csv");
        FileWriter fw = new FileWriter(file, true);
        PrintWriter pw = new PrintWriter(fw);
        pw.println();
        pw.print(supermarket.getIdentifier() + "," + supermarket.getName());
        pw.close();
        fw.close();
    }
}
