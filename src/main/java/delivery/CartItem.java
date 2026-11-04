package src.main.java.delivery;

public class CartItem {
    int dishIndex;
    int quantity;

    public CartItem(int dishIndex, int quantity){
        this.dishIndex = dishIndex;
        this.quantity = quantity;
    }
    public int total(){
        return DeliveryData.dishPrices[dishIndex] * quantity;
    }
}
