package app.algoritmos;

public class Conteo implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "CountingSort";
    }
    @Override
    public void Ordenar(int[] arr) {
        if (arr == null || arr.length <= 1) return;

        //Encontrar Max y Min con un bucle tradicional
        int max = arr[0];
        int min = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
            if (arr[i] < min) min = arr[i];
        }

        //Calcular rango y validar
        long range = (long) max - min + 1;
        if (range > 15000000) { // Límite de seguridad para evitar OutOfMemory
            System.out.println("Rango demasiado grande para Counting Sort. Usando QuickSort como respaldo...");
            new Rapido().Ordenar(arr);
            return;
        }

        //Crear el arreglo de frecuencias
        int[] count = new int[(int) range];

        //Contar apariciones
        for (int x : arr) {
            count[x - min]++;
        }

        //Reconstruir el arreglo original
        int k = 0;
        for (int i = 0; i < count.length; i++) {
            while (count[i] > 0) {
                arr[k++] = i + min;
                count[i]--;
            }
        }
    }
}
