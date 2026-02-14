package app.algoritmos;

public class Insercion implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "InsertionSort";
    }
    @Override
    public void Ordenar(int[] arr) {
        int n = arr.length;

        // Empezamos desde el segundo elemento (indice 1)
        for (int i = 1; i < n; i++) {
            int key = arr[i]; // El elemento que queremos "insertar"
            int j = i - 1;

            //Movemos los elementos de arr[0..i-1] que son mayores que la llave
            //a una posición adelante de su posición actual
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j--;
            }
            // Insertamos la llave en su posicion correcta
            arr[j + 1] = key;
        }
    }

}
