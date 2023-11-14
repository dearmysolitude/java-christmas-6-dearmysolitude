package christmas.controller;

import christmas.entity.Order;
import christmas.business.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class Controller {
    private final EventPlanner eventPlanner;
    private final InputView inputView;
    private final OutputView outputView;
    private static final String ERROR = "[ERROR]";

    public Controller(EventPlanner eventPlanner, InputView inputView, OutputView outputView) {
        this.eventPlanner = eventPlanner;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void startMessagePrint() {
        outputView.printStart();
    }
    
    public void handleDate() {
        Integer input = null;
        while(input == null) {
            try{
                input = inputView.readDate();
                eventPlanner.setDate(input);
            } catch(IllegalArgumentException e) {
                input = null;
                System.out.println(ERROR + " 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            }
        }
    }
    
    public void handleMenu() {
        List<Order> input;
        while(eventPlanner.getMenuOrders() == null) {
            try{
                input = inputView.readMenu();
                eventPlanner.setMenuOrders(input);
                eventPlanner.checkMenuOrders();
            } catch(IllegalArgumentException e) {
                eventPlanner.setMenuOrders(null);
                System.out.println(ERROR + " 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
            eventPlanner.ifOrderedOverTwenty();
            eventPlanner.ifOrderedOnlyDrink();
        }
    }
    
    public void makeResult() {
        eventPlanner.makeTotalprice();
        if(eventPlanner.getTotalPrice() < 10000) {
            return;
        }
        eventPlanner.makeTotalAdvantage();
        eventPlanner.setBadge();
    }
    
    public void middleMessagePrint() {
        System.out.println("12월 "+ eventPlanner.getDate() 
                + "일에 우테코 식당에서 받을 이벤트 혜택 미리보기!");
        System.out.println();
    }
    
    public void printResult() {
        outputView.printMenu();
        outputView.printTotalPrice();
        outputView.printGift();
//        outputView.printDiscounts();
        outputView.printAmountOfAdvantages();
        outputView.printExpectedPrice();
        outputView.printEventBadge();
    }
}
