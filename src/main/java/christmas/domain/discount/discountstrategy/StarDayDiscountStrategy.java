package christmas.domain.discount.discountstrategy;

import christmas.domain.Order;
import christmas.domain.ReservationDate;
import christmas.domain.discount.discountbenefit.ChristmasDiscountBenefit;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;
import christmas.domain.discount.discountbenefit.StarDayDiscountBenefit;

public class StarDayDiscountStrategy implements DiscountStrategy {
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
        if (starDayDiscount == 0) {
            return new NoDiscountBenefit();
        }
        return new StarDayDiscountBenefit();
    }
}
