package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.Controller;
import christmas.model.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        EventPlanner eventPlanner = new EventPlanner();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView();
        
        Controller controller = new Controller(eventPlanner, inputView, outputView);
        
        controller.handleDate();
        controller.handleMenu();
        
        
        
        Console.close();
    }
}
