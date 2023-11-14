package christmas.entity;

import christmas.constant.Menu;

public class Order {
    private final Menu menu;
    private final Integer number;
    
    public Order(Menu menu, Integer number) {
        this.number = number;
        this.menu = menu;
    }

    public Menu getMenu() {
        return menu;
    }

    public Integer getNumber() {
        return number;
    }
}
