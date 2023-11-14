package christmas.entity;

import christmas.constants.Menu;

public class Order {
    private Menu menu;
    private Integer number;
    
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
