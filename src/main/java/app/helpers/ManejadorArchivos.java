package app.helpers;

//Librerias
import java.io.File;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

public class ManejadorArchivos
{
    //Atributos
    private static final int BUFFER_SIZE = 64 * 1024; //Buffer de 64KB

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
        //Lectura de datos
        try (BufferedReader br = new BufferedReader(new FileReader(archivo), BUFFER_SIZE))
        {
            // Leemos la el bloque de datos
            String linea = br.readLine();
            if (linea == null || linea.isEmpty())
            {
                return null;
            }

            //Usamos StringTokenizer (corte de texto)
            StringTokenizer st = new StringTokenizer(linea, ",");
            int totalTokens = st.countTokens();
            int[] datos = new int[totalTokens];

            int i = 0;
            while (st.hasMoreTokens()) {
                // Integer.parseInt es rapido, pero requiere que el token no tenga espacios
                datos[i++] = Integer.parseInt(st.nextToken().trim());
            }

            System.out.println("Lectura completada: " + totalTokens + " numeros cargados.");
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
