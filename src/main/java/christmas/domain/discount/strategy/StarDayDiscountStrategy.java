package christmas.domain.discount.strategy;

import christmas.domain.Order;
import christmas.domain.ReservationDate;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.discount.discountbenefit.StarDayDiscountBenefit;

public class StarDayDiscountStrategy implements DiscountStrategy {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 0;
    private final ReservationDate reservationDate;

    public StarDayDiscountStrategy(ReservationDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        Integer starDayDiscount = order.getStarDayDiscount(reservationDate);

        return createDiscountBenefit(starDayDiscount);
    }

    private DiscountBenefit createDiscountBenefit(Integer starDayDiscount) {
        if (starDayDiscount == DEFAULT_DISCOUNT_AMOUNT) {
            return new NoDiscountBenefit();
        }
        return new StarDayDiscountBenefit();
    }
}
