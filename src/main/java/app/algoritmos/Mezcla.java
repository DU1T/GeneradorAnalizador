package app.algoritmos;

public class Mezcla implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "MergeSort";
    }
    @Override
    public void Ordenar(int[] arr) {
        //Validacion
        if (arr == null || arr.length <= 1) return;
        // Llamada al metodo recursivo usando los indices del array
        ejecutarMergeSort(arr, 0, arr.length - 1);
    }
    private void ejecutarMergeSort(int[] arr, int izquierda, int derecha) {
        if (izquierda < derecha) {
            // Encontrar el punto medio
            int medio = izquierda + (derecha - izquierda) / 2;

            // Ordenar la primera y segunda mitad
            ejecutarMergeSort(arr, izquierda, medio);
            ejecutarMergeSort(arr, medio + 1, derecha);

            // Combinar las mitades ordenadas
            merge(arr, izquierda, medio, derecha);
        }
    }
    private void merge(int[] arr, int izquierda, int medio, int derecha)
    {
        // Tamaños de los sub-arreglos a unir
        int n1 = medio - izquierda + 1;
        int n2 = derecha - medio;

        // Arreglos temporales
        int[] L = new int[n1];
        int[] R = new int[n2];

        // Copiar datos a los arreglos temporales
        for (int i = 0; i < n1; i++)
            L[i] = arr[izquierda + i];
        for (int j = 0; j < n2; j++)
            R[j] = arr[medio + 1 + j];

        // Indices iniciales para los sub-arreglos y el arreglo combinado
        int i = 0, j = 0;
        int k = izquierda;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
            }
            k++;
        }

        // Copiar elementos restantes de L[] si los hay
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        // Copiar elementos restantes de R[] si los hay
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }
    }
}
