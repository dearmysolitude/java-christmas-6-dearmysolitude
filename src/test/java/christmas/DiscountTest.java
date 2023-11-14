package christmas;

import static org.assertj.core.api.Assertions.assertThat;

import christmas.constant.Constants;
import christmas.constant.Menu;
import christmas.business.Discount;
import christmas.entity.Order;
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
    void testDecideStatus(int date, List<Order> orders, boolean expectedIsItWeekEnds, boolean expectedIsItSpecialDay) {
        discount.makeTotalDiscount(date, orders);
        assertThat(discount.isItWeekEnds()).isEqualTo(expectedIsItWeekEnds);
        assertThat(discount.isItSpecialDay()).isEqualTo(expectedIsItSpecialDay);
    }

    private static Stream<Arguments> provideTestCasesForDecideStatus() {
        Order order1 = new Order(Menu.SOUP, 1);
        Order order2 = new Order(Menu.STEAK, 1);
        Order order3 = new Order(Menu.CAKE, 1);
        Order order4 = new Order(Menu.COKE, 1);
        Order order5 = new Order(Menu.ICECREAM, 1);
        List<Order> orders = List.of(order1, order2, order3, order5, order4);
        return Stream.of(
                Arguments.of(1, orders, true, false),
                Arguments.of(25, orders, false, true),
                Arguments.of(12, orders, false, false)
        );
    }

    @ParameterizedTest
    @MethodSource("provideTestCasesForMakeTotalDiscountPrice")
    @DisplayName("makeTotalDisckountPrice메서드")
    void testMakeTotalDiscountPrice(int date, List<Order> orders, int expectedDDayDiscount, int expectedWeekDiscount, int expectedSpecialDiscount) {

        discount.makeTotalDiscount(date, orders);

        assertThat(discount.getdDayDiscount()).isEqualTo(expectedDDayDiscount);
        assertThat(discount.getWeekDiscount()).isEqualTo(expectedWeekDiscount);
        assertThat(discount.getSpecialDiscount()).isEqualTo(expectedSpecialDiscount);
    }

    private static Stream<Arguments> provideTestCasesForMakeTotalDiscountPrice() {
        Order order1 = new Order(Menu.SOUP, 1);
        Order order2 = new Order(Menu.STEAK, 1);
        Order order3 = new Order(Menu.CAKE, 1);
        Order order4 = new Order(Menu.COKE, 1);
        Order order5 = new Order(Menu.ICECREAM, 1);
        List<Order> orders = List.of(order1, order2, order3, order5, order4);

        return Stream.of(
                Arguments.of(25, orders, Constants.DDAY_BASICDISCOUNT + 24 * Constants.DDAY_DISCOUNT, 2 * Constants.WEEKLY_DISCOUNT, Constants.SPECIAL_DISCOUNT),
                Arguments.of(28, orders, 0, 2 * Constants.WEEKLY_DISCOUNT, 0),
                Arguments.of(1, orders, Constants.DDAY_BASICDISCOUNT, Constants.WEEKLY_DISCOUNT, 0)
        );
    }

}
