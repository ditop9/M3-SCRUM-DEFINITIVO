package classes;

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

    @Override
    public String toString() {
        return "Supermarket" +
               " ID " + identifier +
               " Nom " + name;
    }
}

