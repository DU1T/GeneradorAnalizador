package app.helpers;

//Librerias
import java.io.BufferedWriter; //Escritura de datos en buffer
import java.io.FileWriter; //Escritura en archivo
import java.io.IOException; //Control de errores IO
import java.util.concurrent.ThreadLocalRandom; //Random MultiHilo

public class GeneradorNumeros implements Runnable //Runnable para ejecucion en hilo separado
{
    //Atributos
    private final String filePath;
    private final int quantity;

    //Constructor
    public GeneradorNumeros(String filePath, int quantity) {
        this.filePath = filePath;
        this.quantity = quantity;
    }
    //Metodo
    @Override
    public void run() {
        System.out.println("\n Iniciando generación de " + quantity + " datos...");

        long tiempoInicio = System.currentTimeMillis();

        // Buffer de 64KB para mejor eficiencia
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath), 65536)) {
            for (int i = 0; i < quantity; i++) {
                // Rango: -100M a 100M
                int num = ThreadLocalRandom.current().nextInt(-100_000_000, 100_000_001);
                writer.write(Integer.toString(num));
                if (i < quantity - 1) {
                    writer.write(","); // Separado por coma
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        long tiempoFin = System.currentTimeMillis();
        System.out.println("Generación finalizada en: " + (tiempoFin - tiempoInicio) + "ms");
    }
}
