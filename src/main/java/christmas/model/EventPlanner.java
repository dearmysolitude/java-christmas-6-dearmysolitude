package christmas.model;

import christmas.constants.Constants;
import christmas.constants.Badge;
import christmas.constants.Menu;

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
        this.gift = null;
        this.totalAdvantage = null;
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

    public Menu getGift() {
        return gift;
    }

    public Badge getBadge() {
        return badge;
    }

    public Integer getTotalAdvantage() {
        return totalAdvantage;
    }

    public void setMenuOrders(List<Menu> menuOrders) {
        this.orders = menuOrders;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
    
    public void makeTotalprice() {
        this.totalPrice = this.orders.stream()
                .mapToInt(Menu::getPrice)
                .sum();
    }
    
    public void makeTotalAdvantage() {
        checkGift();
        if(this.gift != null){
            this.totalAdvantage = this.discount.makeTotalDiscount(this.date, this.orders) + gift.getPrice();
            return;
        }
        this.totalAdvantage = this.discount.makeTotalDiscount(this.date, this.orders);
    }
    
    private void checkGift() {
        if(this.totalPrice > Constants.GIFTPRICE) {
            this.gift = Menu.CHAMPAGNE;
        }
    }
    
    public void setBadge() {
        this.badge = Badge.badgeOf(this.totalAdvantage);
    }
    
    public Integer makeActualCost() {
        return this.totalPrice - this.totalAdvantage;
    }
}
