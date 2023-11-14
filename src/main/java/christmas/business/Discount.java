package christmas.business;

import christmas.constant.Constants;
import christmas.constant.Sort;
import christmas.entity.Order;

import java.util.List;

public class Discount {
    private Integer dDayDiscount;
    private boolean weekEnds;
    private Integer weekDiscount;
    private boolean specialDay;
    private Integer specialDiscount;
    private Integer totalDiscount;
    
    public Discount() {
        this.weekEnds = false;
        this.specialDay = false;
        this.dDayDiscount = null;
        this.weekDiscount = null;
        this.specialDiscount = null;
        this.totalDiscount = null;
    }

    public boolean isItWeekEnds() {
        return weekEnds;
    }

    public boolean isItSpecialDay() {
        return specialDay;
    }

    public Integer getdDayDiscount() {
        return dDayDiscount;
    }

    public Integer getWeekDiscount() {
        return weekDiscount;
    }

    public Integer getSpecialDiscount() {
        return specialDiscount;
    }
    
    public Integer getTotalDiscount() { return totalDiscount; }

    public void makeTotalDiscount(Integer date, List<Order> orders) {
        
        decideStatus(date);
        
        dDayDiscount(date);
        weekDiscount(orders);
        specialDiscount();
        
        this.totalDiscount = dDayDiscount + weekDiscount + specialDiscount;
    }

    private void decideStatus(Integer date) {
        isItWeekday(date);
        isItSpecialDay(date);
    }
    private void isItWeekday(Integer date) {
        int rest = date % 7;
        if(rest == 1 || rest == 2)  {
            this.weekEnds = true;
        }
    }
    private void isItSpecialDay(Integer date) {
        int rest = date % 7;
        if(rest == 3 || date == 25) {
            this.specialDay = true;
        }
    }
    
    private void dDayDiscount(Integer date) {
        if(date > Constants.CHRISTMAS) {
            this.dDayDiscount = 0;
            return;
        }
        this.dDayDiscount = Constants.DDAY_BASICDISCOUNT 
                + (date - 1) * Constants.DDAY_DISCOUNT;
    }
    
    private void weekDiscount(List<Order> orders) {
        if(this.weekEnds) {
            weekEndDiscount(orders);
            return;
        }
        weekDayDiscount(orders);
    }
    private void weekDayDiscount(List<Order> orders) {
        this.weekDiscount = 0;
        orders.forEach(order -> {
            if(order.getMenu().getSort() == Sort.DESSERT) {
                this.weekDiscount += Constants.WEEKLY_DISCOUNT * order.getNumber();
            }
        });
    }
    private void weekEndDiscount(List<Order> orders) {
        this.weekDiscount = 0;
        orders.forEach(order -> {
            if(order.getMenu().getSort() == Sort.MAINDISH) {
                this.weekDiscount += Constants.WEEKLY_DISCOUNT * order.getNumber();
            }
        });
    }
    
    private void specialDiscount() {
        if(this.specialDay) {
            this.specialDiscount = Constants.SPECIAL_DISCOUNT;
            return;
        }
        this.specialDiscount = 0;
    }
}
