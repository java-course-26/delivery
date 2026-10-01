package src.main.java.delivery;

/**
 * Модуль 7. МАРШРУТ.
 * Курьер выезжает из точки (startX, startY) и объезжает несколько адресов.
 * Адреса заданы массивами xs и ys, порядок объезда — массивом order
 * (order = {2, 0, 1} значит: сначала адрес 2, потом 0, потом 1).
 */
public class Route {

    /**
     * Своё расстояние «по кварталам». Пока пишем прямо здесь.
     * Потом заменить его вызовом Delivery.distance(...) напарника и удалишь этот метод.
     */
    public static int dist(int x1, int y1, int x2, int y2) {
        return Math.abs(x1 - x2) + Math.abs(y1 - y2);
    }

    /** Длина маршрута: старт → адрес order[0] → order[1] → ... (обратно не возвращаемся). */
    public static int routeLength(int startX, int startY, int[] xs, int[] ys, int[] order) {
        int length = 0;
        int x = startX;
        int y = startY;
        for (int stop : order) {
            length += dist(x, y, xs[stop], ys[stop]);
            x = xs[stop];
            y = ys[stop];
        }
        return length;
    }

    /**
     * Жадный маршрут «ближайший сосед»: построить маршрут, где 
     * каждый раз едем в ближайший ещё не посещённый адрес.
     * При равенстве — адрес с меньшим индексом. Вернуть порядок объезда.
     */
    public static int[] greedyOrder(int startX, int startY, int[] xs, int[] ys) {
        int n = xs.length;
        boolean[] visited = new boolean[n];
        int[] order = new int[n];
        int x = startX;
        int y = startY;
        for (int step = 0; step < n; step++) {
            int best = -1;
            for (int i = 0; i < n; i++) {
                if (!visited[i] && (best == -1
                        || dist(x, y, xs[i], ys[i]) < dist(x, y, xs[best], ys[best]))) {
                    best = i;
                }
            }
            visited[best] = true;
            order[step] = best;
            x = xs[best];
            y = ys[best];
        }
        return order;
    }

    /**
     * Для РОВНО 4 адресов найти длину самого короткого маршрута полным перебором, 
     * пропуская варианты с повторами.
     * Сравни с жадным: всегда ли жадный находит лучший?
     */
    public static int bestLength4(int startX, int startY, int[] xs, int[] ys) {
        int best = -1;
        for (int a = 0; a < 4; a++) {
            for (int b = 0; b < 4; b++) {
                if (b == a) continue;
                for (int c = 0; c < 4; c++) {
                    if (c == a || c == b) continue;
                    int d = 6 - a - b - c;
                    int len = routeLength(startX, startY, xs, ys, new int[]{a, b, c, d});
                    if (best == -1 || len < best) best = len;
                }
            }
        }
        return best;
    }

     /**
     * Интеграция с другим модулем (Delivery).
     * Длина маршрута, если курьер стартует из ресторана restaurantId.
     * Расстояния считай через Delivery.distance(...). После этого удалить свой dist,
     * а routeLength и greedyOrder переведи на Delivery.distance — проверки выше должны остаться OK.
     */
    public static int routeLengthFromRestaurant(int restaurantId, int[] xs, int[] ys, int[] order) {
        return routeLength(DeliveryData.restaurantX[restaurantId], DeliveryData.restaurantY[restaurantId], xs, ys, order);
    }

    public static void main(String[] args) {
        int[] xs = {4, 1, 5, 2};
        int[] ys = {1, 3, 5, 0};
        int[] xs2 = {0, 3, 5, 9};
        int[] ys2 = {0, 0, 0, 0};

        Check.eq("routeLength(0, 0, {0, 1, 2, 3})", routeLength(0, 0, xs, ys, new int[]{0, 1, 2, 3}), 24);
        Check.eq("routeLength(0, 0, {})", routeLength(0, 0, xs, ys, new int[]{}), 0);
        Check.eq("greedyOrder(0, 0)", greedyOrder(0, 0, xs, ys), new int[]{3, 0, 1, 2});
        Check.eq("routeLength по жадному порядку", routeLength(0, 0, xs, ys, greedyOrder(0, 0, xs, ys)), 16);

        Check.eq("bestLength4(0, 0, первый набор)", bestLength4(0, 0, xs, ys), 16);
        Check.eq("bestLength4(2, 0, второй набор)", bestLength4(2, 0, xs2, ys2), 11);
        Check.eq("жадный на втором наборе", routeLength(2, 0, xs2, ys2, greedyOrder(2, 0, xs2, ys2)), 16);

        System.out.println("--- Интеграция ---");
        Check.eq("routeLengthFromRestaurant(0, {0, 1, 2, 3})", routeLengthFromRestaurant(0, xs, ys, new int[]{0, 1, 2, 3}), 23);
    }
}
