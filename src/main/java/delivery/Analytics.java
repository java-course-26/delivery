package src.main.java.delivery;




/**
 * Модуль 9. АНАЛИТИКА.
 * Заказ номер i за день: час DeliveryData.orderHour[i], сумма orderTotal[i],
 * клиент orderClient[i], ресторан orderRestaurant[i].
 */
public class Analytics {

    /** Выручка за день в копейках. */
    public static int totalRevenue() {
        int sumTotal =0;
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
    /** Сколько клиентов сделали 2 и больше заказов за день. */
    public static int repeatClients() {
        int[] zakazClients= new int[DeliveryData.orderClient.length];
        for(int i=0;i<zakazClients.length;i++) {
            int g = DeliveryData.orderClient[i];
            zakazClients[g]++;
        }
        int sum=0;
        for (int i:zakazClients){
            if(i>1){
                sum++;
            }
        }
        return sum;
    }
    public static int maxFromArray(int [] arr) {
        int max = 0;
        for (int i : arr) {
            if (max < i) {
                max = i;
            }
        }
        return max;
    }
    /** Сколько клиентов сделали 2 и больше заказов за день. */
    public static int repeatClients(int[] clients){
        if (clients.length>1) {
            int p = maxFromArray(clients)+1;
            int[] zakazClients = new int[p];
            for (int i:clients) {
                zakazClients[i]++;
            }
            int sum = 0;
            for (int i : zakazClients) {
                if (i > 1) {
                    sum++;
                }
            }
            return sum;
        }
        return 0;
        }

    public static int repeatClientsSlow(int[] clients){
        int count=0;
        for(int i=0;i<clients.length;i++) {
            boolean isCounted = false;
            for (int g = 0; g < i; g++) {
                if (clients[i] == clients[g]) {
                    isCounted = true;
                    break;
                }
            }
            if (!isCounted){
                for (int r= i+1; r<clients.length;r++){
                    if (clients[i] == clients[r]){
                        count+=1;
                        break;
                    }
                }

            }
        }
        return count;
    }

    /** 
     * Интеграция с другим модулем (Reviews).
     * Выручка ресторана с лучшим рейтингом. Используй Reviews.bestRestaurant() напарника. 
     */
    public static int revenueOfBestRestaurant() {
        int sum=0;
        int bestRestaurant = Reviews.bestRestaurant();
        for (int i = 0; i< DeliveryData.orderTotal.length;i++){
            if (DeliveryData.orderRestaurant[i] == bestRestaurant){
                sum += DeliveryData.orderTotal[i];
            }
        }
        return sum;
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
        Check.eq("repeatClients({5})",repeatClients(new int[]{5}),0);
        Check.eq("repeatClients({1,2,3})",repeatClients(new int[]{1,2,3}),0);
        Check.eq("repeatClients({7,7,7})",repeatClients(new int[]{7,7,7}),1);
        Check.eq("repeatClients({5})",repeatClients(new int[]{5}),0);
        Check.eq("repeatClients({1,2,3})",repeatClients(new int[]{1,2,3}),0);
        System.out.println("--- Замер ---");
        Check.eq("repeatClients(клиенты за день)", repeatClients(DeliveryData.orderClient),5);
        Check.eq("repeatClients({25,25})", repeatClients(new int[]{25,25}),1);
        Check.eq("repeatClients({})", repeatClients(new int[]{}),0);
        Check.eq("repeatClientsSlow(клиенты за день)", repeatClientsSlow(DeliveryData.orderClient),5);
        Check.eq("repeatClientsSlow({7,7,7})", repeatClientsSlow(new int[]{7,7,7}),1);
        Check.eq("repeatClientsSlow({})", repeatClientsSlow(new int[]{}),0);
        for (int seed=0;seed<100;seed++){
            int[] data = BigData.randomArray(1000,0,1000,seed);
            if (repeatClients(data) != repeatClientsSlow(data)){
                System.out.println("FAIL"+ seed);
            }
        }
    }
}
