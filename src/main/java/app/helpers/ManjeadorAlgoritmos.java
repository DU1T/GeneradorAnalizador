package app.helpers;

//Manejador de algoritmos
import app.algoritmos.AlgoritmoOrdenamiento;
import java.util.Arrays;

public class ManjeadorAlgoritmos
{
    //Metodos
    //Ejecutar Algoritmos
    public static void ejecutarAlgoritmo(AlgoritmoOrdenamiento algoritmo, int[] datosOriginales, String rutaOriginal)
    {
        //Creamos un clon del archivo original
        int[] datosParaOrdenar = datosOriginales.clone();

        //Mensaje de inicio
        System.out.println("\nIniciando: " + algoritmo.getNombre());

        //Medicion tiempo
        long startTime = System.currentTimeMillis();

        algoritmo.Ordenar(datosParaOrdenar);

        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        System.out.println("Finalizado: " + algoritmo.getNombre());
        System.out.println("Tiempo de ejecución: " + duration + " ms (" + (duration / 1000.0) + " segundos)");

        //Nuevo archivo
        String rutaOrdenada = RutaOrdenada(rutaOriginal, algoritmo.getNombre());
        ManejadorArchivos.guardarArchivo(datosParaOrdenar, rutaOrdenada);
    }
    //Crear nuevo archivo
    private static String RutaOrdenada(String rutaOriginal, String nombreAlgoritmo) {
        // Limpiamos el nombre del algoritmo de espacios para el nombre del archivo
        String tag = nombreAlgoritmo.replaceAll("\\s+", "");

        if (rutaOriginal.contains(".")) {
            // Insertamos "_Ordenado" antes de la extensión .txt
            int lastDot = rutaOriginal.lastIndexOf(".");
            return rutaOriginal.substring(0, lastDot) + "_" + tag + "_Ordenado" + rutaOriginal.substring(lastDot);
        } else {
            // Si por alguna razón no tiene extensión
            return rutaOriginal + "_" + tag + "_Ordenado";
        }
    }
}
