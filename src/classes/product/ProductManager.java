package classes.product;

import app.Main;
import data.DataInput;

import java.util.List;

public class ProductManager {
    private static final ProductDAO db = new ProductDAO();

    public static void run() {
        int option;
        do {
            displayMenu();
            option = DataInput.getValidInteger("Escull una opció");
            handleOption(option);
        } while (option != 0);
    }

    public static void displayMenu() {
        System.out.println("""
                _________________________________
                |  ==== MENÚ DE PRODUCTES ====  |
                |_______________________________|
                | 3. MOSTRAR TOTS ELS PRODUCTES |
                | 2. CERCAR PRODUCTE PER ID     |
                | 3. AFEGIR PRODUCTE            |
                | 4. ELIMINAR PRODUCTE          |
                | 5. ACTUALITZAR PRODUCTE       |
                | 0. TORNAR AL MENÚ PRINCIPAL   |
                |_______________________________|""");
    }

    public static void handleOption(int option) {
        switch (option) {
            case 1:
                listAll();
                break;
            case 2:
                int searchId = DataInput.getValidInteger("Introdueix l'ID del producte.");
                Product product = searchById(searchId);
                System.out.println(product);
                break;
            case 3:
                addNewProduct();
                break;
            case 4:
                int deleteId = DataInput.getValidInteger("Introdueix l'ID del producte.");
                deleteProduct(deleteId);
                break;
            case 5:
                int updateId = DataInput.getValidInteger("Introdueix l'ID del producte.");
                updateProduct(updateId);
                break;
            case 0:
                System.out.println("Tornant al menú principal");
                Main.run();
                break;
            default:
                System.out.println("Error: No és una opció vàlida");
                break;
        }
    }

    public static void listAll() {
        List<Product> products = db.read();
        for (Product p : products) {
            System.out.println(p);
        }
    }

    public static Product searchById(int id) {
        try {
            return db.searchById(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    private static void addNewProduct() {
        Product product = Product.createNewProduct();
        db.create(product);
    }

    private static void deleteProduct(int id) {
        Product product = searchById(id);
        if (product != null) {
            db.delete(product.getId());
        } else System.out.println("Error: no s'ha trobat el producte.");
    }

    private static void updateProduct(int id) {
        Product product = searchById(id);
        if (product != null) {
            Product updatedProduct = Product.createNewProduct();
            db.update(updatedProduct, product.getId());
        } else System.out.println("Error: no s'ha trobat el producte.");
    }
}
