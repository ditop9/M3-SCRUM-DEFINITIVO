package classes;

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
    @Override
    public String toString() {
        return "Client" +
                " ID " + identifier +
                " DNI " + dni +
                " Nom " + name +
                " Edat " + age;
    }
}


