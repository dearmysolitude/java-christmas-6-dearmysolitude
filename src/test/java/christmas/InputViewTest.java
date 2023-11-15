package christmas;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constant.Menu;
import christmas.entity.Order;
import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.internal.matchers.Or;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

class InputViewTest {
    InputView inputView = new InputView();
    
    //readDate
    @ParameterizedTest
    @MethodSource("provideTestCasesForParseDateTest")
    @DisplayName("readDate Test: exceptions")
    void parseDateTest(String input) {
        assertThatThrownBy(() -> {
           inputView.makeDate(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    
    private static Stream<Arguments> provideTestCasesForParseDateTest() {
        return Stream.of(
                Arguments.of("-1"),
                Arguments.of("0"),
                Arguments.of("a"),
                Arguments.of(""),
                Arguments.of("32")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForParseDateTest2")
    @DisplayName("readDate Test: success")
    void parseDateTest2(String input, int expectedResult) {
        assertThat(inputView.makeDate(input)).isEqualTo(expectedResult);
    }

    private static Stream<Arguments> provideTestCasesForParseDateTest2() {
        return Stream.of(
                Arguments.of("1", 1),
                Arguments.of("13", 13),
                Arguments.of("5", 5),
                Arguments.of("31", 31),
                Arguments.of("25", 25)
        );
    }
    
    //readMenu
    @ParameterizedTest
    @MethodSource("provideTestCasesForparseMenuTest")
    @DisplayName("readDate Test: exceptions")
    void parseMenuTest(String input) {
        assertThatThrownBy(() -> {
            inputView.makeOrders(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideTestCasesForparseMenuTest() {
        return Stream.of(
                Arguments.of("화이트와인-1,제로콜라-1,해산물파스타-1"),
                Arguments.of("양송이수프-1, 타파스-1, 티본스테이크-1"),
                Arguments.of(""),
                Arguments.of("제로콜라-1 타파스 -1")
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForparseMenuTest2")
    @DisplayName("readDate Test: correct")
    void parseMenuTest2(String input, List<Order> expectedResult) {
        assertThat(inputView.makeOrders(input)
                .equals(expectedResult));
    }

    private static Stream<Arguments> provideTestCasesForparseMenuTest2() {

        Order order1 = new Order(Menu.TAPAS, 1);
        Order order2 = new Order(Menu.STEAK, 1);
        Order order3 = new Order(Menu.COKE, 1);
        Order order4 = new Order(Menu.WINE, 1);
        Order order5 = new Order(Menu.CHRISTMASPASTA, 2);
        Order order6 = new Order(Menu.SOUP, 1);

        List<Order> orders1 = List.of(order1, order2);
        List<Order> orders2 = List.of(order3, order4, order5);
        List<Order> orders3 = List.of(order6, order1, order2);

        return Stream.of(
                Arguments.of("타파스-1,티본스테이크-1", orders1),
                Arguments.of("제로콜라-1,레드와인-1,크리스마스파스타-2", orders2),
                Arguments.of("양송이수프-1,타파스-1,티본스테이크-1", orders3)
        );
    }
    
    

}
