

public class Principal {

    static Validador_Metodos validar = new Validador_Metodos();
    public static void main(String[] args) {
        int opcion;

        do {

            System.out.println("\n╔═════════════════════════════════════╗");
            System.out.println("║            Menu Principal           ║");
            System.out.println("╠═════════════════════════════════════╣");
            System.out.println("║  1. Ingresar como Administrador     ║");
            System.out.println("║  2. Ingresar como Comprador         ║");
            System.out.println("║  3. Salir                           ║");
            System.out.println("╚═════════════════════════════════════╝");
            System.out.print("\u001B[36m" + "Selecciona una opción: " + "\u001B[0m");

            opcion = validar.validar_entero();

            switch (opcion) {
                case 1:
                    Metodos_especiales.limpiarpantalla();
                    Administrador.menuPrincipalAdministrador();
                    Metodos_especiales.pause();
                    break;
                case 2:
                    Metodos_especiales.limpiarpantalla();
                    Comprador.menuPrincipalComprador();
                    Metodos_especiales.pause();
                    break;
                case 3:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 3);
    }
}

