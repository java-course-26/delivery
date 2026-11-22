package src.main.java.delivery;

public class Courier {
    public String name;
    public int x;
    public int y;
    public boolean busy;
    public int currentOrders;
    public Courier(String name, int x, int y, boolean busy, int currentOrders){
        this.name = name;
        this.x = x;
        this.y = y;
        this.busy = busy;
        this.currentOrders = currentOrders;
    }

    public int earnings() {
        int bonus = currentOrders >= 10?500:0;
        int money = currentOrders * 150 + bonus;
        return money*100;
    }

    public void takeOrder(){
        busy = true;
        currentOrders++;
    }

    public boolean busierThan(Courier other){
        if (currentOrders > other.currentOrders){return true;}
        return false;
    }
}
