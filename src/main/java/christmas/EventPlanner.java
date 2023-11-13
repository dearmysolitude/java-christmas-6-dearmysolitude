package christmas;

import java.util.ArrayList;
import java.util.List;

public class EventPlanner {
    private List<Menu> orders;
    private Integer date;
    private Integer totalPrice;
    private Discount discount;
    
    public EventPlanner() {
        this.orders = null;
        this.date = null;
        this.totalPrice = null;
        this.discount = new Discount();
    }

    public List<Menu> getMenuOrders() {
        return orders;
    }

    public Integer getDate() {
        return date;
    }

    public Integer getTotalPrice() {
        return totalPrice;
    }
    
    public EventPlanner setMenuOrders(List<Menu> menuOrders) {
        this.orders = menuOrders;
        return this;
    }

    public EventPlanner setDate(Integer date) {
        this.date = date;
        return this;
    }

    public EventPlanner setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
        return this;
    }
    
    public void makeTotalprice() {
        this.totalPrice = this.orders.stream()
                .mapToInt(Menu::getPrice)
                .sum();
    }
    
    public void discountOrNot() {
        this.discount.decideStatus(this.date);
    }
    
}
