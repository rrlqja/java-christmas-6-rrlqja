package christmas.domain.discount.strategy;

import christmas.domain.Order;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.GiftDiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;

public class GiftDiscountStrategy implements DiscountStrategy {
    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        Integer giftDiscountMoney = order.getGiftDiscountMoney();

        return createDiscountBenefit(giftDiscountMoney);
    }

    private DiscountBenefit createDiscountBenefit(Integer giftDiscountMoney) {
        if (giftDiscountMoney == 0) {
            return new NoDiscountBenefit();
        }
        return new GiftDiscountBenefit();
    }
}
