package src.main.java.delivery;

/**
 * Модуль 4. СТОИМОСТЬ ДОСТАВКИ.
 * Город — сетка клеток, расстояние считаем «по кварталам» (манхэттенское):
 * |x1 - x2| + |y1 - y2|. Одна клетка = 1 км.
 */
public class Delivery {

    /** Манхэттенское расстояние между точками. */
    public static int distance(int x1, int y1, int x2, int y2) {
        // TODO
        return 0;
    }

    /**
     * Стоимость доставки в копейках по расстоянию:
     * до 3 км включительно — 9900; дальше +2000 за каждый км сверх трёх;
     * больше 10 км — не доставляем, вернуть -1.
     */
    public static int deliveryCost(int distance) {
        // TODO
        return 0;
    }

    /**
     * Ночная надбавка: с 23:00 до 06:00 (minute >= 1380 или minute < 360) цена растёт на 30%.
     */
    public static int nightPrice(int cost, int minute) {
        // TODO
        return 0;
    }

    /** Лежит ли точка внутри карты города: 0..10 по обеим координатам. */
    public static boolean isInCity(int x, int y) {
        // TODO
        return false;
    }

    /**
     * Интеграция с другим модулем (Cart).
     * Полная стоимость доставки корзины из ресторана restaurantId в точку (x, y) в момент minute.
     * Если сумма корзины (Cart.cartTotal) от 1500 ₽ (150000 коп.) — доставка бесплатная (0).
     * Иначе: расстояние от ресторана → deliveryCost → nightPrice.
     */
    public static int deliveryCostForCart(int[] cart, int restaurantId, int x, int y, int minute) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Check.eq("distance(2, 3, 5, 7)", distance(2, 3, 5, 7), 7);
        Check.eq("distance(5, 7, 2, 3)", distance(5, 7, 2, 3), 7);
        Check.eq("distance(4, 4, 4, 4)", distance(4, 4, 4, 4), 0);
        Check.eq("deliveryCost(2)", deliveryCost(2), 9900);
        Check.eq("deliveryCost(3)", deliveryCost(3), 9900);
        Check.eq("deliveryCost(7)", deliveryCost(7), 17900);
        Check.eq("deliveryCost(11)", deliveryCost(11), -1);
        Check.eq("nightPrice(17900, 1400)", nightPrice(17900, 1400), 23270);
        Check.eq("nightPrice(9900, 720)", nightPrice(9900, 720), 9900);
        Check.eq("nightPrice(9900, 360)", nightPrice(9900, 360), 9900);
        Check.eq("nightPrice(-1, 1400)", nightPrice(-1, 1400), -1);

        Check.eq("isInCity(10, 0)", isInCity(10, 0), true);
        Check.eq("isInCity(11, 5)", isInCity(11, 5), false);
        Check.eq("isInCity(-1, 3)", isInCity(-1, 3), false);

        System.out.println("--- Интеграция ---");
        Check.eq("deliveryCostForCart({8}, 2, 1, 1, 1410)", deliveryCostForCart(new int[]{8}, 2, 1, 1, 1410), 28470);
        Check.eq("deliveryCostForCart({9, 9, 8}, 2, 1, 1, 1410)", deliveryCostForCart(new int[]{9, 9, 8}, 2, 1, 1, 1410), 0);
    }
}
