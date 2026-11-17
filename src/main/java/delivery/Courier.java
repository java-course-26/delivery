package src.main.java.delivery;

public class Courier {
    public String name;
    public int X;
    public int Y;
    public boolean busy;
    public int currentOrders;
    public Courier(String name, int X,int Y,boolean busy,int currentOrders){
        this.name = name;
        this.X = X;
        this.Y=Y;
        this.busy = busy;
        this.currentOrders = currentOrders;
    }
    public int earnings(int courierIndex) {
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
