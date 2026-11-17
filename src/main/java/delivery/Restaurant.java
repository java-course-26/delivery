package src.main.java.delivery;

public class Restaurant {
    String name;
    int closeTime;
    int openTime;

    public Restaurant(String name,int openTime,int closeTime){
        this.name=name;
        this.openTime=openTime;
        this.closeTime = closeTime;
    }

    static public Restaurant[] loadAll(){
        Restaurant[] rests = new Restaurant[DeliveryData.restaurantNames.length];
        for (int i=0;i<DeliveryData.restaurantNames.length;i++) {
            rests[i] = new Restaurant(DeliveryData.restaurantNames[i], DeliveryData.restaurantOpen[i], DeliveryData.restaurantClose[i]);
        }
        return rests;
    }

    public String getName(){
        return name;
    }
    boolean isOpen(int now){
        if (closeTime < openTime){
            closeTime = 1440 + closeTime;
        }
        if ((openTime < now) && (closeTime > now))
        {
            return true;
        }
        return false;
    }

}
