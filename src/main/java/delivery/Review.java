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

    public boolean isNegative() {
        return stars<3;
        
        
    }
    public boolean sameRestaurant(Review other) {

        return (restaurantId == other.restaurantId);

    }

}
