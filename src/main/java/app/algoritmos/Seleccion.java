package app.algoritmos;

public class Seleccion implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "SelectionSort";
    }
    @Override
    public void Ordenar(int[] arr) {
        int n = arr.length;

        // Uno por uno movemos el limite del sub-arreglo desordenado
        for (int i = 0; i < n - 1; i++) {
            // Encontrar el indice del minimo elemento en el arreglo desordenado
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }

            // Intercambiar el elemento minimo encontrado con el primer elemento
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }
}
