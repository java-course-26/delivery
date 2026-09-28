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
     * На домашке заменишь его вызовом Delivery.distance(...) напарника и удалишь этот метод.
     */
    public static int dist(int x1, int y1, int x2, int y2) {
        // TODO
        return 0;
    }

    // ================= Задачи на пару =================

    /** Длина маршрута: старт → адрес order[0] → order[1] → ... (обратно не возвращаемся). */
    public static int routeLength(int startX, int startY, int[] xs, int[] ys, int[] order) {
        // TODO
        return 0;
    }

    /**
     * Жадный маршрут «ближайший сосед»: построить маршрут, где 
     * каждый раз едем в ближайший ещё не посещённый адрес.
     * При равенстве — адрес с меньшим индексом. Вернуть порядок объезда.
     */
    public static int[] greedyOrder(int startX, int startY, int[] xs, int[] ys) {
        // TODO
        return new int[0];
    }

    /**
     * Для РОВНО 4 адресов найти длину самого короткого маршрута полным перебором, 
     * пропуская варианты с повторами.
     * Сравни с жадным: всегда ли жадный находит лучший?
     */
    public static int bestLength4(int startX, int startY, int[] xs, int[] ys) {
        // TODO
        return 0;
    }

    /**
     * Интеграция с другим модулем (Delivery).
     * Длина маршрута, если курьер стартует из ресторана restaurantId.
     * Расстояния считай через Delivery.distance(...). После этого удали свой dist,
     * а routeLength и greedyOrder переведи на Delivery.distance — проверки выше должны остаться OK.
     */
    public static int routeLengthFromRestaurant(int restaurantId, int[] xs, int[] ys, int[] order) {
        // TODO
        return 0;
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
