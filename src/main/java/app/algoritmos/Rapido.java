package app.algoritmos;

public class Rapido implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "QuickSort";
    }
    @Override
    public void Ordenar(int[] datos) {
        if (datos == null || datos.length == 0) return;

        // Llamada al metodo recursivo principal
        EjecutarRapido(datos, 0, datos.length - 1);
    }
    private void EjecutarRapido(int[] arr, int bajo, int alto) {
        if (bajo < alto) {
            // Obtener el indice de la particion (pivote en su lugar)
            int pi = particion(arr, bajo, alto);

            // Ordenar las dos mitades recursivamente
            EjecutarRapido(arr, bajo, pi - 1);
            EjecutarRapido(arr, pi + 1, alto);
        }
    }
    private int particion(int[] arr, int bajo, int alto) {
        // OPTIMIZACION: Elegimos el elemento del medio como pivote
        // para evitar el peor caso O(N^2) en listas ya ordenadas.
        int medio = bajo + (alto - bajo) / 2;
        int pivote = arr[medio];

        // Intercambiamos el pivote con el ultimo elemento para usar logica estandar
        int tempPivote = arr[medio];
        arr[medio] = arr[alto];
        arr[alto] = tempPivote;

        pivote = arr[alto];
        int i = (bajo - 1); // Índice del elemento más pequeño

        for (int j = bajo; j < alto; j++) {
            // Si el elemento actual es menor o igual al pivote
            if (arr[j] <= pivote) {
                i++;
                // Intercambio
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        // Colocamos el pivote en su posición final correcta
        int tempFinal = arr[i + 1];
        arr[i + 1] = arr[alto];
        arr[alto] = tempFinal;

        return i + 1;
    }
}
