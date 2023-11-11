package christmas.handler;

import christmas.validator.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class InputHandlerTest {

    private InputValidator inputValidator;
    private InputHandler inputHandler;

    @BeforeEach
    void beforeEach() {
        this.inputValidator = new InputValidator();
        this.inputHandler = new InputHandler(inputValidator);
    }

    @DisplayName("예상 방문 날짜 입력값 테스트")
    @ValueSource(strings = {"1", "31", "25", "10"})
    @ParameterizedTest
    void getReservationDateTest(String reservationDateInput) {
        assertDoesNotThrow(() -> inputHandler.toReservationDate(reservationDateInput));
    }

    @DisplayName("예상 방문 날짜 입력값 예외 테스트")
    @ValueSource(strings = {"-1", "50", "abc", "1a"})
    @ParameterizedTest
    void getReservationDateExceptionTest(String reservationDateInput) {
        assertThatThrownBy(() -> inputHandler.toReservationDate(reservationDateInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("사용자 메뉴 주문 입력값 검증 성공 테스트")
    @ValueSource(strings = {"양송이수프-1", "티본스테이크-1", "양송이수프-1, 티본스테이크-2",
            "양송이수프-1, 티본스테이크-2, 제로콜라-2", "양송이수프-2, 티본스테이크-10, 초코케이크-5"})
    @ParameterizedTest
    void getOrdersTest(String ordersInput) {
        assertDoesNotThrow(() -> inputHandler.toOrders(ordersInput));
    }

    @DisplayName("사용자 메뉴 주문 입력값 예외 테스트")
    @ValueSource(strings = {"안녕하세요", "양송이수프1개", "양송이수프-100, ", "양송이수프-1, 양송이수프-2", "양송이죽-1",
            "양송이수프-1, 티본스테이크-200", "제로콜라-2", "    "})
    @ParameterizedTest
    void getOrdersExceptionTest(String ordersInput) {
        assertThatThrownBy(() -> inputHandler.toOrders(ordersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}