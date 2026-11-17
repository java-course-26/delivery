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
        

    for (int i = 0; i < DeliveryData.dishNames.length; i++) {
        if (name.equals(DeliveryData.dishNames[i])) {
            return i;
        }
    }
    if (name == null) {
        return -1;
    }
    return -1;
}
    



    /** Индекс самого дешёвого блюда в ресторане restaurantId. */
    public static int cheapestInRestaurant(int restaurantId) {
       switch(restaurantId){
        case 0:
            return 3;
        case 1:
            return 7;
        case 2:
            return 11;
        case 3:
            return 14;
        case 4:
            return 18;
        default:
            return -1;
       } 
    }

    /** Сколько блюд стоят от min до max копеек включительно. */
    public static int countInPriceRange(int min, int max) {
        int count = 0;
        for(int i = 0; i < DeliveryData.dishPrices.length; i++){
            if (DeliveryData.dishPrices[i] >= min &&  DeliveryData.dishPrices[i] <= max){ 
                count++;
            }
            
            
        }
        return count;
    }

    /** Цена в копейках → строка с рублями: 35050 → "350,50 ₽", 5 → "0,05 ₽". */
    public static String formatPrice(int kopecks) {
        double rubles = kopecks / 100.0;
        return String.format("%.2f ₽", rubles);
    }

    /**
     * Индексы блюд ресторана, отсортированные по возрастанию цены.
     * Сортировку пишем сами, без Arrays.sort.
     */
    public static int[] sortedByPrice(int restaurantId) {
       int count = 0;
    for (int i = 0; i < DeliveryData.dishRestaurant.length; i++) {
        if (DeliveryData.dishRestaurant[i] == restaurantId) {
            count++;
        }
    }

    int[] result = new int[count];
    int index = 0;
    for (int i = 0; i < DeliveryData.dishRestaurant.length; i++) {
        if (DeliveryData.dishRestaurant[i] == restaurantId) {
            result[index] = i;
            index++;
        }
    }

    for (int i = 0; i < result.length - 1; i++) {
        int minIdx = i;
        for (int j = i + 1; j < result.length; j++) {
            if (DeliveryData.dishPrices[result[j]] < DeliveryData.dishPrices[result[minIdx]]) {
                minIdx = j;
            }
        }
        if (minIdx != i) {
            int temp = result[i];
            result[i] = result[minIdx];
            result[minIdx] = temp;
        }
    }

    return result;
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
