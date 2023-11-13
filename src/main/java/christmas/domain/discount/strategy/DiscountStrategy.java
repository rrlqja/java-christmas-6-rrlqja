package christmas.domain.discount.strategy;

import christmas.domain.order.Order;
import christmas.domain.discount.discountbenefit.DiscountBenefit;

public interface DiscountStrategy {
    DiscountBenefit calculateDiscount(Order order);
}
