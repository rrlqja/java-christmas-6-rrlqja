package christmas.domain.order.discount;

import christmas.domain.order.Order;
import christmas.domain.order.discount.discountbenefit.DiscountBenefit;
import christmas.domain.order.discount.strategy.DiscountStrategy;

import java.util.List;

public class DiscountStrategies {
    private final List<DiscountStrategy> discountStrategies;

    public DiscountStrategies(List<DiscountStrategy> discountStrategies) {
        this.discountStrategies = discountStrategies;
    }

    public List<DiscountBenefit> getDiscountBenefits(Order order) {
        return discountStrategies.stream()
                .map(discountStrategy -> discountStrategy.calculateDiscount(order))
                .distinct()
                .toList();
    }
}
