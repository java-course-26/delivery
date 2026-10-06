package src.main.java.delivery;

import java.sql.SQLOutput;

/**
 * Модуль 6. КУРЬЕРЫ.
 * Данные — DeliveryData.courierNames, courierX, courierY, courierBusy, courierOrdersToday.
 */
public class Couriers {

    /** Сколько курьеров сейчас свободны. */
    public static int freeCount() {
        int counter = 0;
        for (boolean n:DeliveryData.courierBusy){
            if (n == false){
                counter++;
            }
        }
        return counter;
    }

    /** Свободный курьер с наименьшим числом заказов за день. Нет свободных — -1. */
    public static int leastLoadedFree() {
        if (DeliveryData.courierNames == null || DeliveryData.courierBusy == null || DeliveryData.courierOrdersToday == null){
            return 0;
        }
        int naimZakaz = 1000;
        int figovyKurier = 0;
        String name = "";
        for (int i=0;i<DeliveryData.courierNames.length;i++){
            if ((DeliveryData.courierBusy[i]==false) &&  (naimZakaz > DeliveryData.courierOrdersToday[i])){
                name = DeliveryData.courierNames[i];
                naimZakaz = DeliveryData.courierOrdersToday[i];
                figovyKurier = i;
            }
        }
        return figovyKurier;
    }

    /** Заработок курьера за день в копейках: 150 ₽ за заказ + бонус 500 ₽, если заказов 10 и больше. */
    public static int earnings(int courierIndex) {
        if (courierIndex < 0 || courierIndex >= DeliveryData.courierNames.length){
            System.out.println("Введено некорректное значение");
            return 0;
        }
        int bonus = DeliveryData.courierOrdersToday[courierIndex] >= 10?500:0;
        int money = DeliveryData.courierOrdersToday[courierIndex] * 150 + bonus;
        return money*100;
    }

    /**
     * Раздать k новых заказов свободным курьерам: каждый заказ получает свободный курьер
     * с наименьшей ТЕКУЩЕЙ загрузкой (заказы за день + уже выданные новые).
     * Вернуть массив: сколько новых заказов получил каждый курьер.
     * DeliveryData не менять — загрузку копируем в свой массив.
     */


    public static int[] distribute(int k) {
        int[] currentLoad = DeliveryData.courierOrdersToday.clone();
        int[] result = new int[DeliveryData.courierNames.length];
        for (int order = 0; order < k; order++) {
            int minCourier = -1;
            for (int i = 0; i < currentLoad.length; i++) {
                if (DeliveryData.courierBusy[i]==false) {
                    if (minCourier == -1 || currentLoad[i] < currentLoad[minCourier]) {
                        minCourier = i;
                    }
                }
            }
            if (minCourier == -1) {
                break;
            }
            result[minCourier]++;
            currentLoad[minCourier]++;
        }
        return result;
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
