package app.algoritmos;

public class Burbuja implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "BubbleSort";
    }
    @Override
    public void Ordenar(int[] arr) {
        int n = arr.length;
        boolean intercambio;

        for (int i = 0; i < n - 1; i++) {
            intercambio = false;

            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    // Intercambio de variables
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;

                    intercambio = true;
                }
            }

            // Si no hubo intercambios en esta pasada, el arreglo ya esta ordenado
            if (!intercambio) {
                break;
            }
        }
    }
}
