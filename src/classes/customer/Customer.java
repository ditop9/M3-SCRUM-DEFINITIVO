package classes.customer;

import data.DataInput;

import java.sql.ResultSet;

public class Customer {
    private int id;
    private String dni;
    private String name;
    private String lastName;
    private int age;
    private int phone;
    private String email;

    public int getId() {
        return id;
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

    public Customer() {
    }

    public Customer(int identifier, String dni, String name, String lastName, int edad, int phone, String email) {
        this.id = identifier;
        this.dni = dni;
        this.name = name;
        this.lastName = lastName;
        this.age = edad;
        this.phone = phone;
        this.email = email;
    }



    public static Customer createNewCustomer() {
        int identifier = CustomerDAO.getNewIdentifier();
        String dni = DataInput.getValidDni();
        String name = DataInput.getValidString("Introdueix el nom del client");
        String lastName = DataInput.getValidString("Introdueix el cognom del client");
        int age = DataInput.getValidAge();
        int phone = DataInput.getValidInteger("Introdueix el telèfon del Client.");
        String email = DataInput.getValidString("Introdueix el correu electrònic del Client.");
        return new Customer(identifier, dni, name, lastName, age, phone, email);
    }

    @Override
    public String toString() {
        return "Client" +
                "\nID " + id +
                "\nDNI " + dni +
                "\nNom " + name +
                "\nCognom " + lastName +
                "\nEdat " + age +
                "\nTelèfon " + phone +
                "\nEmail " + email +
                "\n-------------------------------";
    }
}



