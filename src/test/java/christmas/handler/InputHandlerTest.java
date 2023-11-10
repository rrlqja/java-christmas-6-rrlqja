package christmas.handler;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


class InputHandlerTest {

    @DisplayName("예상 방문 날짜 입력값 테스트")
    @ValueSource(strings = {"1", "31", "25", "10"})
    @ParameterizedTest
    void getReservationDateTest(String reservationDateInput) {
        InputHandler inputHandler = new InputHandler();

        assertDoesNotThrow(() -> inputHandler.toReservationDate(reservationDateInput));
    }

    @DisplayName("예상 방문 날짜 입력값 예외 테스트")
    @ValueSource(strings = {"-1", "50", "abc", "1a"})
    @ParameterizedTest
    void getReservationDateExceptionTest(String reservationDateInput) {
        InputHandler inputHandler = new InputHandler();

        assertThatThrownBy(() -> inputHandler.toReservationDate(reservationDateInput))
                .isInstanceOf(IllegalArgumentException.class);
    }

}