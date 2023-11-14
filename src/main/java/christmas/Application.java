package christmas;

import camp.nextstep.edu.missionutils.Console;
import christmas.controller.Controller;
import christmas.business.EventPlanner;
import christmas.view.InputView;
import christmas.view.OutputView;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        EventPlanner eventPlanner = new EventPlanner();
        InputView inputView = new InputView();
        OutputView outputView = new OutputView(eventPlanner);
        
        Controller controller = new Controller(eventPlanner, inputView, outputView);
        
        controller.startMessagePrint();
        
        controller.handleDate();
        controller.handleMenu();
        
        controller.makeResult();
        
        controller.middleMessagePrint();
        
        controller.printResult();
        
        Console.close();
    }
}
