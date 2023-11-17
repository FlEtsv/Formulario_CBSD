import java.util.Random;

public class GeneradorRandomConSemillaNumerica{
/**
 *     public static void main(String[] args) {
        GeneradorRandomConSemillaNumerica generadorRandom = new GeneradorRandomConSemillaNumerica();

        // Obtener y mostrar la semilla
        System.out.println("Semilla generada: " + generadorRandom.getSemilla());

        // Obtener y mostrar un número pseudoaleatorio largo
        long numeroAleatorioLargo = generadorRandom.generarNumeroAleatorioLargo();
        System.out.println("Número Aleatorio Largo: " + numeroAleatorioLargo);
    }
 * @param args
 */

    private long semilla;

    // Constructor: Genera una semilla numérica aleatoria
    public GeneradorRandomConSemillaNumerica() {
        this.semilla = generarSemillaNumericaAleatoria();
    }

    // Método para generar una semilla numérica aleatoria
    private long generarSemillaNumericaAleatoria() {
        return System.nanoTime();
    }

    // Método para obtener la semilla
    public long getSemilla() {
        return semilla;
    }

    // Método para generar y obtener un número pseudoaleatorio largo
    public long generarNumeroAleatorioLargo() {
        Random generador = new Random(semilla);
        return generador.nextLong();
    }

}