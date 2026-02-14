package app.algoritmos;

public class Monticulos implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "HeapSort";
    }
    @Override
    public void Ordenar(int[] datos) {
        int n = datos.length;

        //Construir el monticulo (reorganizar el array)
        // Empezamos desde el ultimo nodo que tiene hijos
        for (int i = n / 2 - 1; i >= 0; i--) {
            aplicarHeapify(datos, n, i);
        }

        //Extraer elementos uno a uno del monticulo
        for (int i = n - 1; i > 0; i--) {
            // Movemos la raiz actual (el mas grande) al final
            int temp = datos[0];
            datos[0] = datos[i];
            datos[i] = temp;

            // Llamamos a heapify en el monticulo reducido
            aplicarHeapify(datos, i, 0);
        }
    }
    // Metodo para amontonar un subarbol con raiz en el indice i
    private void aplicarHeapify(int[] arr, int n, int i) {
        int mayor = i; // Inicializar el mayor como la raiz
        int izquierda = 2 * i + 1;
        int derecha = 2 * i + 2;

        // Si el hijo izquierdo es mas grande que la raiz
        if (izquierda < n && arr[izquierda] > arr[mayor]) {
            mayor = izquierda;
        }

        // Si el hijo derecho es mas grande que el mayor hasta ahora
        if (derecha < n && arr[derecha] > arr[mayor]) {
            mayor = derecha;
        }

        // Si el mayor no es la raiz
        if (mayor != i) {
            int intercambio = arr[i];
            arr[i] = arr[mayor];
            arr[mayor] = intercambio;

            // Reorganizar recursivamente el subarbol afectado
            aplicarHeapify(arr, n, mayor);
        }
    }
}
