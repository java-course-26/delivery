package src.main.java.delivery;
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
        int naimZakaz = Integer.MAX_VALUE;
        int figovyKurier = -1;
        for (int i=0;i<DeliveryData.courierNames.length;i++){
            if ((DeliveryData.courierBusy[i]==false) &&  (naimZakaz > DeliveryData.courierOrdersToday[i])){
                naimZakaz = DeliveryData.courierOrdersToday[i];
                figovyKurier = i;
            }
        }
        return figovyKurier;
    }

    /** Заработок курьера за день в копейках: 150 ₽ за заказ + бонус 500 ₽, если заказов 10 и больше. */
    public static int earnings(int courierIndex) {
        if (courierIndex < 0 || courierIndex >= DeliveryData.courierNames.length){
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
        boolean isBusy = true;
        for (int j = 0; j < DeliveryData.courierBusy.length;j++){
            if (DeliveryData.courierBusy[j]==false){
                isBusy = false;
                break;
            }
        }
        if (isBusy){
            return -1;
        }
        int nearest = Integer.MAX_VALUE;
        int index_courier = 0;
        for (int i=0;i<DeliveryData.courierX.length;i++){
            int dist = Delivery.distance(x,y,DeliveryData.courierX[i], DeliveryData.courierY[i]);
            if (DeliveryData.courierBusy[i]){
                continue;
            }
            if(dist == nearest){
                if(DeliveryData.courierOrdersToday[index_courier] < DeliveryData.courierOrdersToday[i]){
                    continue;
                }else{
                    index_courier = i;
                }
            }
            if((DeliveryData.courierBusy[i]==false) && (dist < nearest)){
                index_courier = i;
                nearest = dist;
            }
        }
        return index_courier;
    }

    public static int minloadGap(int[] orders){
        if (orders.length < 2){return -1;}
        int minLoad = Integer.MAX_VALUE;
        for (int i=0;i<orders.length-1;i++){
            for (int j=1;j<orders.length;j++){
                if(i==j){continue;}
                if (Math.abs(orders[i]-orders[j]) < minLoad){
                    minLoad = Math.abs(orders[i]-orders[j]);
                }
            }
        }
        return minLoad;
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

        System.out.println("---Крайние случаи---");
        Check.eq("earnings(5)",earnings(5),215000);
        Check.eq("distribute(4)",distribute(4),new int[]{1,0,3,0,0,0});
        Check.eq("distribute(10)",distribute(10),new int[]{3,0,6,1,0,0});
        Check.eq("nearestFree(4,4)",nearestFree(4,4),2);
        Check.eq("nearestFree(9,9)",nearestFree(9,9),2);

        System.out.println("---Замер---");
        Check.eq("minLoadGap(заказы курьеров)",minloadGap(DeliveryData.courierOrdersToday),1);
        Check.eq("minLoadGap({10,4})",minloadGap(new int[]{10,4}),6);
        Check.eq("minLoadGap({5})",minloadGap(new int[]{5}),-1);
    }
}
