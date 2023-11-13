package christmas.domain.discount.strategy;

import christmas.domain.Order;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountbenefit.GiftDiscountBenefit;
import christmas.domain.discount.discountbenefit.NoDiscountBenefit;

public class GiftDiscountStrategy implements DiscountStrategy {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 0;
    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        Integer giftDiscountMoney = order.getGiftDiscountMoney();

        return createDiscountBenefit(giftDiscountMoney);
    }

    private DiscountBenefit createDiscountBenefit(Integer giftDiscountMoney) {
        if (giftDiscountMoney == DEFAULT_DISCOUNT_AMOUNT) {
            return new NoDiscountBenefit();
        }
        return new GiftDiscountBenefit();
    }
}
