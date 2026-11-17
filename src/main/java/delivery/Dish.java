package src.main.java.delivery;




public class Dish {
    String name;
    int price;
    int restaurantId;
    int category;
    int cookMinutes;

public Dish(String name, int price, int restaurantId, int category, int cookMinutes){
    this.name = name;
    this.price = price;
    this.restaurantId = restaurantId;
    this.category = category;
    this.cookMinutes = cookMinutes;
}

    
public boolean isCheaperThan(Dish other){
    if(price < other.price ){
        return true;
    }
    return false;
    
}
}