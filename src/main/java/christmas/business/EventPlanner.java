package christmas.business;

import christmas.constant.Badge;
import christmas.constant.Constants;
import christmas.constant.Menu;
import christmas.entity.Order;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EventPlanner {
    private List<Order> orders;
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

    public List<Order> getMenuOrders() {
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

    public void setMenuOrders(List<Order> menuOrders) {
        this.orders = menuOrders;
    }

    public void setDate(Integer date) {
        this.date = date;
    }
    
    public void makeTotalprice() {
        this.totalPrice = this.orders.stream()
                .mapToInt(order -> {
                    return order.getMenu().getPrice();
                })
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
    
    public void checkMenuOrders() {
        if(this.orders == null || checkDuplicatedMenu() || checkOrderNumber()) {
            System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            this.orders = null;
        }
    }
    private boolean checkDuplicatedMenu() {
        Map<Menu, List<Order>> groupOrders = orders.stream()
                .collect(Collectors.groupingBy(Order::getMenu));
        for(Map.Entry<Menu, List<Order>> entry : groupOrders.entrySet()) {
            if(entry.getValue().size() > 1) {
                return true;
            }
        }
        return false;
    }
    private boolean checkOrderNumber() {
        for(Order order : this.orders) {
            if(order.getNumber() < 1){
                return true;
            }
        }
        return false;
    }
}
