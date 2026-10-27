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
        while (minutes >= 1440) {
            minutes = minutes % 1440;
        }
        int hours = minutes / 60;
        int mins = minutes % 60;
        String hoursStr = "";
        if (hours <10) {
            hoursStr = "0" + hours;
        } else {
            hoursStr = "" + hours;
        }
        String minsStr = "";
        if (mins < 10) {
            minsStr = "0" + mins;
        } else {
            minsStr = "" + mins;
        }
        return hoursStr + ":" + minsStr;
    }
    /**
     * Открыт ли ресторан в момент minute. Время открытия включительно, закрытия — нет.
     * Если open > close, ресторан работает через полночь (например, 10:00–02:00).
     */
    public static boolean isOpen(int restaurantId, int minute) {
        int open = DeliveryData.restaurantOpen[restaurantId];
        int close = DeliveryData.restaurantClose[restaurantId];

        if (open < close) {
            return minute >= open && minute < close;
        } else {
            return minute >= open || minute < close;
        }
    }

    /**
     * Сколько минут готовится корзина. Кухня готовит все блюда одновременно,
     * поэтому время — это время самого долгого блюда.
     */
    public static int cookTime(int[] cart) {
        int max = 0;
        for (int dishId : cart) {
            int dishTime = DeliveryData.dishCookMinutes[dishId];
            if (dishTime > max) {
                max = dishTime;
            }
        }
        return max;
    }

    /** Сколько минут до открытия ресторана. Если он открыт — 0. */
    public static int minutesUntilOpen(int restaurantId, int minute) {
        if (isOpen(restaurantId, minute)) {
            return 0;
        }
        int open = DeliveryData.restaurantOpen[restaurantId];
        if (minute < open) {
            return open - minute;
        } else {
            return (1440 - minute) + open;
        }
    }
    /**
     * Интеграция с другим модулем (Delivery).
     * Во сколько привезут заказ: now + время готовки + 4 минуты на каждый км пути.
     * Расстояние от ресторана до (x, y) считай через Delivery.distance(...).
     * Ответ — строкой "ЧЧ:ММ".
     */
    public static String eta(int[] cart, int restaurantId, int x, int y, int now) {
        int cooking = cookTime(cart);

        int rx = DeliveryData.restaurantX[restaurantId];
        int ry = DeliveryData.restaurantY[restaurantId];

        int dist = Delivery.distance(rx, ry, x, y);
        int travelTime = dist * 4;

        return formatTime(now + cooking + travelTime);
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
        Check.eq("cookTime({})", cookTime(new int[]{}, 0);

        Check.eq("minutesUntilOpen(0, 540)", minutesUntilOpen(0, 540), 60);
        Check.eq("minutesUntilOpen(0, 1380)", minutesUntilOpen(0, 1380), 660);
        Check.eq("minutesUntilOpen(3, 100)", minutesUntilOpen(3, 100), 0);

        System.out.println("--- Интеграция ---");
        Check.eq("eta({0, 2}, 0, 5, 7, 1200)", eta(new int[]{0, 2}, 0, 5, 7, 1200), "20:43");
        Check.eq("eta({8}, 2, 5, 6, 1430)", eta(new int[]{8}, 2, 5, 6, 1430), "00:07");

        System.out.println("--- Крайние случае ---");
        Check.eq("formatTime(1440)",formatTime(1440),"00:00");
        Check.eq("formatTime(599)",formatTime(599),"09:59");
        Check.eq("isOpen(0,600)",isOpen(0,600),true);
        Check.eq("isOpen(2,120)",isOpen(2,120),false);
        Check.eq("isOpen(3,1439)",isOpen(3,1439),true);
        Check.eq("minutesUntilOpen(4,1200)",minutesUntilOpen(4,1200),720);

        System.out.println("---Замер---");
    }
}
