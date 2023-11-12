package christmas.domain.discount;

import christmas.domain.Order;
import christmas.domain.discount.discountbenefit.DiscountBenefit;
import christmas.domain.discount.discountstrategy.DiscountStrategy;

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
