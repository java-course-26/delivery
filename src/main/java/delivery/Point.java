package src.main.java.delivery;

public class Point {
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public boolean isInCity(){
        return Delivery.isInCity(x, y);
    }

    public boolean sameAs(Point other){
        if (x == other.x){
            if (y == other.y){
                return true;
            }
        }
        return false;
    }

    public int distanceTo(Point other){
        return Delivery.distance(x, y, other.x, other.y);
    }
}
