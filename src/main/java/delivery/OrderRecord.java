package src.main.java.delivery;

public class OrderRecord {
    int hour;
    int total;
    int clientId;
    int restaurantId;
    public OrderRecord(int hour,int total, int clientId,int restaurantId){
        this.hour=hour;
        this.total= total;
        this.clientId= clientId;
        this.restaurantId=restaurantId;
    }
    public boolean isEvening(){
        return (18 <= hour && hour<= 22);
    }
}
