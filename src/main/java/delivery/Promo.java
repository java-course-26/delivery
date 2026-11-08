package src.main.java.delivery;

/**
 * Модуль 3. ПРОМОКОДЫ И СКИДКИ.
 * Данные — DeliveryData.promoCodes, promoType, promoValue, promoMinTotal.
 */
public class Promo {

    /**
     * Правильный ли формат промокода:
     * длина от 5 до 12 символов, только заглавные латинские буквы A-Z и цифры 0-9,
     * есть хотя бы одна буква и хотя бы одна цифра.
     * "WELCOME10" → true, "welcome10" → false, "BIG-20" → false, "12345" → false.
     */
    public static boolean isValidFormat(String code) {
        // TODO
        return false;
    }

    /** Индекс промокода в DeliveryData.promoCodes или -1. */
    public static int findPromo(String code) {
        // TODO
        return -1;
    }

    /**
     * Скидка в копейках по промокоду promoIndex для заказа на сумму total.
     * Если total меньше минимальной суммы промокода — 0.
     * Скидка не может быть больше суммы заказа.
     */
    public static int discountFor(int promoIndex, int total) {
        // TODO
        return 0;
    }

    /** Индекс самого выгодного промокода для суммы total. */
    public static int bestPromo(int total) {
        // TODO
        return -1;
    }

    /**
     * Интеграция с другим модулем (Cart).
     * Итоговая цена корзины с промокодом. Сумму считай через Cart.cartTotal(...).
     * Если код неправильного формата или не найден — цена без скидки.
     */
    public static int finalPrice(int[] cart, String code) {
        // TODO
        return 0;
    }

    public static void main(String[] args) {
        Check.eq("isValidFormat(\"WELCOME10\")", isValidFormat("WELCOME10"), true);
        Check.eq("isValidFormat(\"welcome10\")", isValidFormat("welcome10"), false);
        Check.eq("isValidFormat(\"SALE\")", isValidFormat("SALE"), false);
        Check.eq("isValidFormat(\"BIG-20\")", isValidFormat("BIG-20"), false);
        Check.eq("isValidFormat(\"12345\")", isValidFormat("12345"), false);
        Check.eq("isValidFormat(\"ABCDEFGHIJKL1\")", isValidFormat("ABCDEFGHIJKL1"), false);
        Check.eq("findPromo(\"BIG20\")", findPromo("BIG20"), 2);
        Check.eq("findPromo(new String(\"PIZZA300\"))", findPromo(new String("PIZZA300")), 1);
        Check.eq("findPromo(\"NOPE1\")", findPromo("NOPE1"), -1);
        Check.eq("discountFor(0, 50000)", discountFor(0, 50000), 5000);
        Check.eq("discountFor(1, 100000)", discountFor(1, 100000), 0);
        Check.eq("discountFor(1, 160000)", discountFor(1, 160000), 30000);
        Check.eq("discountFor(3, 30000)", discountFor(3, 30000), 5000);
        Check.eq("discountFor(3, 29999)", discountFor(3, 29999), 0);

        Check.eq("bestPromo(200000)", bestPromo(200000), 1);
        Check.eq("bestPromo(400000)", bestPromo(400000), 2);
        Check.eq("bestPromo(10000)", bestPromo(10000), 0);
        Check.eq("bestPromo(0)", bestPromo(0), -1);

        System.out.println("--- Интеграция ---");
        Check.eq("finalPrice({9, 9, 8}, \"PIZZA300\")", finalPrice(new int[]{9, 9, 8}, "PIZZA300"), 149000);
        Check.eq("finalPrice({0}, \"bad!\")", finalPrice(new int[]{0}, "bad!"), 34000);
    }
}
