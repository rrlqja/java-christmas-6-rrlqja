package christmas.domain.order.discount.strategy;

import christmas.domain.order.Order;
import christmas.domain.order.discount.discountbenefit.DiscountBenefit;

public interface DiscountStrategy {
    DiscountBenefit calculateDiscount(Order order);
}
