package christmas.domain.discount;

import christmas.domain.ReservationDate;
import christmas.domain.discount.discountstrategy.ChristmasDiscountStrategy;
import christmas.domain.discount.discountstrategy.DiscountStrategy;
import christmas.domain.discount.discountstrategy.WeekdayDiscountStrategy;
import christmas.domain.discount.discountstrategy.WeekendDiscountStrategy;
import christmas.domain.discount.discountstrategy.StarDayDiscountStrategy;
import christmas.domain.discount.discountstrategy.GiftDiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class DiscountStrategyFactory {
    public static List<DiscountStrategy> createStrategies(ReservationDate reservationDate) {
        List<DiscountStrategy> strategies = new ArrayList<>();
        strategies.add(new ChristmasDiscountStrategy(reservationDate));
        strategies.add(new WeekendDiscountStrategy(reservationDate));
        strategies.add(new WeekdayDiscountStrategy(reservationDate));
        strategies.add(new StarDayDiscountStrategy(reservationDate));
        strategies.add(new GiftDiscountStrategy());
        return strategies;
    }
}
