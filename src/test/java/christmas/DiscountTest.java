package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

class DiscountTest {
    Discount discount = new Discount();
    
    @Test
    @DisplayName("decideStatus메서드: 1일, 주말, 비이벤트일")
    void testDecideStatus1() {
        int date = 1;
        discount.decideStatus(date);
        assertThat(discount.isItWeekEnds()).isTrue();
        assertThat(discount.isItSpecialDay()).isFalse();
    }
    @Test
    @DisplayName("decideStatus메서드: 25일, 비주말, 이벤트일")
    void testDecideStatus2() {
        int date = 25;
        discount.decideStatus(date);
        assertThat(discount.isItWeekEnds()).isFalse();
        assertThat(discount.isItSpecialDay()).isTrue();
    }
    @Test
    @DisplayName("decideStatus메서드: 12일, 비주말, 이벤트일")
    void testDecideStatus3() {
        int date = 12;
        discount.decideStatus(date);
        assertThat(discount.isItWeekEnds()).isFalse();
        assertThat(discount.isItSpecialDay()).isFalse();
    }
    
    @Test
    @DisplayName("makeTotalDisckountPrice메서드: 25일, 수프, 스테이크, 케이크, 아이스크림, 콜라")
    void testMakeTotalDiscountPrice1() {
        int date = 25;
        List<Menu> menus = List.of(Menu.SOUP, Menu.STEAK, Menu.CAKE, Menu.ICECREAM, Menu.COKE);
        discount.decideStatus(date);
        discount.makeTotalDiscountPrice(date, menus);
        
        assertThat(discount.getdDayDiscount()).isEqualTo(Constants.DDAY_BASICDISCOUNT + 24 * Constants.DDAY_DISCOUNT);
        assertThat(discount.getWeekDiscount()).isEqualTo(2 * Constants.WEEKLY_DISCOUNT);
        assertThat(discount.getSpecialDiscount()).isEqualTo(Constants.SPECIAL_DISCOUNT);
    }

    @Test
    @DisplayName("makeTotalDisckountPrice메서드: 28일, 수프, 스테이크, 케이크, 아이스크림, 콜라")
    void testMakeTotalDiscountPrice2() {
        int date = 28;
        List<Menu> menus = List.of(Menu.SOUP, Menu.STEAK, Menu.CAKE, Menu.ICECREAM, Menu.COKE);
        discount.decideStatus(date);
        discount.makeTotalDiscountPrice(date, menus);

        assertThat(discount.getdDayDiscount()).isZero();
        assertThat(discount.getWeekDiscount()).isEqualTo(2 * Constants.WEEKLY_DISCOUNT);
        assertThat(discount.getSpecialDiscount()).isZero();
    }

    @Test
    @DisplayName("makeTotalDisckountPrice메서드: 1일, 수프, 스테이크, 케이크, 아이스크림 ,콜라")
    void testMakeTotalDiscountPrice3() {
        int date = 1;
        List<Menu> menus = List.of(Menu.SOUP, Menu.STEAK, Menu.CAKE, Menu.ICECREAM, Menu.COKE);
        discount.decideStatus(date);
        discount.makeTotalDiscountPrice(date, menus);

        assertThat(discount.getdDayDiscount()).isEqualTo(Constants.DDAY_BASICDISCOUNT + 0 * Constants.DDAY_DISCOUNT);
        assertThat(discount.getWeekDiscount()).isEqualTo(1 * Constants.WEEKLY_DISCOUNT);
        assertThat(discount.getSpecialDiscount()).isZero();
    }
}
