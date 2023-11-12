package christmas.domain.discount.strategy;

import christmas.domain.Order;
import christmas.domain.discount.discountbenefit.DiscountBenefit;

public interface DiscountStrategy {
    DiscountBenefit calculateDiscount(Order order);
}