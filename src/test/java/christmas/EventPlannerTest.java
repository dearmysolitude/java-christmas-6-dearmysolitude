package christmas;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import christmas.constant.Badge;
import christmas.constant.Menu;
import christmas.entity.Order;
import christmas.business.EventPlanner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

class EventPlannerTest {
    EventPlanner eventPlanner = new EventPlanner();

    @Test
    @DisplayName("makeTotalPrice test")
    void testMakeTotalPrice() {
        Order order1 = new Order(Menu.SOUP, 1);
        Order order2 = new Order(Menu.RIB, 1);
        Order order3 = new Order(Menu.CAKE, 1);
        Order order4 = new Order(Menu.COKE, 1);
        List<Order> orders = List.of(order1, order2, order3, order4);
        eventPlanner.setMenuOrders(orders);
        eventPlanner.makeTotalprice();
        assertThat(eventPlanner.getTotalPrice())
                .isEqualTo(Menu.SOUP.getPrice() + Menu.RIB.getPrice() 
                        + Menu.CAKE.getPrice() + Menu.COKE.getPrice());
    }
    
    //makeTotalAdvantage
    @DisplayName("makeTotalAdvantage test")
    @ParameterizedTest
    @MethodSource("provideTestCasesForMakeTotalAdvantage")
    void testMakeTotalPrice(List<Order> orders, Integer date, Integer expectedTotalAdvantage, Badge expectedBadge) {
        
        eventPlanner.setMenuOrders(orders);
        eventPlanner.setDate(date);
        eventPlanner.makeTotalprice();
        
        eventPlanner.makeTotalAdvantage();
        eventPlanner.setBadge();
        
        assertThat(eventPlanner.getTotalAdvantage()).isEqualTo(expectedTotalAdvantage);
        assertThat(eventPlanner.getBadge()).isEqualTo(expectedBadge);
    }

    private static Stream<Arguments> provideTestCasesForMakeTotalAdvantage() {
        Order order1 = new Order(Menu.SOUP, 1);
        Order order2 = new Order(Menu.RIB, 1);
        Order order3 = new Order(Menu.CAKE, 1);
        Order order4 = new Order(Menu.COKE, 1);
        Order order5 = new Order(Menu.CHRISTMASPASTA, 1);
        Order order6 = new Order(Menu.WINE, 1);

        return Stream.of(
                Arguments.of(
                        List.of(order1, order2, order3, order4), 
                        25,
                        3400 + 1000 + 2023,
                        Badge.STAR),
                Arguments.of(
                        List.of(order1, order2, order3, order4, order5, order6),
                        1,
                        1000 + 2023 * 2 + 25000,
                        Badge.SANTA),
                Arguments.of(
                        List.of(order1, order2, order3, order4, order5, order6),
                        31,
                        2023 + 1000 + 25000,
                        Badge.SANTA)
                );
    }
    
    @DisplayName("checkMenuOrders test")
    @ParameterizedTest
    @MethodSource("provideTestCasesForCheckMenuOrders")
    void testCheckMenuOrders(List<Order> menus) {
        assertThatThrownBy(() -> {
            eventPlanner.setMenuOrders(menus);
        }).isInstanceOf(IllegalArgumentException.class);
    }
    private static Stream<Arguments> provideTestCasesForCheckMenuOrders() {
        Order order1 = new Order(Menu.SOUP, 1);
        Order order2 = new Order(Menu.RIB, 1);
        Order order3 = new Order(Menu.CAKE, 1);
        Order order4 = new Order(Menu.COKE, 1);
        Order order5 = new Order(Menu.CHRISTMASPASTA, 1);
        Order order6 = new Order(Menu.WINE, 0);


        return Stream.of(
                Arguments.of((Object) null),
                Arguments.of(List.of(order1, order1, order3, order4)),
                Arguments.of(List.of(order1, order2, order3, order4, order5, order6))
        );
    }
    
    @Test
    @DisplayName("20개 이상 주문한 경우")
    void testIfOrderedOverTwenty() {
        Order order1 = new Order(Menu.SOUP, 1);
        Order order2 = new Order(Menu.RIB, 1);
        Order order3 = new Order(Menu.WINE, 30);
        
        assertThatThrownBy(() -> {
            eventPlanner.setMenuOrders(List.of(order3, order2, order1));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("한 번에 20개 품목 이하의 주문을 할 수 있습니다. 다시 입력해 주세요.");
    }

    @Test
    @DisplayName("음료만 주문한 경우")
    void testIfOrderedOnlyDrink() {
        Order order1 = new Order(Menu.CHAMPAGNE, 1);
        Order order2 = new Order(Menu.WINE, 5);

        assertThatThrownBy(() -> {
            eventPlanner.setMenuOrders(List.of(order2, order1));
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("음료만 주문할 수 없습니다. 다시 입력해 주세요.");
    }
}
