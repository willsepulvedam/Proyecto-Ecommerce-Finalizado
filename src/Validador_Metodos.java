import java.util.*;
public class Validador_Metodos {
    static Scanner scanner = new Scanner(System.in);
    static Validador_Metodos validar = new Validador_Metodos();

    public String validar_string() {
        String valor;
        System.out.print("Por favor, ingrese una cadena de texto (solo letras): ");
        valor = scanner.nextLine().trim();

        while (valor.isEmpty() || !valor.matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ\\s]+")) {
            System.out.print("Error: Solo se permiten letras y espacios. Intente de nuevo: ");
            valor = scanner.nextLine().trim();
        }
        return valor;
    }
    public int validar_entero() {
        int valor = 0;
        boolean validador = false;

        while (!validador) {
            System.out.print("Por favor, ingrese un número entero válido: ");
            String capturador = scanner.nextLine();
            try {
                valor = Integer.parseInt(capturador);

                if (valor <= 0) {
                    System.out.println("El valor no debe ser menor o igual a cero. Por favor, inténtelo nuevamente.");
                } else {
                    validador = true;
                }
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }

        return valor;
    }
    public double validar_double(){
        double  valor = 0;
        boolean validador = false;
        while (!validador) {
            System.out.print("Por favor, ingrese un número entero o decimal válido: ");
            String capturador = scanner.nextLine();
            capturador = capturador.replace(",", ".");
            try {
                if(capturador.contains(".")){
                    valor = Double.parseDouble(capturador);
                }else{
                    valor = Integer.parseInt(capturador);
                }
                if(valor <= 0){
                    System.out.println("el valor no tiene que ser menor o igual a cero please ingrese la informacion de nuevo.");
                }else{
                    validador = true;
                }
            } catch (Exception e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero o decimal válido.");
            }
        }
        return valor;
    }
}
