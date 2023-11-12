package christmas.utils;

import christmas.domain.ReservationDate;

public class WeekdayPredicate implements WeekPredicate<ReservationDate> {

    @Override
    public boolean match(ReservationDate reservationDate) {
        return reservationDate.isWeekday();
    }

    @Override
    public String getDescription() {
        return "평일 할인";
    }
}
