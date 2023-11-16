package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ReservationDateTest {

    @DisplayName("예약 날짜가 주말인지 테스트")
    @ValueSource(ints = {1, 2, 8, 9, 15, 16, 22, 23, 29, 30})
    @ParameterizedTest
    void weekendTest(int date) {
        ReservationDate reservationDate = new ReservationDate(date);

        assertThat(reservationDate.isWeekend())
                .isTrue();
    }

    @DisplayName("예약 날짜가 별표시된 날짜인지 테스트")
    @ValueSource(ints = {3, 10, 17, 24, 31, 25})
    @ParameterizedTest
    void starDayTest(int date) {
        ReservationDate reservationDate = new ReservationDate(date);

        assertThat(reservationDate.isStarDay())
                .isTrue();
    }

}