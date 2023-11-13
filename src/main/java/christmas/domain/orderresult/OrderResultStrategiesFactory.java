package christmas.domain.orderresult;

import christmas.domain.orderresult.strategy.OrderResultGiftMenuStrategy;
import christmas.domain.orderresult.strategy.OrderResultOrderMenuStrategy;
import christmas.domain.orderresult.strategy.OrderResultStrategy;
import christmas.domain.orderresult.strategy.OrderResultTotalAmountStrategy;
import christmas.domain.orderresult.strategy.OrderResultBenefitsStrategy;
import christmas.domain.orderresult.strategy.OrderResultTotalBenefitStrategy;
import christmas.domain.orderresult.strategy.OrderResultFinalAmountStrategy;
import christmas.domain.orderresult.strategy.OrderResultBadgeStrategy;

import java.util.ArrayList;
import java.util.List;

public class OrderResultStrategiesFactory {
    public static OrderResultStrategies createStrategies() {
        List<OrderResultStrategy> strategies = new ArrayList<>();
        strategies.add(new OrderResultOrderMenuStrategy());
        strategies.add(new OrderResultTotalAmountStrategy());
        strategies.add(new OrderResultGiftMenuStrategy());
        strategies.add(new OrderResultBenefitsStrategy());
        strategies.add(new OrderResultTotalBenefitStrategy());
        strategies.add(new OrderResultFinalAmountStrategy());
        strategies.add(new OrderResultBadgeStrategy());

        return new OrderResultStrategies(strategies);
    }
}
