package classes;

import java.util.List;
import java.util.Scanner;

public class Cliente {
    private String dni;
    private String nombre;
    private int edad;
    private int numeroDeCliente;
    private List<Cliente> listaClientes;

    public Cliente(String dni, String nombre, int edad, int numeroDeCliente) {
        this.dni = dni;
        this.nombre = nombre;
        this.edad = edad;
        this.numeroDeCliente = numeroDeCliente;
        this.listaClientes = listaClientes;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getNumeroDeCliente() {
        return numeroDeCliente;
    }

    public void setNumerpDeCliente(int numeroDeCliente) {
        this.numeroDeCliente = numeroDeCliente;
    }

    public List<Cliente> getListaClientes() {
        return listaClientes;
    }

    public void setListaClientes(List<Cliente> listaClientes) {
        this.listaClientes = listaClientes;
    }
    public void anadirCliente(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce el DNI");
        String dni = sc.next();
        System.out.println("Introduce el nombre");
        String nombre = sc.next();
        System.out.println("Introduce la edad");
        int edad = sc.nextInt();
        int numeroCliente= listaClientes.getLast().getNumeroDeCliente() + 1;
        Cliente cliente = new Cliente( dni, nombre, edad, numeroCliente);
        listaClientes.add(cliente);

        System.out.println("classes.Cliente añadido correctamente.");
    }

    @Override

    public String toString() {
        return "Client{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", numeroDeCliente=" + numeroDeCliente +
                ", listaClientes=" + listaClientes +
                '}';
    }
}


