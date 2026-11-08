package src.main.java.delivery;

import java.util.Random;

/**
 * Генератор больших тестовых данных для замеров.
 *
 * seed — «зерно» случайности: с одним и тем же seed всегда получается один и тот же массив.
 * Так у всех одинаковые данные, и результаты замеров можно сравнивать.
 */
public class BigData {

    /** Массив из n случайных чисел от from (включительно) до to (НЕ включительно). */
    public static int[] randomArray(int n, int from, int to, long seed) {
        Random random = new Random(seed);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = from + random.nextInt(to - from);
        }
        return result;
    }

    /** Числа 0, 1, 2, ..., n-1 в случайном порядке. Повторов нет. */
    public static int[] distinctArray(int n, long seed) {
        Random random = new Random(seed);
        int[] result = new int[n];
        for (int i = 0; i < n; i++) {
            result[i] = i;
        }
        for (int i = n - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            int tmp = result[i];
            result[i] = result[j];
            result[j] = tmp;
        }
        return result;
    }

    /** Пример: напечатать маленький массив, чтобы посмотреть, как он выглядит. */
    public static void main(String[] args) {
        int[] a = randomArray(10, 0, 100, 42);
        int[] b = distinctArray(10, 42);
        for (int x : a) System.out.print(x + " ");
        System.out.println();
        for (int x : b) System.out.print(x + " ");
        System.out.println();
    }
}
