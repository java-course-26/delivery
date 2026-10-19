package src.main.java.delivery;

/**
 * Модуль 2. КОРЗИНА.
 * Корзина (int[] cart) — массив индексов блюд: {0, 0, 3} значит «две порции пельменей и морс».
 */
public class Cart {

    /** Сумма корзины в копейках. Пустая корзина — 0. */
    public static int cartTotal(int[] cart) {
        int summa = 0;
        for(int i = 0; i < cart.length; i++){
            int dishIndex = cart[i];
            summa += DeliveryData.dishPrices[dishIndex];
        }
        return summa;
    }

    /** Сколько раз блюдо dishIndex лежит в корзине. */
    public static int countOf(int[] cart, int dishIndex) {
        int count = 0;
        for (int i = 0; i < cart.length; i++) {
            if (cart[i] == dishIndex) {
                count++;
            }
        }
        return count;
    }

    /** Сколько разных блюд в корзине: {0, 0, 3, 3, 2} → 3. */
    public static int distinctCount(int[] cart) {
        int uniqueCount = 0;
        for (int i = 0; i < cart.length; i++) {
            boolean alreadySeen = false;
            for (int j = 0; j < i; j++) {
                if (cart[i] == cart[j]) {
                    alreadySeen = true;
                    break;
                }
            }
            if (!alreadySeen) {
                uniqueCount++;
            }
        }
        return uniqueCount;
    }

    /**
     * Набрана ли минимальная сумма заказа для ресторана.
     * Считаем, что все блюда из одного ресторана — берём ресторан первого блюда.
     */
    public static boolean isMinOrderReached(int[] cart) {
        if (cart.length == 0) {
            return false;
        }
        int firstDish = cart[0];
        int restaurantIndex = DeliveryData.dishRestaurantIndexes[firstDish];
        int minPrice = DeliveryData.restaurantMinPrices[restaurantIndex];
        int total = cartTotal(cart);
        return total >= minPrice;
    }

    /** Все ли блюда из одного ресторана. */
    public static boolean isSingleRestaurant(int[] cart) {

        if (cart.length <= 1) {
            return true;
        }
        int firstDish = cart[0];
        int firstRestaurant = DeliveryData.dishRestaurantIndexes[firstDish];
        for (int i = 1; i < cart.length; i++) {
            int currentDish = cart[i];
            int currentRestaurant = DeliveryData.dishRestaurantIndexes[currentDish];
            if (currentRestaurant != firstRestaurant) {
                return false;
            }
        }
        return true;
    }

    /** Индекс самого дорогого блюда в корзине (именно индекс блюда, не позиция в корзине). */
    public static int mostExpensive(int[] cart) {
        if (cart.length == 0) {
            return -1;
        }
        int bestDish = cart[0];
        for (int i = 1; i < cart.length; i++) {
            int currentDish = cart[i];
            if (DeliveryData.dishPrices[currentDish] > DeliveryData.dishPrices[bestDish]) {
                bestDish = currentDish;
            }
        }
        return bestDish;
    }

    /**
     * Интеграция с другим модулем (Menu).
     * Сумма корзины, заданной названиями блюд. Неизвестные названия пропускаем.
     * Используй Menu.findDish(...) из модуля напарника.
     */
    public static int cartTotalByNames(String[] names) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Check.eq("cartTotal({0, 0, 3})", cartTotal(new int[]{0, 0, 3}), 77000);
        Check.eq("cartTotal({})", cartTotal(new int[]{}), 0);
        Check.eq("countOf({0, 0, 3}, 0)", countOf(new int[]{0, 0, 3}, 0), 2);
        Check.eq("countOf({0, 0, 3}, 5)", countOf(new int[]{0, 0, 3}, 5), 0);
        Check.eq("distinctCount({0, 0, 3, 3, 2})", distinctCount(new int[]{0, 0, 3, 3, 2}), 3);
        Check.eq("distinctCount({})", distinctCount(new int[]{}), 0);
        Check.eq("isMinOrderReached({0, 3})", isMinOrderReached(new int[]{0, 3}), false);
        Check.eq("isMinOrderReached({0, 0})", isMinOrderReached(new int[]{0, 0}), true);
        Check.eq("isMinOrderReached({})", isMinOrderReached(new int[]{}), false);

        Check.eq("isSingleRestaurant({0, 3, 2})", isSingleRestaurant(new int[]{0, 3, 2}), true);
        Check.eq("isSingleRestaurant({0, 4})", isSingleRestaurant(new int[]{0, 4}), false);
        Check.eq("mostExpensive({3, 9, 0})", mostExpensive(new int[]{3, 9, 0}), 9);

        System.out.println("--- Интеграция ---");
        Check.eq("cartTotalByNames({Борщ, Морс клюквенный, Окрошка})",
                cartTotalByNames(new String[]{"Борщ", "Морс клюквенный", "Окрошка"}), 40000);
    }
}
