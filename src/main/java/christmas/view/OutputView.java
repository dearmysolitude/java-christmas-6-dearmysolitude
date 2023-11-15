package christmas.view;

import christmas.business.EventPlanner;
import christmas.entity.Order;

import java.text.NumberFormat;

public class OutputView {
    EventPlanner eventPlanner;
    NumberFormat numberFormat = NumberFormat.getInstance();

    public OutputView(EventPlanner eventPlanner) {
        this.eventPlanner = eventPlanner;
    }
    public void printStart() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
    }
    public void printMiddle() {         
        System.out.println("12월 "+ eventPlanner.getDate() + "일에 우테코 식당에서 받을 이벤트 혜택 미리보기!");
        System.out.println();
    }
    public void printResult() {
        printMenu();
        printTotalPrice();
        printGift();
        printDiscounts();
        printAmountOfAdvantages();
        printExpectedPrice();
        printEventBadge();
    }
    private void printMenu() {
        System.out.println("<주문 메뉴>");
        for(Order order : eventPlanner.getMenuOrders()) {
            System.out.println(order.getMenu().getfoodName() + " " +order.getNumber() + "개");
        }
        System.out.println();
    }
    private void printTotalPrice() {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(numberFormat.format(eventPlanner.getTotalPrice()) + "원");
        System.out.println();
    }
    private void printGift() {
        System.out.println("<증정 메뉴>");
        if(eventPlanner.getGift() == null) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        System.out.println(eventPlanner.getGift().getfoodName() + " 1개");
        System.out.println();
    }
    private void printDiscounts() {
        System.out.println("<혜택 내역>");
        if(eventPlanner.getDiscount().getTotalDiscount() == 0) {
            System.out.println("없음");
            System.out.println();
            return;
        }
        printDDayDiscount();
        printWeekDiscount();
        printSpecialDiscount();
        printGitfEvent();
        
        System.out.println();
    }
    
    private void printDDayDiscount() {
        if(eventPlanner.getDiscount().getdDayDiscount() == 0) {
            return;
        }
        System.out.println("크리스마스 디데이 할인: -" + numberFormat.format(eventPlanner.getDiscount().getdDayDiscount())+"원");
    }
    private void printWeekDiscount() {
        if(eventPlanner.getDiscount().getWeekDiscount() == 0) {
            return;
        }
        checkDayOrEndAndPrint();
    }
    private void checkDayOrEndAndPrint() {
        if(eventPlanner.getDiscount().getWeekDiscount() == 0) {
            return;
        }
        if(eventPlanner.getDiscount().isItWeekEnds()) {
            System.out.println("주말 할인: -" +numberFormat.format(eventPlanner.getDiscount().getWeekDiscount())+"원");
        }
        System.out.println("평일 할인: -" +numberFormat.format(eventPlanner.getDiscount().getWeekDiscount())+"원");
    }
    private void printSpecialDiscount() {
        if(eventPlanner.getDiscount().getSpecialDiscount() == 0) {
            return;
        }
        System.out.println("특별 할인: -" + numberFormat.format(eventPlanner.getDiscount().getSpecialDiscount())+"원");
    }
    private void printGitfEvent() {
        if(eventPlanner.getGift() == null) {
            return;
        }
        System.out.println("증정 이벤트: -" + numberFormat.format(eventPlanner.getGift().getPrice())+"원");
    }

    private void printAmountOfAdvantages() {
        System.out.println("<총혜택 금액>");
        if(eventPlanner.getTotalAdvantage() == 0) { //할인 금액이 0일 경우 발생
            printZeroWon();
            return;
        }
        System.out.println("-"+numberFormat.format(eventPlanner.getTotalAdvantage())+"원");
        System.out.println();
    }
    
    private void printZeroWon() {
        System.out.println("0원");
        System.out.println();
    }
    
    private void printExpectedPrice() {
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(numberFormat.format(eventPlanner.makeActualCost())+"원");
        System.out.println();
    }
    private void printEventBadge() {
        System.out.println("<12월 이벤트 배지>");
        if(eventPlanner.getBadge() == null) {
            System.out.println("없음");
            return;
        }
        System.out.println(eventPlanner.getBadge().getName());
    }
}
