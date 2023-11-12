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
    private final WeekPredicate weekPredicate;


    public WeekdayDiscountStrategy(ReservationDate reservationDate) {
        this.reservationDate = reservationDate;
        this.weekPredicate = new WeekdayPredicate();
    }

    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        Integer weekDiscountMoney = order.getWeekDiscountMoney(MenuCategory.DESSERT);

        return createDiscountBenefit(weekDiscountMoney, weekPredicate);
    }

    private DiscountBenefit createDiscountBenefit(Integer weekDiscountMoney, WeekPredicate weekPredicate) {
        if (!weekPredicate.match(reservationDate) || weekDiscountMoney == 0) {
            return new NoDiscountBenefit();
        }

        return new WeekDiscountBenefit(weekPredicate.getDescription(), weekDiscountMoney);
    }
}
