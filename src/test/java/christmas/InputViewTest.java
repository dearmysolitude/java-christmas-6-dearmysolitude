package christmas;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.view.InputView;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

class InputViewTest {
    InputView inputView = new InputView();
    
    //readDate
    @ParameterizedTest
    @MethodSource("provideTestCasesForParseDateTest")
    @DisplayName("readDate Test: exceptions")
    void parseDateTest(String input) {
        assertThatThrownBy(() -> {
           inputView.parseDate(input);
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
    //readMenu
    @ParameterizedTest
    @MethodSource("provideTestCasesForparseMenuTest")
    @DisplayName("readDate Test: exceptions")
    void parseMenuTest(String input) {
        assertThatThrownBy(() -> {
            inputView.parseMenuWithComma(input);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    private static Stream<Arguments> provideTestCasesForparseMenuTest() {
        return Stream.of(
                Arguments.of("화이트와인-1,제로콜라-1,해산물파스타-1"),
                Arguments.of("제로콜라-1,제로콜라-1"),
                Arguments.of("양송이수프-1, 타파스-1, 티본스테이크-1"),
                Arguments.of(""),
                Arguments.of("제로콜라-20,레드와인-1"),
                Arguments.of("제로콜라-1 타파스 -1")
        );
    }
    
}
