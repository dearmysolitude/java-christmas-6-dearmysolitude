package christmas;

import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private final List<Menu> menuOrders;
    private Integer date;
    private Integer totalPrice;
    private Integer discountPrice;
    
    public EventPlanner() {
        this.menuOrders = new ArrayList<>();
        this.date = null;
    }
}
