package src.main.java.delivery;

import java.util.Arrays;

/**
 * Модуль 8. ОТЗЫВЫ И РЕЙТИНГ.
 */
public class Reviews {

    /** Средняя оценка ресторана. */
    public static double averageRating(int restaurantId) {
        int sum = 0, count = 0;
        for (int i = 0; i < DeliveryData.reviewRestaurant.length; i++) {
            if (DeliveryData.reviewRestaurant[i] == restaurantId) {
                sum += DeliveryData.reviewStars[i];
                count++;
            }
        }
        return count == 0 ? 0 : (double) sum / count;
    }

    /** Сколько у ресторана отзывов с оценкой stars. */
    public static int countByStars(int restaurantId, int stars) {
        int count = 0;
        for (int i = 0; i < DeliveryData.reviewRestaurant.length; i++) {
            if (DeliveryData.reviewRestaurant[i] == restaurantId
                    && DeliveryData.reviewStars[i] == stars) {
                count++;
            }
        }
        return count;
    }

    /** Ресторан с лучшей средней оценкой (минимум 3 отзыва). */
    public static int bestRestaurant() {
        int best = -1;
        double bestAvg = -1;
        for (int r = 0; r < DeliveryData.restaurantNames.length; r++) {
            int count = 0;
            for (int i = 0; i < DeliveryData.reviewRestaurant.length; i++) {
                if (DeliveryData.reviewRestaurant[i] == r) count++;
            }
            if (count >= 3 && averageRating(r) > bestAvg) {
                bestAvg = averageRating(r);
                best = r;
            }
        }
        return best;
    }

    /** Медиана оценок ресторана. */
    public static double medianRating(int restaurantId) {
        int count = 0;
        for (int i = 0; i < DeliveryData.reviewRestaurant.length; i++) {
            if (DeliveryData.reviewRestaurant[i] == restaurantId) count++;
        }
        if (count == 0) return 0;

        int[] ratings = new int[count];  /**Создание нового массива */
        
        int k = 0;
        for (int i = 0; i < DeliveryData.reviewRestaurant.length; i++) {
            if (DeliveryData.reviewRestaurant[i] == restaurantId) {
                ratings[k] = DeliveryData.reviewStars[i];
                k++;
            }
        }
        double result = medianRating(ratings);
        return result;
        
    }

   public static double medianRating(int[] stars) {
        int[] sorted=Arrays.copyOf(stars,stars.length);

        int count = sorted.length;
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - 1 - i; j++) {
                if (sorted[j] > sorted[j + 1]) {
                    int t = sorted[j];
                    sorted[j] = sorted[j + 1];
                    sorted[j + 1] = t;
                }
            }
        }
        if (count % 2 == 1) return sorted[(count / 2)];
        return (sorted[count / 2 - 1] + sorted[count / 2]) / 2.0;
    }

    /** Самое дешёвое блюдо в лучшем ресторане. */
    public static int cheapestDishInBestRestaurant() {
        int best = bestRestaurant();
        if (best == -1) return 0;
        return Menu.cheapestInRestaurant(best);
    } 
    

    public static void main(String[] args) {
        Check.eq("averageRating(0)", averageRating(0), 4.4);
        Check.eq("averageRating(3)", averageRating(3), 3.5);
        Check.eq("averageRating(1)", averageRating(1), 4.17);
        Check.eq("countByStars(1, 5)", countByStars(1, 5), 4);
        Check.eq("countByStars(3, 4)", countByStars(3, 4), 0);
        Check.eq("bestRestaurant()", bestRestaurant(), 0);

        Check.eq("medianRating(1)", medianRating(1), 5.0);
        Check.eq("medianRating(2)", medianRating(2), 4.0);
        Check.eq("medianRating(4)", medianRating(4), 4.0);

        System.out.println("--- Интеграция ---");
        /** Check.eq("cheapestDishInBestRestaurant()", cheapestDishInBestRestaurant(), "Морс клюквенный"); */
        System.out.println("--- Крайние случаи ---");
        Check.eq("averageRating(9)", averageRating(9),0.0);
        Check.eq("averageRating(4)", averageRating(4),4.33);
        Check.eq("countByStars(0,5)", countByStars(0,5),3);
        Check.eq("medianRating(0)", medianRating(0),5.0);
        Check.eq("medianRating(3)", medianRating(3),3.5);
        Check.eq("medianRating(9)", medianRating(9),0.0);
        System.out.println("Замер");
        Check.eq("medianRating({5,1,3})",medianRating(new int[]{5,1,3}),3.0);
        Check.eq("medianRating({4,5})",medianRating(new int[]{4,5}),4.5);
        Check.eq("medianRating({2,5,1,5})",medianRating(new int[]{2,5,1,5}),3.5);
        Check.eq("medianRating({5,1,3})",medianRating(new int[]{5,1,3}),3.0);

        
    }
}
