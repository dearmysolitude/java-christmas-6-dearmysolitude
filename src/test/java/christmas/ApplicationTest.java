package christmas;

import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.Test;

class ApplicationTest extends NsTest {
    private static final String LINE_SEPARATOR = System.lineSeparator();

    @Test
    void 모든_타이틀_출력() {
        assertSimpleTest(() -> {
            run("3", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
            assertThat(output()).contains(
                "<주문 메뉴>",
                "<할인 전 총주문 금액>",
                "<증정 메뉴>",
                "<혜택 내역>",
                "<총혜택 금액>",
                "<할인 후 예상 결제 금액>",
                "<12월 이벤트 배지>"
            );
        });
    }

    @Test
    void 혜택_내역_없음_출력() {
        assertSimpleTest(() -> {
            run("26", "타파스-1,제로콜라-1");
            assertThat(output()).contains("<혜택 내역>" + LINE_SEPARATOR + "없음");
        });
    }

    @Test
    void 날짜_예외_테스트() {
        assertSimpleTest(() -> {
            runException("a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        });
    }

    @Test
    void 주문_예외_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-a");
            assertThat(output()).contains("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        });
    }
    
    @Test
    void 스무개_이상_주문_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-3,타파스-18");
            assertThat(output()).contains("[ERROR] 한 번에 20개 품목 이하의 주문을 할 수 있습니다. 다시 입력해 주세요.");
        });
    }
    @Test
    void 음료만_주문시_테스트() {
        assertSimpleTest(() -> {
            runException("3", "제로콜라-14");
            assertThat(output()).contains("[ERROR] 음료만 주문할 수 없습니다. 다시 입력해 주세요.");
        });
    }
    
    @Test
    void 크리스마스_당일_주문() {
        assertSimpleTest(() -> {
            run("25", "양송이수프-1,타파스-2,시저샐러드-3,티본스테이크-1,바비큐립-2,해산물파스타-1,크리스마스파스타-2,초코케이크-1,아이스크림-2,제로콜라-1,레드와인-2,샴페인-1");
            assertThat(output()).contains(
                    "<주문 메뉴>",
                    "<할인 전 총주문 금액>" + LINE_SEPARATOR + "462,000원",
                    "<증정 메뉴>" + LINE_SEPARATOR + "샴페인 1개",
                    "<혜택 내역>",
                    "<총혜택 금액>"+LINE_SEPARATOR+"-35,469원",
                    "<할인 후 예상 결제 금액>"+LINE_SEPARATOR+"451,531원",
                    "<12월 이벤트 배지>"+LINE_SEPARATOR+"산타"
            );
        });
    }
    
    @Override
    protected void runMain() {
        Application.main(new String[]{});
    }
}
