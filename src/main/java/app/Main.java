package app;

//Librerias
import app.helpers.*;
import app.algoritmos.*;
import java.util.Scanner;


public class Main
{
    //Atributos
    // Rutas relativas a la raíz del proyecto
    private static final String DATA_DIR = "data/";
    private static final String AUDIO_PATH = "recursos/musica/AudioFondoMC.wav";
    private static final int NUMEROS_MAX = 10000000;

    private static ReproductorAudio audioPlayer;
    private static boolean ocupado = false;

    //Metodo Prinicipal
    public static void main(String[] args)
    {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        //Preparar directorios y audio
        ManejadorArchivos.checkyCreacionDirectorio(DATA_DIR);
        audioPlayer = new ReproductorAudio(AUDIO_PATH);

        System.out.println("-----------------------------------------");
        System.out.println("   BIENVENIDO AL ANALIZADOR UMG ");
        System.out.println("-----------------------------------------");

        //Bucle menu principal
        while (!exit) {
            System.out.println("\n-----------------------------------------");
            System.out.println("           Menu Principal            ");
            System.out.println("-----------------------------------------");
            System.out.println("1. Generar Numeros Aleatorios");
            System.out.println("2. Ordenar Archivo (Individual)");
            System.out.println("3. MODO AUTOMATICO (Todos los algoritmos)");
            System.out.println("4. Prueba de Estado (No-Bloqueo)");
            System.out.println("5. Salir");
            System.out.print("Seleccione una opcion: ");

            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1": menuGenerar(scanner); break;
                case "2": menuIndividual(scanner); break;
                case "3": menuAutomatico(scanner); break;
                case "4": System.out.println("[MAIN] Hilo principal activo y respondiendo."); break;
                case "5":
                    exit = true;
                    audioPlayer.cerrar();
                    System.out.println("Cerrando analizador UMG...");
                    break;
                default: System.out.println("Opcion no valida.");
            }
        }
        scanner.close();
    }
    //Sub Menus
    private static void menuGenerar(Scanner sc)
    {
        System.out.println("-----------------------------------------");
        System.out.println("Nombre del archivo: ");
        String nombre = sc.nextLine().trim();
        if (nombre.isEmpty()) nombre = "numerosDesordenados";
        String ruta = DATA_DIR + nombre + ".txt";

        System.out.println("Cantidad (Max " + NUMEROS_MAX + "): ");
        try {
            int cantidad = Integer.parseInt(sc.nextLine());
            GeneradorNumeros gen = new GeneradorNumeros(ruta, cantidad);
            new Thread(gen).start();
            System.out.println("Generando en segundo plano...");
        } catch (NumberFormatException e) {
            System.out.println("Error: Ingrese un numero valido.");
        }
    }
    private static void menuIndividual(Scanner sc)
    {
        if (ocupado) {
            System.out.println("[SISTEMA] Hay un proceso en curso. Por favor, espere.");
            return;
        }

        System.out.println("-----------------------------------------");
        System.out.print("Nombre del archivo (sin .txt, se busca en /data): ");
        String nombre = sc.nextLine().trim();

        String ruta = DATA_DIR + nombre + (nombre.endsWith(".txt") ? "" : ".txt");

        // Verificación manual antes de intentar leer
        java.io.File archivo = new java.io.File(ruta);
        if (!archivo.exists()) {
            System.out.println("ERROR: El archivo '" + ruta + "' aun no existe.");
            System.out.println("Si acabas de generarlo, espera a que aparezca el mensaje de 'Finalizado'.");
            return;
        }

        int[] datos = ManejadorArchivos.leerArchivo(ruta);

        if (datos != null) {
            System.out.println("-----------------------------------------");
            System.out.println("\n--- Seleccione Algoritmo ---");
            System.out.println("1. Counting Sort  2. Quick Sort  3. Merge Sort  4. Heap Sort");
            System.out.println("5. Shell Sort     6. Insertion Sort  7. Bubble Sort  8. Selection Sort");
            System.out.print("Opcion: ");

            AlgoritmoOrdenamiento alg = seleccionarAlgoritmo(sc.nextLine());
            if (alg != null) {
                // Lanzamos el proceso en un hilo nuevo
                Thread hiloIndividual = new Thread(() -> {
                    ocupado = true;
                    audioPlayer.iniciar();
                    System.out.println("\nEjecutando " + alg.getNombre() + "...");
                    ManjeadorAlgoritmos.ejecutarAlgoritmo(alg, datos, ruta);
                    audioPlayer.detener();
                    ocupado = false;
                    System.out.println("\nOrdenamiento finalizado. (Menú liberado)");
                });
                hiloIndividual.start();
                System.out.println("Algoritmo ejecutándose en segundo plano.");
            }
        }
    }
    private static void menuAutomatico(Scanner sc)
    {
        if (ocupado) {
            System.out.println("[SISTEMA] El Test Automatico ya esta corriendo o hay otro proceso activo.");
            return;
        }
        System.out.println("-----------------------------------------");
        System.out.print("Nombre del archivo para Test (se busca en /data): ");
        String nombre = sc.nextLine().trim();
        String ruta = DATA_DIR + nombre + (nombre.endsWith(".txt") ? "" : ".txt");
        int[] datosOriginales = ManejadorArchivos.leerArchivo(ruta);

        if (datosOriginales != null)
        {
            Thread hiloTest = new Thread(() -> {
                ocupado = true;
                System.out.println("-----------------------------------------");
                System.out.println("ALERTA: Iniciando prueba completa de 8 algoritmos.");
                System.out.println("El tiempo total para 10M de datos puede ser de varias HORAS o DIAS.");

                // Lista ordenada de más eficientes a menos eficientes
                AlgoritmoOrdenamiento[] listaInvestigacion = {
                        new Conteo(),
                        new Rapido(),
                        new Mezcla(),
                        new Monticulos(),
                        new Shell(),
                        new Insercion(),
                        new Burbuja(),
                        new Seleccion()
                };

                audioPlayer.iniciar();
                for (AlgoritmoOrdenamiento alg : listaInvestigacion) {
                    // El manager clonara el array original en cada iteracion
                    ManjeadorAlgoritmos.ejecutarAlgoritmo(alg, datosOriginales, ruta);
                }
                audioPlayer.detener();
                System.out.println("[TEST] Pruebas finalizadas.");
                ocupado = false;
            });
            hiloTest.start();
            System.out.println("Pruebas iniciada en segundo plano.");
        }
    }
    private static AlgoritmoOrdenamiento seleccionarAlgoritmo(String opc) {
        switch (opc) {
            case "1": return new Conteo();
            case "2": return new Rapido();
            case "3": return new Mezcla();
            case "4": return new Monticulos();
            case "5": return new Shell();
            case "6": return new Insercion();
            case "7": return new Burbuja();
            case "8": return new Seleccion();
            default: return null;
        }
    }
}

