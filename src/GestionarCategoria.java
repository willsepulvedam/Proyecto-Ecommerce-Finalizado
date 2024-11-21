public class GestionarCategoria extends Json{
    static Validador_Metodos validar = new Validador_Metodos();
    public static void agregarCategoria() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║       Agregar Categoria     ║");
        System.out.println("╚═════════════════════════════╝");
        cargarDatosDesdeJson();

        System.out.print("Ingrese el ID de la categoría: ");
        int id = validar.validar_entero();
        if (id <= 0) {
            System.out.println("ID de categoría no válido. Debe ser un número positivo.");
            return;
        }

        System.out.print("Ingrese el nombre de la nueva categoría: ");
        String nombre = validar.validar_string();
        if (nombre.isEmpty()) {
            System.out.println("El nombre de la categoría no puede estar vacío.");
            return;
        }

        if (getCategorias().stream().anyMatch(c -> c.getId() == id)) {
            System.out.println("Error: Ya existe una categoría con ese ID.");
            return;
        }

        Categoria categoria = new Categoria(id, nombre);
        getCategorias().add(categoria);
        guardarDatosEnJson();

        System.out.println("Categoría '" + nombre + "' agregada con éxito.");
    }


    public static void mostrarCategorias() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║       Mostrar Categoria     ║");
        System.out.println("╚═════════════════════════════╝");
        cargarDatosDesdeJson();
        if (getCategorias().isEmpty()) {
            System.out.println("No hay categorías disponibles.");
        } else {
            System.out.println("Categorías disponibles:");
            for (Categoria categoria : getCategorias()) {
                System.out.println("ID: " + categoria.getId() + " - Nombre: " + categoria.getNombre());
            }
        }
    }

    public static void mostrarCategoriasSinEnc() {

        cargarDatosDesdeJson();
        if (getCategorias().isEmpty()) {
            System.out.println("No hay categorías disponibles.");
        } else {
            System.out.println("Categorías disponibles:");
            for (Categoria categoria : getCategorias()) {
                System.out.println("ID: " + categoria.getId() + " - Nombre: " + categoria.getNombre());
            }
        }
    }

    public static void modificarCategoria() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║      Modificar Categoria    ║");
        System.out.println("╚═════════════════════════════╝");
        cargarDatosDesdeJson();
        System.out.print("Ingrese el ID de la categoría a modificar: ");
        int id = validar.validar_entero();
        if (id <= 0) {
            System.out.println("ID de categoría no válido.");
            return;
        }

        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            System.out.print("Ingrese el nuevo nombre de la categoría: ");
            String nuevoNombre = validar.validar_string();
            if (nuevoNombre.isEmpty()) {
                System.out.println("El nombre no puede estar vacío.");
                return;
            }

            categoria.setNombre(nuevoNombre);
            guardarDatosEnJson();
            System.out.println("Categoría modificada correctamente.");
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }

    public static void eliminarCategoria() {

        System.out.println("\n╔═════════════════════════════╗");
        System.out.println("║      Eliminar Categoria     ║");
        System.out.println("╚═════════════════════════════╝");
        cargarDatosDesdeJson();
        System.out.print("Ingrese el ID de la categoría a eliminar: ");
        int id = validar.validar_entero();
        if (id <= 0) {
            System.out.println("ID de categoría no válido.");
            return;
        }

        Categoria categoria = buscarCategoriaPorId(id);
        if (categoria != null) {
            getCategorias().remove(categoria);
            guardarDatosEnJson();
            System.out.println("Categoría eliminada correctamente.");
        } else {
            System.out.println("Categoría no encontrada.");
        }
    }
}
