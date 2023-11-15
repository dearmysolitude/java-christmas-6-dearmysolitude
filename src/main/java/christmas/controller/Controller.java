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
    private static final String ERROR = "[ERROR] ";

    public Controller(EventPlanner eventPlanner, InputView inputView, OutputView outputView) {
        this.eventPlanner = eventPlanner;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void startMessagePrint() {
        outputView.printStart();
    }
    
    public void handleDate() {
        while(eventPlanner.getDate() == null) {
            eventPlanner.setDate(enterDateToEventPlanner());
        }
    }
    private Integer enterDateToEventPlanner() {
        Integer input = null;
        try{
            input = inputView.readDate();
        } catch(IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage());
        }
        return input;
    }
    
    public void handleMenu() {
        while(eventPlanner.getMenuOrders() == null) {
            enterMenuToEventPlanner();
        }
    }
    
    private void enterMenuToEventPlanner() {
        try{
            eventPlanner.setMenuOrders(inputView.readMenu());
        } catch(IllegalArgumentException e) {
            System.out.println(ERROR + e.getMessage());
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
        outputView.printMiddle();
    }
    
    public void printResult() {
        outputView.printMenu();
        outputView.printTotalPrice();
        outputView.printGift();
        outputView.printDiscounts();
        outputView.printAmountOfAdvantages();
        outputView.printExpectedPrice();
        outputView.printEventBadge();
    }
}
