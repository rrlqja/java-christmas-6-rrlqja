package christmas.domain.discount.discountstrategy;

import christmas.domain.MenuCategory;
import christmas.domain.Order;
import christmas.domain.ReservationDate;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.discount.discountbenefit.WeekDiscountBenefit;
import christmas.utils.WeekPredicate;
import christmas.utils.WeekdayPredicate;

public class WeekdayDiscountStrategy implements DiscountStrategy {
    private final ReservationDate reservationDate;

    public WeekdayDiscountStrategy(ReservationDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        WeekPredicate weekPredicate = new WeekdayPredicate();
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
