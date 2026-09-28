package src.main.java.delivery;

/**
 * Модуль 2. КОРЗИНА.
 * Корзина (int[] cart) — массив индексов блюд: {0, 0, 3} значит «две порции пельменей и морс».
 */
public class Cart {

    /** Сумма корзины в копейках. Пустая корзина — 0. */
    public static int cartTotal(int[] cart) {
        // TODO
        return 0;
    }

    /** Сколько раз блюдо dishIndex лежит в корзине. */
    public static int countOf(int[] cart, int dishIndex) {
        // TODO
        return 0;
    }

    /** Сколько разных блюд в корзине: {0, 0, 3, 3, 2} → 3. */
    public static int distinctCount(int[] cart) {
        // TODO
        return 0;
    }

    /**
     * Набрана ли минимальная сумма заказа для ресторана.
     * Считаем, что все блюда из одного ресторана — берём ресторан первого блюда.
     */
    public static boolean isMinOrderReached(int[] cart) {
        // TODO
        return false;
    }

    /** Все ли блюда из одного ресторана. */
    public static boolean isSingleRestaurant(int[] cart) {
        // TODO
        return false;
    }

    /** Индекс самого дорогого блюда в корзине (именно индекс блюда, не позиция в корзине). */
    public static int mostExpensive(int[] cart) {
        // TODO
        return -1;
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
