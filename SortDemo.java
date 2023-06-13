import java.util.Random;

public class SortDemo {

    private static final int ARRAY_SIZE = 10000000;  // 10 Millionen
    private static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("Erzeugen und sortieren eines großen Arrays...");

        Integer[] array = generateRandomArray();
        long startTime = System.nanoTime();

        quickSort(array, 0, array.length - 1);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;  // Millisekunden

        System.out.println("Sortierung abgeschlossen in " + duration + " Millisekunden.");
    }

    private static Integer[] generateRandomArray() {
        Integer[] array = new Integer[ARRAY_SIZE];
        for (int i = 0; i < ARRAY_SIZE; i++) {
            array[i] = random.nextInt();
        }
        return array;
    }

    private static void quickSort(Integer[] array, int low, int high) {
        if (low < high) {
            int pi = partition(array, low, high);

            quickSort(array, low, pi - 1);
            quickSort(array, pi + 1, high);
        }
    }

    private static int partition(Integer[] array, int low, int high) {
        int pivot = array[high];
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }

        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;

        return i + 1;
    }
}
