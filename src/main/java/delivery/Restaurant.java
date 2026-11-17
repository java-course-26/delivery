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
    public String getName(){
        return name;
    }
    boolean isOpen(int now){
        if ((openTime < now) && (closeTime > now))
        {
            System.out.print("Открыт");
            return true;
        }
        System.out.print("Закрыт");
        return false;
    }

}
