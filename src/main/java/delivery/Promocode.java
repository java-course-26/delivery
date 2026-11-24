package src.main.java.delivery;

public class Promocode {
    String code;
    int type;
    int value;
    int minTotal;

    public Promocode(String code, int type, int value, int minTotal) {
        this.code = code;
        this.type = type;
        this.value = value;
        this.minTotal = minTotal;
    }

    public int discountFor(int total) {
        if (total <= 0 || total < minTotal) {
            return 0;
        }
        int discount = 0;
        if (type == 0) {
            discount = (int) ((long) total * value / 100);
        } else {
            discount = value;
        }

        return Math.min(discount, total);
    }
}

