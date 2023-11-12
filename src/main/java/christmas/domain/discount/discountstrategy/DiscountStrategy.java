package christmas.domain.discount.discountstrategy;

import christmas.domain.Order;
import christmas.domain.discount.discountbenefit.DiscountBenefit;

public interface DiscountStrategy {
    DiscountBenefit calculateDiscount(Order order);
}
