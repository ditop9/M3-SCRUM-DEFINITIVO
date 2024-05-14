package classes.supermarket;

import data.DataInput;

public class Supermarket {
    private final int id;
    private final String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Supermarket(int identifier, String name) {
        this.id = identifier;
        this.name = name;
    }

    public static Supermarket createNewSupermarket() {
        int identifier = SupermarketDAO.getNewIdentifier();
        String name = DataInput.getValidString("Introdueix el nom del supermercat");
        return new Supermarket(identifier, name);
    }

    @Override
    public String toString() {
        return "Supermarket" +
               " ID " + id +
               " Nom " + name;
    }
}

