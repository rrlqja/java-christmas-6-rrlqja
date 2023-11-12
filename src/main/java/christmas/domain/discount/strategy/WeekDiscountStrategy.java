package christmas.domain.discount.strategy;

import christmas.domain.MenuCategory;
import christmas.domain.Order;
import christmas.domain.ReservationDate;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.discount.discountbenefit.WeekDiscountBenefit;

public class WeekDiscountStrategy implements DiscountStrategy {
    public static final String WEEKEND_DISCOUNT = "주말 할인";
    public static final String WEEKDAY_DISCOUNT = "평일 할인";
    private final ReservationDate reservationDate;


    public WeekDiscountStrategy(ReservationDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        if (reservationDate.isWeekend()) {
            return createDiscountBenefit(order, MenuCategory.MAIN, WEEKEND_DISCOUNT);
        }
        return createDiscountBenefit(order, MenuCategory.DESSERT, WEEKDAY_DISCOUNT);
    }

    private DiscountBenefit createDiscountBenefit(Order order, MenuCategory menuCategory, String description) {
        Integer weekDiscountMoney = order.getWeekDiscountMoney(menuCategory);

        return getDiscountBenefit(weekDiscountMoney, description);
    }

    private DiscountBenefit getDiscountBenefit(Integer weekDiscountMoney, String description) {
        if (weekDiscountMoney == 0) {
            return new NoDiscountBenefit();
        }

        return new WeekDiscountBenefit(description, weekDiscountMoney);
    }
}
