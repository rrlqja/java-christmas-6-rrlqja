package christmas.utils;

import christmas.domain.ReservationDate;

public class WeekendPredicate implements WeekPredicate<ReservationDate> {
    @Override
    public boolean match(ReservationDate reservationDate) {
        return reservationDate.isWeekend();
    }

    @Override
    public String getDescription() {
        return "주말 할인";
    }
}
