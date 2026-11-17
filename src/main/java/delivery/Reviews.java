package src.main.java.delivery;

/**
 * Модуль 8. ОТЗЫВЫ И РЕЙТИНГ.
 * Отзыв i: ресторан DeliveryData.reviewRestaurant[i], оценка DeliveryData.reviewStars[i] (от 1 до 5).
 */
public class Reviews {

    /** Средняя оценка ресторана (double). */
    public static double averageRating(int restaurantId) {
        // TODO
        return 0;
    }

    /** Сколько у ресторана отзывов с оценкой stars. */
    public static int countByStars(int restaurantId, int stars) {
        // TODO
        return 0;
    }

    /**
     * Ресторан с лучшей средней оценкой среди тех, у кого МИНИМУМ 3 отзыва.
     */
    public static int bestRestaurant() {
        // TODO
        return -1;
    }

    /**
     * Медиана оценок ресторана. Собери оценки в отдельный массив, отсортируй (без Arrays.sort).
     * Для нечётного количества — средний элемент массива.
     * Для чётного количества — среднее двух средних элементов. Нет отзывов — 0.
     */
    public static double medianRating(int restaurantId) {
        // TODO
        return 0;
    }

    /**
     * Интеграция с другим модулем (Menu).
     * Название самого дешёвого блюда в лучшем ресторане.
     * Используй свой bestRestaurant() и Menu.cheapestInRestaurant(...) напарника.
     */
    public static String cheapestDishInBestRestaurant() {
        // TODO
        return "";
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
        Check.eq("cheapestDishInBestRestaurant()", cheapestDishInBestRestaurant(), "Морс клюквенный");
    }
}
