package christmas.domain.discount.strategy;

import christmas.domain.order.Order;
import christmas.domain.ReservationDate;
import christmas.domain.discount.discountbenefit.DDAyDiscountBenefit;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;

public class ChristmasDiscountStrategy implements DiscountStrategy {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 0;
    private final ReservationDate reservationDate;

    public ChristmasDiscountStrategy(ReservationDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        Integer dateDiscountMoney = order.getDDayDiscountAmount(reservationDate);

        return createDiscountBenefit(dateDiscountMoney);
    }

    private DiscountBenefit createDiscountBenefit(Integer dateDiscountMoney) {
        if (dateDiscountMoney == DEFAULT_DISCOUNT_AMOUNT) {
            return new NoDiscountBenefit();
        }
        return new DDAyDiscountBenefit(dateDiscountMoney);
    }
}
