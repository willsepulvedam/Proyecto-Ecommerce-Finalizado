import com.google.gson.GsonBuilder;
import java.io.FileReader;
import java.util.*;

public class Comprador {
    static Scanner s = new Scanner(System.in);
    static Validador_Metodos validar = new Validador_Metodos();
    private static List<Producto> carrito = new ArrayList<>();

    public static void menuPrincipalComprador() {

        int opcion;

        do {
            System.out.println("\n╔═══════════════════════════════════════════╗");
            System.out.println("║               Menu Comprador              ║");
            System.out.println("╠═══════════════════════════════════════════╣");
            System.out.println("║  1. Visualizar las Categorias             ║");
            System.out.println("║  2. Visualizar Productos por categoria    ║");
            System.out.println("║  3. Agregar al Carrito                    ║");
            System.out.println("║  4. Carrito                               ║");
            System.out.println("║  5. Salir                                 ║");
            System.out.println("╚═══════════════════════════════════════════╝");
            System.out.print("\u001B[36m" + "Selecciona una opción: " + "\u001B[0m");

            opcion = validar.validar_entero();

            switch (opcion) {
                case 1:
                    Metodos_especiales.limpiarpantalla();
                    GestionarCategoria.mostrarCategorias();
                    Metodos_especiales.pause();
                    break;
                case 2:
                    Metodos_especiales.limpiarpantalla();
                    visualizarProductosPorCategorias();
                    Metodos_especiales.pause();
                    break;
                case 3:
                    Metodos_especiales.limpiarpantalla();
                    agregarProductoCarrito();
                    Metodos_especiales.pause();
                    break;
                case 4:
                    Metodos_especiales.limpiarpantalla();
                    mostrarCarrito();
                    Metodos_especiales.pause();
                    break;
                case 5:
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 5);

    }

    private static void mostrarCarrito() {
        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║           Carrito           ║");
        System.out.println("╚═════════════════════════════╝");
        if (carrito.isEmpty()) {
            System.out.println("El carrito está vacío.");
        } else {
            System.out.println("Estos son los productos en tu carrito:");
            carrito.forEach(producto -> {
                System.out.println("Producto: " + producto.getNombre() + " - Precio: $" + producto.getPrecio());
            });

            double montoTotal = carrito.stream()
                    .mapToDouble(Producto::getPrecio)
                    .sum();
            System.out.println("\nEl monto total de tu carrito es: $" + montoTotal);
        }
    }


    private static void agregarProductoCarrito() {
        boolean continuarAgregando = true;
        while (continuarAgregando) {

            System.out.println("\n╔═════════════════════════════╗");
            System.out.println("║      Agregar Al Carrito     ║");
            System.out.println("╚═════════════════════════════╝");
            GestionarCategoria.mostrarCategoriasSinEnc();
            System.out.println("Estas son las categorías disponibles.");
            System.out.print("Selecciona el ID de una categoría para mostrar sus productos: ");
            int idCategoria = validar.validar_entero();

            CategoriasContainer datos = cargarDatosDesdeJson();
            if (datos != null) {
                Optional<Categoria> categoriaOpt = datos.getCategorias().stream()
                        .filter(categoria -> categoria.getId() == idCategoria)
                        .findFirst();

                if (categoriaOpt.isPresent()) {
                    Categoria categoriaSeleccionada = categoriaOpt.get();
                    System.out.println("\nCategoría seleccionada: " + categoriaSeleccionada.getNombre());

                    if (!categoriaSeleccionada.getProductos().isEmpty()) {
                        System.out.println("Productos disponibles en esta categoría:");
                        categoriaSeleccionada.getProductos().forEach(producto ->
                                System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + ", Precio: $" + producto.getPrecio())
                        );
                    }
                } else {
                    System.out.println("\u001B[31mCategoría no encontrada con el ID proporcionado.\u001B[0m");
                }
            } else {
                System.out.println("\u001B[31mError al cargar las categorías. No se puede mostrar los productos.\u001B[0m");
            }
            if (datos == null) {
                System.out.println("Error al cargar los datos. Intenta nuevamente.");
                return;
            }

            GestionarProductos.mostrarProductos();

            System.out.println("\nSeleccione el ID del producto que desea agregar:");
            int productoId = validar.validar_entero();

            Producto productoSeleccionado = null;
            Categoria categoriaEncontrada = null;

            for (Categoria categoria : datos.getCategorias()) {
                productoSeleccionado = categoria.getProductos().stream()
                        .filter(producto -> producto.getId() == productoId)
                        .findFirst()
                        .orElse(null);
                if (productoSeleccionado != null) {
                    categoriaEncontrada = categoria;
                    break;
                }
            }

            if (productoSeleccionado == null) {
                productoSeleccionado = datos.getProductos().stream()
                        .filter(producto -> producto.getId() == productoId)
                        .findFirst()
                        .orElse(null);
            }

            if (productoSeleccionado != null) {
                carrito.add(productoSeleccionado);
                System.out.println("Producto agregado: " + productoSeleccionado.getNombre() + " - Precio: $" + productoSeleccionado.getPrecio());

                if (categoriaEncontrada != null) {
                    categoriaEncontrada.getProductos().remove(productoSeleccionado);
                    System.out.println("Producto eliminado de la categoría.");
                }

                if (datos.getProductos().remove(productoSeleccionado)) {
                    System.out.println("Producto eliminado de la lista global.");
                }

                Json.guardarDatosEnJson(datos);
            } else {
                System.out.println("Producto no encontrado.");
                continue;
            }

            System.out.print("¿Desea seguir agregando productos? (Sí/No): ");
            String respuesta = s.nextLine().toLowerCase();
            if (respuesta.equals("no")) {
                continuarAgregando = false;
            }
        }

        System.out.println("\nHas terminado de agregar productos al carrito.");
    }






    static void visualizarProductosPorCategorias() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║        Ver Productos        ║");
        System.out.println("╚═════════════════════════════╝");
        GestionarCategoria.mostrarCategoriasSinEnc();
        System.out.println("Estas son las categorías disponibles.");
        System.out.print("Selecciona el ID de una categoría para mostrar sus productos: ");
        int idCategoria = validar.validar_entero();

        CategoriasContainer datos = cargarDatosDesdeJson(); // Cargamos los datos
        if (datos != null) {
            Optional<Categoria> categoriaOpt = datos.getCategorias().stream()
                    .filter(categoria -> categoria.getId() == idCategoria)
                    .findFirst();

            if (categoriaOpt.isPresent()) {
                Categoria categoriaSeleccionada = categoriaOpt.get();
                System.out.println("\nCategoría seleccionada: " + categoriaSeleccionada.getNombre());

                if (!categoriaSeleccionada.getProductos().isEmpty()) {
                    System.out.println("Productos disponibles en esta categoría:");
                    categoriaSeleccionada.getProductos().forEach(producto ->
                            System.out.println("ID: " + producto.getId() + ", Nombre: " + producto.getNombre() + ", Precio: $" + producto.getPrecio())
                    );
                } else {
                    System.out.println("Esta categoría no tiene productos disponibles.");
                }
            } else {
                System.out.println("\u001B[31mCategoría no encontrada con el ID proporcionado.\u001B[0m");
            }
        } else {
            System.out.println("\u001B[31mError al cargar las categorías. No se puede mostrar los productos.\u001B[0m");
        }
    }

    public static CategoriasContainer cargarDatosDesdeJson() {
        try {
            return new GsonBuilder().create().fromJson(
                    new FileReader("ecommerce.json"), CategoriasContainer.class
            );
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}