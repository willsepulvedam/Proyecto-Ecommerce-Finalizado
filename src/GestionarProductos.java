
import java.util.*;

public class GestionarProductos extends Json{
    static Validador_Metodos validar = new Validador_Metodos();
    static Scanner scanner = new Scanner(System.in);
    public static void agregarProducto() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║       Agregar Producto      ║");
        System.out.println("╚═════════════════════════════╝");
        cargarDatosDesdeJson();
        System.out.print("Ingrese el ID del producto: ");
        int id = validar.validar_entero();
        if (getProductos().stream().anyMatch(p -> p.getId() == id)) {
            System.out.println("Error: Ya existe un producto con ese ID.");
            return;
        }
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = validar.validar_string();
        if (nombre == null || nombre.trim().isEmpty()) {
            System.out.println("El nombre del producto no puede estar vacío.");
            return;
        }
        System.out.print("Ingrese el precio del producto: ");
        double precio = validar.validar_double();
        if (precio <= 0) {
            System.out.println("El precio del producto debe ser mayor a 0.");
            return;
        }
        cargarDatosDesdeJson();
        GestionarCategoria.mostrarCategoriasSinEnc();
        System.out.print("Ingrese el ID de la categoría para registrar el producto: ");
        int categoriaId = validar.validar_entero();

        Categoria categoria = buscarCategoriaPorId(categoriaId);
        if (categoria == null) {
            System.out.println("Categoría no encontrada.");
            return;
        }

        Producto producto = new Producto(id, nombre, precio);
        categoria.addProducto(producto);
        getProductos().add(producto);
        guardarDatosEnJson();
        System.out.println("Producto agregado correctamente.");
    }

    public static void mostrarProductos() {
        Comprador.visualizarProductosPorCategorias();
    }
    public static void modificarProducto() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║      Modificar Producto     ║");
        System.out.println("╚═════════════════════════════╝");
        cargarDatosDesdeJson();
        System.out.print("Ingrese el ID del producto a modificar: ");
        int productoId = validar.validar_entero();

        if (productoId <= 0) {
            System.out.println("ID de producto no válido.");
            return;
        }

        Producto productoEncontrado = null;
        for (Producto producto : getProductos()) {
            if (producto.getId() == productoId) {
                productoEncontrado = producto;
                break;
            }
        }

        if (productoEncontrado == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nuevoNombre = validar.validar_string();
        if (nuevoNombre == null || nuevoNombre.trim().isEmpty()) {
            System.out.println("El nombre no puede estar vacío.");
            return;
        }

        System.out.print("Ingrese el nuevo precio del producto: ");
        double nuevoPrecio = validar.validar_double();
        if (nuevoPrecio <= 0) {
            System.out.println("El precio debe ser mayor a 0.");
            return;
        }

        productoEncontrado.setNombre(nuevoNombre);
        productoEncontrado.setPrecio(nuevoPrecio);

        guardarDatosEnJson();
        System.out.println("Producto modificado correctamente.");
    }

    public static void eliminarProducto() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║       Eliminar Producto     ║");
        System.out.println("╚═════════════════════════════╝");
        cargarDatosDesdeJson();
        System.out.print("Ingrese el ID del producto a eliminar: ");
        int productoId = validar.validar_entero();
        if (productoId <= 0) {
            System.out.println("ID de producto no válido.");
            return;
        }

        Producto productoAEliminar = null;
        for (Producto producto : getProductos()) {
            if (producto.getId() == productoId) {
                productoAEliminar = producto;
                break;
            }
        }

        if (productoAEliminar == null) {
            System.out.println("Producto no encontrado.");
            return;
        }

        System.out.print("¿Está seguro de que desea eliminar este producto? (sí/no): ");
        String confirmacion = scanner.nextLine();
        if (!confirmacion.equalsIgnoreCase("sí")) {
            System.out.println("Operación cancelada.");
            return;
        }

        getProductos().removeIf(producto -> producto.getId() == productoId);
        for (Categoria categoria : getCategorias()) {
            categoria.getProductos().removeIf(producto -> producto.getId() == productoId);
        }
        guardarDatosEnJson();
        System.out.println("Producto eliminado correctamente.");
    }
}
