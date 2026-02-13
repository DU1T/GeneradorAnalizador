package app;

//Librerias
import app.helpers.GeneradorNumeros;
import app.helpers.ReproductorAudio;
import app.helpers.ManejadorArchivos;

import java.util.Scanner;

public class Main
{
    //Atributos
    // Rutas relativas a la raíz del proyecto
    private static final String DATA_DIR = "data/";
    private static final String FILE_PATH = DATA_DIR + "numeros_desordenados.txt";
    private static final String AUDIO_PATH = "recursos/musica/AudioFondoMC.wav";
    private static final int NUMEROS_MAX = 10000000;

    //Metodo Prinicipal
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        //Preparar directorios y audio
        ManejadorArchivos.checkyCreacionDirectorio(DATA_DIR);
        ReproductorAudio audioPlayer = new ReproductorAudio(AUDIO_PATH);

        System.out.println("-----------------------------------------");
        System.out.println("   BIENVENIDO AL ANALIZADOR UMG ");
        System.out.println("-----------------------------------------");

        //Bucle menu principal
        while (!exit)
        {
            System.out.println("--- Menu Principal ---");
            System.out.println("1. Generar Numeros Aleatorios");
            System.out.println("2. Hacer otra cosa (Prueba de que no se colgo)");
            System.out.println("3. Salir");
            System.out.print("Escoga una opcion: ");
            String entrada = scanner.nextLine();
            switch (entrada)
            {
                case "1":
                    System.out.print("Ingrese la cantidad de numeros a generar (Ej: 10000000): ");
                    try
                    {
                        int cantidad = Integer.parseInt(scanner.nextLine());

                        //Validaciones
                        if(cantidad <= 0)
                        {
                            System.out.println("[MAIN] Error: la cantidad debe ser mayor a 0!.");
                        }
                        else if (cantidad > NUMEROS_MAX)
                        {
                            System.out.println("[MAIN] Error: No puedes generar más de" + NUMEROS_MAX + " números.");
                        }
                        else
                        {
                            //Instanciamos el generador
                            GeneradorNumeros generador = new GeneradorNumeros(FILE_PATH, cantidad);
                            //Envolvemos el generador en un hilo y lo iniciamos
                            Thread threadGenerador = new Thread(generador);
                            threadGenerador.start();
                            System.out.println("[MAIN] Hilo para generar numeros lanzado en segundo plano.");
                        }
                    }
                    catch (NumberFormatException e)
                    {
                        System.out.println("Por favor, ingresa un número válido.");
                    }
                    break;
                case "2":
                    System.out.println("[MAIN] ¡El programa sigue respondiendo perfectamente!");
                    break;
                case "3":
                    exit = true;
                    audioPlayer.cerrar(); //Liberamos el espacio del audio
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        }
        scanner.close();
    }
}
