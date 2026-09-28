package src.main.java.delivery;

/**
 * Модуль 5. ВРЕМЯ.
 * Время хранится в минутах от полуночи: 545 = 09:05.
 */
public class Time {

    /**
     * Минуты → "ЧЧ:ММ". Если минут больше суток, переходим на следующие сутки:
     * 545 → "09:05", 1500 → "01:00", 0 → "00:00".
     */
    public static String formatTime(int minutes) {
        // TODO
        return "";
    }

    /**
     * Открыт ли ресторан в момент minute. Время открытия включительно, закрытия — нет.
     * Если open > close, ресторан работает через полночь (например, 10:00–02:00).
     */
    public static boolean isOpen(int restaurantId, int minute) {
        // TODO
        return false;
    }

    /**
     * Сколько минут готовится корзина. Кухня готовит все блюда одновременно,
     * поэтому время — это время самого долгого блюда.
     */
    public static int cookTime(int[] cart) {
        // TODO
        return 0;
    }

    /** Сколько минут до открытия ресторана. Если он открыт — 0. */
    public static int minutesUntilOpen(int restaurantId, int minute) {
        // TODO
        return 0;
    }

    /**
     * Интеграция с другим модулем (Delivery).
     * Во сколько привезут заказ: now + время готовки + 4 минуты на каждый км пути.
     * Расстояние от ресторана до (x, y) считай через Delivery.distance(...).
     * Ответ — строкой "ЧЧ:ММ".
     */
    public static String eta(int[] cart, int restaurantId, int x, int y, int now) {
        // TODO
        return "";
    }

    public static void main(String[] args) {
        Check.eq("formatTime(545)", formatTime(545), "09:05");
        Check.eq("formatTime(1500)", formatTime(1500), "01:00");
        Check.eq("formatTime(0)", formatTime(0), "00:00");
        Check.eq("isOpen(0, 720)", isOpen(0, 720), true);
        Check.eq("isOpen(0, 1320)", isOpen(0, 1320), false);
        Check.eq("isOpen(2, 1430)", isOpen(2, 1430), true);
        Check.eq("isOpen(2, 60)", isOpen(2, 60), true);
        Check.eq("isOpen(2, 300)", isOpen(2, 300), false);
        Check.eq("isOpen(3, 200)", isOpen(3, 200), true);
        Check.eq("cookTime({0, 2, 3})", cookTime(new int[]{0, 2, 3}), 15);
        Check.eq("cookTime({})", cookTime(new int[]{}), 0);

        Check.eq("minutesUntilOpen(0, 540)", minutesUntilOpen(0, 540), 60);
        Check.eq("minutesUntilOpen(0, 1380)", minutesUntilOpen(0, 1380), 660);
        Check.eq("minutesUntilOpen(3, 100)", minutesUntilOpen(3, 100), 0);

        System.out.println("--- Интеграция ---");
        Check.eq("eta({0, 2}, 0, 5, 7, 1200)", eta(new int[]{0, 2}, 0, 5, 7, 1200), "20:43");
        Check.eq("eta({8}, 2, 5, 6, 1430)", eta(new int[]{8}, 2, 5, 6, 1430), "00:07");
    }
}
