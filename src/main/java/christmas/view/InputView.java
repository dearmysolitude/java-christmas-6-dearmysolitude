package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.Menu;
import christmas.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        try {
            return isItValid(Integer.parseInt(input));
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(e);
        }
    }
    private int isItValid(Integer input) {
        if(input >= 1 && input <= 31) {
            return input;
        }
        throw new IllegalArgumentException();
    }
    
    public List<Order> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1,타파스-1,제로콜라1)");
        String input = Console.readLine();
        return parseMenuWithComma(input); 
    }
    
    private List<Order> parseMenuWithComma(String input) {
        List<Order> output = new ArrayList<>();
        List<String> temps = Arrays.stream(input.split(",")).toList();
        temps.forEach(temp -> output.add(parseMenuWithHypen(temp)));
        return output;
    }
    
    private Order parseMenuWithHypen(String input) {
        String[] temp = input.split("-");
        
        Menu menu;
        Integer number;
        
        try {
            menu = Menu.valueOf(temp[0]);
            number = Integer.parseInt(temp[1]);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        return new Order(menu, number);
    }
    
}
