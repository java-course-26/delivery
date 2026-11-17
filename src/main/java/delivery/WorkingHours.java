package src.main.java.delivery;

public class WorkingHours {
    int open;
    int close;
    public WorkingHours(int open,int close) {
        this.open=open;
        this.close=close;
    }
    public boolean isOpen(int minute) {
        if (open <= close) {
            return minute >= open && minute < close;
        } else {
            return minute >= open || minute < close;
        }
    }
}