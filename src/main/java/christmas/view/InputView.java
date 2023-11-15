package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constant.Menu;
import christmas.entity.Order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {
    
    private static final String INPUT_MENU_ERROR_MESSAGE = "유효하지 않은 주문입니다. 다시 입력해 주세요.";
    private static final String INPUT_DATE_ERROR_MESSAGE = "유효하지 않은 날짜입니다. 다시 입력해 주세요.";
    
    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        String input = Console.readLine();
        return makeDate(input);
    }
    public Integer makeDate(String input) {
        Integer parsedInput = pareseStringToInteger(input, INPUT_DATE_ERROR_MESSAGE);
        return isItValid(parsedInput);
    }
    
    private Integer isItValid(Integer input) {
        if(input >= 1 && input <= 31) {
            return input;
        }
        throw new IllegalArgumentException();
    }
    
    public List<Order> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1,타파스-1,제로콜라-1)");
        String input = Console.readLine();
        
        checkIfOrderIsEmpty(input);
        
        return makeOrders(input);
    }
    
    public List<Order> makeOrders (String input) {
        List<String> parsedInput = parseWith(input, ",");
        return makeOrderList(parsedInput);
    }
    
    private void checkIfOrderIsEmpty(String input) {
        if(input.isEmpty()){
            throw new IllegalArgumentException(INPUT_MENU_ERROR_MESSAGE);
        }
    }
    
    private List<Order> makeOrderList(List<String> temps) {
        List<Order> output = new ArrayList<>();
        temps.forEach(temp -> {
                List<String> parsedInput = parseWith(temp, "-");
                output.add(makeOrderObject(parsedInput));
        });
        return output;
    }
    
    private Order makeOrderObject(List<String> input) {
        
        Menu menu = Menu.getMenuWithName(input.get(0));
        int number = pareseStringToInteger(input.get(1), INPUT_MENU_ERROR_MESSAGE + " 음식 수량은 1 이상 숫자로 입력해주세요.");
        
        if(input.size() != 2) {
            throw new IllegalArgumentException(INPUT_MENU_ERROR_MESSAGE);
        }
        
        return new Order(menu, number);
    }
    public List<String> parseWith(String input, String delimeter) {
        return Arrays.stream(input.split(delimeter)).toList();
    }
    
    private Integer pareseStringToInteger(String input, String exceptionMessage) {
        try {
            return Integer.parseInt(input);
        } catch(NumberFormatException e) {
            throw new IllegalArgumentException(exceptionMessage , e);
        }
    }
    
}
