package christmas.view;

import christmas.business.EventPlanner;
import christmas.entity.Order;

public class OutputView {
    EventPlanner eventPlanner;

    public OutputView(EventPlanner eventPlanner) {
        this.eventPlanner = eventPlanner;
    }
    public void printStart() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    public void printMenu() {
        System.out.println("<주문 메뉴> ");
        for(Order order : eventPlanner.getMenuOrders()) {
            System.out.println(order.getMenu().getfoodName() + " " +order.getNumber() + "개");
        }
        System.out.println();
    }
    public void printTotalPrice() {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(eventPlanner.getTotalPrice() + "원");
        System.out.println();
    }
    public void printGift() {
        System.out.println("<증정 메뉴>");
        if(eventPlanner.getGift() == null) {
            System.out.println("없음");
            System.out.println();
        }
        System.out.println(eventPlanner.getGift().getfoodName() + " 1개");
        System.out.println();
    }
    public void printDiscounts() {
        System.out.println("<혜택 내역>");
        System.out.println("크리스마스 디데이 할인: -" + eventPlanner.getDiscount().getdDayDiscount()+"원");
        System.out.println("주말 할인: -" + eventPlanner.getDiscount().getWeekDiscount()+"원");
        System.out.println("특별 할인: -" + eventPlanner.getDiscount().getSpecialDiscount()+"원");
        System.out.println("증정 이벤트: -" + eventPlanner.getGift().getPrice()+"원");
        System.out.println();
    }
    public void printAmountOfAdvantages() {
        System.out.println("<총혜택 금액>");
        System.out.println("-"+eventPlanner.getTotalAdvantage()+"원");
        System.out.println();
    }
    public void printExpectedPrice() {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(eventPlanner.makeActualCost()+"원");
        System.out.println();

    }
    public void printEventBadge() {
        System.out.println("<12월 이벤트 배지>");
        System.out.println(eventPlanner.getBadge().getName());
    }
}
