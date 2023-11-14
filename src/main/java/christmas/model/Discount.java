package christmas.model;

import christmas.constants.Constants;
import christmas.constants.Menu;
import christmas.constants.Sort;

import java.util.List;

public class Discount {
    private Integer dDayDiscount;
    private boolean weekEnds;
    private Integer weekDiscount;
    private boolean specialDay;
    private Integer specialDiscount;
    
    public Discount() {
        this.weekEnds = false;
        this.specialDay = false;
        this.dDayDiscount = null;
        this.weekDiscount = null;
        this.specialDiscount = null;
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

    public int makeTotalDiscount(Integer date, List<Menu> menus) {
        
        decideStatus(date);
        
        dDayDiscount(date);
        weekDiscount(menus);
        specialDiscount();
        
        return dDayDiscount + weekDiscount + specialDiscount;
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
    
    private void weekDiscount(List<Menu> menus) {
        if(this.weekEnds) {
            weekEndDiscount(menus);
            return;
        }
        weekDayDiscount(menus);
    }
    private void weekDayDiscount(List<Menu> menus) {
        this.weekDiscount = 0;
        menus.forEach(menu -> {
            if(menu.getSort() == Sort.DESSERT) {
                this.weekDiscount += Constants.WEEKLY_DISCOUNT;
            }
        });
    }
    private void weekEndDiscount(List<Menu> menus) {
        this.weekDiscount = 0;
        menus.forEach(menu -> {
            if(menu.getSort() == Sort.MAINDISH) {
                this.weekDiscount += Constants.WEEKLY_DISCOUNT;
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
