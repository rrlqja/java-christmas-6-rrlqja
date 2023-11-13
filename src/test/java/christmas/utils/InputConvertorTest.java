package christmas.utils;

import christmas.input.convertor.InputConvertor;
import christmas.input.validator.InputValidator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class InputConvertorTest {

    InputValidator inputValidator;
    InputConvertor inputConvertor;

    @BeforeEach
    void beforeEach() {
        this.inputValidator = new InputValidator();
        this.inputConvertor = new InputConvertor(inputValidator);
    }

    @DisplayName("예약 날짜 정상 생성 테스트")
    @ValueSource(strings = {"1", "5", "10", "20", "30"})
    @ParameterizedTest
    void getReservationDateTest(String date) {
        assertDoesNotThrow(() -> inputConvertor.convertToReservationDate(date));
    }

    @DisplayName("예약 날짜 생성 예외 테스트")
    @ValueSource(strings = {"-1", "0", "안녕하세요", "20일", "50", "100", "", " "})
    @ParameterizedTest
    void getReservationDateExceptionTest(String date) {
        assertThatThrownBy(() -> inputConvertor.convertToReservationDate(date))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("주문 생성 테스트")
    @ValueSource(strings = {"양송이수프-1", "양송이수프-1,티본스테이크-1"})
    @ParameterizedTest
    void getOrderTest(String ordersInput) {
        assertDoesNotThrow(() -> inputConvertor.convertToOrders(ordersInput));
    }

    @DisplayName("주문 생성 예외 테스트")
    @ValueSource(strings = {"", " ", "라면-1,햄버그스테이크-1", "펩시-1", "양송이수프-1,양송이수프-1",
            "양송이수프-", "양송이수프-100", "양송이수프-10, 티본스테이크-20"})
    @ParameterizedTest
    void getOrderExceptionTest(String ordersInput) {
        assertThatThrownBy(() -> inputConvertor.convertToOrders(ordersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("음료만 주문시 주문 실패 테스트")
    @ValueSource(strings = {"제로콜라-10", "제로콜라-1,레드와인-1"})
    @ParameterizedTest
    void onlyDrinkOrder(String ordersInput) {
        assertThatThrownBy(() -> inputConvertor.convertToOrders(ordersInput))
                .isInstanceOf(IllegalArgumentException.class);
    }
}