package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Menu;
import christmas.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return parseDate(input);
    }
    
    public int parseDate(String input) {
        try {
            return isItValid(Integer.parseInt(input));
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] Invalid input format", e);
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
    
    public List<Order> parseMenuWithComma(String input) {
        List<String> temps = Arrays.stream(input.split(",")).toList();
        System.out.println("List<String>temps = " + temps);
        return makeOrderList(temps);
    }
    
    private List<Order> makeOrderList(List<String> temps) {
        List<Order> output = new ArrayList<>();
        temps.forEach(temp -> output.add(parseMenuWithHypen(temp)));
        System.out.println("List<Order> output = " + output);
        return output;
    }
    
    private Order parseMenuWithHypen(String input) {
        String[] temp = input.split("-");
        
        if(temp[0].isEmpty() || temp[1].isEmpty()) {
            throw new IllegalArgumentException();
        }
        
        Menu menu;
        int number;
        
        try {
            System.out.println("temp[0] = " + temp[0]);
            System.out.println("temp[1] = " + temp[1]);
            menu = Menu.getMenuWithName(temp[0]);
            System.out.println("Menu menu = " + menu);
            number = Integer.parseInt(temp[1]);
            System.out.println("Menu - " + menu.getfoodName()+ ": " + number);
            return new Order(menu, number);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] Invalid input format",e);
        }

    }
    
}
