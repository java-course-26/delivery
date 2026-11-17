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
    return price < other.price;
    
}
public boolean isDrink(){
    if(category == 3){
        return true;
    }
    return false;
}
public void discount(int percent){
    price -= price * (percent / 100.0);
}
}