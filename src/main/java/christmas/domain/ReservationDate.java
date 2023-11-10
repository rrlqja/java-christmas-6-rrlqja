package christmas.domain;

import christmas.exception.InvalidDateException;

public class ReservationDate {
    private final Integer reservationDate;

    public ReservationDate(Integer reservationDate) {
        validate(reservationDate);
        this.reservationDate = reservationDate;
    }

    private void validate(Integer reservationDate) {
        if (reservationDate < 1 || reservationDate > 31) {
            throw new InvalidDateException();
        }
    }

    public Integer getReservationDate() {
        return this.reservationDate;
    }

    public boolean isWeekend() {
        return reservationDate % 7 <= 2;
    }

    public boolean isStarDay() {
        return reservationDate % 7 == 3 || reservationDate == 25;
    }

    public boolean isWeekday() {
        return !isWeekend();
    }
}
