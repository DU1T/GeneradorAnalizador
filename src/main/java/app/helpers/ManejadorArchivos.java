package app.helpers;

//Librerias
import java.io.File;

public class ManejadorArchivos
{
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
}
