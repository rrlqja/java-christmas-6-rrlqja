package christmas.domain.discount.discountstrategy;

import christmas.domain.Order;
import christmas.domain.ReservationDate;
import christmas.domain.discount.discountbenefit.ChristmasDiscountBenefit;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;

public class ChristmasDiscountStrategy implements DiscountStrategy {
    private final ReservationDate reservationDate;

    public ChristmasDiscountStrategy(ReservationDate reservationDate) {
        this.reservationDate = reservationDate;
    }

    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        Integer dateDiscountMoney = order.getDateDiscountMoney(reservationDate);

        return createDiscountBenefit(dateDiscountMoney);
    }

    private DiscountBenefit createDiscountBenefit(Integer dateDiscountMoney) {
        if (dateDiscountMoney == 0) {
            return new NoDiscountBenefit();
        }
        return new ChristmasDiscountBenefit(dateDiscountMoney);
    }
}
