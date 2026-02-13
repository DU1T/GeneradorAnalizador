package app.helpers;

//Librerias
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.File;
import java.io.IOException;

public class ReproductorAudio
{
    //Atributos
    private Clip clip;
    private String rutaArchivo;

    //Constructor
    public  ReproductorAudio(String rutaArchivo)
    {
        this.rutaArchivo = rutaArchivo;
        cargarAudio();
    }
    //Metodos
    //Cargar audio a memoria
    private void cargarAudio()
    {
        try
        {
            File archivoAudio = new File(rutaArchivo);
            if (!archivoAudio.exists()) {
                System.err.println("Error: No se encontro el archivo de audio en: " + rutaArchivo);
                return;
            }

            // Obtener el flujo de audio del archivo
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(archivoAudio);

            // Obtener un clip de sonido
            clip = AudioSystem.getClip();

            // Abrir el clip con el flujo de audio
            clip.open(audioStream);

        }
        catch (UnsupportedAudioFileException e)
        {
            System.err.println("Error: El formato del archivo de audio no es soportado. Usa .wav");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            System.err.println("Error: Problema de lectura del archivo de audio.");
            e.printStackTrace();
        }
        catch (LineUnavailableException e)
        {
            System.err.println("Error: El audio no esta disponible.");
            e.printStackTrace();
        }
    }

    //Bucle
    public void iniciar()
    {
        if (clip != null)
        {
            // Reiniciar el audio
            clip.setFramePosition(0);
            // Reproducir en bucle infinito
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            System.out.println("Reproduciendo musica...");
        }
        else
        {
            System.err.println("No se puede reproducir.");
        }
    }
    //Detener bucle
    public void detener()
    {
        if (clip != null && clip.isRunning())
        {
            clip.stop();
            System.out.println("Se detuvo la musica.");
        }
    }
    //Cerar el clip
    public void cerrar()
    {
        if (clip != null)
        {
            clip.close();
        }
    }
}

