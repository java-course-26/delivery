package src.main.java.delivery;

import java.util.Arrays;

/**
 * Мини-проверки для модулей. НЕ МЕНЯТЬ.
 * Печатает OK, если результат совпал с ожидаемым, и FAIL с подробностями, если нет.
 */
public class Check {

    public static void eq(String what, int actual, int expected) {
        report(what, actual == expected, String.valueOf(expected), String.valueOf(actual));
    }

    public static void eq(String what, boolean actual, boolean expected) {
        report(what, actual == expected, String.valueOf(expected), String.valueOf(actual));
    }

    public static void eq(String what, double actual, double expected) {
        report(what, Math.abs(actual - expected) < 0.01, String.valueOf(expected), String.valueOf(actual));
    }

    public static void eq(String what, String actual, String expected) {
        boolean ok = actual != null && actual.equals(expected);
        report(what, ok, "\"" + expected + "\"", "\"" + actual + "\"");
    }

    public static void eq(String what, int[] actual, int[] expected) {
        report(what, Arrays.equals(actual, expected), Arrays.toString(expected), Arrays.toString(actual));
    }

    /**
     * Проверка, что действие выбрасывает исключение (понадобится с пары 8).
     * Пример: Check.fails("цена < 0", () -> new Dish("Борщ", -1, 0, 1, 10));
     */
    public static void fails(String what, Runnable action) {
        try {
            action.run();
            report(what, false, "исключение", "исключения не было");
        } catch (RuntimeException e) {
            report(what, true, "", "");
        }
    }

    private static void report(String what, boolean ok, String expected, String actual) {
        if (ok) {
            System.out.println("OK    " + what);
        } else {
            System.out.println("FAIL  " + what + "  ожидалось " + expected + ", получено " + actual);
        }
    }
}
