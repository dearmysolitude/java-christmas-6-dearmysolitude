package christmas.view;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    private static final String ERROR = "[ERROR]";
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
}
