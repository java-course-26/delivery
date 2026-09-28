package src.main.java.delivery;

/**
 * Общие данные сервиса доставки. НЕ МЕНЯТЬ — этим файлом пользуются все модули.
 *
 * Данные лежат в «параллельных массивах»: i-й элемент каждого массива
 * относится к одному и тому же объекту. Например, блюдо номер 2 — это
 * dishNames[2], dishPrices[2], dishRestaurant[2] и так далее.
 *
 * Деньги — в копейках (int): 34000 = 340,00 ₽.
 * Время — в минутах от полуночи: 600 = 10:00, 1320 = 22:00.
 * Координаты — клетки на карте города 11×11, от (0, 0) до (10, 10).
 */
public class DeliveryData {

    // ---------- Рестораны ----------
    public static final String[] restaurantNames = {
        "Пельменная №1", "Суши Сити", "Пицца Паоло", "Шаурма 24", "Кофе и точка"
    };
    public static final int[] restaurantX = {2, 8, 5, 1, 6};
    public static final int[] restaurantY = {3, 1, 6, 8, 4};
    /** Время открытия и закрытия. Если open > close — ресторан работает через полночь. */
    public static final int[] restaurantOpen  = {600, 660, 600, 0,    480};
    public static final int[] restaurantClose = {1320, 1380, 120, 1440, 1200};
    /** Минимальная сумма заказа. */
    public static final int[] restaurantMinOrder = {50000, 80000, 60000, 30000, 20000};

    // ---------- Блюда ----------
    /** Категории: 0 — закуска, 1 — основное, 2 — десерт, 3 — напиток. */
    public static final String[] dishNames = {
        "Пельмени сибирские", "Вареники с картошкой", "Борщ", "Морс клюквенный",
        "Филадельфия", "Калифорния", "Мисо-суп", "Моти",
        "Маргарита", "Пепперони", "Тирамису", "Лимонад",
        "Шаурма классическая", "Шаурма сырная", "Картофель фри",
        "Капучино", "Круассан", "Сырники", "Лимонад"
    };
    public static final int[] dishPrices = {
        34000, 29000, 31000, 9000,
        59000, 49000, 22000, 18000,
        55000, 62000, 32000, 15000,
        27000, 31000, 14000,
        21000, 19000, 36000, 16000
    };
    public static final int[] dishRestaurant = {
        0, 0, 0, 0,
        1, 1, 1, 1,
        2, 2, 2, 2,
        3, 3, 3,
        4, 4, 4, 4
    };
    public static final int[] dishCategory = {
        1, 1, 1, 3,
        1, 1, 0, 2,
        1, 1, 2, 3,
        1, 1, 0,
        3, 2, 1, 3
    };
    /** Время приготовления в минутах. */
    public static final int[] dishCookMinutes = {
        15, 12, 10, 2,
        20, 18, 8, 5,
        17, 17, 5, 2,
        7, 8, 6,
        4, 3, 12, 2
    };

    // ---------- Курьеры ----------
    public static final String[] courierNames = {"Алексей", "Марина", "Тимур", "Ольга", "Денис", "Света"};
    public static final int[] courierX = {3, 7, 5, 1, 9, 4};
    public static final int[] courierY = {3, 2, 5, 7, 9, 1};
    public static final boolean[] courierBusy = {false, true, false, false, true, false};
    public static final int[] courierOrdersToday = {7, 12, 4, 9, 3, 11};

    // ---------- Отзывы ----------
    public static final int[] reviewRestaurant = {0, 1, 2, 0, 1, 4, 3, 0, 1, 2, 1, 0, 4, 2, 1, 3, 0, 1, 4, 2};
    public static final int[] reviewStars      = {5, 5, 4, 4, 5, 5, 2, 5, 4, 3, 1, 3, 4, 4, 5, 5, 5, 5, 4, 5};

    // ---------- Промокоды ----------
    public static final String[] promoCodes = {"WELCOME10", "PIZZA300", "BIG20", "COFFEE50"};
    /** Тип: 0 — скидка в процентах, 1 — фиксированная скидка в копейках. */
    public static final int[] promoType = {0, 1, 0, 1};
    public static final int[] promoValue = {10, 30000, 20, 5000};
    /** Минимальная сумма заказа, с которой действует промокод. */
    public static final int[] promoMinTotal = {0, 150000, 300000, 30000};

    // ---------- История заказов за день (для аналитики) ----------
    public static final int[] orderHour = {12, 12, 13, 13, 13, 14, 18, 19, 19, 19, 19, 20, 20, 21, 21, 22, 23, 0, 1, 9};
    public static final int[] orderTotal = {
        65000, 98000, 45000, 120000, 38000, 54000, 187000, 76000, 64000, 31000,
        142000, 58000, 27000, 99000, 44000, 71000, 62000, 35000, 124000, 42000
    };
    public static final int[] orderClient = {0, 1, 2, 0, 3, 4, 5, 1, 6, 7, 2, 8, 3, 9, 0, 10, 11, 7, 12, 13};
    public static final int[] orderRestaurant = {0, 1, 3, 2, 4, 0, 1, 2, 0, 3, 1, 2, 3, 2, 4, 0, 2, 3, 2, 4};
}
