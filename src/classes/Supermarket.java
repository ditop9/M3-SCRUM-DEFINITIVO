package classes;

import data.DataInput;
import data.input_output.Input;
import main.Main;

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
    static Supermarket chooseExistingSupermarket() {
        ArrayList<Supermarket> supermarkets = Input.readSupermarketsFile();
        Input.showSupermarkets();
        System.out.println("0 => Sortir");
        int id = DataInput.getValidInteger("Introdueix l'ID del supermercat");
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
    public static Supermarket selectSupermarketById(int id) {
        ArrayList<Supermarket> supermarkets = Input.readSupermarketsFile();
        for (Supermarket s : supermarkets) {
            if (id == s.getIdentifier()) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "Supermarket" +
                " ID " + identifier +
                " Nom " + name;
    }
}

