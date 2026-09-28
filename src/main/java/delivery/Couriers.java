package src.main.java.delivery;

/**
 * Модуль 6. КУРЬЕРЫ.
 * Данные — DeliveryData.courierNames, courierX, courierY, courierBusy, courierOrdersToday.
 */
public class Couriers {

    /** Сколько курьеров сейчас свободны. */
    public static int freeCount() {
        // TODO
        return 0;
    }

    /** Свободный курьер с наименьшим числом заказов за день. Нет свободных — -1. */
    public static int leastLoadedFree() {
        // TODO
        return -1;
    }

    /** Заработок курьера за день в копейках: 150 ₽ за заказ + бонус 500 ₽, если заказов 10 и больше. */
    public static int earnings(int courierIndex) {
        // TODO
        return 0;
    }

    /**
     * Раздать k новых заказов свободным курьерам: каждый заказ получает свободный курьер
     * с наименьшей ТЕКУЩЕЙ загрузкой (заказы за день + уже выданные новые).
     * Вернуть массив: сколько новых заказов получил каждый курьер.
     * DeliveryData не менять — загрузку копируем в свой массив.
     */
    public static int[] distribute(int k) {
        // TODO
        return new int[0];
    }

    /**
     * Интеграция с другим модулем (Delivery).
     * Ближайший к точке (x, y) свободный курьер. Расстояние — через Delivery.distance(...).
     * При равном расстоянии — тот, у кого меньше заказов за день.
     */
    public static int nearestFree(int x, int y) {
        // TODO
        return -1;
    }

    public static void main(String[] args) {
        Check.eq("freeCount()", freeCount(), 4);
        Check.eq("leastLoadedFree()", leastLoadedFree(), 2);
        Check.eq("earnings(1)", earnings(1), 230000);
        Check.eq("earnings(2)", earnings(2), 60000);
        Check.eq("earnings(4)", earnings(4), 45000);

        Check.eq("distribute(5)", distribute(5), new int[]{1, 0, 4, 0, 0, 0});
        Check.eq("distribute(0)", distribute(0), new int[]{0, 0, 0, 0, 0, 0});

        System.out.println("--- Интеграция ---");
        Check.eq("nearestFree(5, 5)", nearestFree(5, 5), 2);
        Check.eq("nearestFree(1, 9)", nearestFree(1, 9), 3);
        Check.eq("nearestFree(4, 3)", nearestFree(4, 3), 0);
    }
}
