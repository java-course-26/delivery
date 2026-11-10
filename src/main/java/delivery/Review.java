package src.main.java.delivery;

public class Review {
    int restaurantId;
    int stars;


    public Review(int restaurantId,int stars) {
        this.restaurantId = restaurantId;
        this.stars = stars;
    }


    public boolean isPositive() {
        return stars>3;
        
        
    }

}
