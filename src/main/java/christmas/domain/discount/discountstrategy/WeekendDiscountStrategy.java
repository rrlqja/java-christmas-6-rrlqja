package christmas.domain.discount.discountstrategy;

import christmas.domain.*;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.discount.discountbenefit.WeekDiscountBenefit;
import christmas.domain.discount.dto.DiscountDto;
import christmas.utils.WeekPredicate;
import christmas.utils.WeekdayPredicate;
import christmas.utils.WeekendPredicate;

public class WeekendDiscountStrategy implements DiscountStrategy {
    private final ReservationDate reservationDate;

    public WeekendDiscountStrategy(ReservationDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        WeekPredicate weekPredicate = new WeekendPredicate();
        return createDiscountBenefit(order, weekPredicate);
    }

    private DiscountBenefit createDiscountBenefit(Order order, WeekPredicate weekPredicate) {
        if (!weekPredicate.match(reservationDate)) {
            return new NoDiscountBenefit();
        }

        Integer weekDiscountMoney = order.getWeekDiscountMoney(MenuCategory.DESSERT);
        return new WeekDiscountBenefit(weekPredicate.getDescription(), weekDiscountMoney);
    }
}
