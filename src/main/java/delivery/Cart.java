package src.main.java.delivery;

/**
 * Модуль 2. КОРЗИНА.
 * Корзина (int[] cart) — массив индексов блюд: {0, 0, 3} значит «две порции пельменей и морс».
 */
public class Cart {
    /** Сумма корзины в копейках. Пустая корзина — 0. */
    public static int cartTotal(int[] cart) {
        int summa = 0;
        for(int dishIndex : cart){
            summa += DeliveryData.dishPrices[dishIndex];
        }
        return summa;
    }

    public static int cartTotal(CartItem[] items) {
        int sum = 0;
        for (int i = 0; i < items.length; i++) {
            sum += items[i].total();
        }
        return sum;
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
    public static int distinctCountFast(int[] cart) {
        if (cart.length == 0) {
            return 0;
        }
        int maxDishIndex = 0;
        for (int dishIndex : cart) {
            if (dishIndex > maxDishIndex) {
                maxDishIndex = dishIndex;
            }
        }
        boolean[] visited = new boolean[maxDishIndex + 1];
        int uniqueCount = 0;
        for (int dishIndex : cart) {
            if (!visited[dishIndex]) {
                visited[dishIndex] = true;
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
        if (cart == null || cart.length == 0) {
            return false;
        }
        int firstDish = cart[0];
        int restaurantIndex = DeliveryData.dishRestaurant[firstDish];
        int minPrice = DeliveryData.restaurantMinOrder[restaurantIndex];
        int total = cartTotal(cart);
        return total >= minPrice;
    }

    /** Все ли блюда из одного ресторана. */
    public static boolean isSingleRestaurant(int[] cart) {
        if (cart == null || cart.length <= 1) {
            return true;
        }
        int firstDish = cart[0];
        int firstRestaurant = DeliveryData.dishRestaurant[firstDish];
        for (int i = 1; i < cart.length; i++) {
            int currentDish = cart[i];
            int currentRestaurant = DeliveryData.dishRestaurant[currentDish];
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
        int totalSum = 0;
        for (String name : names) {
            int dishIndex = Menu.findDish(name);
            if (dishIndex != -1) {
                totalSum += DeliveryData.dishPrices[dishIndex];
            }
        }
        return totalSum;
    }
    public static CartItem[] toItems(int[] cart){
        CartItem[] items = new CartItem[cart.length];
        int size = 0;
        for (int dishIndex : cart) {
            int found = -1;
            for (int j = 0; j < size; j++) {
                if (items[j].dishIndex == dishIndex) {
                    found = j;
                    break;
                }
            }
            if (found != -1) {
                items[found].add(1);
            }
            else {
                items[size++] = new CartItem(dishIndex, 1);
            }
        }
        CartItem[] result = new CartItem[size];
        for (int i = 0; i < size; i++){
            result[i] = items[i];
        }
        return result;
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

        System.out.println("--- Крайние случаи ---");
        Check.eq("isMinOrderReached({16})", isMinOrderReached(new int[]{16}), false);
        Check.eq("isSingleRestaurant({})", isSingleRestaurant(new int[]{}), true);
        Check.eq("isSingleRestaurant({0, 1, 2, 4})", isSingleRestaurant(new int[]{0, 1, 2, 4}), false);
        Check.eq("mostExpensive({})", mostExpensive(new int[]{}), -1);
        Check.eq("mostExpensive({2, 13})", mostExpensive(new int[]{2, 13}), 2);
        Check.eq("cartTotal({18, 18})", cartTotal(new int[]{18, 18}), 32000);

        System.out.println("--- пара 5 ---");
        Check.eq("distinctCountFast({0,0,3,3,2})", distinctCountFast(new int[]{0, 0, 3, 3, 2}), 3);
        Check.eq("distinctCountFast({})", distinctCountFast(new int[]{}), 0);
        Check.eq("distinctCountFast({7})", distinctCountFast(new int[]{7}), 1);

        System.out.println("--- Тестирование быстрой версии ---");
        int seed;
        for (seed = 0; seed < 100; seed++) {
            int[] data = BigData.randomArray(1000, 0, 1000, seed);
            int slowResult = distinctCount(data);
            int fastResult = distinctCountFast(data);
            if (slowResult != fastResult) {
                System.out.println("FAIL! seed = " + seed);
                break;
            }
        }
        if (seed == 100) {
            System.out.println("OK!");
        }
    System.out.println("---Классы---");
    CartItem a = new CartItem(0,2);
    Check.eq("new CartItem(0,2).total()", a.total(),68000);
    Check.eq("new CartItem(3,1).total()", new CartItem(3,1).total(),9000);
    Check.eq("new CartItem(5, 0).total()", new CartItem(5,0).total(),0);

    System.out.println("---Методы класса---");
    CartItem c = new CartItem(3,1);
    Check.eq("c.name()", c.name(),"Морс клюквенный");
    c.add(2);
    Check.eq("после c.add(2):c.quantity", c.quantity,3);
    Check.eq("после c.add(2):c.total", c.total(),27000);

    System.out.println("--- Функции ---");
    CartItem[] items = toItems(new int[]{0, 0, 3});
    Check.eq("toItems({0, 0, 3}).length", items.length, 2);
    Check.eq("items[0].dishIndex", items[0].dishIndex, 0);
    Check.eq("items[0].quantity", items[0].quantity, 2);
    Check.eq("items[1].dishIndex", items[1].dishIndex, 3);
    }
}