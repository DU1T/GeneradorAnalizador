package app.helpers;

//Librerias
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;

public class ManejadorArchivos
{
    //Atributos
    private static final int BUFFER_SIZE = 1024 * 1024; //Buffer de 10MB

    //Metodos
    //Verificar directorios
    public static void checkyCreacionDirectorio(String pathDirectorio)
    {
        File directorio = new File(pathDirectorio);
        if (!directorio.exists())
        {
            boolean existe = directorio.mkdirs();
            if (existe) {
                System.out.println("Carpeta creada: " + pathDirectorio);
            } else {
                System.err.println("Error al crear la carpeta: " + pathDirectorio);
            }
        }
    }
    //Lectura del archivo
    public static int[] leerArchivo(String pathArchivo)
    {
        File archivo = new File(pathArchivo);
        //Validacion
        if (!archivo.exists())
        {
            System.err.println("Error: El archivo no existe en " + pathArchivo);
            return null;
        }
        if (archivo.length() == 0) {
            System.err.println("El archivo esta vacio.");
            return null;
        }
        //Usamos una lista dinamica, con capacidad inicial de 10Mill.
        ArrayList<Integer> listaTemporal = new ArrayList<>(10000000);
        //Lectura de datos
        try (BufferedReader br = new BufferedReader(new FileReader(archivo), BUFFER_SIZE))
        {
            String linea;
            System.out.println("Cargando datos en memoria...");
            while ((linea = br.readLine()) != null)
            {
                StringTokenizer st = new StringTokenizer(linea, ", \t");
                while (st.hasMoreTokens()) {
                    listaTemporal.add(Integer.parseInt(st.nextToken().trim()));
                }
            }
            // Conversion eficiente a int[]
            int[] datos = new int[listaTemporal.size()];
            for (int i = 0; i < listaTemporal.size(); i++)
            {
                datos[i] = listaTemporal.get(i);
            }
            System.out.println("Carga exitosa: " + datos.length + " numeros en memoria.");
            return datos;

        } catch (IOException | NumberFormatException e) {
            System.err.println("Error crítico durante la lectura: " + e.getMessage());
            return null;
        }

    }
    //Guardar el archivo ordenado
    public static void guardarArchivo(int[] datos, String ruta) {
        //Validacion
        if (datos == null || datos.length == 0) {
            System.err.println("No hay datos para guardar.");
            return;
        }
        //Escritura
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ruta), BUFFER_SIZE)) {
            for (int i = 0; i < datos.length; i++) {
                // Escribir el numero convertido a String
                bw.write(Integer.toString(datos[i]));

                // Evitar la coma al final del archivo
                if (i < datos.length - 1) {
                    bw.write(",");
                }
            }
            bw.flush(); // Aseguramos que todo lo del buffer se guarde
            System.out.println("Guardado exitoso: " + ruta);

        } catch (IOException e) {
            System.err.println("Error al guardar el archivo: " + e.getMessage());
        }
    }

}
