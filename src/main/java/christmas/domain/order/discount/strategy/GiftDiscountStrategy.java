package christmas.domain.order.discount.strategy;

import christmas.domain.order.Order;
import christmas.domain.order.discount.discountbenefit.DiscountBenefit;
import christmas.domain.order.discount.discountbenefit.GiftDiscountBenefit;
import christmas.domain.order.discount.discountbenefit.NoDiscountBenefit;

public class GiftDiscountStrategy implements DiscountStrategy {
    private static final int DEFAULT_DISCOUNT_AMOUNT = 0;
    @Override
    public DiscountBenefit calculateDiscount(Order order) {
        Integer giftDiscountMoney = order.getGiftDiscountAmount();

        return createDiscountBenefit(giftDiscountMoney);
    }

    private DiscountBenefit createDiscountBenefit(Integer giftDiscountMoney) {
        if (giftDiscountMoney == DEFAULT_DISCOUNT_AMOUNT) {
            return new NoDiscountBenefit();
        }
        return new GiftDiscountBenefit();
    }
}
