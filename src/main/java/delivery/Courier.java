package src.main.java.delivery;

public class Courier {
    String name;
    int x;
    int y;
    boolean busy;
    int ordersToday;
    public Courier(String name, int x,int y, boolean busy, int ordersToday){
        this.name = name;
        this.x = x;
        this.y = y;
        this.busy = busy;
        this.ordersToday = ordersToday;
    }
    public int earnings() {
        int bonus = ordersToday >= 10?500:0;
        int money = ordersToday * 150 + bonus;
        return money*100;
    }
}
