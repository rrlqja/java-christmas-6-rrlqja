package christmas.domain.order.discount;

import christmas.domain.ReservationDate;
import christmas.domain.order.discount.strategy.DDayDiscountStrategy;
import christmas.domain.order.discount.strategy.DiscountStrategy;
import christmas.domain.order.discount.strategy.WeekDiscountStrategy;
import christmas.domain.order.discount.strategy.StarDayDiscountStrategy;
import christmas.domain.order.discount.strategy.GiftDiscountStrategy;

import java.util.ArrayList;
import java.util.List;

public class DiscountStrategyFactory {
    public static DiscountStrategies createStrategies(ReservationDate reservationDate) {
        List<DiscountStrategy> strategies = new ArrayList<>();
        strategies.add(new DDayDiscountStrategy(reservationDate));
        strategies.add(new WeekDiscountStrategy(reservationDate));
        strategies.add(new StarDayDiscountStrategy(reservationDate));
        strategies.add(new GiftDiscountStrategy());

        return new DiscountStrategies(strategies);
    }
}
