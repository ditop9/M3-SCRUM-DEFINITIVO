package classes;

import data.DataInput;
import data.input_output.Input;
import data.input_output.Output;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;

public class Supermarket {
    private int identifier;
    private String name;

    public int getIdentifier() {
        return identifier;
    }

    public String getName() {
        return name;
    }

    public Supermarket(int identifier, String name) {
        this.identifier = identifier;
        this.name = name;
    }
    static int getNewIdentifier() {
        ArrayList<Supermarket> supermarkets = Input.readSupermarketsFile();
        return supermarkets.getLast().getIdentifier() + 1;
    }
    static Supermarket chooseExistingSupermarket() {
        ArrayList<Supermarket> supermarkets = Input.readSupermarketsFile();
        Input.showCustomers();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID de l'usuari");
        DataInput.handleExit(String.valueOf(id));
        for (Supermarket s : supermarkets) {
            if (s.getIdentifier() == id) {
                return s;
            }
        }
        System.out.println("Error: No s'ha trobat el supermercat");
        Main.run();
        return null;
    }
}

