package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class DiscountTest {
    
    Discount discount = new Discount();
    
    @ParameterizedTest
    @MethodSource("provideTestCasesForDecideStatus")
    @DisplayName("decideStatus Test: 1일, 25일, 12일")
    void testDecideStatus(int date, boolean expectedIsItWeekEnds, boolean expectedIsItSpecialDay) {
        discount.decideStatus(date);
        assertThat(discount.isItWeekEnds()).isEqualTo(expectedIsItWeekEnds);
        assertThat(discount.isItSpecialDay()).isEqualTo(expectedIsItSpecialDay);
    }
    
    private static Stream<Arguments> provideTestCasesForDecideStatus() {
        return Stream.of(
                Arguments.of(1, true, false),
                Arguments.of(25, false, true),
                Arguments.of(12, false, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForMakeTotalDiscountPrice")
    @DisplayName("makeTotalDisckountPrice메서드")
    void testMakeTotalDiscountPrice(int date, List<Menu> menus, int expectedDDayDiscount, int expectedWeekDiscount, int expectedSpecialDiscount) {
        discount.decideStatus(date);
        discount.makeTotalDiscount(date, menus);

        assertThat(discount.getdDayDiscount()).isEqualTo(expectedDDayDiscount);
        assertThat(discount.getWeekDiscount()).isEqualTo(expectedWeekDiscount);
        assertThat(discount.getSpecialDiscount()).isEqualTo(expectedSpecialDiscount);
    }

    private static Stream<Arguments> provideTestCasesForMakeTotalDiscountPrice() {
        List<Menu> menus = List.of(Menu.SOUP, Menu.STEAK, Menu.CAKE, Menu.ICECREAM, Menu.COKE);
        return Stream.of(
                Arguments.of(25, menus, Constants.DDAY_BASICDISCOUNT + 24 * Constants.DDAY_DISCOUNT, 2 * Constants.WEEKLY_DISCOUNT, Constants.SPECIAL_DISCOUNT),
                Arguments.of(28, menus, 0, 2 * Constants.WEEKLY_DISCOUNT, 0),
                Arguments.of(1, menus, Constants.DDAY_BASICDISCOUNT + 0 * Constants.DDAY_DISCOUNT, 1 * Constants.WEEKLY_DISCOUNT, 0)
        );
    }

}
