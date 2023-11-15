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
        while(true) {
            try {
                eventPlanner.setDate(inputView.readDate());
                break;
            } catch(IllegalArgumentException e) {
                System.out.println(ERROR + e.getMessage());
            }
        }
    }
    
    public void handleMenu() {
        while(true) {
            try{
                eventPlanner.setMenuOrders(inputView.readMenu());
                break;
            } catch(IllegalArgumentException e) {
                System.out.println(ERROR + e.getMessage());
            }
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
        outputView.printResult();
    }
}
