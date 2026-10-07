package src.main.java.delivery;

import java.util.Arrays;

/**
 * Модуль 9. АНАЛИТИКА.
 * Заказ номер i за день: час DeliveryData.orderHour[i], сумма orderTotal[i],
 * клиент orderClient[i], ресторан orderRestaurant[i].
 */
public class Analytics {

    /** Выручка за день в копейках. */
    public static int totalRevenue() {

        return Arrays.stream(DeliveryData.orderTotal).sum();
    }

    /** Средний чек в копейках (double). */
    public static double averageCheck() {
        double SrArif = Arrays.stream(DeliveryData.orderTotal).sum()/DeliveryData.orderTotal.length;
        return SrArif;
    }
    public static int[] FindPeakHour() {
        int[] HourStat = new int[24];
        for (int i : DeliveryData.orderHour){
            HourStat[i]++;
        }
        return HourStat;
    }
    /**
     * Пиковый час — час, в который было больше всего заказов.
     * Заведи массив на 24 ячейки и посчитай заказы по часам. 
     */
    public static int peakHour() {
        int maxx = 0;
        int NuzhnHour=0;
        for (int i=0;i< FindPeakHour().length;i++){
            if (maxx<FindPeakHour()[i]){
                NuzhnHour= i;
                maxx = FindPeakHour()[i];
            }
        }
        return NuzhnHour;
    }

    /** Выручка одного ресторана за день. */
    public static int revenueByRestaurant(int restaurantId) {
        int u = restaurantId;
        int sumRevenue=0;
        for (int i=1;i<DeliveryData.orderRestaurant.length;i++){
            if(DeliveryData.orderRestaurant[i]==u){
                sumRevenue+=DeliveryData.orderTotal[i];
            }
        }
        return sumRevenue;
    }
    public static int[] ZakaziUClients(){
        int[] ZakazClients= new int[DeliveryData.orderClient.length];
        for(int i=0;i<ZakazClients.length;i++) {
            int g = DeliveryData.orderClient[i];
            ZakazClients[g]++;
        }
        return ZakazClients;
    }
    /** Сколько клиентов сделали 2 и больше заказов за день. */
    public static int repeatClients() {
        int sum=0;
        for (int i:ZakaziUClients()){
            if(i>1){
                sum++;
            }
        }
        return sum;
    }

    /** 
     * Интеграция с другим модулем (Reviews).
     * Выручка ресторана с лучшим рейтингом. Используй Reviews.bestRestaurant() напарника. 
     */
    public static int revenueOfBestRestaurant() {
        // TODO
        return 0;
    }

    public static void main(String[] args) {

        //System.out.println(DeliveryData.orderTotal);
        Check.eq("totalRevenue()", totalRevenue(), 1482000);
        Check.eq("averageCheck()", averageCheck(), 74100.0);
        Check.eq("peakHour()", peakHour(), 19);
        Check.eq("revenueByRestaurant(2)", revenueByRestaurant(2), 539000);
        Check.eq("revenueByRestaurant(7)", revenueByRestaurant(7), 0);

        Check.eq("repeatClients()", repeatClients(), 5);

        System.out.println("--- Интеграция ---");
        Check.eq("revenueOfBestRestaurant()", revenueOfBestRestaurant(), 254000);
    }
}
