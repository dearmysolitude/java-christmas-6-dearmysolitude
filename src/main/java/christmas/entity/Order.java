package christmas.entity;

import christmas.constant.Menu;

public class Order {
    private final Menu menu;
    private final int number;
    
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
    
    public boolean equals(Order o) {
        return this.menu == o.menu && this.number == o.number;
    }
}
