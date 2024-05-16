package classes.customer;

import data.DataInput;

public class Customer {
    private final int id;
    private final String dni;
    private final String name;
    private final String lastName;
    private final int age;
    private final int phone;
    private final String email;

    public int getId() {
        return id;
    }

    public String getDni() {
        return dni;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public int getAge() {
        return age;
    }

    public int getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
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



