package app.algoritmos;

public class Shell implements AlgoritmoOrdenamiento
{
    @Override
    public String getNombre() {
        return "ShellSort";
    }
    @Override
    public void Ordenar(int[] arr) {
        int n = arr.length;

        // Empezamos con una brecha grande, y lo reducimos a la mitad cada vez
        for (int gap = n / 2; gap > 0; gap /= 2) {

            // Hacemos un ordenamiento por insercion para los elementos separados por el gap
            for (int i = gap; i < n; i++) {
                int temp = arr[i];
                int j;

                // Desplazamos los elementos ordenados previamente hasta encontrar
                // la posición correcta para arr[i]
                for (j = i; j >= gap && arr[j - gap] > temp; j -= gap) {
                    arr[j] = arr[j - gap];
                }

                // Colocamos el elemento temporal en su lugar correcto
                arr[j] = temp;
            }
        }
    }
}
