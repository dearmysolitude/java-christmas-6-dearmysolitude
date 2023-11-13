package christmas;


import static org.assertj.core.api.Assertions.assertThat;

import christmas.Constants.Badge;
import christmas.Constants.Menu;
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
        List<Menu> menus = List.of(Menu.SOUP,Menu.RIB, Menu.CAKE, Menu.COKE);
        eventPlanner.setMenuOrders(menus);
        eventPlanner.makeTotalprice();
        assertThat(eventPlanner.getTotalPrice())
                .isEqualTo(Menu.SOUP.getPrice() + Menu.RIB.getPrice() 
                        + Menu.CAKE.getPrice() + Menu.COKE.getPrice());
    }
    
    //makeTotalAdvantage
    @DisplayName("makeTotalAdvantage test")
    @ParameterizedTest
    @MethodSource("provideTestCasesForMakeTotalAdvantage")
    void testMakeTotalPrice(List<Menu> menus, Integer date, Integer expectedTotalAdvantage, Badge expectedBadge) {
        
        eventPlanner.setMenuOrders(menus);
        eventPlanner.setDate(date);
        eventPlanner.makeTotalprice();
        
        eventPlanner.makeTotalAdvantage();
        eventPlanner.setBadge();
        
        assertThat(eventPlanner.getTotalAdvantage()).isEqualTo(expectedTotalAdvantage);
        assertThat(eventPlanner.getBadge()).isEqualTo(expectedBadge);
    }

    private static Stream<Arguments> provideTestCasesForMakeTotalAdvantage() {
        return Stream.of(
                Arguments.of(
                        List.of(Menu.SOUP,Menu.RIB, Menu.CAKE, Menu.COKE), 
                        25,
                        3400 + 1000 + 2023,
                        Badge.STAR),
                Arguments.of(
                        List.of(Menu.SOUP, Menu.RIB, Menu.CHRISTMASPASTA, Menu.CAKE, Menu.WINE),
                        1,
                        1000 + 2023 * 2 + 0 + 25000,
                        Badge.SANTA),
                Arguments.of(
                        List.of(Menu.SOUP, Menu.RIB, Menu.CHRISTMASPASTA, Menu.CAKE, Menu.WINE),
                        31,
                        0 + 2023 * 1 + 1000 + 25000,
                        Badge.SANTA)
                );
    }
}
