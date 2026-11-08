package src.main.java.delivery;

public class Point {
    int x;
    int y;
    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int distanceTo(Point other){
        return Delivery.distance(x, y, other.x, other.yы);
    }
}
