package christmas;

import christmas.Constants.Constants;
import christmas.Enum.Badge;
import christmas.Enum.Menu;

import java.util.List;

public class EventPlanner {
    private List<Menu> orders;
    private Integer date;
    private Integer totalPrice;
    private final Discount discount;
    private Menu gift;
    private Badge badge;
    private Integer totalAdvantage;
    
    public EventPlanner() {
        this.orders = null;
        this.date = null;
        this.totalPrice = null;
        this.discount = new Discount();
        this.totalAdvantage = null;
        this.gift = null;
        this.badge = null;
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

    public boolean isGift() {
        return gift;
    }

    public Badge getBadge() {
        return badge;
    }

    public Integer getTotalAdvantage() {
        return totalAdvantage;
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
        checkGift();
    }
    
    public void makeTotalAdvantage() {
        this.totalAdvantage = this.discount.makeTotalDiscount(this.date, this.orders);
        if(this.gift != null){
            this.totalAdvantage += gift.getPrice();
        }
    }
    
    private void checkGift() {
        if(this.totalPrice > Constants.GIFTPRICE) {
            this.gift = Menu.CHAMPAGNE;
        }
    }
    
    public void setBadge() {
        this.badge = Badge.badgeOf(totalAdvantage);
    }
    
    public Integer makeActualCost() {
        return this.totalPrice - this.totalAdvantage;
    }
}
