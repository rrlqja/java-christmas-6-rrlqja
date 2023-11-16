package christmas.domain;

import christmas.exception.InvalidDateException;

public class ReservationDate {
    private static final int MINIMUM_DATE = 1;
    private static final int MAXIMUM_DATE = 31;
    private static final int DAY_IN_WEEK = 7;
    private static final int WEEKEND_THRESHOLD = 2;
    private static final int STAR_DAY = 3;
    private static final int CHRISTMAS = 25;
    private final Integer reservationDate;

    public ReservationDate(Integer reservationDate) {
        validate(reservationDate);
        this.reservationDate = reservationDate;
    }

    private void validate(Integer reservationDate) {
        if (reservationDate < MINIMUM_DATE || reservationDate > MAXIMUM_DATE) {
            throw new InvalidDateException();
        }
    }

    public Integer getReservationDate() {
        return this.reservationDate;
    }

    public boolean isWeekend() {
        return reservationDate % DAY_IN_WEEK <= WEEKEND_THRESHOLD;
    }

    public boolean isStarDay() {
        return reservationDate % DAY_IN_WEEK == STAR_DAY || reservationDate == CHRISTMAS;
    }

    public boolean isBeforeChristmas() {
        return reservationDate <= CHRISTMAS;
    }
}
