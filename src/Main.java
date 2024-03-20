public class Main {
    public static void main(String[] args) {
        run();
    }
    static void run() {
        int option;
        do {
            ui();
            option = DataIntroduction.introduceInteger("Escull una opció");
            chooseOption(option);
        } while (option != 0);
    }
    static void ui() {
        System.out.println("""
                ===== BIENVENIDO AL ENTORNO DE USUARIO =====
                +___________________________________________
                | * 1. INICIAR SESSIÓ                       |
                | * 2. AFEGIR NOVA COMPRA                  |
                | * 3. FILTRAR COMPRES                     |
                | * 0. SORTIR                              |
                |__________________________________________|""");
    }
    static void chooseOption(int option) {
        switch (option) {
            case 1:
                break;
            case 2:
                break;
            case 3:
                break;
            case 0:
                System.out.println("El programa es tanca...");
                System.exit(0);
                break;
            default:
                System.out.println("Error: No és una opció vàlida.");
                break;
        }
    }

}
