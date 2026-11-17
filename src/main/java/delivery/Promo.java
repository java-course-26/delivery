package src.main.java.delivery;

/**
 * Модуль 3. ПРОМОКОДЫ И СКИДКИ.
 * Данные — DeliveryData.promoCodes, promoType, promoValue, promoMinTotal.
 */
public class Promo {

    public static boolean isValidFormat(String code) {
        if (code == null) {
            return false;
        }
        return code.matches("^(?=.*[A-Z])(?=.*[0-9])[A-Z0-9]{5,12}$");
    }

    public static int findPromo(String code) {
        if (code == null) {
            return -1;
        }
        for (int i = 0; i < DeliveryData.promoCodes.length; i++) {
            if (DeliveryData.promoCodes[i].equals(code)) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Скидка в копейках по промокоду promoIndex для заказа на сумму total.
     * Если total меньше минимальной суммы промокода — 0.
     * Скидка не может быть больше суммы заказа.
     */
    public static int discountFor(int promoIndex, int total) {
        if (promoIndex < 0 || promoIndex >= DeliveryData.promoCodes.length || total <= 0) {
            return 0;
        }

        if (total < DeliveryData.promoMinTotal[promoIndex]) {
            return 0;
        }

        int discount = 0;
        if (DeliveryData.promoType[promoIndex] == 0) {
            // Процентная скидка (promoValue[promoIndex] в %)
            discount = (int) ((long) total * DeliveryData.promoValue[promoIndex] / 100);
        } else if (DeliveryData.promoType[promoIndex] == 1) {
            // Фиксированная скидка в копейках
            discount = DeliveryData.promoValue[promoIndex];
        }

        // Скидка не может превышать сумму заказа
        return Math.min(discount, total);
    }

    /**
     * Индекс самого выгодного промокода для суммы total.
     */
    public static int bestPromo(int total) {
        int bestIndex = -1;
        int maxDiscount = 0;

        for (int i = 0; i < DeliveryData.promoCodes.length; i++) {
            int currentDiscount = discountFor(i, total);
            if (currentDiscount > maxDiscount) {
                maxDiscount = currentDiscount;
                bestIndex = i;
            }
        }
        return bestIndex;
    }

    /**
    public static int finalPrice(int[] cart, String code) {
        int total = 0;
        if (cart != null) {
            try {
                // Если класс Cart доступен в проекте, вызываем Cart.cartTotal(cart)
                total = Cart.cartTotal(cart);
            } catch (NoClassDefFoundError | NoSuchMethodError e) {
                // Запасной расчет напрямую из DeliveryData.dishPrices, если Cart еще не скомпилирован
                for (int dishIndex : cart) {
                    if (dishIndex >= 0 && dishIndex < DeliveryData.dishPrices.length) {
                        total += DeliveryData.dishPrices[dishIndex];
                    }
                }
            }
        }

        if (!isValidFormat(code)) {
            return total;
        }

        int promoIndex = findPromo(code);
        if (promoIndex == -1) {
            return total;
        }

        int discount = discountFor(promoIndex, total);
        return total - discount;
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
