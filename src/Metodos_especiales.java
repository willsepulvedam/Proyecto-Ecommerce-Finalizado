import java.util.*;
public class Metodos_especiales {
    static Scanner scanner = new Scanner(System.in);
    // Método para limpiar la pantalla (cmd) en sistemas Windows
    public static void limpiarpantalla() {
        try {
            ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "cls"); // Limpia la pantalla
            Process startProcess = pb.inheritIO().start();
            startProcess.waitFor();
        } catch (Exception e) {
            System.out.println("ERROR DE TIPO: " + e.getMessage());
        }
    }
    public static void pause(){
        System.out.println("\t\nPresione enter para continuar...");
        scanner.nextLine();
    }
}
