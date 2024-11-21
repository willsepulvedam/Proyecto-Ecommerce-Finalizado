import java.util.Scanner;

public class Administrador {
    static Scanner s = new Scanner(System.in);
    static Validador_Metodos validar = new Validador_Metodos();

    public static void menuPrincipalAdministrador() {

        int opcion;

        do {

            System.out.println("\n╔═════════════════════════════╗");
            System.out.println("║      Menu Administrador     ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║  1. Gestionar Productos     ║");
            System.out.println("║  2. Gestionar Categorías    ║");
            System.out.println("║  3. Salir                   ║");
            System.out.println("╚═════════════════════════════╝");
            System.out.print("\u001B[36m" + "Selecciona una opción: " + "\u001B[0m");

            opcion = validar.validar_entero();
            switch (opcion) {
                case 1:
                    Metodos_especiales.limpiarpantalla();
                    menuProductos();
                    Metodos_especiales.pause();
                    break;
                case 2:
                    Metodos_especiales.limpiarpantalla();
                    menuCategorias();
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

    public static void menuCategorias() {
        int opcion;
        do {

            System.out.println("\n╔═════════════════════════════╗");
            System.out.println("║        Menu Categoria       ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║  1. Agregar Categoría       ║");
            System.out.println("║  2. Mostrar Categorías      ║");
            System.out.println("║  3. Modificar Categorías    ║");
            System.out.println("║  4. Eliminar Categorías     ║");
            System.out.println("║  5. Salir                   ║");
            System.out.println("╚═════════════════════════════╝");
            System.out.print("\u001B[36m" + "Selecciona una opción: " + "\u001B[0m");
            opcion = validar.validar_entero();

            switch (opcion) {
                case 1:
                    Metodos_especiales.limpiarpantalla();
                    GestionarCategoria.agregarCategoria();
                    Metodos_especiales.pause();
                    break;
                case 2:
                    Metodos_especiales.limpiarpantalla();
                    GestionarCategoria.mostrarCategorias();
                    Metodos_especiales.pause();
                    break;
                case 3:
                    Metodos_especiales.limpiarpantalla();
                    GestionarCategoria.modificarCategoria();
                    Metodos_especiales.pause();
                    break;
                case 4:
                    Metodos_especiales.limpiarpantalla();
                    GestionarCategoria.eliminarCategoria();
                    Metodos_especiales.pause();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }

    public static void menuProductos() {
        int opcion;
        do {

            System.out.println("\n╔═════════════════════════════╗");
            System.out.println("║        Menu Producto        ║");
            System.out.println("╠═════════════════════════════╣");
            System.out.println("║  1. Agregar Producto        ║");
            System.out.println("║  2. Mostrar Productos       ║");
            System.out.println("║  3. Modificar Producto      ║");
            System.out.println("║  4. Eliminar Producto       ║");
            System.out.println("║  5. Salir                   ║");
            System.out.println("╚═════════════════════════════╝");
            System.out.print("\u001B[36m" + "Selecciona una opción: " + "\u001B[0m");
            opcion = validar.validar_entero();

            switch (opcion) {
                case 1:
                    Metodos_especiales.limpiarpantalla();
                    GestionarProductos.agregarProducto();
                    Metodos_especiales.pause();
                    break;
                case 2:
                    Metodos_especiales.limpiarpantalla();
                    GestionarProductos.mostrarProductos();
                    Metodos_especiales.pause();
                    break;
                case 3:
                    Metodos_especiales.limpiarpantalla();
                    GestionarProductos.modificarProducto();
                    Metodos_especiales.pause();
                    break;
                case 4:
                    Metodos_especiales.limpiarpantalla();
                    GestionarProductos.eliminarProducto();
                    Metodos_especiales.pause();
                    break;
                case 5:
                    System.out.println("Saliendo...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);
    }
}
