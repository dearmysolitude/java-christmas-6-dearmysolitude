package christmas.controller;

import christmas.constants.Constants;
import christmas.constants.Menu;
import christmas.entity.Order;
import christmas.model.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

import java.util.List;

public class Controller {
    private EventPlanner eventPlanner;
    private InputView inputView;
    private OutputView outputView;
    private static final String ERROR = "[ERROR]";


    public Controller(EventPlanner eventPlanner, InputView inputView, OutputView outputView) {
        this.eventPlanner = eventPlanner;
        this.inputView = inputView;
        this.outputView = outputView;
    }
    
    public void handleDate() {
        Integer input = null;
        while(input == null) {
            try{
                input = inputView.readDate();
                eventPlanner.setDate(input);
            } catch(IllegalArgumentException e) {
            System.out.println(ERROR + " 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                input = null;
            }
        }
    }
    
    public void handleMenu() {
        List<Order> input = null;
        while(input == null) {
            try{
                input = inputView.readMenu();
                eventPlanner.setMenuOrders(input);
            } catch(IllegalArgumentException e) {
                System.out.println(ERROR + " 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                input = null;
            }
        }
    }
}
