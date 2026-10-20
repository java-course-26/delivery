package src.main.java.delivery;



/**
 * Модуль 9. АНАЛИТИКА.
 * Заказ номер i за день: час DeliveryData.orderHour[i], сумма orderTotal[i],
 * клиент orderClient[i], ресторан orderRestaurant[i].
 */
public class Analytics {

    /** Выручка за день в копейках. */
    public static double totalRevenue() {
        double sumTotal =0;
        for (int x: DeliveryData.orderTotal){
            sumTotal += x;
        }
        return sumTotal;
    }

    /** Средний чек в копейках (double). */
    public static double averageCheck() {
        double srArif = totalRevenue()/DeliveryData.orderTotal.length;
        return srArif;
    }
    public static int[] countByHour() {
        int[] hourStat = new int[24];
        for (int i : DeliveryData.orderHour){
            hourStat[i]++;
        }
        return hourStat;
    }
    /**
     * Пиковый час — час, в который было больше всего заказов.
     * Заведи массив на 24 ячейки и посчитай заказы по часам. 
     */
    public static int peakHour() {
        int maxx = 0;
        int nuzhnHour=0;
        int[] fph= countByHour();
        for (int i=0;i< fph.length;i++){
            if (maxx<fph[i]){
                nuzhnHour= i;
                maxx = fph[i];
            }
        }
        return nuzhnHour;
    }

    /** Выручка одного ресторана за день. */
    public static int revenueByRestaurant(int restaurantId) {
        int u = restaurantId;
        int sumRevenue=0;
        for (int i=0;i<DeliveryData.orderRestaurant.length;i++){
            if(DeliveryData.orderRestaurant[i]==u){
                sumRevenue+=DeliveryData.orderTotal[i];
            }
        }
        return sumRevenue;
    }
    public static int[] ordersPerClient(){
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
        for (int i:ordersPerClient()){
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

        Check.eq("totalRevenue()", totalRevenue(), 1482000);
        Check.eq("averageCheck()", averageCheck(), 74100.0);
        Check.eq("peakHour()", peakHour(), 19);
        Check.eq("revenueByRestaurant(2)", revenueByRestaurant(2), 539000);
        Check.eq("revenueByRestaurant(7)", revenueByRestaurant(7), 0);

        Check.eq("repeatClients()", repeatClients(), 5);

        System.out.println("--- Интеграция ---");
        Check.eq("revenueOfBestRestaurant()", revenueOfBestRestaurant(), 254000);
        System.out.println("--- Крайние случаи ---");
        Check.eq("revenueByRestaurant(0)",revenueByRestaurant(0),254000);
        Check.eq("revenueByRestaurant(4)",revenueByRestaurant(4),124000);

    }
}
