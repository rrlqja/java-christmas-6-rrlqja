package christmas.domain.order.discount.strategy;

import christmas.domain.menu.MenuCategory;
import christmas.domain.order.Order;
import christmas.domain.ReservationDate;
import christmas.domain.order.discount.discountbenefit.DiscountBenefit;
import christmas.domain.order.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.order.discount.discountbenefit.WeekDiscountBenefit;

public class WeekDiscountStrategy implements DiscountStrategy {
    private static final String WEEKEND_DISCOUNT = "주말 할인";
    private static final String WEEKDAY_DISCOUNT = "평일 할인";
    private static final int DEFAULT_DISCOUNT_AMOUNT = 0;
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
        Integer weekDiscountMoney = order.getWeekDiscountAmount(menuCategory);

        return getDiscountBenefit(weekDiscountMoney, description);
    }

    private DiscountBenefit getDiscountBenefit(Integer weekDiscountMoney, String description) {
        if (weekDiscountMoney == DEFAULT_DISCOUNT_AMOUNT) {
            return new NoDiscountBenefit();
        }

        return new WeekDiscountBenefit(description, weekDiscountMoney);
    }
}
