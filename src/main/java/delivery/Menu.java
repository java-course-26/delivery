package src.main.java.delivery;

/**
 * Модуль 1. МЕНЮ — поиск блюд и цены.
 * Все данные о блюдах — в DeliveryData (dishNames, dishPrices, dishRestaurant ...).
 */
public class Menu {
    
    /**
     * Индекс первого блюда с таким названием или -1, если такого нет.
     */
    public static int findDish(String name) {
        // TODO
        return -1;
    }

    /** Индекс самого дешёвого блюда в ресторане restaurantId. */
    public static int cheapestInRestaurant(int restaurantId) {
        // TODO
        return -1;
    }

    /** Сколько блюд стоят от min до max копеек включительно. */
    public static int countInPriceRange(int min, int max) {
        // TODO
        return 0;
    }

    /** Цена в копейках → строка с рублями: 35050 → "350,50 ₽", 5 → "0,05 ₽". */
    public static String formatPrice(int kopecks) {
        // TODO
        return "";
    }

    /**
     * Индексы блюд ресторана, отсортированные по возрастанию цены.
     * Сортировку пишем сами, без Arrays.sort.
     */
    public static int[] sortedByPrice(int restaurantId) {
        // TODO
        return new int[0];
    }

    /**
     * Интеграция с другим модулем (Time).
     * Индекс первого блюда с таким названием, ресторан которого открыт в момент minute.
     * Используй Time.isOpen(...) из модуля напарника. Если ничего не нашлось — -1.
     */
    public static int findOpenDish(String name, int minute) {
        // TODO
        return -1;
    }

    public static void main(String[] args) {
        Check.eq("findDish(\"Борщ\")", findDish("Борщ"), 2);
        Check.eq("findDish(\"Лимонад\")", findDish("Лимонад"), 11);
        Check.eq("findDish(\"Окрошка\")", findDish("Окрошка"), -1);
        Check.eq("findDish(new String(\"Капучино\"))", findDish(new String("Капучино")), 15);
        Check.eq("cheapestInRestaurant(0)", cheapestInRestaurant(0), 3);
        Check.eq("cheapestInRestaurant(1)", cheapestInRestaurant(1), 7);
        Check.eq("cheapestInRestaurant(9)", cheapestInRestaurant(9), -1);
        Check.eq("countInPriceRange(20000, 35000)", countInPriceRange(20000, 35000), 8);
        Check.eq("formatPrice(35050)", formatPrice(35050), "350,50 ₽");
        Check.eq("formatPrice(9000)", formatPrice(9000), "90,00 ₽");
        Check.eq("formatPrice(5)", formatPrice(5), "0,05 ₽");

        Check.eq("sortedByPrice(0)", sortedByPrice(0), new int[]{3, 1, 2, 0});
        Check.eq("sortedByPrice(4)", sortedByPrice(4), new int[]{18, 16, 15, 17});

        System.out.println("--- Интеграция ---");
        Check.eq("findOpenDish(\"Лимонад\", 1260)", findOpenDish("Лимонад", 1260), 11);
        Check.eq("findOpenDish(\"Лимонад\", 540)", findOpenDish("Лимонад", 540), 18);
        Check.eq("findOpenDish(\"Тирамису\", 180)", findOpenDish("Тирамису", 180), -1);
    }
}
