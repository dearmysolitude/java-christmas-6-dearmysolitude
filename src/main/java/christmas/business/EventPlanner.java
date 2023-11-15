package christmas.business;

import christmas.constant.Badge;
import christmas.constant.Constants;
import christmas.constant.Menu;
import christmas.constant.Sort;
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
        this.totalPrice = 0;
        this.discount = new Discount();
        this.gift = null;
        this.totalAdvantage = 0;
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
    
    public Discount getDiscount() { return discount; }

    public void setMenuOrders(List<Order> menuOrders) {
        this.orders = menuOrders;
        checkMenuOrders();
        ifOrderedOverTwenty();
        ifOrderedOnlyDrink();
    }

    public void setDate(Integer date) {
        this.date = date;
    }
    
    public void makeTotalprice() {
        this.totalPrice = this.orders
                .stream()
                .mapToInt(order -> order.getMenu().getPrice() * order.getNumber())
                .sum();
    }
    
    public void makeTotalAdvantage() {
        checkGift();
        this.discount.makeTotalDiscount(this.date,this.orders);
        if(this.gift != null){
            this.totalAdvantage = this.discount.getTotalDiscount() + gift.getPrice();
            return;
        }
        this.totalAdvantage = this.discount.getTotalDiscount();
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
        return this.totalPrice - this.discount.getTotalDiscount();
    }
    
    private void checkMenuOrders() {
        if(this.orders == null || checkDuplicatedMenu() || checkOrderNumber()) {
            this.orders = null;
            throw new IllegalArgumentException("유효하지 않은 주문입니다. 다시 입력해 주세요.");
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
    private void ifOrderedOverTwenty(){
        int number = 0;
        for(Order order : this.orders) {
            number += order.getNumber();
        }
        if(number > 20) {
            this.orders = null;
            throw new IllegalArgumentException("한 번에 20개 품목 이하의 주문을 할 수 있습니다. 다시 입력해 주세요.");
        }
    }
    private void ifOrderedOnlyDrink() {
        int number = 0;
        for(Order order : this.orders) {
            if(order.getMenu().getSort() == Sort.DRINK) {
                number += 1;
            }
        }
        if(number == this.orders.size()) {
            this.orders = null;
            throw new IllegalArgumentException("음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        }
    }
}
