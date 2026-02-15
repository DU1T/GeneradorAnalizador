=======================================================================
          ANALIZADOR DE ALGORITMOS DE ORDENAMIENTO - UMG
=======================================================================

Este proyecto permite generar y analizar el rendimiento de 8 
algoritmos de ordenamiento con volúmenes de hasta 10,000,000 
de datos, incluyendo música de fondo durante el proceso.
Para realizar pruebas con 10M cargar numerosByLocalRandom.txt a la
carpeta data de GeneradoryAnalizadorUMG_BuildFINAL.
-----------------------------------------------------------------------
1. INSTRUCCIONES DE EJECUCIÓN (Windows)
-----------------------------------------------------------------------
El build se encuentra en la carpeta "GeneradoryAnalizadorUMG_FINAL"

Dependiendo de la memoria RAM disponible en su equipo, ejecute el 
archivo correspondiente:

• Ejecutar_4GB.bat: 
  Ideal para la mayoría de computadoras (mínimo 8GB de RAM total).
  
• Ejecutar_6GB.bat: 
  Recomendado para pruebas estables de 10 millones de datos.

-----------------------------------------------------------------------
2. EJECUCIÓN MANUAL / OTRAS PLATAFORMAS (Terminal)
-----------------------------------------------------------------------
Si usa Linux, macOS o desea asignar memoria personalizada, abra una 
terminal en esta carpeta y ejecute:

   java -Xmx4G -Dfile.encoding=UTF-8 -jar GenerardoryAnalizadordeNumerosAleatoriosUMG-1.0.jar

(Puede cambiar "4G" por la cantidad de RAM deseada, ej: 8G, 12G).

-----------------------------------------------------------------------
4. NOTAS TÉCNICAS
-----------------------------------------------------------------------
• MÚSICA DE FONDO: La música se activará automáticamente al iniciar 
  un proceso de ordenamiento y se detendrá al finalizar.
  
• NO BLOQUEO: Gracias al uso de hilos (Threads), el menú sigue activo 
  mientras los algoritmos corren. Puede usar la "Opción 4" en el menú 
  para verificar que el programa sigue respondiendo.

• ALGORITMOS PESADOS: Algoritmos como Burbuja o Selección pueden 
  tardar minutos u horas con 10M de datos. No cierre la ventana 
  mientras la música esté sonando.
  Repo: https://github.com/DU1T/GeneradorAnalizador.git

-----------------------------------------------------------------------
Estructuras de datos - ING. Sistemas 5B - LT,BM,EL
=======================================================================
